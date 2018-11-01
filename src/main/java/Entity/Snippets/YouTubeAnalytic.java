package Entity.Snippets;

import Entity.ResponseGlobalInfo;
import Entity.ShowInfo;
import Start.Main;
import com.mashape.unirest.http.exceptions.UnirestException;
import javafx.animation.ParallelTransition;
import javafx.animation.RotateTransition;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

import java.io.IOException;
import java.util.ArrayList;

public class YouTubeAnalytic {

    public static final String API_KEY = "AIzaSyD_kAboIzzECcXH8BdjcKsxnKKvegokkO4";

    public static String getName() {
        return name;
    }

    public static String getDate() {
        return date;
    }

    public static String getSubscribers() {
        return subscribers;
    }

    public static String getCountVideo() {
        return countVideo;
    }

    public static String getCountViews() {
        return countViews;
    }

    public static void setName(String name) {
        YouTubeAnalytic.name = name;
    }

    public static void setDate(String date) {
        YouTubeAnalytic.date = date;
    }

    public static void setSubscribers(String subscribers) {
        YouTubeAnalytic.subscribers = subscribers;
    }

    public static void setCountVideo(String countVideo) {
        YouTubeAnalytic.countVideo = countVideo;
    }

    public static void setCountViews(String countViews) {
        YouTubeAnalytic.countViews = countViews;
    }

    private static String name;
    private static String date;
    private static String subscribers;
    private static String countVideo;
    private static String countViews;

    public static String getDate(String date) {
        String string = new String();

        for (int i = 0; i < 10; i++) {
            string += date.charAt(i);
        }

        return string;
    }


    public YouTubeAnalytic(String name, String date, String subscribers, String countVideo, String countViews) {
        this.name = name;
        this.date = date;
        this.subscribers = subscribers;
        this.countVideo = countVideo;
        this.countViews = countViews;
    }


    public static void getGlobalInfo(String query) throws UnirestException {
        ResponseGlobalInfo globalInfo = ShowInfo.getInfo(query);
        for (int i = 0; i < globalInfo.items.length; i++) {
            setName(globalInfo.items[i].snippet.title);
            String publishedDate = globalInfo.items[i].snippet.publishedAt;
            setDate(getDate(publishedDate));
            setSubscribers(globalInfo.items[i].statistics.subscriberCount);
            setCountVideo(globalInfo.items[i].statistics.videoCount);
            setCountViews(globalInfo.items[i].statistics.viewCount);
        }

    }

    public static void rotatePicture(ImageView imageView) {
        Timeline timeline = new Timeline();
        RotateTransition rotateTransition =
                new RotateTransition(Duration.millis(3000), imageView);
        rotateTransition.setByAngle(180f);
        rotateTransition.setCycleCount(4);
        rotateTransition.setAutoReverse(true);

        ParallelTransition parallelTransition = new ParallelTransition();
        parallelTransition.getChildren().addAll(
                rotateTransition
        );
        parallelTransition.setCycleCount(Timeline.INDEFINITE);
        timeline.setAutoReverse(true);
        parallelTransition.play();
    }

    public static ArrayList<String> getChannelsFromTextField(String text) {
        ArrayList<String> arrayList = new ArrayList<>();
        String[] channelsId = text.split(" ");
        for (int i = 0; i < channelsId.length; i++) {
            arrayList.add(channelsId[i]);
        }

        return arrayList;
    }




}
