package Controllers;

import Entity.Snippets.Channel;
import Entity.Snippets.CommentsChannel;
import Entity.Snippets.YouTubeAnalytic;
import Requests.CompareResonance;
import Start.Main;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class CompareMediaResonance implements Initializable {

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
    private TableView<CommentsChannel> tableView;

    @FXML
    private TableColumn<CommentsChannel, String> nameColumn;

    @FXML
    private TableColumn<CommentsChannel, String> dateColumn;

    @FXML
    private TableColumn<CommentsChannel, String> subsColumn;

    @FXML
    private TableColumn<CommentsChannel, String> videoColumn;

    @FXML
    private TableColumn<CommentsChannel, String> viewsColumn;

    @FXML
    private TableColumn<CommentsChannel, String> commentsColumn;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Main.service.submit(() -> YouTubeAnalytic.rotatePicture(imageView));
        backToMain();
        backToYoutubeAnalytic();
        CompareResonance.showInfo(searchButton, channelIdField, tableView, nameColumn, dateColumn, subsColumn,
                videoColumn, viewsColumn, commentsColumn, showTime);
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
