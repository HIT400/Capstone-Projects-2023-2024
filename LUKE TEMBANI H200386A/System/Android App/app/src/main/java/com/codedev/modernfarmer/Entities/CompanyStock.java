package com.codedev.modernfarmer.Entities;


import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "company_stock")
public class CompanyStock {

    @PrimaryKey(autoGenerate = true)
    private int company_stock_id;

    @ColumnInfo(name = "company_name")
    private String company_name;

    @ColumnInfo(name = "product_name")
    private String product_name;

    @ColumnInfo(name = "quantity")
    private int quantity;

    @ColumnInfo(name = "usd_price")
    private float usd_price;

    @ColumnInfo(name = "zwl_price")
    private float zwl_price;

    @ColumnInfo(name = "rate")
    private float rate;

    @ColumnInfo(name = "type")
    private String type;

    @ColumnInfo(name = "bag_size")
    private float bag_size;

    @ColumnInfo(name = "unit_price")
    private float unit_size;

    public int getCompany_stock_id() {
        return company_stock_id;
    }

    public void setCompany_stock_id(int company_stock_id) {
        this.company_stock_id = company_stock_id;
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

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public float getUsd_price() {
        return usd_price;
    }

    public void setUsd_price(float usd_price) {
        this.usd_price = usd_price;
    }

    public float getZwl_price() {
        return zwl_price;
    }

    public void setZwl_price(float zwl_price) {
        this.zwl_price = zwl_price;
    }

    public float getRate() {
        return rate;
    }

    public void setRate(float rate) {
        this.rate = rate;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public float getBag_size() {
        return bag_size;
    }

    public void setBag_size(float bag_size) {
        this.bag_size = bag_size;
    }

    public float getUnit_size() {
        return unit_size;
    }

    public void setUnit_size(float unit_size) {
        this.unit_size = unit_size;
    }

    @Override
    public String toString() {
        return "CompanyStockDC{" +
                "company_stock_id=" + company_stock_id +
                ", company_name='" + company_name + '\'' +
                ", product_name='" + product_name + '\'' +
                ", quantity=" + quantity +
                ", usd_price=" + usd_price +
                ", zwl_price=" + zwl_price +
                ", rate=" + rate +
                ", type='" + type + '\'' +
                ", bag_size=" + bag_size +
                ", unit_size=" + unit_size +
                '}';
    }
}
