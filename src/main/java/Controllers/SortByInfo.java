package Controllers;

import Entity.Snippets.Channel;
import Requests.SortByInformation;
import Start.Main;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class SortByInfo implements Initializable {

    @FXML
    private ImageView imageView;

    @FXML
    private Button mainMenu;

    @FXML
    private Button analyticMenu;

    @FXML
    private TextField channelsIdField;

    @FXML
    private Text showTime;

    @FXML
    private TableView<Channel> tableView;

    @FXML
    private TableColumn<Channel, String> nameColumn;

    @FXML
    private TableColumn<Channel, String> dateColumn;

    @FXML
    private TableColumn<Channel, String> subsColumn;

    @FXML
    private TableColumn<Channel, String> videoColumn;

    @FXML
    private TableColumn<Channel, String> viewsColumn;

    @FXML
    private MenuItem nameItem;

    @FXML
    private MenuItem dateItem;

    @FXML
    private MenuItem subsItem;

    @FXML
    private MenuItem videoItem;

    @FXML
    private MenuItem viewsItem;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Main.service.submit(() -> Entity.Snippets.YouTubeAnalytic.rotatePicture(imageView));
        backToMain();
        backToAnalytic();
        SortByInformation.sortByName(nameItem, channelsIdField, tableView, nameColumn, dateColumn, subsColumn,
                videoColumn, viewsColumn, showTime);
        SortByInformation.sortByDate(dateItem, channelsIdField, tableView, nameColumn, dateColumn, subsColumn,
                videoColumn, viewsColumn, showTime);
        SortByInformation.sortBySubscribers(subsItem, channelsIdField, tableView, nameColumn, dateColumn, subsColumn,
                videoColumn, viewsColumn, showTime);
        SortByInformation.sortByVideoCount(videoItem, channelsIdField, tableView, nameColumn, dateColumn, subsColumn,
                videoColumn, viewsColumn, showTime);
        SortByInformation.sortByViewsCount(viewsItem, channelsIdField, tableView, nameColumn, dateColumn, subsColumn,
                videoColumn, viewsColumn, showTime);
    }

    private void changeScreen(String path) {
        try {
            Main.window.setScene(new Scene(FXMLLoader.load(getClass().getResource(path))));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void backToMain() {
        mainMenu.setOnAction(event -> changeScreen("/fxmls/MainMenu.fxml"));
    }

    @FXML
    void backToAnalytic() {
        analyticMenu.setOnAction(event -> changeScreen("/fxmls/YouTubeAnalytic.fxml"));
    }

}
