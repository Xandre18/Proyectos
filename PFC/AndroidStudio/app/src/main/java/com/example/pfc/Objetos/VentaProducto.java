package com.example.pfc.Objetos;

public class VentaProducto {
    int codV, idPro, cantidad;

    public VentaProducto(int codV, int idPro, int cantidad) {
        this.codV = codV;
        this.idPro = idPro;
        this.cantidad = cantidad;
    }

    public int getCodV() {
        return codV;
    }

    public void setCodV(int codV) {
        this.codV = codV;
    }

    public int getIdPro() {
        return idPro;
    }

    public void setIdPro(int idPro) {
        this.idPro = idPro;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
}
