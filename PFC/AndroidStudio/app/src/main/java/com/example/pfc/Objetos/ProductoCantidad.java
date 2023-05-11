package com.example.pfc.Objetos;

public class ProductoCantidad {
    private int producto;
    private int cantidad;

    public ProductoCantidad(int producto, int cantidad) {
        this.producto = producto;
        this.cantidad = cantidad;
    }

    public int getProducto() {
        return producto;
    }

    public int getCantidad() {
        return cantidad;
    }
}
