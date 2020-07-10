package models;

public class Product {

    private int id;
    private String name;
    private float price;
    private int type;
    private int amount;
    private String img_url;

    public Product(int id, String name, float price, int type, int amount, String img_url) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.type = type;
        this.amount = amount;
        this.img_url = img_url;
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

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getImg_url() {
        return img_url;
    }

    public void setImg_url(String img_url) {
        this.img_url = img_url;
    }
}