package models;

import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

import java.io.IOException;

public class GridPosItem extends Pane {

    private int p_id;
    private String name;
    private int count;
    private Image img;
    private Pane pane;
    private String price;
    private TableView<Bill> table;
    private Label totalPrice;
    private TextField txt_discount;
    private TextField txt_tax_bill;
    private TextField txt_service;
    private Product product;

    public GridPosItem(int p_id, String name, int count, Image img, String price, TableView<Bill> table
            , Label totalPrice, TextField txt_discount, TextField txt_tax_bill, TextField txt_service, Product product) {
        this.p_id = p_id;
        this.name = name;
        this.count = count;
        this.img = img;
        this.price = price;
        this.table = table;
        this.totalPrice = totalPrice;
        this.txt_discount = txt_discount;
        this.txt_service = txt_service;
        this.txt_tax_bill = txt_tax_bill;
        this.product = product;
    }

    public int getP_id() {
        return p_id;
    }

    public void setP_id(int p_id) {
        this.p_id = p_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public Image getImg() {
        return img;
    }

    public void setImg(Image img) {
        this.img = img;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public Pane getPane() {
        pane = new Pane();

        Label num = new Label(count+"");
        num.setFont(new Font(18));
        num.setPadding(new Insets(10));
        Label text = new Label(name);
        Text txtPrice = new Text(price);
        text.setTextAlignment(TextAlignment.CENTER);
        txtPrice.setTextAlignment(TextAlignment.CENTER);
        txtPrice.setFont(new Font(13));
        text.setFont(new Font(14));
        text.setPrefWidth(100);

        ImageView imageView = new ImageView(img);
        imageView.setFitHeight(80);
        imageView.setFitWidth(80);

        Button btnAdd = new Button("+");
        btnAdd.setPrefHeight(30);
        btnAdd.setPrefWidth(30);
        Button btnSub = new Button("-");
        btnSub.setPrefHeight(30);
        btnSub.setPrefWidth(30);

        btnAdd.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                boolean check = false;
                Bill bill = new Bill(p_id, name, 1, Float.parseFloat(price), null);
                for (int i = 0; i < table.getItems().size(); i++){
                    if (bill.getId() == table.getItems().get(i).getId()){
                        if (table.getItems().get(i).getAmount() < count){
                            System.out.println("EXIST");
                            Bill b = table.getItems().get(i);
                            b.setAmount(b.getAmount() + 1);
                            b.setPrice(Float.parseFloat(price) * b.getAmount());
                            table.getItems().set(i, b);
                            totalPrice.setText(getTotalPrice(table, txt_discount,txt_service,txt_tax_bill));
                        }
                        check = true;
                        break;
                    }
                }
                if (!check && count > 0){
                    table.getItems().add(new Bill(
                                p_id,
                                name,
                                1,
                                Float.parseFloat(price),
                            product
                    ));
                    totalPrice.setText(getTotalPrice(table, txt_discount,txt_service,txt_tax_bill));
                }
            }
        });
        btnSub.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                for (int i = 0; i < table.getItems().size(); i++){
                    if (p_id == table.getItems().get(i).getId()){
                        if (table.getItems().get(i).getAmount() > 1){
                            System.out.println("EXIST");
                            Bill b = table.getItems().get(i);
                            b.setAmount(b.getAmount() - 1);
                            b.setPrice(Float.parseFloat(price) * b.getAmount());
                            table.getItems().set(i, b);
                            totalPrice.setText(getTotalPrice(table, txt_discount,txt_service,txt_tax_bill));
                        }else {
                            table.getItems().remove(i);
                        }
                        break;
                    }
                }

            }
        });

        HBox hBox = new HBox();
        hBox.setAlignment(Pos.CENTER);
        hBox.getChildren().addAll(btnAdd, num, btnSub);

        VBox vBox = new VBox();
        vBox.setAlignment(Pos.CENTER);
        vBox.getChildren().addAll(imageView, text, txtPrice, hBox);
        vBox.setPadding(new Insets(7));
        pane.getChildren().add(vBox);
        return pane;
    }

    private String getTotalPrice(TableView<Bill> table, TextField txt_discount,TextField txt_service,TextField txt_tax_bill) {
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

    public void setPane(Pane pane) {
        this.pane = pane;
    }

}