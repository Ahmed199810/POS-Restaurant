package sample;

import database.DBHelper;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import org.json.JSONArray;
import org.json.JSONObject;
import utils.APIRequests;
import utils.AnalogClock;

import java.io.IOException;

public class Main extends Application {

    public static BorderPane root;
    public static boolean isLoggedin = false;
    public static AnalogClock clock = new AnalogClock();
    private Stage stage;
    public static JSONArray setting_arr = new JSONArray();

    @Override
    public void start(Stage primaryStage) throws Exception{
        Image image = new Image("/res/images/cafelogo.png");
        primaryStage.getIcons().add(image);
        Parent content = null;

        if(isLoggedin){
            System.out.println("Main Home");
            content = FXMLLoader.load(getClass().getResource("../res/fxml/Main.fxml"));

        }else{
            System.out.println("Login");
            content = FXMLLoader.load(getClass().getResource("../res/fxml/Login.fxml"));
        }

        initSettings();

        root = new BorderPane();
        root.setCenter(content);

        Scene scene = new Scene(root);

        primaryStage.setMinHeight(690);
        primaryStage.setMinWidth(1050);
        primaryStage.setScene(scene);
        scene.getStylesheets().add(getClass().getResource("../res/css/clock.css").toExternalForm());
        primaryStage.show();
        primaryStage.setTitle("Cafe Zone");
        this.stage = primaryStage;

    }

    public static void initSettings() {
        String url = APIRequests.GET_SETTINGS;
        String response = null;
        try {
            response = DBHelper.getData(url);
        } catch (IOException e) {
            e.printStackTrace();
        }
        JSONObject o = new JSONObject(response.toString());
        if(o.getInt("success") == 1){
            setting_arr = o.getJSONArray("settings");
        }
    }

    public static void setContent(Node node) {
        root.setCenter(node);
    }

    public static void main(String[] args) {
        launch(args);
    }

    public Stage getStage() {
        return stage;
    }
}