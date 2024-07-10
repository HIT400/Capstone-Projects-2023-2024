package com.codedev.modernfarmer.Classes;

public class FeedSuppliers {

    private String companyname;

    public String getCompanyname() {
        return companyname;
    }

    public void setCompanyname(String companyname) {
        this.companyname = companyname;
    }

    @Override
    public String toString() {
        return "FeedSuppliers{" +
                "companyname='" + companyname + '\'' +
                '}';
    }
}
