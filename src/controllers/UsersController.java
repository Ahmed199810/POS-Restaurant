package controllers;

import database.DBHelper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import okhttp3.FormBody;
import okhttp3.RequestBody;
import org.json.JSONArray;
import org.json.JSONObject;
import sample.Main;
import utils.APIRequests;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.InetAddress;
import java.net.URL;
import java.util.LinkedList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UsersController extends SideMenuController {

    @FXML
    private ComboBox<String> combo_perm;

    @FXML
    private VBox user_box;

    @FXML
    private TextField txt_user;

    @FXML
    private TextField txt_pass;

    @FXML
    private Label l_pass;

    @FXML
    private Label l_user;

    @FXML
    private Label txt_perm;

    @FXML
    private Button btn_del;

    private static int UID = 0;

    @FXML
    private ListView<String> list_users;

    @FXML
    private Label txt_state;

    @FXML
    private void BtnSave(ActionEvent event) throws ClassNotFoundException, UnsupportedEncodingException, IOException, IOException {
        int index = combo_perm.getSelectionModel().getSelectedIndex();
        if (index == -1) {

            RequestBody formBody = new FormBody.Builder()
                    .add("user", txt_user.getText().toString())
                    .add("pass", txt_pass.getText().toString())
                    .add("type", "1")
                    .build();

            String url = APIRequests.INSERT_USER;
            DBHelper.postData(url, formBody);
            // refresh UI
            try {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../res/fxml/Users.fxml"));
                Parent root = (Parent) fxmlLoader.load();
                Main.setContent(root);

            } catch (Exception e) {

            }
            utils.utils.AlertMSG("تم التسجيل بنجاح");
            getAllUsers();
            txt_user.setText("");
            txt_pass.setText("");
            user_box.setVisible(false);
            txt_state.setText("");
        } else {
            txt_state.setText("اختر الصلاحيات");
        }
    }


    @FXML
    private void BtnAddUser(ActionEvent event) throws IOException {
        user_box.setVisible(true);
        l_user.setVisible(false);
        l_pass.setVisible(false);
        txt_perm.setVisible(false);
        btn_del.setVisible(false);
    }


    @FXML
    private void BtnDel(ActionEvent event) throws IOException {
        System.out.println("DELETE");

        RequestBody formBody = new FormBody.Builder()
                .add("id", UID + "")
                .add("table", "users")
                .build();
        String url = APIRequests.DELETE_ITEM;
        DBHelper.postData(url, formBody);
        utils.utils.AlertMSG("تم الحذف بنجاح");

        l_user.setText("");
        l_pass.setText("");
        l_user.setVisible(false);
        l_pass.setVisible(false);
        txt_perm.setVisible(false);
        btn_del.setVisible(false);

        // refresh UI
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../res/fxml/Users.fxml"));
            Parent root = (Parent) fxmlLoader.load();
            Main.setContent(root);

        } catch (Exception e) {

        }


    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            // get all users
            getAllUsers();
        } catch (IOException ex) {
            Logger.getLogger(SettingsController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void checkMachineId() {
        InetAddress ip;

        try {
            ip = InetAddress.getLocalHost();
            System.out.println("" + ip);
        } catch (Exception e) {

        }
    }

    private void getAllUsers() throws IOException {
        String url = APIRequests.GET_ALL_USERS;
        String response = DBHelper.getData(url);
        JSONObject object = new JSONObject(response.toString());
        JSONArray arr = object.getJSONArray("users"); // getting users
        LinkedList<String> strList = new LinkedList();

        for (int i = 0; i < arr.length(); i++) {
            JSONObject obj = arr.getJSONObject(i);
            strList.add(obj.getString("user"));
        }

        ObservableList<String> items = FXCollections.observableArrayList(strList);
        list_users.setItems(items);

        list_users.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                int index = list_users.getSelectionModel().getSelectedIndex();
                System.out.println(index);
                // show new window
                l_user.setVisible(true);
                l_pass.setVisible(true);
                txt_perm.setVisible(true);

                JSONObject user = arr.getJSONObject(index);

                l_user.setText(user.getString("user"));

                UID = user.getInt("id");
                if (user.getInt("type") == 0) {
                    btn_del.setVisible(false);
                    l_pass.setText("");
                    previewPermitions(user.getInt("type"));
                } else {
                    btn_del.setVisible(true);
                    l_pass.setText(user.getString("pass"));
                    previewPermitions(user.getInt("type"));
                }
                user_box.setVisible(false);


            }

            private void previewPermitions(int type) {

                if (type == 0) {
                    txt_perm.setText("Super Admin");
                } else if (type == 1) {
                    txt_perm.setText("Casher");
                } else if (type == 2) {
                    txt_perm.setText("Maintenance Engineer");
                } else if (type == 3) {
                    txt_perm.setText("Supervisor");
                }

            }
        });
    }


}