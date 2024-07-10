package com.codedev.modernfarmer.Entities;


import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "FeedStockAdp")

public class FeedStock {

    @PrimaryKey(autoGenerate = true)
    private int feed_stock_id;

    @ColumnInfo(name = "company_name")
    private String company_name;

    @ColumnInfo(name = "product_name")
    private String product_name;

    @ColumnInfo(name = "quantity")
    private String quantity;

    @ColumnInfo(name = "used_price")
    private String usd_price;

    @ColumnInfo(name = "zwl_price")
    private String zwl_price;

    @ColumnInfo(name = "rate")
    private String rate;

    @ColumnInfo(name = "type")
    private String type;

    @ColumnInfo(name = "bag_size")
    private String bag_size;


    public int getFeed_stock_id() {
        return feed_stock_id;
    }

    public void setFeed_stock_id(int feed_stock_id) {
        this.feed_stock_id = feed_stock_id;
    }

    public String getCompany_name() {
        return company_name;
    }

    public void setCompany_name(String company_name) {
        this.company_name = company_name;
    }

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getUsd_price() {
        return usd_price;
    }

    public void setUsd_price(String usd_price) {
        this.usd_price = usd_price;
    }

    public String getZwl_price() {
        return zwl_price;
    }

    public void setZwl_price(String zwl_price) {
        this.zwl_price = zwl_price;
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getBag_size() {
        return bag_size;
    }

    public void setBag_size(String bag_size) {
        this.bag_size = bag_size;
    }

    @Override
    public String toString() {
        return "FeedStockAdp{" +
                "feed_stock_id=" + feed_stock_id +
                ", company_name='" + company_name + '\'' +
                ", product_name='" + product_name + '\'' +
                ", quantity='" + quantity + '\'' +
                ", usd_price='" + usd_price + '\'' +
                ", zwl_price='" + zwl_price + '\'' +
                ", rate='" + rate + '\'' +
                ", type='" + type + '\'' +
                ", bag_size='" + bag_size + '\'' +
                '}';
    }
}
