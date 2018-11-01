package Controllers;

import Entity.Snippets.YouTubeAnalytic;
import Start.Main;
import Settings.ComponentSettings;
import Settings.LoadSettings;
import Settings.SaveSettings;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ResourceBundle;

public class Settings implements Initializable {

    @FXML
    private ImageView imageView;

    @FXML
    private Button mainMenu;

    @FXML
    private Button infoButton;

    @FXML
    public CheckBox useCache;

    @FXML
    private TextField pathToCache;

    @FXML
    private CheckBox showTimeRequest;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        loadSettings();
        Main.service.submit(() -> YouTubeAnalytic.rotatePicture(imageView));
        backToMainMenu();
        saveCache();
        saveShowTime();
        savePath();
        showInfo();

    }

    private void changeScreen(String path) {
        try {
            Main.window.setScene(new Scene(FXMLLoader.load(getClass().getResource(path))));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void backToMainMenu() {
        mainMenu.setOnAction(event -> changeScreen("/fxmls/MainMenu.fxml"));
    }

    @FXML
    void saveCache() {
        useCache.setOnAction(event -> {
            Main.service.submit(() -> {
                    try {
                        ComponentSettings settings = LoadSettings.loadSettings();
                        if (useCache.isSelected()) settings.setUseCacheBool(true);
                        else settings.setUseCacheBool(false);
                        SaveSettings.saveSettings(settings);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
            });
        });
    }

    @FXML
    void saveShowTime() {
        showTimeRequest.setOnAction(event -> {
            Main.service.submit(() -> {
                try {
                    ComponentSettings settings = LoadSettings.loadSettings();
                    if (showTimeRequest.isSelected()) settings.setShowTime(true);
                    else settings.setShowTime(false);
                    SaveSettings.saveSettings(settings);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        });
    }

    @FXML
    void savePath() {
        Main.service.submit(()->{
            pathToCache.setOnKeyPressed(new EventHandler<KeyEvent>() {
                public void handle(KeyEvent ke) {
                    if (ke.getCode().equals(KeyCode.ENTER)) {
                        try {
                            String path = pathToCache.getText();
                            File file = new File(path);
                            ComponentSettings settings = LoadSettings.loadSettings();
                            settings.setPath(path);
                            SaveSettings.saveSettings(settings);
                            if (Files.notExists(file.toPath())) {
                                Files.createDirectory(Paths.get(path));
                            }

                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            });
        });
    }

    @FXML
    void loadSettings(){
        Main.service.submit(()->{
            try {
                ComponentSettings settings = LoadSettings.loadSettings();
                useCache.setSelected(settings.isUseCacheBool());
                showTimeRequest.setSelected(settings.isShowTime());
                pathToCache.setText(settings.getPath());
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        });
    }

    @FXML
    void showInfo(){
        infoButton.setOnAction(event -> {
            AlertBox.display("Введите путь к папке с кэшем, например src/main/cache , и нажмите Enter");
        });
    }
}
