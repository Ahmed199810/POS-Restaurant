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

public class DialogNewBillCategory {

    public static void AlertMSG(String msg, Stage stage, Order order, String printer){
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

        Button btnPrintChef = new Button("طباعه المطلخ");
        btnPrintChef.setStyle("-fx-background-color: #15a321;-fx-text-fill: #fff;");

        HBox numBox = new HBox(new Label(" فاتوره رقم "), bill_num);
        numBox.setNodeOrientation(NodeOrientation.RIGHT_TO_LEFT);
        numBox.setAlignment(Pos.CENTER);
        numBox.setSpacing(5);

        VBox vbox = new VBox(logo,
                shop_name,
                numBox,
                new Label(order.getDate_time()),
                products);

        vbox.setSpacing(5);
        vbox.setAlignment(Pos.CENTER);
        vbox.setPadding(new Insets(10));
        vbox.setNodeOrientation(NodeOrientation.RIGHT_TO_LEFT);
        vbox.setStyle("-fx-background-color: #fff;");

        VBox box = new VBox(vbox, btnPrintChef);
        box.setSpacing(5);
        box.setAlignment(Pos.CENTER);
        box.setNodeOrientation(NodeOrientation.RIGHT_TO_LEFT);
        box.setPadding(new Insets(10));
        box.setStyle("-fx-background-color: #fff;");

        btnPrintChef.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                box.getChildren().removeAll(btnPrintChef);
                String chef_printer = Main.setting_arr.getJSONObject(0).getString("chef_printer");
                utils.PrinterDialog(box, "تم الارسال الي الطباعه", printer);
            }
        });

        dialogStage.setTitle(msg);
        dialogStage.setScene(new Scene(box));
        dialogStage.setMaxWidth(300);
        dialogStage.show();
    }


}