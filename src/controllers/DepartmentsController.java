package controllers;

import database.DBHelper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Callback;
import models.Department;
import okhttp3.FormBody;
import okhttp3.RequestBody;
import org.json.JSONArray;
import org.json.JSONObject;
import sample.Main;
import utils.APIRequests;
import utils.DialogNewCategory;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class DepartmentsController extends SideMenuController {

    @FXML
    private ListView<Department> list_dep = new ListView<>();

    private List<Department> list = new ArrayList<>();
    private ObservableList<Department> items;
    private JSONArray deps_list = new JSONArray();

    private Stage dialogStage = new Stage();

    private Image img = new Image("/res/images/cafee.png");

    private int ADD_CATEGORY = 0;
    private int EDIT_CATEGORY = 1;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        // get categories
        String url = APIRequests.GET_CATEGORIES;
        try {
            String response = DBHelper.getData(url);
            JSONObject departments = new JSONObject(response.toString());
            int success = departments.getInt("success");
            if (success == 1){
                deps_list = departments.getJSONArray("categories");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        for (int i = 0; i < deps_list.length(); i++){
            JSONObject o = deps_list.getJSONObject(i);
            Department department = new Department(
                    o.getInt("id"),
                    o.getString("name"),
                    o.getString("img_url"),
                    o.getString("printer")
            );
            list.add(department);
        }

        items = FXCollections.observableArrayList (list);
        list_dep.setItems(items);

        list_dep.setCellFactory(param -> new ListCell<Department>() {
            @Override
            public void updateItem(Department item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setText(null);
                    setGraphic(null);
                } else {
                    ImageView imageView = new ImageView();
                    imageView.setFitHeight(100);
                    imageView.setFitWidth(100);
                    imageView.setId("id" + item.getId());
                    setText(item.getName());
                    System.out.println(item.getImg_url());
                    imageView.setImage(new Image(item.getImg_url()));
                    setGraphic(imageView);
                }
            }
        });


        list_dep.setOnMouseClicked(event -> {
            int index = list_dep.getSelectionModel().getSelectedIndex();
            Department department = list.get(index);
            System.out.println(department.getName());
            DialogNewCategory.AlertMSG("تعديل القسم", new Main().getStage(), EDIT_CATEGORY, department);
        });

    }

    public void BtnAdd(ActionEvent actionEvent)throws IOException {
        DialogNewCategory.AlertMSG("ادخل اسم القسم", new Main().getStage(), ADD_CATEGORY, null);
    }

    private void showAddDialog(String msg) {
        dialogStage.initModality(Modality.WINDOW_MODAL);
        dialogStage.setTitle(msg);
        Pane pane = null;
        try {
            pane = FXMLLoader.load(getClass().getResource("../res/fxml/DialogDep.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        dialogStage.setScene(new Scene(pane));
        dialogStage.show();

    }

    private void previewList(Department department) {
        list.add(department);
        items.add(department);
    }

    public void BtnSave(ActionEvent actionEvent) {
        /*String name = txt_name.getText();
        Department department = new Department(6, name, new ImageView(img));
        previewList(department);
        System.out.println(name);
        txt_name.setText("");

         */
    }

}