package controllers;

import database.DBHelper;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import models.Table;
import org.json.JSONArray;
import org.json.JSONObject;
import utils.APIRequests;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class TablesController extends SideMenuController {

    @FXML
    private GridPane hBox;

    private int x = 0;
    private int y = 0;

    private List<Table> list = new ArrayList<>();
    private ObservableList<Table> items;
    private JSONArray tables_list = new JSONArray();
    private int EDIT_TABLE = 1;
    private int ADD_TABLE = 0;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        getAllTables();
    }

    private void getAllTables() {
        String url = APIRequests.GET_TABLES;
        try {
            String response = DBHelper.getData(url);
            JSONObject table = new JSONObject(response.toString());
            int success = table.getInt("success");
            if (success == 1){
                tables_list = table.getJSONArray("tables");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        for (int i = 0; i < tables_list.length(); i++){
            JSONObject o = tables_list.getJSONObject(i);

            Table table = new Table(
                    o.getInt("id"),
                    Float.parseFloat(o.getString("price")),
                    Integer.parseInt(o.getString("chair_num")),
                    Integer.parseInt(o.getString("table_num")),
                    Integer.parseInt(o.getString("bill_num")),
                    Integer.parseInt(o.getString("state"))
            );

            list.add(table);
            Pane pane = null;
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("../res/fxml/item_table.fxml"));
                pane = loader.load();
                ItemTableController itemTableController = loader.getController();
                itemTableController.setVals(table, EDIT_TABLE);
            } catch (IOException e) {
                e.printStackTrace();
            }
            hBox.add(pane,x,y);
            if (x >= 2){
                y++;
                x = 0;
            }else {
                x++;
            }
        }
    }

    public void BtnAdd(ActionEvent actionEvent) {
        System.out.println("ADD");
        Pane pane = null;
        try {
            pane = FXMLLoader.load(getClass().getResource("../res/fxml/item_table.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        hBox.add(pane,x,y);
        if (x >= 2){
            y++;
            x = 0;
        }else {
            x++;
        }
    }


}