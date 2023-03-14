package com.example.proyectoerp.fragment_admins;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.proyectoerp.AddCont;
import com.example.proyectoerp.AddCustomer;
import com.example.proyectoerp.DBHandler;
import com.example.proyectoerp.EditCustomer;
import com.example.proyectoerp.R;
import com.example.proyectoerp.adapters.CustomerAdapter;
import com.example.proyectoerp.objects.Cliente;

import java.util.ArrayList;

public class ClientesFragment extends Fragment {

    View v;
    DBHandler handler;
    ArrayList<Cliente> customerList;
    ListView lv;
    CustomerAdapter ca;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        v = inflater.inflate(R.layout.fragment_clientes, container, false);
        lv = v.findViewById(R.id.lvClientes);

        handler = new DBHandler(v.getContext());
        customerList = handler.readCustomer();
        ca = new CustomerAdapter(v.getContext(),R.layout.cliente_list_item,customerList);
        lv.setAdapter(ca);

        lv.setClickable(true);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Cliente c = customerList.get(position);
                String nombre = c.getNombre();
                String apellido = c.getApellido();
                String edad = c.getEdad();
                int tlf = c.getTel();
                String email = c.getEmail();
                Intent intent = new Intent(v.getContext(), EditCustomer.class);
                intent.putExtra("nom", nombre);
                intent.putExtra("ape" , apellido);
                intent.putExtra("ed", edad);
                intent.putExtra("tlf", tlf);
                intent.putExtra("mail", email);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);

            }
        });


        return v;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_customer, menu);
        super.onCreateOptionsMenu(menu,inflater);
    }

    @Override
    public boolean onOptionsItemSelected( @NonNull MenuItem item ) {
        switch (item.getItemId()){
            case R.id.add:
                Intent intent = new Intent(v.getContext(), AddCustomer.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }


}