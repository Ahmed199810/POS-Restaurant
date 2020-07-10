package controllers;

import database.DBHelper;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import models.Department;
import models.Order;
import models.Product;
import models.Table;
import okhttp3.FormBody;
import okhttp3.RequestBody;
import org.json.JSONArray;
import org.json.JSONObject;
import sample.Main;
import utils.APIRequests;
import utils.NumberTextField;
import utils.utils;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import utils.DialogNewBill;

public class ItemTableController implements Initializable {

    @FXML
    private Label txt_num;

    @FXML
    private Label txt_id;

    @FXML
    private NumberTextField txt_price;

    @FXML
    private NumberTextField txt_bill_num;

    private Table table;

    @FXML
    private Button btn_edit;

    @FXML
    private Button btn_inc;

    @FXML
    private Button btn_dec;
    @FXML
    private Button btn_reserve;
    @FXML
    private Button btn_view;

    private int EDIT_TABLE = 1;
    private int ADD_TABLE = 0;
    private int type = ADD_TABLE;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        txt_id.setVisible(false);
        disableViews(false);
        btn_edit.setDisable(true);
        btn_view.setDisable(true);
    }

    public void BtnEdit(ActionEvent actionEvent) {
        disableViews(false);
        btn_edit.setDisable(true);
        btn_view.setDisable(true);
    }

    public void BtnReserve(ActionEvent actionEvent) throws IOException {
        insertTable(type);
    }

    private void insertTable(int type) throws IOException {
        String url = APIRequests.INSERT_TABLE;
        RequestBody formBody = new FormBody.Builder()
                .add("table_num", txt_id.getText().toString())
                .add("chair_num", txt_num.getText().toString())
                .add("price", txt_price.getText().toString())
                .add("bill_num", txt_bill_num.getText().toString())
                .add("state", "0")
                .build();

        if (type == EDIT_TABLE){
            formBody = new FormBody.Builder()
                    .add("id", table.getId() + "")
                    .add("table_num", txt_id.getText().toString())
                    .add("chair_num", txt_num.getText().toString())
                    .add("price", txt_price.getText().toString())
                    .add("bill_num", txt_bill_num.getText().toString())
                    .add("state", "0")
                    .build();
            url = APIRequests.UPDATE_TABLE;
        }

        DBHelper.postData(url, formBody);
        // refresh UI
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../res/fxml/Tables.fxml"));
            Parent root = (Parent) fxmlLoader.load();
            Main.setContent(root);
        } catch (Exception e) {

        }
    }


    public void BtnAdd(ActionEvent actionEvent) {
        int n = Integer.parseInt(txt_num.getText().toString());
        txt_num.setText(n + 1 + "");
    }

    public void BtnDel(ActionEvent actionEvent) {
        int n = Integer.parseInt(txt_num.getText().toString());
        if (n == 0) {
            txt_num.setText(0 + "");
        } else {
            txt_num.setText(n - 1 + "");
        }
    }

    public void BtnDelTable(ActionEvent actionEvent) throws IOException {
        if (type == EDIT_TABLE){
            deleteTable(table);
        }else {

        }
    }

    public void setVals(Table table, int type){
        disableViews(true);
        btn_edit.setDisable(false);
        btn_view.setDisable(false);
        txt_id.setText(table.getTable_num() + "");
        txt_price.setText(table.getPrice() + "");
        txt_bill_num.setText(table.getBill_num() + "");
        txt_num.setText(table.getChair_nums() + "");
        this.table = table;
        this.type = type;
    }


    private static void deleteTable(Table table) throws IOException {
        String url = APIRequests.DELETE_ITEM;
        RequestBody formBody = new FormBody.Builder()
                .add("id", table.getId() + "")
                .add("table", "cafe_tables")
                .build();
        if(DBHelper.postData(url, formBody)){
            utils.AlertMSG("تم الحذف بنجاح");
            // refresh UI
            try {
                FXMLLoader fxmlLoader = new FXMLLoader(ItemTableController.class.getResource("../res/fxml/Tables.fxml"));
                Parent root = (Parent) fxmlLoader.load();
                Main.setContent(root);
            } catch (Exception e) {

            }
        }else{
            utils.AlertMSG("لم يتم الحذف ");
        }
    }
    private void disableViews(boolean b){
        txt_price.setEditable(!b);
        txt_bill_num.setEditable(!b);
        btn_dec.setDisable(b);
        btn_inc.setDisable(b);
        btn_reserve.setDisable(b);
    }

    public void BtnView(ActionEvent actionEvent) throws IOException {
        int bill_id = table.getBill_num();
        /*Order order = new Order(
                bill_id, bill_id,table.getPrice(), bill_products, getDate().toString().substring(0, 10),
                discount, tax, service);
        */
        RequestBody formBody = new FormBody.Builder()
                .add("word", txt_bill_num.getText().toString())
                .build();
        String url_search = APIRequests.SEARCH_BILLS;
        String response = DBHelper.searchItem(url_search, formBody);
        if(response != null){
            JSONObject devices = new JSONObject(response.toString());
            JSONArray bills = devices.getJSONArray("bills");
            JSONObject o = bills.getJSONObject(0);
            Order order = new Order(
                    o.getInt("id"),
                    o.getInt("bill_num"),
                    Float.parseFloat(o.getString("price")),
                    o.getString("products"),
                    o.getString("date_time"),
                    Float.parseFloat(o.getString("discount")),
                    Float.parseFloat(o.getString("bill_tax")),
                    Float.parseFloat(o.getString("service_tax"))
            );
            DialogNewBill.AlertMSG("طباعه فاتوره", new Main().getStage(), order);
        }else{

        }


    }
}