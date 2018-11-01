package Requests;

import Cache.Cache;
import Client.Requests;
import Entity.Snippets.Channel;
import Entity.Snippets.CommentsChannel;
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

public class ShowMediaResonance {

    public static void showInfo(Button search, TextField channelId, TableView<CommentsChannel> tableView,
                            TableColumn<CommentsChannel, String> nameColumn, TableColumn<CommentsChannel, String> dateColumn,
                            TableColumn<CommentsChannel, String> subsColumn, TableColumn<CommentsChannel, String> videoColumn,
                            TableColumn<CommentsChannel, String> viewsColumn, TableColumn<CommentsChannel, String> commentsColumn,
                            Text showTime){

        search.setOnAction(event -> {
            long start = CheckTimeToShow.start();
            Main.service.submit(() -> {
                String query = channelId.getText();
                try {
                    if (ShowGlobalInfo.saveCache()) {
                        if (Cache.CommentsContains(query)) {
                            loadCommentsFromCache(query, tableView, nameColumn, dateColumn, subsColumn, videoColumn,
                                    viewsColumn, commentsColumn);
                        } else {
                            loadCommentsFromYouTube(query, tableView, nameColumn, dateColumn, subsColumn, videoColumn,
                                    viewsColumn, commentsColumn);
                        }
                    } else
                        loadCommentsFromYouTube(query, tableView, nameColumn, dateColumn, subsColumn, videoColumn,
                                viewsColumn, commentsColumn);
                    Platform.runLater(() -> {
                        try {
                            if (ShowGlobalInfo.showTime()) showTime.setText(CheckTimeToShow.getTime(start));
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

    private static void loadCommentsFromYouTube(String id, TableView<CommentsChannel> tableView,
                                            TableColumn<CommentsChannel, String> nameColumn, TableColumn<CommentsChannel, String> dateColumn,
                                            TableColumn<CommentsChannel, String> subsColumn, TableColumn<CommentsChannel, String> videoColumn,
                                            TableColumn<CommentsChannel, String> viewsColumn, TableColumn<CommentsChannel, String> commentsColumn) {

        ObservableList<CommentsChannel> userData = FXCollections.observableArrayList();
        Collections.synchronizedList(userData);
            try {
                userData.add(Requests.showComments(id).get(0));
                nameColumn.setCellValueFactory(new PropertyValueFactory<CommentsChannel, String>("name"));
                dateColumn.setCellValueFactory(new PropertyValueFactory<CommentsChannel, String>("date"));
                subsColumn.setCellValueFactory(new PropertyValueFactory<CommentsChannel, String>("subscribers"));
                videoColumn.setCellValueFactory(new PropertyValueFactory<CommentsChannel, String>("countVideo"));
                viewsColumn.setCellValueFactory(new PropertyValueFactory<CommentsChannel, String>("countViews"));
                commentsColumn.setCellValueFactory(new PropertyValueFactory<CommentsChannel, String>("countComments"));
                tableView.setItems(userData);
                if (ShowGlobalInfo.saveCache()) {
                    Cache.saveCommentsToCache(userData.get(0), id);
                }
            } catch (UnirestException e) {
                e.printStackTrace();
            } catch (ParseException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
    }

    private static void loadCommentsFromCache(String id, TableView<CommentsChannel> tableView,
                                          TableColumn<CommentsChannel, String> nameColumn, TableColumn<CommentsChannel, String> dateColumn,
                                          TableColumn<CommentsChannel, String> subsColumn, TableColumn<CommentsChannel, String> videoColumn,
                                          TableColumn<CommentsChannel, String> viewsColumn, TableColumn<CommentsChannel, String> commentsColumn) throws FileNotFoundException {

        ObservableList<CommentsChannel> userData = FXCollections.observableArrayList();
        Collections.synchronizedList(userData);
        userData.add(Cache.getCommentsFromCache(id));
        nameColumn.setCellValueFactory(new PropertyValueFactory<CommentsChannel, String>("name"));
        dateColumn.setCellValueFactory(new PropertyValueFactory<CommentsChannel, String>("date"));
        subsColumn.setCellValueFactory(new PropertyValueFactory<CommentsChannel, String>("subscribers"));
        videoColumn.setCellValueFactory(new PropertyValueFactory<CommentsChannel, String>("countVideo"));
        viewsColumn.setCellValueFactory(new PropertyValueFactory<CommentsChannel, String>("countViews"));
        commentsColumn.setCellValueFactory(new PropertyValueFactory<CommentsChannel, String>("countComments"));
        tableView.setItems(userData);
    }
}
