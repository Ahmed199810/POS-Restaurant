package controllers;

import database.DBHelper;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import models.Order;
import okhttp3.FormBody;
import okhttp3.RequestBody;
import org.json.JSONArray;
import org.json.JSONObject;
import sample.Main;
import utils.APIRequests;
import utils.DialogBillEdit;

import java.io.IOException;
import java.net.URL;
import java.sql.Timestamp;
import java.util.ResourceBundle;

public class ReportsController extends SideMenuController {

    @FXML
    private TableView<Order> table = new TableView<>();

    @FXML
    TableColumn<Order, String> bill_num;
    @FXML
    TableColumn<Order, String> price;
    @FXML
    TableColumn<Order, String> products;
    @FXML
    TableColumn<Order, String> date_time;
    @FXML
    TableColumn<Order, String> service;
    @FXML
    TableColumn<Order, String> bill_tax;
    @FXML
    TableColumn<Order, String> discount;

    @FXML
    private DatePicker start_date;

    @FXML
    private DatePicker end_date;

    @FXML
    private TextField txt_search;

    @FXML
    private Label total_sells;

    private JSONArray bills = new JSONArray();

    private int REPORT_PERIOD = 0;
    private int REPORT_TODAY = 1;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        bill_num.setCellValueFactory(new PropertyValueFactory<>("bill_num"));
        date_time.setCellValueFactory(new PropertyValueFactory<>("date_time"));
        products.setCellValueFactory(new PropertyValueFactory<>("products"));
        price.setCellValueFactory(new PropertyValueFactory<>("price"));
        service.setCellValueFactory(new PropertyValueFactory<>("service"));
        discount.setCellValueFactory(new PropertyValueFactory<>("discount"));
        bill_tax.setCellValueFactory(new PropertyValueFactory<>("bill_tax"));


        table.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (event.getClickCount() == 2){
                    int index = table.getSelectionModel().getSelectedIndex();
                    Order order = table.getItems().get(index);
                    System.out.println(order.getId());
                    System.out.println(order.getProducts());
                    DialogBillEdit.AlertMSG("تعديل فاتوره",
                            new Main().getStage(),
                            order);
                }
            }
        });

    }

    public void BtnViewReports(ActionEvent actionEvent) {
        if (start_date.getValue() != null && end_date.getValue() != null){
            getReports(REPORT_PERIOD);
        }
    }

    private void getReports(int type) {
        table.getItems().clear();
        String url = APIRequests.GET_BILLS;
        System.out.println(getDate().toString().substring(0, 10));
        RequestBody formBody = new FormBody.Builder()
                .add("start_date", getDate().toString().substring(0, 10))
                .add("end_date", getDate().toString().substring(0, 10))
                .build();
        if (type == REPORT_PERIOD){
            formBody = new FormBody.Builder()
                    .add("start_date", start_date.getValue().toString())
                    .add("end_date", end_date.getValue().toString())
                    .build();
        }
        try {
            String response = DBHelper.getData(url, formBody);
            JSONObject products = new JSONObject(response.toString());
            int success = products.getInt("success");
            if (success == 1){
                bills = products.getJSONArray("bills");
                for (int i = 0; i < bills.length(); i++){
                    JSONObject o = bills.getJSONObject(i);
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
                    table.getItems().add(order);
                }

            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        total_sells.setText(getTotalPrice(table));

    }


    public void BtnPrintReport(ActionEvent actionEvent) {
        if (start_date.getValue() != null && end_date.getValue() != null){
            Node reportForm = utils.utils.ReportForm("تقرير مبيعات", new Image(Main.setting_arr.getJSONObject(0).getString("img_url")),
                    getDate()+"",
                    start_date.getValue().toString(),
                    end_date.getValue().toString(),
                    total_sells.getText(), table.getItems().size()+""
            );
            utils.utils.PrinterDialog(reportForm, "تم الارسال الي الطابعه", "default");
        }

    }


    public void BtnSearch(ActionEvent actionEvent) throws IOException {
        RequestBody formBody = new FormBody.Builder()
                .add("word", txt_search.getText().toString())
                .build();
        String url_search = APIRequests.SEARCH_BILLS;
        String response = DBHelper.searchItem(url_search, formBody);
        table.getItems().clear();
        if(response != null){
            JSONObject devices = new JSONObject(response.toString());
            bills = devices.getJSONArray("bills");
            for (int i = 0; i < bills.length(); i++){
                JSONObject o = bills.getJSONObject(i);
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
                table.getItems().add(order);
            }
        }else{

        }
    }

    private String getTotalPrice(TableView<Order> table) {
        float total = 0;
        for (int i = 0; i < table.getItems().size(); i++){
            total += table.getItems().get(i).getPrice();
        }
        return total + " L.E ";
    }

    private static Timestamp getDate() {
        java.sql.Timestamp date = new java.sql.Timestamp(new java.util.Date().getTime());
        return date;
    }

    public void BtnTodayReports(ActionEvent actionEvent) {
        getReports(REPORT_TODAY);
    }

}