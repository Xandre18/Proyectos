package com.example.pfc.Fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.pfc.Activity.InfoVenta;
import com.example.pfc.Adapters.VentasAdapter;
import com.example.pfc.BaseDatos.DBHandler;
import com.example.pfc.Objetos.Venta;
import com.example.pfc.R;

import java.util.ArrayList;

public class Ventas extends Fragment {
    View v;
    DBHandler dbHandler;
    ArrayList<Venta> ventaClienteList;
    VentasAdapter ventasAdapter;
    RecyclerView recyclerView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        v =  inflater.inflate(R.layout.fragment_ventas, container, false);
        dbHandler = new DBHandler(v.getContext());
        ventaClienteList = dbHandler.getVentasByCliente(dbHandler.getIDuserConectado());

        ventasAdapter = new VentasAdapter(ventaClienteList, v.getContext(), new VentasAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Venta venta) {
                datosVenta(venta);
            }
        });
        recyclerView = v.findViewById(R.id.ventasList);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(v.getContext()));
        recyclerView.setAdapter(ventasAdapter);
        return v;
    }

    public void datosVenta(Venta venta){
        Intent intent = new Intent(v.getContext(), InfoVenta.class);
        intent.putExtra("OBJdata", venta);

        startActivity(intent);
    }

}