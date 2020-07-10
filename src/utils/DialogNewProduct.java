package utils;

import database.DBHelper;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.NodeOrientation;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import models.Department;
import models.Product;
import okhttp3.FormBody;
import okhttp3.RequestBody;
import org.apache.http.util.TextUtils;
import sample.Main;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.stream.Collectors;

public class DialogNewProduct {

    private static int ADD_PRODUCT = 0;
    private static int EDIT_PRODUCT = 1;

    public static void AlertMSG(String msg, Stage stage, List<Department> deps, Product product, int type){
        Stage dialogStage = new Stage();
        dialogStage.initModality(Modality.WINDOW_MODAL);

        TextField textField = new TextField();
        NumberTextField amount = new NumberTextField();
        NumberTextField price = new NumberTextField();
        price.setPromptText("السعر");
        amount.setPromptText("ادخل الكميه");
        ComboBox<String> comboBox = new ComboBox();
        comboBox.getItems().addAll(deps.stream().map(Department::getName).collect(Collectors.toList()));

        Image img = new Image("/res/images/break.png");
        ImageView imageView = new ImageView(img);
        imageView.setFitWidth(100);
        imageView.setFitHeight(100);
        imageView.setOpacity(0.5);

        // labels
        Label quantity = new Label("الكميه");
        Label cost = new Label("السعر");
        HBox q = new HBox(quantity, amount);
        q.setNodeOrientation(NodeOrientation.RIGHT_TO_LEFT);
        q.setAlignment(Pos.CENTER);
        HBox c = new HBox(cost, price);
        c.setNodeOrientation(NodeOrientation.RIGHT_TO_LEFT);
        c.setAlignment(Pos.CENTER);
        c.setSpacing(5);
        q.setSpacing(5);

        final File[] selectedImage = {null};

        Button btnSave = new Button("حفظ");
        btnSave.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println(textField.getText().toString());
                String path = "";
                if (selectedImage[0] != null){
                    path = "C:\\xampp\\htdocs\\cafeApi\\images\\products\\" + selectedImage[0].getName();
                }

                File targetPath = new File(path);
                if (!validateInputs(textField, amount, comboBox, price)){
                    return;
                }
                RequestBody formBody = new FormBody.Builder()
                        .add("name", textField.getText().toString())
                        .add("amount", amount.getText())
                        .add("type", deps.get(comboBox.getSelectionModel().getSelectedIndex()).getId() + "")
                        .add("img_url", targetPath.toURI().toString())
                        .add("price", price.getText())
                        .build();
                String url = APIRequests.INSERT_PRODUCT;
                if (type == EDIT_PRODUCT){
                    formBody = new FormBody.Builder()
                            .add("id", product.getId() + "")
                            .add("name", textField.getText().toString())
                            .add("amount", amount.getText())
                            .add("type", deps.get(comboBox.getSelectionModel().getSelectedIndex()).getId() + "")
                            .add("img_url", targetPath.toURI().toString())
                            .add("price", price.getText())
                            .build();
                    url = APIRequests.UPDATE_PRODUCT;
                    if (selectedImage[0] == null){
                        formBody = new FormBody.Builder()
                                .add("id", product.getId() + "")
                                .add("name", textField.getText().toString())
                                .add("amount", amount.getText())
                                .add("type", deps.get(comboBox.getSelectionModel().getSelectedIndex()).getId() + "")
                                .add("img_url", "")
                                .add("price", price.getText())
                                .build();
                    }
                }

                try {
                    if(DBHelper.postData(url, formBody)){
                        System.out.println("INSERTED PRODUCT SUCCESS");
                        if (selectedImage[0] != null){
                            Files.copy(selectedImage[0].toPath(), targetPath.toPath(), StandardCopyOption.REPLACE_EXISTING);
                        }
                        // refresh UI
                        try {
                            FXMLLoader fxmlLoader = new FXMLLoader(DialogNewCategory.class.getResource("../res/fxml/Products.fxml"));
                            Parent root = (Parent) fxmlLoader.load();
                            Main.setContent(root);
                        } catch (Exception e) {

                        }
                        dialogStage.hide();
                        if (type == EDIT_PRODUCT){
                            utils.AlertMSG("تم التعديل بنجاح");
                        }else {
                            utils.AlertMSG("تم التسجيل بنجاح");
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        Button btnImg = new Button("اختر صوره");
        btnImg.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                FileChooser fileChooser = new FileChooser();
                fileChooser.setTitle("Open Resource File");
                selectedImage[0] = fileChooser.showOpenDialog(stage);
                if (selectedImage[0] != null){
                    imageView.setImage(new Image(selectedImage[0].toURI().toString()));
                }
                imageView.setId("idxtest");
                imageView.setOpacity(1);
            }
        });

        Button btnDel = new Button("حذف");
        btnDel.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                try {
                    deleteProduct(product, dialogStage);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });


