package com.codedev.modernfarmer.Entities;


import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Date;

@Entity(tableName = "meds_inventory")
public class MedsInventory {

    @PrimaryKey(autoGenerate = true)
    private int med_inventory_id;

    @ColumnInfo(name = "med_name")
    private String med_name;

    @ColumnInfo(name = "med_quantity")
    private float med_quantity;

    @ColumnInfo(name = "date_modified")
    private Date modified_date;

    public int getMed_inventory_id() {
        return med_inventory_id;
    }

    public void setMed_inventory_id(int med_inventory_id) {
        this.med_inventory_id = med_inventory_id;
    }

    public String getMed_name() {
        return med_name;
    }

    public void setMed_name(String med_name) {
        this.med_name = med_name;
    }

    public float getMed_quantity() {
        return med_quantity;
    }

    public void setMed_quantity(float med_quantity) {
        this.med_quantity = med_quantity;
    }

    public Date getModified_date() {
        return modified_date;
    }

    public void setModified_date(Date modified_date) {
        this.modified_date = modified_date;
    }

    @Override
    public String toString() {
        return "MedsInventory{" +
                "med_inventory_id=" + med_inventory_id +
                ", med_name='" + med_name + '\'' +
                ", med_quantity=" + med_quantity +
                ", modified_date=" + modified_date +
                '}';
    }
}
