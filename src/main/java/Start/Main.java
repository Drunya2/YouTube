package Start;

import Entity.JsonMapper;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main extends Application {

    public static Stage window;
    public static ExecutorService service = Executors.newFixedThreadPool(32);

    public static void main(String[] args) {
        launch(args);
    }


    @Override
    public void start(Stage primaryStage) throws Exception {
        new JsonMapper();
        window = primaryStage;
        Parent root = FXMLLoader.load(getClass().getResource("/fxmls/MainMenu.fxml"));

        Scene scene = new Scene(root, 700, 600);
        primaryStage.setScene(scene);
        primaryStage.setTitle("YouTube Analyzer");
        primaryStage.show();

        primaryStage.setOnCloseRequest(event -> {
            service.shutdown();
        });
    }


}




