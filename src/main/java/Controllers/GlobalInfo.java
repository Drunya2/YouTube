package Controllers;

import Requests.ShowGlobalInfo;
import Entity.Snippets.Channel;
import Entity.Snippets.YouTubeAnalytic;
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

public class GlobalInfo implements Initializable {


    @FXML
    private ImageView imageView;

    @FXML
    private Button mainMenu;

    @FXML
    private Button analyticMenu;

    @FXML
    private TextField channelIdField;

    @FXML
    private Button searchButton;

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


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Main.service.submit(()-> YouTubeAnalytic.rotatePicture(imageView));
        backToMain();
        backToYoutubeAnalytic();
        ShowGlobalInfo.show(searchButton, channelIdField, tableView, nameColumn, dateColumn, subsColumn,
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
    void backToYoutubeAnalytic() {
        analyticMenu.setOnAction(event -> changeScreen("/fxmls/YouTubeAnalytic.fxml"));
    }




}
