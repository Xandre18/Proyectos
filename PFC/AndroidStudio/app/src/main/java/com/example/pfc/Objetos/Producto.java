package com.example.pfc.Objetos;

public class Producto {
    int refNu, id , stock, precio, img, cantidad;
    String nombre;

    public Producto(int refNu, int stock, int precio, int img , String nombre) {
        this.refNu = refNu;
        this.stock = stock;
        this.precio = precio;
        this.img = img;
        this.nombre = nombre;
        this.cantidad = 0;
    }

    public Producto(int refNu,int id, int stock, int precio, int img , String nombre) {
        this.refNu = refNu;
        this.id = id;
        this.stock = stock;
        this.precio = precio;
        this.img = img;
        this.nombre = nombre;
        this.cantidad = 0;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public int getRefNu() {
        return refNu;
    }

    public void setRefNu(int refNu) {
        this.refNu = refNu;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
