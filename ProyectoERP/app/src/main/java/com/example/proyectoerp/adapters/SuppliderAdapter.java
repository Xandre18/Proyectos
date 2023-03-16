package com.example.proyectoerp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.proyectoerp.R;
import com.example.proyectoerp.objects.Supplier;

import java.util.ArrayList;

public class SuppliderAdapter extends BaseAdapter {
    Context c;
    int layout;
    ArrayList<Supplier> supList;

    public SuppliderAdapter(Context c, int layout, ArrayList<Supplier> supList) {
        this.c = c;
        this.layout = layout;
        this.supList = supList;
    }

    @Override
    public int getCount() {
        return supList.size();
    }

    @Override
    public Supplier getItem(int position) {
        return supList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return supList.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Supplier s = getItem(position);
        if(convertView == null){
            convertView = LayoutInflater.from(this.c).inflate(this.layout,parent,false);
        }
        // Obtenemos los TextViews de la vista inflada
        TextView tvId, tvComapñia, tvTel, tvEmail, tvDir, tvProducto;
        tvId = convertView.findViewById(R.id.tvId);
        tvComapñia = convertView.findViewById(R.id.tvCompany);
        tvTel = convertView.findViewById(R.id.tvTelf);
        tvEmail = convertView.findViewById(R.id.tvEmail);
        tvDir = convertView.findViewById(R.id.tvAddress);
        tvProducto = convertView.findViewById(R.id.tvProducto);

        // Establecemos los valores correspondientes en los TextViews
        tvId.setText("ID: " + s.getId());
        tvComapñia.setText("Comp. " + s.getCompany());
        tvTel.setText("Tlf: " + s.getTlfn());
        tvEmail.setText("@: " + s.getEmail());
        tvDir.setText("Dirección: " + s.getAddress());
        tvProducto.setText("Prod. " + s.getProduct());

        // Devolvemos la vista inflada con los datos actualizados
        return convertView;
    }
}
