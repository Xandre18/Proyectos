package com.example.proyectoerp.objects;

public class Supplier {
    String product , company , email, tlfn, address;
    int id;
    //Constructor

    public Supplier(int id, String product, String company, String email, String tlfn, String address) {
        this.id = id;
        this.product = product;
        this.company = company;
        this.email = email;
        this.tlfn = tlfn;
        this.address = address;
    }

    public Supplier( String product, String company, String email, String tlfn, String address) {
        this.product = product;
        this.company = company;
        this.email = email;
        this.tlfn = tlfn;
        this.address = address;
    }
    //Getters y setters

    public int getId() {
        return id;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTlfn() {
        return tlfn;
    }

    public void setTlfn(String tlfn) {
        this.tlfn = tlfn;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
