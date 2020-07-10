package controllers;

import database.CounterHelper;
import database.DBHelper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;

import models.*;
import okhttp3.FormBody;
import okhttp3.RequestBody;
import org.json.JSONArray;
import org.json.JSONObject;
import sample.Main;
import utils.APIRequests;
import utils.DialogNewBill;
import utils.DialogNewBillCategory;

import java.io.IOException;
import java.net.URL;
import java.sql.Timestamp;
import java.util.*;

public class PosController extends SideMenuController {

    @FXML
    private GridPane grid = new GridPane();
    @FXML
    private ListView<Department> list_dep = new ListView<>();

    private List<Department> list = new ArrayList<>();
    private ObservableList<Department> items;
    private JSONArray deps_list = new JSONArray();
    private JSONArray products_list = new JSONArray();

    @FXML
    private TableView<Bill> table = new TableView<>();

    @FXML
    TableColumn<Bill, String> name;
    @FXML
    TableColumn<Bill, String> price;
    @FXML
    TableColumn<Bill, String> amount;

    @FXML
    private Label total_price;

    @FXML
    private Label bill_num;

    @FXML
    private TextField txt_tax_bill;

    @FXML
    private TextField txt_service;

    @FXML
    private TextField txt_discount;

    @FXML
    private CheckBox check_table;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // setting bill number
        setBillNum();
        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        amount.setCellValueFactory(new PropertyValueFactory<>("amount"));
        price.setCellValueFactory(new PropertyValueFactory<>("price"));
        // get categories
        getAllCategories();

        items = FXCollections.observableArrayList (list);
        list_dep.setItems(items);

        list_dep.setCellFactory(param -> new ListCell<Department>() {
            @Override
            public void updateItem(Department item, boolean empty) {
                ImageView imageView = new ImageView();
                imageView.setFitHeight(100);
                imageView.setFitWidth(100);
                super.updateItem(item, empty);
                if (empty) {
                    setText(null);
                    setGraphic(null);
                } else {
                    setText(item.getName());
                    System.out.println(item.getImg_url());
                    imageView.setImage(new Image(item.getImg_url()));
                    setGraphic(imageView);
                }
            }
        });

        list_dep.setOnMouseClicked(event -> {
            int index = list_dep.getSelectionModel().getSelectedIndex();
            System.out.println(index);
            // get product of selected category
            getProducts(list.get(index));
        });

