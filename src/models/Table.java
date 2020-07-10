package models;

public class Table {

    private int id;
    private float price;
    private int chair_nums;
    private int table_num;
    private int bill_num;
    private int state;

    public Table(int id, float price, int chair_nums, int table_num, int bill_num, int state) {
        this.id = id;
        this.price = price;
        this.chair_nums = chair_nums;
        this.table_num = table_num;
        this.bill_num = bill_num;
        this.state = state;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getChair_nums() {
        return chair_nums;
    }

    public void setChair_nums(int chair_nums) {
        this.chair_nums = chair_nums;
    }

    public int getTable_num() {
        return table_num;
    }

    public void setTable_num(int table_num) {
        this.table_num = table_num;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public int getBill_num() {
        return bill_num;
    }

    public void setBill_num(int bill_num) {
        this.bill_num = bill_num;
    }


}