        HBox hBox = new HBox();
        if (type == EDIT_PRODUCT){
            hBox.getChildren().addAll(btnImg, btnSave, btnDel);
            btnSave.setText("حفظ التعديل");
            textField.setText(product.getName());
            amount.setText(product.getAmount() + "");
            price.setText(product.getPrice() + "");
            imageView.setImage(new Image(product.getImg_url()));
        }else {
            hBox.getChildren().addAll(btnImg, btnSave);
        }

        hBox.setSpacing(10);
        hBox.setAlignment(Pos.CENTER);
        VBox vbox = new VBox(imageView, new Text(msg), textField, new Text("اختر القسم"), comboBox, q, c, hBox);
        vbox.setSpacing(10);
        vbox.setAlignment(Pos.CENTER);
        vbox.setPadding(new Insets(30));
        dialogStage.setScene(new Scene(vbox));
        dialogStage.show();
    }

    private static boolean validateInputs(TextField textField, NumberTextField amount, ComboBox<String> comboBox, NumberTextField price) {
        if (TextUtils.isEmpty(textField.getText().toString())){
            textField.setPromptText("يجب ادخال الاسم");
            textField.setStyle("-fx-padding: 1,1,1,1;-fx-border-color: #f7000c;-fx-border-width: 1;");
            return false;
        }else {
            textField.setStyle(null);
        }

        if (TextUtils.isEmpty(amount.getText().toString())){
            amount.setPromptText("يجب ادخال الكميه");
            amount.setStyle("-fx-padding: 1,1,1,1;-fx-border-color: #f7000c;-fx-border-width: 1;");
            return false;
        }else {
            amount.setStyle(null);
        }

        if (TextUtils.isEmpty(price.getText().toString())){
            price.setPromptText("يجب ادخال الكميه");
            price.setStyle("-fx-padding: 1,1,1,1;-fx-border-color: #f7000c;-fx-border-width: 1;");
            return false;
        }else {
            price.setStyle(null);
        }

        if (TextUtils.isEmpty(comboBox.getSelectionModel().getSelectedItem())){
            comboBox.setStyle("-fx-padding: 1,1,1,1;-fx-border-color: #f7000c;-fx-border-width: 1;");
            return false;
        }else {
            comboBox.setStyle(null);
        }
        return true;
    }

    private static void deleteProduct(Product product, Stage dialogStage) throws IOException {
        String url = APIRequests.DELETE_ITEM;
        RequestBody formBody = new FormBody.Builder()
                .add("id", product.getId() + "")
                .add("table", "products")
                .build();
        if(DBHelper.postData(url, formBody)){
            utils.AlertMSG("تم الحذف بنجاح");
            // refresh UI
            try {
                FXMLLoader fxmlLoader = new FXMLLoader(DialogNewCategory.class.getResource("../res/fxml/Products.fxml"));
                Parent root = (Parent) fxmlLoader.load();
                Main.setContent(root);
            } catch (Exception e) {

            }
            dialogStage.hide();

        }else{
            utils.AlertMSG("لم يتم الحذف ");
        }
    }

}