package com.example.pfc.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.example.pfc.Objetos.Producto;

import java.util.ArrayList;

public class ProductosAdapter extends BaseAdapter {
    Context c;
    int layout;
    ArrayList<Producto> productosList;

    public ProductosAdapter(Context c , int layout, ArrayList<Producto> productosList){
        this.c = c;
        this.layout = layout;
        this.productosList = productosList;
    }

    @Override
    public int getCount() {
        return productosList.size();
    }

    @Override
    public Producto getItem(int i) {
        return productosList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return productosList.get(i).getId();
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        Producto p = getItem(i);
        if(view == null){
            // Si la vista aún no ha sido creada, infla el layout correspondiente
            view = LayoutInflater.from(this.c).inflate(this.layout,viewGroup,false);
        }

        return view;
    }


}
