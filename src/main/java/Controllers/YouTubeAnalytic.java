package Controllers;

import Start.Main;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.image.ImageView;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class YouTubeAnalytic implements Initializable{

    @FXML
    private ImageView imageView;

    @FXML
    private Button backToMainScreen;

    @FXML
    private MenuItem globalInfoChannel;

    @FXML
    private MenuItem compareGlobalInfo;

    @FXML
    private MenuItem sortByInfo;

    @FXML
    private MenuItem mediaResonance;

    @FXML
    private MenuItem compareMediaResonanse;

    @FXML
    private MenuItem sortByMediaresonanse;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Main.service.submit(()-> Entity.Snippets.YouTubeAnalytic.rotatePicture(imageView));
        backToMainMenu();
        goToGlobalInfo();
        goToCompareGlobalInfo();
        sortByInfo();
        goToMediaResonance();
        goToCompareMediaResonance();
        goToSortByMediaResonance();
    }

    private void changeScreen(String path){
        try {
            Main.window.setScene(new Scene(FXMLLoader.load(getClass().getResource(path))));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void backToMainMenu(){
        backToMainScreen.setOnAction(event -> changeScreen("/fxmls/MainMenu.fxml"));
    }


    @FXML
    void goToGlobalInfo(){
        globalInfoChannel.setOnAction(event -> changeScreen("/fxmls/GlobalInfo.fxml"));
    }

    @FXML
    void goToCompareGlobalInfo(){
        compareGlobalInfo.setOnAction(event -> changeScreen("/fxmls/CompareInfo.fxml"));
    }

    @FXML
    void sortByInfo(){
        sortByInfo.setOnAction(event -> changeScreen("/fxmls/SortByInfo.fxml"));
    }

    @FXML
    void goToMediaResonance(){
        mediaResonance.setOnAction(event -> changeScreen("/fxmls/MediaResonance.fxml"));
    }

    @FXML
    void goToCompareMediaResonance(){
        compareMediaResonanse.setOnAction(event -> changeScreen("/fxmls/CompareMediaResonance.fxml"));
    }

    @FXML
    void goToSortByMediaResonance(){
        sortByMediaresonanse.setOnAction(event -> changeScreen("/fxmls/SortByMediaResonance.fxml"));
    }


}
