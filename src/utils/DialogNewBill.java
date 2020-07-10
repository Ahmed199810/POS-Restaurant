package utils;

import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.NodeOrientation;
import javafx.geometry.Pos;
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
import sample.Main;

public class DialogNewBill {

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
        Button btnPrintChef = new Button("طباعه المطلخ");
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
        vbox.setPadding(new Insets(10));
        vbox.setNodeOrientation(NodeOrientation.RIGHT_TO_LEFT);
        vbox.setStyle("-fx-background-color: #fff;");

        VBox box = new VBox(vbox, btnPrintCashier, btnPrintChef);
        box.setSpacing(5);
        box.setAlignment(Pos.CENTER);
        box.setNodeOrientation(NodeOrientation.RIGHT_TO_LEFT);
        box.setPadding(new Insets(10));
        box.setStyle("-fx-background-color: #fff;");


        btnPrintCashier.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                box.getChildren().removeAll(btnPrintCashier,btnPrintChef);
                String cashier_printer = Main.setting_arr.getJSONObject(0).getString("cashier_printer");
                utils.PrinterDialog(box, "تم الارسال الي الطباعه", cashier_printer);
            }
        });

        btnPrintChef.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                box.getChildren().removeAll(btnPrintCashier,btnPrintChef);
                String chef_printer = Main.setting_arr.getJSONObject(0).getString("chef_printer");
                utils.PrinterDialog(box, "تم الارسال الي الطباعه", chef_printer);
            }
        });

        dialogStage.setTitle(msg);
        dialogStage.setScene(new Scene(box));
        dialogStage.setMaxWidth(300);
        dialogStage.show();
    }


}