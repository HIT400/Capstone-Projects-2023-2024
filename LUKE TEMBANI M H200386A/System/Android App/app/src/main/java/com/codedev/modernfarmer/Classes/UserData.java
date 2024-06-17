package com.codedev.modernfarmer.Classes;

public class UserData {

    private String fullname;
    private String username;
    private String password;
    private String address;
    private String contact;

    public UserData(String fullname, String username, String password, String address, String contact) {
        this.fullname = fullname;
        this.username = username;
        this.password = password;
        this.address = address;
        this.contact = contact;
    }


    @Override
    public String toString() {
        return "UserData{" +
                "fullname='" + fullname + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", address='" + address + '\'' +
                ", contact='" + contact + '\'' +
                '}';
    }
}
