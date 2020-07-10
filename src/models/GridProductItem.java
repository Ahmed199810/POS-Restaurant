package models;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

public class GridProductItem extends Pane {
    private String title;
    private String price;
    private Image img;
    private Pane pane;

    public GridProductItem(String title, Image img, String price) {
        this.title = title;
        this.img = img;
        this.price = price;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

        Label text = new Label(title);
        Text txtPrice = new Text(price);
        text.setTextAlignment(TextAlignment.CENTER);
        txtPrice.setTextAlignment(TextAlignment.CENTER);
        txtPrice.setFont(new Font(13));
        text.setFont(new Font(14));
        text.setPrefWidth(100);


        ImageView imageView = new ImageView(img);
        imageView.setFitHeight(80);
        imageView.setFitWidth(80);
        VBox vBox = new VBox();
        vBox.setAlignment(Pos.CENTER);
        vBox.getChildren().addAll(imageView, text, txtPrice);
        vBox.setPadding(new Insets(5));
        pane.getChildren().add(vBox);
        return pane;
    }

    public void setPane(Pane pane) {
        this.pane = pane;
    }
}