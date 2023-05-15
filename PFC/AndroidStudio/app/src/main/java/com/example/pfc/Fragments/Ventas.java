package com.example.pfc.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.pfc.BaseDatos.DBHandler;
import com.example.pfc.Objetos.Venta;
import com.example.pfc.R;

import java.util.ArrayList;

public class Ventas extends Fragment {
    View v;
    DBHandler dbHandler;
    ArrayList<Venta> ventaClienteList;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        v =  inflater.inflate(R.layout.fragment_ventas, container, false);
        dbHandler = new DBHandler(v.getContext());
        ventaClienteList = dbHandler.getVentasByCliente(dbHandler.getIDuserConectado());


        return v;
    }
}