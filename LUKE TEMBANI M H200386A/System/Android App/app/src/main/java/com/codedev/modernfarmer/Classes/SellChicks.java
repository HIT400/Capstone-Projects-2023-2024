package com.codedev.modernfarmer.Classes;

import java.util.Date;

public class SellChicks {
    private String customer_username;
    private Integer number_of_birds;
    private Float weight_of_birds;
    private String location;

    private String price;

    private String Company;
    private Date date;


    public String getCustomer_username() {
        return customer_username;
    }

    public void setCustomer_username(String customer_username) {
        this.customer_username = customer_username;
    }

    public String getCompany() {
        return Company;
    }

    public void setCompany(String company) {
        Company = company;
    }

    public Integer getNumber_of_birds() {
        return number_of_birds;
    }

    public void setNumber_of_birds(Integer number_of_birds) {
        this.number_of_birds = number_of_birds;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public Float getWeight_of_birds() {
        return weight_of_birds;
    }

    public void setWeight_of_birds(Float weight_of_birds) {
        this.weight_of_birds = weight_of_birds;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }


    @Override
    public String toString() {
        return "SellChicks{" +
                "customer_username='" + customer_username + '\'' +
                ", number_of_birds=" + number_of_birds +
                ", weight_of_birds=" + weight_of_birds +
                ", location='" + location + '\'' +
                ", price=" + price +
                ", Company='" + Company + '\'' +
                ", date=" + date +
                '}';
    }
}
