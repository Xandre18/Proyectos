package com.example.pfc.Objetos;

public class Producto {
    int refNu, id , stock, precio;

    public Producto(int refNu, int id, int stock, int precio) {
        this.refNu = refNu;
        this.id = id;
        this.stock = stock;
        this.precio = precio;
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


}
