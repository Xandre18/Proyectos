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

    // Constructor que recibe el contexto, el layout y la lista de clientes
    public CustomerAdapter(Context c, int layout, ArrayList<Cliente> customerList){
        this.c = c;
        this.layout = layout;
        this.customerList = customerList;
    }

    // Retorna el tamaño de la lista de clientes
    @Override
    public int getCount() {
        return customerList.size();
    }

    // Retorna el cliente en la posición dada
    @Override
    public Cliente getItem(int position) {
        return customerList.get(position);
    }

    // Retorna el ID de la posición dada
    @Override
    public long getItemId(int position) {
        return position;
    }

    // Retorna la vista que se mostrará en la lista para la posición dada
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Cliente c = getItem(position);
        if(convertView == null){
            // Si la vista aún no ha sido creada, infla el layout correspondiente
            convertView = LayoutInflater.from(this.c).inflate(this.layout,parent,false);
        }

        // Se obtienen las vistas del layout inflado
        TextView tvNombre = convertView.findViewById(R.id.tvNombre);
        TextView tvEdad = convertView.findViewById(R.id.tvEdad);
        TextView tvTelefono = convertView.findViewById(R.id.tvTelf);
        TextView tvEmail = convertView.findViewById(R.id.tvEmail);

        // Se actualizan los valores de las vistas con la información del cliente actual
        tvNombre.setText("Nombre: " + c.getNombre() + " " + c.getApellido());
        tvEdad.setText("Edad: " + c.getEdad());
        tvTelefono.setText("Tlf: " + c.getTel());
        tvEmail.setText("Email: " + c.getEmail());

        // Se retorna la vista actualizada
        return convertView;


    }
}