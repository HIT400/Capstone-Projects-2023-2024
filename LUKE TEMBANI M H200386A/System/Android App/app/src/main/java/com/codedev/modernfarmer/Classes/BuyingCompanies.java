package com.codedev.modernfarmer.Classes;

public class BuyingCompanies {

    private String company_name;
    private String address;
    private String buying_price;

    public String getCompany_name() {
        return company_name;
    }

    public void setCompany_name(String company_name) {
        this.company_name = company_name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String

    getBuying_price() {
        return buying_price;
    }

    public void setBuying_price(String buying_price) {
        this.buying_price = buying_price;
    }

    @Override
    public String toString() {
        return "BuyingCompanies{" +
                "company_name='" + company_name + '\'' +
                ", address='" + address + '\'' +
                ", buying_price='" + buying_price + '\'' +
                '}';
    }
}
