package controllers;

import database.DBHelper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import models.Department;
import models.GridProductItem;
import models.Product;
import okhttp3.FormBody;
import okhttp3.RequestBody;
import org.json.JSONArray;
import org.json.JSONObject;
import sample.Main;
import utils.APIRequests;
import utils.DialogNewProduct;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class ProductsController extends SideMenuController {

    @FXML
    private ListView<Department> list_dep = new ListView<>();

    private List<Department> list = new ArrayList<>();
    private ObservableList<Department> items;
    private JSONArray deps_list = new JSONArray();
    JSONArray products_list = new JSONArray();

    @FXML
    private GridPane grid = new GridPane();

    private int ADD_PRODUCT = 0;
    private int EDIT_PRODUCT = 1;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

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



    }

    private void getProducts(Department department) {
        String url = APIRequests.GET_PRODUCTS;
        RequestBody formBody = new FormBody.Builder()
                .add("type", department.getId() + "")
                .build();
        try {
            String response = DBHelper.getData(url, formBody);
            System.out.println(response);
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
        if (products_list.length() > 6){
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
                GridProductItem item = new GridProductItem(product.getName(), new Image(product.getImg_url()), " $ " + product.getPrice());
                item.setPadding(new Insets(10,10,10,10));
                grid.add(item.getPane(), col, row);
                col++;
                if (col > 6){
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
                GridProductItem item = new GridProductItem(product.getName(), new Image(product.getImg_url())," $ " + product.getPrice());
                item.setPadding(new Insets(10,10,10,10));
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

    public void BtnAdd(ActionEvent actionEvent) {
        DialogNewProduct.AlertMSG("ادخل اسم المنتج", new Main().getStage(), list, null, ADD_PRODUCT);
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
            DialogNewProduct.AlertMSG("تعديل اسم المنتج", new Main().getStage(), list, product, EDIT_PRODUCT);
        }
    }
}