        table.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                int index = table.getSelectionModel().getSelectedIndex();
                if (event.getClickCount() == 2) {
                    table.getItems().remove(index);
                    total_price.setText(getTotalPrice(table));
                }
            }
        });

    }

    private void getProducts(Department department) {
        String url = APIRequests.GET_PRODUCTS;
        RequestBody formBody = new FormBody.Builder()
                .add("type", department.getId() + "")
                .build();
        try {
            String response = DBHelper.getData(url, formBody);
            JSONObject products = new JSONObject(response.toString());
            int success = products.getInt("success");
            if (success == 1){
                products_list = products.getJSONArray("products");
            }else {
                products_list = new JSONArray();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        grid.getChildren().clear();
        grid.setVgap(20);
        grid.setHgap(20);
        if (products_list.length() > 3){
            int row = 0;
            int col = 0;
            for (int i = 0; i < products_list.length(); i++){
                JSONObject o = products_list.getJSONObject(i);
                Product product = new Product(
                        o.getInt("id"),
                        o.getString("name"),
                        Float.parseFloat(o.getString("price")),
                        o.getInt("type"),
                        o.getInt("amount"),
                        o.getString("img_url")
                );
                GridPosItem item = new GridPosItem(product.getId(),
                        product.getName(),
                        product.getAmount(),
                        new Image(product.getImg_url()),
                        "" + product.getPrice(),
                        table,
                        total_price,
                        txt_discount,
                        txt_tax_bill,
                        txt_service,
                        product);
                grid.add(item.getPane(), col, row);
                col++;
                if (col > 3){
                    col = 0;
                    row++;
                }
            }
        }else {
            for (int i = 0; i < products_list.length(); i++){
                JSONObject o = products_list.getJSONObject(i);
                Product product = new Product(
                        o.getInt("id"),
                        o.getString("name"),
                        Float.parseFloat(o.getString("price")),
                        o.getInt("type"),
                        o.getInt("amount"),
                        o.getString("img_url")
                );
                GridPosItem item = new GridPosItem(product.getId(),
                        product.getName(),
                        product.getAmount(),
                        new Image(product.getImg_url()),
                        "" + product.getPrice(),
                        table,
                        total_price,
                        txt_discount,
                        txt_tax_bill,
                        txt_service,
                        product);
                //item.setPadding(new Insets(10,10,10,10));
                grid.add(item.getPane(),i, 0);
            }
        }

    }

    private void getAllCategories() {
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
    }

    public void gridClick(MouseEvent event) {
        Node clickedNode = event.getPickResult().getIntersectedNode();
        if (clickedNode != grid) {
            // click on descendant node
            Node parent = clickedNode.getParent();
            while (parent != grid) {
                clickedNode = parent;
                parent = clickedNode.getParent();
            }
            Integer colIndex = GridPane.getColumnIndex(clickedNode);
            Integer rowIndex = GridPane.getRowIndex(clickedNode);
            System.out.println("Mouse clicked cell: " + colIndex + " And: " + rowIndex);
            JSONObject o = products_list.getJSONObject(((7*rowIndex-1) + colIndex+1));
            Product product = new Product(
                    o.getInt("id"),
                    o.getString("name"),
                    Float.parseFloat(o.getString("price")),
                    o.getInt("type"),
                    o.getInt("amount"),
                    o.getString("img_url")
            );
            System.out.println(product.getName());
        }
    }

    private String getTotalPrice(TableView<Bill> table) {
        float total = 0;
        for (int i = 0; i < table.getItems().size(); i++){
            total += table.getItems().get(i).getPrice();
        }
        float discount  = 0;
        float tax = 0;
        float service = 0;

        if (txt_discount.getText().equals("")){
            discount = 0;
        }else {
            discount = Float.parseFloat(txt_discount.getText().toString());
        }
        if (txt_service.getText().equals("")){
            service = 0;
        }else {
            service = Float.parseFloat(txt_service.getText().toString());
        }
        if (txt_tax_bill.getText().equals("")){
            tax = 0;
        }else {
            tax = Float.parseFloat(txt_tax_bill.getText().toString());
        }

        total = total - (total * (discount/100));
        total = total + (total * (service/100));

        return total + "";
    }


    public void BtnSaveBill(ActionEvent actionEvent) throws IOException {
        if (!table.getItems().isEmpty()){
            saveBillToDatabase();
        }
    }

    public void BtnCancelBill(ActionEvent actionEvent) {
        table.getItems().clear();
        total_price.setText("0");
    }

    private void saveBillToDatabase() throws IOException {
        float totalPrice = Float.parseFloat(getTotalPrice(table));
        String bill_products = "";
        Map<String, String> map = new HashMap<>();
        List<String> printers = new ArrayList<>();
        for (int i = 0; i < table.getItems().size(); i++) {
            int categ = table.getItems().get(i).getProduct().getType();
            String depName = "";
            printers.clear();
            for (int x = 0 ; x < list.size(); x++){
                if (list.get(x).getId() == categ){
                    depName = list.get(x).getName();
                }
                printers.add(list.get(x).getPrinter());
            }

            if (map.containsKey(depName)){
                System.out.println("KEY1 = " + depName);
                System.out.println("VAL1 = " + map.get(depName));
                map.put(depName, map.get(depName) + table.getItems().get(i).getName() + " x " + table.getItems().get(i).getAmount() + "\n");
            }else {
                System.out.println("KEY = " + depName);
                System.out.println("VAL = " + map.get(depName));
                map.put(depName,table.getItems().get(i).getName() + " x " + table.getItems().get(i).getAmount() + "\n");
            }
            bill_products += table.getItems().get(i).getName() + " x " + table.getItems().get(i).getAmount() + "\n";
        }
        System.out.println("AAAAAAA" + map.toString());
        System.out.println(totalPrice);
        System.out.println(bill_products);
        System.out.println(bill_num.getText());

        //////////////////////////
        float discount  = 0;
        float tax = 0;
        float service = 0;

        if (txt_discount.getText().equals("")){
            discount = 0;
        }else {
            discount = Float.parseFloat(txt_discount.getText().toString());
        }
        if (txt_service.getText().equals("")){
            service = 0;
        }else {
            service = Float.parseFloat(txt_service.getText().toString());
        }
        if (txt_tax_bill.getText().equals("")){
            tax = 0;
        }else {
            tax = Float.parseFloat(txt_tax_bill.getText().toString());
        }
        ////////////////////


        setBillNum();

        RequestBody formBody = new FormBody.Builder()
                .add("bill_num", bill_num.getText().toString())
                .add("price", totalPrice + "")
                .add("products", bill_products)
                .add("date_time", getDate() + "")
                .add("discount", discount + "")
                .add("service_tax", service + "")
                .add("bill_tax", tax + "")
                .build();
        String url = APIRequests.INSERT_BILL;
        if (DBHelper.postData(url, formBody)) {
            // decrement amounts
            for (int i = 0; i < table.getItems().size(); i++){
                Bill bill = table.getItems().get(i);
                RequestBody formBody_Update = new FormBody.Builder()
                        .add("id", bill.getId() + "")
                        .add("amount", bill.getAmount() + "")
                        .build();
                String url_update = APIRequests.UPDATE_PRODUCT_AMOUNT;
                if (DBHelper.postData(url_update, formBody_Update)) {
                    System.out.println("UPDATE");
                }

            }

            // increment bill counter
            CounterHelper.updateCount(Integer.parseInt(bill_num.getText().toString()));

            setBillNum();
            Order order = new Order(
                    0, Integer.parseInt(bill_num.getText().toString())-1,totalPrice, bill_products, getDate().toString().substring(0, 10),
                discount, tax, service);
            //printing bills
            DialogNewBill.AlertMSG("طباعه فاتوره", new Main().getStage(), order);
            int z = 0;
            for (String key : map.keySet()){
                Order orderdep = new Order(
                        0, Integer.parseInt(bill_num.getText().toString())-1,totalPrice, key+"\n"+map.get(key), getDate().toString().substring(0, 10),
                        discount, tax, service);
                //printing bills
                DialogNewBillCategory.AlertMSG("طباعه فاتوره", new Main().getStage(), orderdep, printers.get(z));
                z++;
            }

            //refresh UI
            try {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../res/fxml/Pos.fxml"));
                Parent root = (Parent) fxmlLoader.load();
                Main.setContent(root);

            } catch (Exception e) {

            }
            utils.utils.AlertMSG("تم الحفظ بنجاح");
            if (check_table.isSelected()){
                insertTable(0);
            }
            table.getItems().clear();
            total_price.setText("0");
        }

    }

    private void setBillNum() {
        try {
            // TODO
            CounterHelper.getCount();
        } catch (IOException ex) {

        }
        bill_num.setText(CounterHelper.getCounter() + 1 + "");
    }

    private static Timestamp getDate() {
        java.sql.Timestamp date = new java.sql.Timestamp(new java.util.Date().getTime());
        return date;
    }

    public void BtnCalc(ActionEvent actionEvent) {
        total_price.setText(getTotalPrice(table));
    }

    private void insertTable(int type) throws IOException {
        String url = APIRequests.INSERT_TABLE;
        RequestBody formBody = new FormBody.Builder()
                .add("table_num", "")
                .add("chair_num", "1")
                .add("price", getTotalPrice(table))
                .add("bill_num", Integer.parseInt(bill_num.getText().toString()) - 1 + "")
                .add("state", "0")
                .build();

        DBHelper.postData(url, formBody);
        // refresh UI
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../res/fxml/Tables.fxml"));
            Parent root = (Parent) fxmlLoader.load();
            Main.setContent(root);
        } catch (Exception e) {

        }
    }
}