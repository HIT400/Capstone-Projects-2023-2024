package com.codedev.modernfarmer.Entities;


import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Date;

@Entity(tableName = "chicks_placement")
public class Chicks_Placement {

    @PrimaryKey(autoGenerate = true)
    private int chicks_placement_id;

    @ColumnInfo(name = "Chicks_breed")
    private String chicks_breed;

    @ColumnInfo(name = "Chicks_number")
    private int chicks_number;

    @ColumnInfo(name = "Unit_price")
    private double unit_price;

    @ColumnInfo(name = "total_price")
    private double total_price;

    @ColumnInfo(name ="Supplier")
    private String supplier;

    @ColumnInfo(name = "customer_name")
    private String customer_name;

    @ColumnInfo(name = "placement_date")
    private Date placement_date;


    public String getCustomer_name() {
        return customer_name;
    }

    public void setCustomer_name(String customer_name) {
        this.customer_name = customer_name;
    }

    public int getChicks_placement_id() {
        return chicks_placement_id;
    }

    public void setChicks_placement_id(int chicks_placement_id) {
        this.chicks_placement_id = chicks_placement_id;
    }

    public String getChicks_breed() {
        return chicks_breed;
    }

    public void setChicks_breed(String chicks_breed) {
        this.chicks_breed = chicks_breed;
    }

    public int getChicks_number() {
        return chicks_number;
    }

    public void setChicks_number(int chicks_number) {
        this.chicks_number = chicks_number;
    }

    public double getUnit_price() {
        return unit_price;
    }

    public void setUnit_price(double unit_price) {
        this.unit_price = unit_price;
    }

    public double getTotal_price() {
        return total_price;
    }

    public void setTotal_price(double total_price) {
        this.total_price = total_price;
    }

    public String getSupplier() {
        return supplier;
    }

    public void setSupplier(String supplier) {
        this.supplier = supplier;
    }

    public Date getPlacement_date() {
        return placement_date;
    }

    public void setPlacement_date(Date placement_date) {
        this.placement_date = placement_date;
    }


    @Override
    public String toString() {
        return "Chicks_Placement{" +
                "chicks_placement_id=" + chicks_placement_id +
                ", chicks_breed='" + chicks_breed + '\'' +
                ", chicks_number=" + chicks_number +
                ", unit_price=" + unit_price +
                ", total_price=" + total_price +
                ", supplier='" + supplier + '\'' +
                ", customer_name='" + customer_name + '\'' +
                ", placement_date=" + placement_date +
                '}';
    }
}
