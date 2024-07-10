package com.codedev.modernfarmer.Classes;

public class CompanyStockDC {

    private String company_name;
    private String type;

    public CompanyStockDC(String company_name, String type) {
        this.company_name = company_name;
        this.type = type;
    }

    public String getCompany_name() {
        return company_name;
    }

    public void setCompany_name(String company_name) {
        this.company_name = company_name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "CompanyStockDC{" +
                "company_name='" + company_name + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
