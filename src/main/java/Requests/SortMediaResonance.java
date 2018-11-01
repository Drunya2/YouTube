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
import java.text.ParseException;
import java.util.Collections;

public class SortMediaResonance {

    public static void showSort(Button search, TextField channelId, TableView<CommentsChannel> tableView,
                            TableColumn<CommentsChannel, String> nameColumn, TableColumn<CommentsChannel, String> dateColumn,
                            TableColumn<CommentsChannel, String> subsColumn, TableColumn<CommentsChannel, String> videoColumn,
                            TableColumn<CommentsChannel, String> viewsColumn, TableColumn<CommentsChannel, String> commentsColumn,
                            Text showTime){

        search.setOnAction(event -> {
            long start = CheckTimeToShow.start();
            Main.service.submit(() -> {
                ObservableList<CommentsChannel> userData = FXCollections.observableArrayList();
                Collections.synchronizedList(userData);
                String query = channelId.getText();
                String[] ids = query.split(" ");
                for (int i = 0; i < ids.length; i++) {
                    int index = i;
                    Main.service.submit(() -> {
                        try {
                            if (ShowGlobalInfo.saveCache()) {
                                if (Cache.CommentsContains(ids[index]))
                                    userData.add(CompareResonance.loadFromCache(ids[index]).get(0));
                                else userData.add(CompareResonance.loadFromYouTube(ids[index]).get(0));
                            } else userData.add(CompareResonance.loadFromYouTube(ids[index]).get(0));
                            if (userData.size() == ids.length) {
                                Collections.sort(userData, CommentsChannel.ChannelCommentsComparator);
                                nameColumn.setCellValueFactory(new PropertyValueFactory<CommentsChannel, String>("name"));
                                dateColumn.setCellValueFactory(new PropertyValueFactory<CommentsChannel, String>("date"));
                                subsColumn.setCellValueFactory(new PropertyValueFactory<CommentsChannel, String>("subscribers"));
                                videoColumn.setCellValueFactory(new PropertyValueFactory<CommentsChannel, String>("countVideo"));
                                viewsColumn.setCellValueFactory(new PropertyValueFactory<CommentsChannel, String>("countViews"));
                                commentsColumn.setCellValueFactory(new PropertyValueFactory<CommentsChannel, String>("countComments"));
                                tableView.setItems(userData);
                                Platform.runLater(() -> {
                                    try {
                                        if (ShowGlobalInfo.showTime()) showTime.setText(CheckTimeToShow.getTime(start));
                                    } catch (FileNotFoundException e) {
                                        e.printStackTrace();
                                    }
                                });
                            }
                        } catch (UnirestException e) {
                            e.printStackTrace();
                        } catch (ParseException e) {
                            e.printStackTrace();
                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        }
                    });
                }
            });
        });
    }
}
