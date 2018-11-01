package Requests;

import Cache.Cache;
import Client.Requests;
import Controllers.AlertBox;
import Entity.Snippets.Channel;
import Settings.CheckTimeToShow;
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

public class CompareInfo {

    public static void show(Button search, TextField channelId, TableView<Channel> tableView,
                            TableColumn<Channel, String> nameColumn, TableColumn<Channel, String> dateColumn,
                            TableColumn<Channel, String> subsColumn, TableColumn<Channel, String> videoColumn,
                            TableColumn<Channel, String> viewsColumn, Text showTime) {

        search.setOnAction(event -> {
            long start = CheckTimeToShow.start();
            Main.service.submit(() -> {
                ObservableList<Channel> userData = FXCollections.observableArrayList();
                Collections.synchronizedList(userData);
                String query = channelId.getText();
                String[] ids = query.split(" ");
                if (ids.length == 2) {                            //По условию
                    for (int i = 0; i < ids.length; i++) {
                        int index = i;
                        Main.service.submit(() -> {
                            try {
                                if (ShowGlobalInfo.saveCache()) {
                                    if (Cache.channelContains(ids[index]))
                                        userData.add(loadFromCache(ids[index]).get(0));
                                    else userData.add(loadFromYouTube(ids[index]).get(0));
                                } else userData.add(loadFromYouTube(ids[index]).get(0));
                                if (userData.size() == ids.length) {
                                    nameColumn.setCellValueFactory(new PropertyValueFactory<Channel, String>("name"));
                                    dateColumn.setCellValueFactory(new PropertyValueFactory<Channel, String>("date"));
                                    subsColumn.setCellValueFactory(new PropertyValueFactory<Channel, String>("subscribers"));
                                    videoColumn.setCellValueFactory(new PropertyValueFactory<Channel, String>("countVideo"));
                                    viewsColumn.setCellValueFactory(new PropertyValueFactory<Channel, String>("countViews"));
                                    tableView.setItems(userData);
                                    Platform.runLater(() -> {
                                        try {
                                            if (ShowGlobalInfo.showTime())
                                                showTime.setText(CheckTimeToShow.getTime(start));
                                        } catch (FileNotFoundException e) {
                                            e.printStackTrace();
                                        }
                                    });
                                }
                            } catch (FileNotFoundException e) {
                                e.printStackTrace();
                            } catch (ParseException e) {
                                e.printStackTrace();
                            } catch (UnirestException e) {
                                e.printStackTrace();
                            }
                        });
                    }
                } else Platform.runLater(() -> AlertBox.display("Исправьте кол-во каналов"));
            });
        });
    }

    public static ObservableList<Channel> loadFromYouTube(String id) throws ParseException, UnirestException {
        ObservableList<Channel> userData = FXCollections.observableArrayList();
        Collections.synchronizedList(userData);
        userData.add(Requests.ShowGlobalInfo(id).get(0));
        Main.service.submit(() -> {
            try {
                if (ShowGlobalInfo.saveCache()) Cache.saveToCache(userData.get(0), id);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        return userData;
    }

    public static ObservableList<Channel> loadFromCache(String id) throws FileNotFoundException {
        ObservableList<Channel> userData = FXCollections.observableArrayList();
        Collections.synchronizedList(userData);
        userData.add(Cache.getChannelFromCache(id));
        return userData;
    }
}
