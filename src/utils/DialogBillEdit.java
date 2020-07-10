package utils;

import database.DBHelper;

import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.NodeOrientation;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;
import models.Order;
import models.User;
import okhttp3.FormBody;
import okhttp3.RequestBody;
import sample.Main;

import java.io.IOException;

public class DialogBillEdit {

    public static void AlertMSG(String msg, Stage stage, Order order){
        Stage dialogStage = new Stage();
        dialogStage.initModality(Modality.WINDOW_MODAL);

        ImageView logo = new ImageView(new Image(Main.setting_arr.getJSONObject(0).getString("img_url")));
        logo.setFitWidth(70);
        logo.setFitHeight(70);
        Label shop_name = new Label(Main.setting_arr.getJSONObject(0).getString("name"));
        shop_name.setFont(new Font("Arabic Typesetting", 22));
        shop_name.setAlignment(Pos.CENTER);
        shop_name.setNodeOrientation(NodeOrientation.RIGHT_TO_LEFT);

        Label bill_num = new Label(order.getBill_num() + "");
        Label products = new Label(order.getProducts());
        products.setNodeOrientation(NodeOrientation.RIGHT_TO_LEFT);
        products.setAlignment(Pos.CENTER);
        products.setFont(new Font("Arabic Typesetting", 20));
        Label price = new Label(order.getPrice() + "");

        Button btnPrintCashier = new Button("طباعه كاشير");
        Button btnPrintChef = new Button("طباعه مطبخ");
        Button btnDel = new Button("حذف الفاتوره");
        btnDel.setStyle("-fx-background-color: #e0092d;-fx-text-fill: #fff;");
        btnPrintCashier.setStyle("-fx-background-color: #15a321;-fx-text-fill: #fff;");
        btnPrintChef.setStyle("-fx-background-color: #15a321;-fx-text-fill: #fff;");

        //////////////////////////////
        HBox discountBox = new HBox(new Label(" خصم "), new Label(order.getDiscount()+""), new Label(" % "));
        discountBox.setNodeOrientation(NodeOrientation.RIGHT_TO_LEFT);
        discountBox.setAlignment(Pos.CENTER);
        discountBox.setSpacing(5);

        HBox billTaxBox = new HBox(new Label(" فاتوره ضريبيه "), new Label(order.getBill_tax()+""), new Label(" % "));
        billTaxBox.setNodeOrientation(NodeOrientation.RIGHT_TO_LEFT);
        billTaxBox.setAlignment(Pos.CENTER);
        billTaxBox.setSpacing(5);

        HBox serviceTaxBox = new HBox(new Label(" خدمه "), new Label(order.getService()+""), new Label(" % "));
        serviceTaxBox.setNodeOrientation(NodeOrientation.RIGHT_TO_LEFT);
        serviceTaxBox.setAlignment(Pos.CENTER);
        serviceTaxBox.setSpacing(5);
        ///////////////////////////////

        HBox pBox = new HBox(price, new Label(" L.E "));
        pBox.setNodeOrientation(NodeOrientation.RIGHT_TO_LEFT);
        pBox.setAlignment(Pos.CENTER);
        pBox.setSpacing(5);

        HBox numBox = new HBox(new Label(" فاتوره رقم "), bill_num);
        numBox.setNodeOrientation(NodeOrientation.RIGHT_TO_LEFT);
        numBox.setAlignment(Pos.CENTER);
        numBox.setSpacing(5);

        VBox vbox = new VBox(logo,
                shop_name,
                numBox,
                new Label(order.getDate_time()),
                products,
                discountBox,
                billTaxBox,
                serviceTaxBox,
                pBox);
        vbox.setSpacing(5);
        vbox.setAlignment(Pos.CENTER);
        vbox.setPadding(new Insets(10,10,10,10));
        vbox.setNodeOrientation(NodeOrientation.RIGHT_TO_LEFT);

        VBox box = new VBox(vbox, btnPrintCashier, btnPrintChef, btnDel);
        box.setSpacing(5);
        box.setAlignment(Pos.CENTER);
        box.setNodeOrientation(NodeOrientation.RIGHT_TO_LEFT);
        box.setPadding(new Insets(10,10,10,10));
        box.setStyle("-fx-background-color: #fff;");
        btnPrintCashier.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                box.getChildren().removeAll(btnDel, btnPrintCashier, btnPrintChef);
                String cashier_printer = Main.setting_arr.getJSONObject(0).getString("cashier_printer");
                utils.PrinterDialog(box, "تم الارسال الي الطباعه", cashier_printer);
            }
        });

        btnPrintChef.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                box.getChildren().removeAll(btnDel, btnPrintCashier, btnPrintChef);
                String chef_printer = Main.setting_arr.getJSONObject(0).getString("chef_printer");
                utils.PrinterDialog(box, "تم الارسال الي الطباعه", chef_printer);
            }
        });


        btnDel.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                try {
                    deleteOrder(order, dialogStage);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });


        dialogStage.setTitle(msg);
        dialogStage.setScene(new Scene(box));
        dialogStage.setMaxWidth(300);

        User user = genVars.user;
        if (user.getType() == 0){

        }else {
            btnDel.setDisable(true);
        }

        dialogStage.show();
    }

    private static void deleteOrder(Order order, Stage dialogStage) throws IOException {
        String url = APIRequests.DELETE_ITEM;
        RequestBody formBody = new FormBody.Builder()
                .add("id", order.getId() + "")
                .add("table", "bills")
                .build();
        if(DBHelper.postData(url, formBody)){
            utils.AlertMSG("تم الحذف بنجاح");
            // refresh UI
            try {
                FXMLLoader fxmlLoader = new FXMLLoader(DialogNewCategory.class.getResource("../res/fxml/Reports.fxml"));
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