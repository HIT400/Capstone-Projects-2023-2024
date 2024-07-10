package com.codedev.modernfarmer.Entities;


import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Date;

@Entity(tableName = "placement_list")

public class PlacementList {

    @PrimaryKey(autoGenerate = true)
    private Integer id_chick_placement;

    @ColumnInfo(name = "placement_id")
    private String placement_id;

    @ColumnInfo(name = "company_name")
    private String company_name;

    @ColumnInfo(name = "chicks_breed")
    private String chicks_breed;

    @ColumnInfo(name = "chicks_quantity")
    private String chicks_quantity;

    @ColumnInfo(name = "customer_username")
    private String customer_username;

    @ColumnInfo(name = "placement_date")
    private Date placement_date;

    @ColumnInfo(name = "placement_status")
    private String placement_status;


    public Integer getId_chick_placement() {
        return id_chick_placement;
    }

    public void setId_chick_placement(Integer id_chick_placement) {
        this.id_chick_placement = id_chick_placement;
    }

    public String getPlacement_id() {
        return placement_id;
    }

    public void setPlacement_id(String placement_id) {
        this.placement_id = placement_id;
    }

    public String getCompany_name() {
        return company_name;
    }

    public void setCompany_name(String company_name) {
        this.company_name = company_name;
    }

    public String getChicks_breed() {
        return chicks_breed;
    }

    public void setChicks_breed(String chicks_breed) {
        this.chicks_breed = chicks_breed;
    }

    public String getChicks_quantity() {
        return chicks_quantity;
    }

    public void setChicks_quantity(String chicks_quantity) {
        this.chicks_quantity = chicks_quantity;
    }

    public String getCustomer_username() {
        return customer_username;
    }

    public void setCustomer_username(String customer_username) {
        this.customer_username = customer_username;
    }

    public Date getPlacement_date() {
        return placement_date;
    }

    public void setPlacement_date(Date placement_date) {
        this.placement_date = placement_date;
    }

    public String getPlacement_status() {
        return placement_status;
    }

    public void setPlacement_status(String placement_status) {
        this.placement_status = placement_status;
    }

    @Override
    public String toString() {
        return "PlacementList{" +
                "id_chick_placement=" + id_chick_placement +
                ", placement_id='" + placement_id + '\'' +
                ", company_name='" + company_name + '\'' +
                ", chicks_breed='" + chicks_breed + '\'' +
                ", chicks_quantity='" + chicks_quantity + '\'' +
                ", customer_username='" + customer_username + '\'' +
                ", placement_date=" + placement_date +
                ", placement_status='" + placement_status + '\'' +
                '}';
    }
}
