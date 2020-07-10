package controllers;

import database.DBHelper;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import models.User;
import okhttp3.FormBody;
import okhttp3.RequestBody;
import org.json.JSONArray;
import org.json.JSONObject;
import sample.Main;
import utils.APIRequests;
import utils.genVars;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable {


    @FXML
    private Label login_msg;

    @FXML
    private TextField txt_user;

    @FXML
    private PasswordField txt_pass;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
    }

    public void BtnGetIp(ActionEvent actionEvent) {
    }

    public void BtnLogin(ActionEvent actionEvent) throws IOException {
        System.out.println("Home");
        /*try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../res/fxml/Main.fxml"));
            Parent root = (Parent) fxmlLoader.load();
            Main.setContent(root);

        } catch (Exception e) {

        }

         */
        String user = txt_user.getText().toString();
        String pass = txt_pass.getText().toString();

        RequestBody formBody = new FormBody.Builder()
                .add("user", user)
                .add("pass", pass)
                .add("type", "1")
                .build();

        String url = APIRequests.LOGIN_USER;
        String response = DBHelper.loginUser(url, formBody);
        if(response != null){
            JSONObject object = new JSONObject(response.toString());
            JSONArray arr = object.getJSONArray("users");
            System.out.println(arr.get(0));
            JSONObject o = arr.getJSONObject(0);
            User u = new User();
            u.setId(o.getInt("id"));
            u.setName(o.getString("user"));
            u.setPassword(o.getString("pass"));
            u.setType(o.getInt("type"));
            genVars.user = u;
            Main.isLoggedin = true;
            // start Main
            startMain();
        }else{
            login_msg.setText("خطأ في الدخول");
        }


    }

    private void startMain() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../res/fxml/Main.fxml"));
            Parent root = (Parent) fxmlLoader.load();
            Main.setContent(root);

        } catch (Exception e) {

        }

    }

}