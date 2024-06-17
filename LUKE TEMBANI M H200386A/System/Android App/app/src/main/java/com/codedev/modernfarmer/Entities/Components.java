package com.codedev.modernfarmer.Entities;


import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.codedev.modernfarmer.Classes.ComponentsSwitcher;

@Entity(tableName = "components")

public class Components{

  @ColumnInfo(name = "component_count_id")
    private int component_count_id;
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "component_id")
    private int component_id;

    @ColumnInfo(name = "component_name")
    private String component_name;

    @ColumnInfo(name = "component_status")
    private String component_status;

    public int getComponent_count_id() {
        return component_count_id;
    }

    public void setComponent_count_id(int component_count_id) {
        this.component_count_id = component_count_id;
    }

    public int getComponent_id() {
        return component_id;
    }

    public void setComponent_id(int component_id) {
        this.component_id = component_id;
    }

    public String getComponent_name() {
        return component_name;
    }

    public void setComponent_name(String component_name) {
        this.component_name = component_name;
    }

    public String getComponent_status() {
        return component_status;
    }

    public void setComponent_status(String component_status) {
        this.component_status = component_status;
    }

    public ComponentsSwitcher getComponentsSwitcher(){
        ComponentsSwitcher componentsSwitcher = new ComponentsSwitcher();
        componentsSwitcher.setComponent_id(this.getComponent_id());
        componentsSwitcher.setComponent_status(this.getComponent_status());
        componentsSwitcher.setComponent_name(this.getComponent_name());
        return componentsSwitcher;
    }


    @Override
    public String toString() {
        return "Components{" +
                "component_count_id=" + component_count_id +
                ", component_id=" + component_id +
                ", component_name='" + component_name + '\'' +
                ", component_status='" + component_status + '\'' +
                '}';
    }
}
