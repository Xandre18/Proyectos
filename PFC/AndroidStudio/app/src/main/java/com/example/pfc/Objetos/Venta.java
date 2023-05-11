package com.example.pfc.Objetos;

import java.util.ArrayList;
import java.util.Date;

public class Venta {
    int codigo , cliente;
    String fecha;
    ArrayList<Integer> cantidades;

    public Venta(int cliente, String fecha) {
        this.cliente = cliente;
        this.fecha = fecha;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public int getCliente() {
        return cliente;
    }

    public void setCliente(int cliente) {
        this.cliente = cliente;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public ArrayList<Integer> getCantidades() {
        return cantidades;
    }

    public void setCantidades(ArrayList<Integer> cantidades) {
        this.cantidades = cantidades;
    }
}
