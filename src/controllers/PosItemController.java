package controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class PosItemController implements Initializable {

    @FXML
    private Label txt_food_name;

    @FXML
    private Label txt_num;


    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public Label getTxt_food_name() {
        return txt_food_name;
    }

    public void setTxt_food_name(String name) {
        this.txt_food_name.setText(name);
    }

    public Label getTxt_num() {
        return txt_num;
    }

    public void setTxt_num(int txt_num) {
        this.txt_num.setText(txt_num + "");
    }
}
