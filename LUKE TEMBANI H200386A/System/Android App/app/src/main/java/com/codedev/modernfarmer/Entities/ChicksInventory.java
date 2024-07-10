package com.codedev.modernfarmer.Entities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Date;

@Entity(tableName = "chicks_inventory")
public class ChicksInventory {

    @PrimaryKey(autoGenerate = true)
    private int chicks_inventory_id;

    @ColumnInfo(name = "chicks_breed")
    private String chicks_breed;

    @ColumnInfo(name = "chicks_number")
    private int chicks_number;

    @ColumnInfo(name = "date_modified")
    private Date date_modified;

    public int getChicks_inventory_id() {
        return chicks_inventory_id;
    }

    public void setChicks_inventory_id(int chicks_inventory_id) {
        this.chicks_inventory_id = chicks_inventory_id;
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

    public Date getDate_modified() {
        return date_modified;
    }

    public void setDate_modified(Date date_modified) {
        this.date_modified = date_modified;
    }

    @Override
    public String toString() {
        return "ChicksInventory{" +
                "chicks_inventory_id=" + chicks_inventory_id +
                ", chicks_breed='" + chicks_breed + '\'' +
                ", chicks_number=" + chicks_number +
                ", date_modified=" + date_modified +
                '}';
    }
}
