package controllers;

import database.DBHelper;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import okhttp3.FormBody;
import okhttp3.RequestBody;
import org.json.JSONArray;
import org.json.JSONObject;
import sample.Main;
import utils.APIRequests;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static sample.Main.clock;

public class MainController extends SideMenuController {

    @FXML
    private VBox clk;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        clk.getChildren().add(clock);

    }

}