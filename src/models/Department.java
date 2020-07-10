package models;

import javafx.scene.image.ImageView;

public class Department {
    private int id;
    private String name;
    private String img_url;
    private String printer;

    public Department(int id, String name, String img_url, String printer) {
        this.id = id;
        this.name = name;
        this.img_url = img_url;
        this.printer = printer;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImg_url() {
        return img_url;
    }

    public void setImg_url(String img_url) {
        this.img_url = img_url;
    }

    public String getPrinter() {
        return printer;
    }

    public void setPrinter(String printer) {
        this.printer = printer;
    }
}
