package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import models.User;
import sample.Main;
import utils.genVars;

import java.net.URL;
import java.util.ResourceBundle;

public class SideMenuController implements Initializable {

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void BtnDepartments(ActionEvent actionEvent) {
        System.out.println("Departments");
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../res/fxml/Departments.fxml"));
            Parent root = (Parent) fxmlLoader.load();
            Main.setContent(root);

        } catch (Exception e) {

        }
    }

    public void BtnHome(ActionEvent actionEvent) {
        System.out.println("Home");
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../res/fxml/Main.fxml"));
            Parent root = (Parent) fxmlLoader.load();
            Main.setContent(root);

        } catch (Exception e) {

        }
    }

    public void BtnPos(ActionEvent actionEvent) {
        System.out.println("POS");
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../res/fxml/Pos.fxml"));
            Parent root = (Parent) fxmlLoader.load();
            Main.setContent(root);

        } catch (Exception e) {

        }
    }

    public void BtnUsers(ActionEvent actionEvent) {
        System.out.println("Users");
        User user = genVars.user;
        if (user.getType() == 0){
            try {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../res/fxml/Users.fxml"));
                Parent root = (Parent) fxmlLoader.load();
                Main.setContent(root);

            } catch (Exception e) {

            }
        }else {
            utils.utils.AlertMSG("غير مسموح !");
        }
    }

    public void BtnTables(ActionEvent actionEvent) {
        System.out.println("Tables");
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../res/fxml/Tables.fxml"));
            Parent root = (Parent) fxmlLoader.load();
            Main.setContent(root);

        } catch (Exception e) {

        }
    }


    public void BtnReports(ActionEvent actionEvent) {
        System.out.println("ReportsController");
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../res/fxml/Reports.fxml"));
            Parent root = (Parent) fxmlLoader.load();
            Main.setContent(root);

        } catch (Exception e) {

        }
    }

    public void BtnProducts(ActionEvent actionEvent) {
        System.out.println("Products");
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../res/fxml/Products.fxml"));
            Parent root = (Parent) fxmlLoader.load();
            Main.setContent(root);

        } catch (Exception e) {

        }
    }

    public void BtnSettings(ActionEvent actionEvent) {
        System.out.println("HI ORDERS");
        User user = genVars.user;
        if (user.getType() == 0){
            try {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../res/fxml/Settings.fxml"));
                Parent root = (Parent) fxmlLoader.load();
                Main.setContent(root);

            } catch (Exception e) {

            }
        }else {
            utils.utils.AlertMSG("غير مسموح !");
        }
    }
}