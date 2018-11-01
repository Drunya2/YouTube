package Controllers;

import Entity.Snippets.YouTubeAnalytic;
import Start.Main;
import javafx.animation.*;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainMenu implements Initializable {

    @FXML
    private Button analytic;

    @FXML
    private Button settings;

    @FXML
    private ImageView imageView;

    @FXML
    private Button exit;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Main.service.submit(() -> YouTubeAnalytic.rotatePicture(imageView));
        goToAnalytic();
        goToSettings();
        close();


    }

    private void changeScreen(String path) {
        try {
            Main.window.setScene(new Scene(FXMLLoader.load(getClass().getResource(path))));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @FXML
    private void goToAnalytic() {
        analytic.setOnAction(event1 -> changeScreen("/fxmls/YouTubeAnalytic.fxml"));
    }

    @FXML
    private void goToSettings() {
        settings.setOnAction(event -> changeScreen("/fxmls/Settings.fxml"));
    }

    @FXML
    void close() {
        exit.setOnAction(event -> {
            Main.service.shutdown();
            Platform.exit();
        });
    }


}

