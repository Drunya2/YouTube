package Requests;

import Cache.Cache;
import Client.Requests;
import Entity.Snippets.Channel;
import Settings.CheckTimeToShow;
import Settings.ComponentSettings;
import Settings.LoadSettings;
import Start.Main;
import com.mashape.unirest.http.exceptions.UnirestException;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.util.Collections;

public class ShowGlobalInfo {

    public static void show(Button search, TextField channelId, TableView<Channel> tableView,
                            TableColumn<Channel, String> nameColumn, TableColumn<Channel, String> dateColumn,
                            TableColumn<Channel, String> subsColumn, TableColumn<Channel, String> videoColumn,
                            TableColumn<Channel, String> viewsColumn, Text showTime) {

        search.setOnAction(event -> {
            long start = CheckTimeToShow.start();
            Main.service.submit(() -> {
                String query = channelId.getText();
                try {
                    if (saveCache()) {
                        if (Cache.channelContains(query)) {
                            loadInfoFromCache(query, tableView, nameColumn, dateColumn, subsColumn, videoColumn, viewsColumn);
                        } else {
                            loadInfoFromYouTube(query, tableView, nameColumn, dateColumn, subsColumn, videoColumn, viewsColumn);
                        }
                    } else
                        loadInfoFromYouTube(query, tableView, nameColumn, dateColumn, subsColumn, videoColumn, viewsColumn);
                    Platform.runLater(() -> {
                        try {
                            if (showTime()) showTime.setText(CheckTimeToShow.getTime(start));
                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        }
                    });
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            });
        });
    }


    private static void loadInfoFromYouTube(String id, TableView<Channel> tableView,
                                            TableColumn<Channel, String> nameColumn, TableColumn<Channel, String> dateColumn,
                                            TableColumn<Channel, String> subsColumn, TableColumn<Channel, String> videoColumn,
                                            TableColumn<Channel, String> viewsColumn) {

        ObservableList<Channel> userData = FXCollections.observableArrayList();
        Collections.synchronizedList(userData);
        Main.service.submit(() -> {
            try {
                userData.add(Requests.ShowGlobalInfo(id).get(0));
                nameColumn.setCellValueFactory(new PropertyValueFactory<Channel, String>("name"));
                dateColumn.setCellValueFactory(new PropertyValueFactory<Channel, String>("date"));
                subsColumn.setCellValueFactory(new PropertyValueFactory<Channel, String>("subscribers"));
                videoColumn.setCellValueFactory(new PropertyValueFactory<Channel, String>("countVideo"));
                viewsColumn.setCellValueFactory(new PropertyValueFactory<Channel, String>("countViews"));
                tableView.setItems(userData);
                if (saveCache()) {
                    Cache.saveToCache(userData.get(0), id);
                }
            } catch (UnirestException e) {
                e.printStackTrace();
            } catch (ParseException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    private static void loadInfoFromCache(String id, TableView<Channel> tableView,
                                          TableColumn<Channel, String> nameColumn, TableColumn<Channel, String> dateColumn,
                                          TableColumn<Channel, String> subsColumn, TableColumn<Channel, String> videoColumn,
                                          TableColumn<Channel, String> viewsColumn) throws FileNotFoundException {

        ObservableList<Channel> userData = FXCollections.observableArrayList();
        Collections.synchronizedList(userData);
        userData.add(Cache.getChannelFromCache(id));
        nameColumn.setCellValueFactory(new PropertyValueFactory<Channel, String>("name"));
        dateColumn.setCellValueFactory(new PropertyValueFactory<Channel, String>("date"));
        subsColumn.setCellValueFactory(new PropertyValueFactory<Channel, String>("subscribers"));
        videoColumn.setCellValueFactory(new PropertyValueFactory<Channel, String>("countVideo"));
        viewsColumn.setCellValueFactory(new PropertyValueFactory<Channel, String>("countViews"));
        tableView.setItems(userData);
    }

    public static boolean showTime() throws FileNotFoundException {
        ComponentSettings settings = LoadSettings.loadSettings();
        if (settings.isShowTime() == true) return true;
        else return false;
    }

    public static boolean saveCache() throws FileNotFoundException {
        ComponentSettings settings = LoadSettings.loadSettings();
        if (settings.isUseCacheBool() == true) return true;
        else return false;
    }
}
