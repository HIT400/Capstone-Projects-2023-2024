package com.codedev.modernfarmer.Classes;

public class ComponentsSwitcher {
    private Integer component_id;
    private String component_name;
    private String component_status;

    public Integer getComponent_id() {
        return component_id;
    }

    public void setComponent_id(Integer component_id) {
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

    @Override
    public String toString() {
        return "ComponentsSwitcher{" +
                "component_id=" + component_id +
                ", component_name='" + component_name + '\'' +
                ", component_status=" + component_status +
                '}';
    }
}
