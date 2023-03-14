package com.example.proyectoerp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.proyectoerp.R;
import com.example.proyectoerp.objects.Cliente;

import java.util.ArrayList;

public class CustomerAdapter extends BaseAdapter {
    Context c;
    int layout;
    ArrayList<Cliente> customerList;

    public CustomerAdapter(Context c, int layout, ArrayList<Cliente> customerList){
        this.c = c;
        this.layout = layout;
        this.customerList = customerList;
    }

    @Override
    public int getCount() {
        return customerList.size();
    }

    @Override
    public Cliente getItem(int position) {
        return customerList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Cliente c = getItem(position);
        if(convertView == null){
            convertView = LayoutInflater.from(this.c).inflate(this.layout,parent,false);
        }

        TextView tvNombre = convertView.findViewById(R.id.tvNombre);
        TextView tvEdad = convertView.findViewById(R.id.tvEdad);
        TextView tvTelefono = convertView.findViewById(R.id.tvTelf);
        TextView tvEmail = convertView.findViewById(R.id.tvEmail);

        tvNombre.setText("Nombre: " + c.getNombre() + " " + c.getApellido());
        tvEdad.setText("Edad: " + c.getEdad());
        tvTelefono.setText("Tlf: " + c.getTel());
        tvEmail.setText("Email: " + c.getEmail());

        return convertView;
    }
}
