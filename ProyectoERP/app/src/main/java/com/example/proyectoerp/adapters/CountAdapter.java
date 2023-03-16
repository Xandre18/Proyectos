package com.example.proyectoerp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.proyectoerp.R;
import com.example.proyectoerp.objects.ContControler;

import java.util.ArrayList;

public class CountAdapter extends BaseAdapter {
    Context c; // Contexto de la actividad o fragmento que usa el adaptador
    int loyout; // ID del layout que representa una fila de la lista
    ArrayList<ContControler> contList; // Lista de objetos ContControler que se mostrarán en la lista

    // Constructor que recibe el contexto, el ID del layout y la lista de objetos ContControler
    public CountAdapter(Context c, int loyout, ArrayList<ContControler> contList) {
        this.c = c;
        this.loyout = loyout;
        this.contList = contList;
    }

    // Devuelve la cantidad de objetos en la lista
    @Override
    public int getCount() {
        return contList.size();
    }

    // Devuelve el objeto ContControler en la posición indicada
    @Override
    public ContControler getItem(int position) {
        return contList.get(position);
    }

    // Devuelve el ID del objeto en la posición indicada
    @Override
    public long getItemId(int position) {
        return position;
    }

    // Crea la vista de cada fila de la lista
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ContControler cc = getItem(position);
        if(convertView == null){ // Si convertView no ha sido inflada todavía, se infla
            convertView = LayoutInflater.from(this.c).inflate(this.loyout,parent,false);
        }

        // Se obtienen las vistas (TextViews) del layout de la fila
        TextView tvId = convertView.findViewById(R.id.tvId);
        TextView tvCantidad = convertView.findViewById(R.id.tvCantidad);
        TextView tvSaldo = convertView.findViewById(R.id.tvSaldo);

        // Se establecen los valores de las vistas con los valores del objeto ContControler
        tvId.setText("ID: " + cc.getId());
        tvCantidad.setText("Cant:" + cc.getMount());
        tvSaldo.setText("Saldo: " + cc.getBalance());
        return convertView; // Se devuelve la vista de la fila
    }
}