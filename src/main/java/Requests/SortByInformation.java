package Requests;

import Cache.Cache;
import Client.Requests;
import Entity.Snippets.Channel;
import Settings.CheckTimeToShow;
import Start.Main;
import com.mashape.unirest.http.exceptions.UnirestException;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;

import java.io.FileNotFoundException;
import java.text.ParseException;
import java.util.Collections;

public class SortByInformation {

    public static void sortByName(MenuItem nameItem, TextField channelIds, TableView<Channel> tableView,
                                  TableColumn<Channel, String> nameColumn, TableColumn<Channel, String> dateColumn,
                                  TableColumn<Channel, String> subsColumn, TableColumn<Channel, String> videoColumn,
                                  TableColumn<Channel, String> viewsColumn, Text showTime) {

        nameItem.setOnAction(event -> {
            long start = CheckTimeToShow.start();
            Main.service.submit(() -> {
                ObservableList<Channel> userData = FXCollections.observableArrayList();
                Collections.synchronizedList(userData);
                String query = channelIds.getText();
                String[] ids = query.split(" ");
                for (int i = 0; i < ids.length; i++) {
                    int index = i;
                    Main.service.submit(() -> {
                        try {
                            if (ShowGlobalInfo.saveCache()) {
                                if (Cache.channelContains(ids[index]))
                                    userData.add(CompareInfo.loadFromCache(ids[index]).get(0));
                                else userData.add(CompareInfo.loadFromYouTube(ids[index]).get(0));
                            } else userData.add(CompareInfo.loadFromYouTube(ids[index]).get(0));
                            if (userData.size() == ids.length) {
                                Collections.sort(userData, Channel.ChannelNameComparator);
                                nameColumn.setCellValueFactory(new PropertyValueFactory<Channel, String>("name"));
                                dateColumn.setCellValueFactory(new PropertyValueFactory<Channel, String>("date"));
                                subsColumn.setCellValueFactory(new PropertyValueFactory<Channel, String>("subscribers"));
                                videoColumn.setCellValueFactory(new PropertyValueFactory<Channel, String>("countVideo"));
                                viewsColumn.setCellValueFactory(new PropertyValueFactory<Channel, String>("countViews"));
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

    public static void sortByDate(MenuItem dateItem, TextField channelIds, TableView<Channel> tableView,
                                  TableColumn<Channel, String> nameColumn, TableColumn<Channel, String> dateColumn,
                                  TableColumn<Channel, String> subsColumn, TableColumn<Channel, String> videoColumn,
                                  TableColumn<Channel, String> viewsColumn, Text showTime) {

        dateItem.setOnAction(event -> {
            long start = CheckTimeToShow.start();
            Main.service.submit(() -> {
                ObservableList<Channel> userData = FXCollections.observableArrayList();
                Collections.synchronizedList(userData);
                String query = channelIds.getText();
                String[] ids = query.split(" ");
                for (int i = 0; i < ids.length; i++) {
                    int index = i;
                    Main.service.submit(() -> {
                        try {
                            if (ShowGlobalInfo.saveCache()) {
                                if (Cache.channelContains(ids[index]))
                                    userData.add(CompareInfo.loadFromCache(ids[index]).get(0));
                                else userData.add(CompareInfo.loadFromYouTube(ids[index]).get(0));
                            } else userData.add(CompareInfo.loadFromYouTube(ids[index]).get(0));
                            if (userData.size() == ids.length) {
                                Collections.sort(userData, Channel.ChannelDateComparator);
                                nameColumn.setCellValueFactory(new PropertyValueFactory<Channel, String>("name"));
                                dateColumn.setCellValueFactory(new PropertyValueFactory<Channel, String>("date"));
                                subsColumn.setCellValueFactory(new PropertyValueFactory<Channel, String>("subscribers"));
                                videoColumn.setCellValueFactory(new PropertyValueFactory<Channel, String>("countVideo"));
                                viewsColumn.setCellValueFactory(new PropertyValueFactory<Channel, String>("countViews"));
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

    public static void sortBySubscribers(MenuItem subsItem, TextField channelIds, TableView<Channel> tableView,
                                         TableColumn<Channel, String> nameColumn, TableColumn<Channel, String> dateColumn,
                                         TableColumn<Channel, String> subsColumn, TableColumn<Channel, String> videoColumn,
                                         TableColumn<Channel, String> viewsColumn, Text showTime) {

        subsItem.setOnAction(event -> {
            long start = CheckTimeToShow.start();
            Main.service.submit(() -> {
                ObservableList<Channel> userData = FXCollections.observableArrayList();
                Collections.synchronizedList(userData);
                String query = channelIds.getText();
                String[] ids = query.split(" ");
                for (int i = 0; i < ids.length; i++) {
                    int index = i;
                    Main.service.submit(() -> {
                        try {
                            if (ShowGlobalInfo.saveCache()) {
                                if (Cache.channelContains(ids[index]))
                                    userData.add(CompareInfo.loadFromCache(ids[index]).get(0));
                                else userData.add(CompareInfo.loadFromYouTube(ids[index]).get(0));
                            } else userData.add(CompareInfo.loadFromYouTube(ids[index]).get(0));
                            if (userData.size() == ids.length) {
                                Collections.sort(userData, Channel.ChannelSubsComparator);
                                nameColumn.setCellValueFactory(new PropertyValueFactory<Channel, String>("name"));
                                dateColumn.setCellValueFactory(new PropertyValueFactory<Channel, String>("date"));
                                subsColumn.setCellValueFactory(new PropertyValueFactory<Channel, String>("subscribers"));
                                videoColumn.setCellValueFactory(new PropertyValueFactory<Channel, String>("countVideo"));
                                viewsColumn.setCellValueFactory(new PropertyValueFactory<Channel, String>("countViews"));
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

    public static void sortByVideoCount(MenuItem videoItem, TextField channelIds, TableView<Channel> tableView,
                                        TableColumn<Channel, String> nameColumn, TableColumn<Channel, String> dateColumn,
                                        TableColumn<Channel, String> subsColumn, TableColumn<Channel, String> videoColumn,
                                        TableColumn<Channel, String> viewsColumn, Text showTime) {

        videoItem.setOnAction(event -> {
            long start = CheckTimeToShow.start();
            Main.service.submit(() -> {
                ObservableList<Channel> userData = FXCollections.observableArrayList();
                Collections.synchronizedList(userData);
                String query = channelIds.getText();
                String[] ids = query.split(" ");
                for (int i = 0; i < ids.length; i++) {
                    int index = i;
                    Main.service.submit(() -> {
                        try {
                            if (ShowGlobalInfo.saveCache()) {
                                if (Cache.channelContains(ids[index]))
                                    userData.add(CompareInfo.loadFromCache(ids[index]).get(0));
                                else userData.add(CompareInfo.loadFromYouTube(ids[index]).get(0));
                            } else userData.add(CompareInfo.loadFromYouTube(ids[index]).get(0));
                            if (userData.size() == ids.length) {
                                Collections.sort(userData, Channel.ChannelVideoComparator);
                                nameColumn.setCellValueFactory(new PropertyValueFactory<Channel, String>("name"));
                                dateColumn.setCellValueFactory(new PropertyValueFactory<Channel, String>("date"));
                                subsColumn.setCellValueFactory(new PropertyValueFactory<Channel, String>("subscribers"));
                                videoColumn.setCellValueFactory(new PropertyValueFactory<Channel, String>("countVideo"));
                                viewsColumn.setCellValueFactory(new PropertyValueFactory<Channel, String>("countViews"));
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

    public static void sortByViewsCount(MenuItem viewsItem, TextField channelIds, TableView<Channel> tableView,
                                        TableColumn<Channel, String> nameColumn, TableColumn<Channel, String> dateColumn,
                                        TableColumn<Channel, String> subsColumn, TableColumn<Channel, String> videoColumn,
                                        TableColumn<Channel, String> viewsColumn, Text showTime) {

        viewsItem.setOnAction(event -> {
            long start = CheckTimeToShow.start();
            Main.service.submit(() -> {
                ObservableList<Channel> userData = FXCollections.observableArrayList();
                Collections.synchronizedList(userData);
                String query = channelIds.getText();
                String[] ids = query.split(" ");
                for (int i = 0; i < ids.length; i++) {
                    int index = i;
                    Main.service.submit(() -> {
                        try {
                            if (ShowGlobalInfo.saveCache()) {
                                if (Cache.channelContains(ids[index]))
                                    userData.add(CompareInfo.loadFromCache(ids[index]).get(0));
                                else userData.add(CompareInfo.loadFromYouTube(ids[index]).get(0));
                            } else userData.add(CompareInfo.loadFromYouTube(ids[index]).get(0));
                            if (userData.size() == ids.length) {
                                Collections.sort(userData, Channel.ChannelViewsComparator);
                                nameColumn.setCellValueFactory(new PropertyValueFactory<Channel, String>("name"));
                                dateColumn.setCellValueFactory(new PropertyValueFactory<Channel, String>("date"));
                                subsColumn.setCellValueFactory(new PropertyValueFactory<Channel, String>("subscribers"));
                                videoColumn.setCellValueFactory(new PropertyValueFactory<Channel, String>("countVideo"));
                                viewsColumn.setCellValueFactory(new PropertyValueFactory<Channel, String>("countViews"));
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
