package models;

public class Order {

    private int id;
    private int bill_num;
    private float price;
    private String products;
    private String date_time;
    private float discount;
    private float bill_tax;
    private float service;


    public Order(int id, int bill_num, float price, String products, String date_time, float discount, float bill_tax, float service) {
        this.id = id;
        this.bill_num = bill_num;
        this.price = price;
        this.products = products;
        this.date_time = date_time;
        this.discount = discount;
        this.bill_tax = bill_tax;
        this.service = service;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getBill_num() {
        return bill_num;
    }

    public void setBill_num(int bill_num) {
        this.bill_num = bill_num;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getProducts() {
        return products;
    }

    public void setProducts(String products) {
        this.products = products;
    }

    public String getDate_time() {
        return date_time;
    }

    public void setDate_time(String date_time) {
        this.date_time = date_time;
    }

    public float getDiscount() {
        return discount;
    }

    public void setDiscount(float discount) {
        this.discount = discount;
    }

    public float getBill_tax() {
        return bill_tax;
    }

    public void setBill_tax(float bill_tax) {
        this.bill_tax = bill_tax;
    }

    public float getService() {
        return service;
    }

    public void setService(float service) {
        this.service = service;
    }
}