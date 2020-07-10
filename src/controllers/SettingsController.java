package controllers;


import database.DBHelper;
import javafx.collections.ObservableSet;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.print.Printer;
import javafx.scene.Parent;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import okhttp3.FormBody;
import okhttp3.RequestBody;
import sample.Main;
import utils.APIRequests;
import utils.DialogNewCategory;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.ResourceBundle;

public class SettingsController extends SideMenuController {

    @FXML
    private TextField txt_name;

    @FXML
    private ImageView img_logo;

    @FXML
    private ComboBox<String> combo_chef;

    @FXML
    private ComboBox<String> combo_cashier;

    private final File[] selectedImage = {null};

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        txt_name.setText(Main.setting_arr.getJSONObject(0).getString("name"));
        img_logo.setImage(new Image(Main.setting_arr.getJSONObject(0).getString("img_url")));
        combo_cashier.getItems().add(Main.setting_arr.getJSONObject(0).getString("cashier_printer"));
        combo_chef.getItems().add(Main.setting_arr.getJSONObject(0).getString("chef_printer"));

        addAllPrintersList();

        combo_chef.getSelectionModel().select(0);
        combo_cashier.getSelectionModel().select(0);
    }

    private void addAllPrintersList() {
        ObservableSet<Printer> printers = Printer.getAllPrinters();
        for(Printer printer : printers){
            if (printer.getName().equals(Main.setting_arr.getJSONObject(0).getString("chef_printer"))){

            }else {
                combo_chef.getItems().add(printer.getName());
            }
            if (printer.getName().equals(Main.setting_arr.getJSONObject(0).getString("cashier_printer"))){

            }else {
                combo_cashier.getItems().add(printer.getName());
            }

        }
        combo_chef.getItems().add("default");
        combo_cashier.getItems().add("default");
    }


    public void BtnImage(ActionEvent actionEvent) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Resource File");
        selectedImage[0] = fileChooser.showOpenDialog(new Main().getStage());
        if (selectedImage[0] != null){
            img_logo.setImage(new Image(selectedImage[0].toURI().toString()));
            img_logo.setId("idxtest");
            System.out.println(img_logo.getId());
        }
    }

    public void BtnSaveSettings(ActionEvent actionEvent) throws IOException {
        String path = "";
        if (selectedImage[0] != null){
            path = "C:\\xampp\\htdocs\\cafeApi\\images\\" + selectedImage[0].getName();
        }
        File targetPath = new File(path);
        RequestBody formBody = new FormBody.Builder()
                .add("id", "1")
                .add("name", txt_name.getText().toString())
                .add("img_url", targetPath.toURI().toString())
                .add("cashier_printer", combo_cashier.getSelectionModel().getSelectedItem().toString())
                .add("chef_printer", combo_chef.getSelectionModel().getSelectedItem().toString())
                .build();
        if (selectedImage[0] == null){
            formBody = new FormBody.Builder()
                    .add("id", "1")
                    .add("name", txt_name.getText().toString())
                    .add("img_url", "")
                    .add("cashier_printer", combo_cashier.getSelectionModel().getSelectedItem().toString())
                    .add("chef_printer", combo_chef.getSelectionModel().getSelectedItem().toString())
                    .build();
        }
        String url = APIRequests.UPDATE_SETTINGS;
        if(DBHelper.postData(url, formBody)) {
            System.out.println("INSERTED PRODUCT SUCCESS");
            if (selectedImage[0] != null) {
                Files.copy(selectedImage[0].toPath(), targetPath.toPath(), StandardCopyOption.REPLACE_EXISTING);
            }else {

            }
            Main.initSettings();
            // refresh UI
            try {
                FXMLLoader fxmlLoader = new FXMLLoader(DialogNewCategory.class.getResource("../res/fxml/Settings.fxml"));
                Parent root = (Parent) fxmlLoader.load();
                Main.setContent(root);
            } catch (Exception e) {

            }
            utils.utils.AlertMSG("تم الحفظ بنجاح");
        }
    }

}