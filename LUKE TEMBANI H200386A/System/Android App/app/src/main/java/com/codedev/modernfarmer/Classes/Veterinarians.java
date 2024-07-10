package com.codedev.modernfarmer.Classes;

public class Veterinarians {

    private String vet_name;
    private String vet_contact;
    private  String vet_location;


    public String getVet_name() {
        return vet_name;
    }

    public void setVet_name(String vet_name) {
        this.vet_name = vet_name;
    }

    public String getVet_contact() {
        return vet_contact;
    }

    public void setVet_contact(String vet_contact) {
        this.vet_contact = vet_contact;
    }

    public String getVet_location() {
        return vet_location;
    }

    public void setVet_location(String vet_location) {
        this.vet_location = vet_location;
    }


    @Override
    public String toString() {
        return "Veterinarians{" +
                "vet_name='" + vet_name + '\'' +
                ", vet_contact='" + vet_contact + '\'' +
                ", vet_location='" + vet_location + '\'' +
                '}';
    }
}
