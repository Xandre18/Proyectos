package com.example.proyectoerp.fragment_admins;

import android.app.AlertDialog;
import android.content.DialogInterface;
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

    // Variables de clase
    View v;
    DBHandler handler;
    ArrayList<Cliente> customerList;
    ListView lv;
    CustomerAdapter ca;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Indica que este fragmento tiene su propio menú
        setHasOptionsMenu(true);
        v = inflater.inflate(R.layout.fragment_clientes, container, false);
        lv = v.findViewById(R.id.lvClientes);

        // Crea una instancia de la base de datos
        handler = new DBHandler(v.getContext());

        // Obtiene la lista de clientes de la base de datos
        customerList = handler.readCustomer();

        // Crea un adaptador para la lista de clientes y lo asigna al ListView
        ca = new CustomerAdapter(v.getContext(),R.layout.cliente_list_item,customerList);
        lv.setAdapter(ca);

        // Configura el ListView para que sea clickable
        lv.setClickable(true);

        // Configura la acción que se ejecuta cuando se hace clic en un elemento de la lista
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Obtiene el cliente seleccionado
                Cliente c = customerList.get(position);

                // Obtiene los datos del cliente seleccionado
                String nombre = c.getNombre();
                String apellido = c.getApellido();
                String edad = c.getEdad();
                int tlf = c.getTel();
                String email = c.getEmail();

                // Crea un intent para abrir la actividad de edición de cliente y le pasa los datos del cliente
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

        // Configura la acción que se ejecuta cuando se hace una pulsación larga en un elemento de la lista
        lv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                // Obtiene el cliente seleccionado
                Cliente c = customerList.get(position);

                // Pide confirmación para eliminar el cliente seleccionado
                confirfDelete(c);

                return true;
            }
        });
        return v;
    }
    public void confirfDelete(Cliente c){
        new AlertDialog.Builder(v.getContext())
                .setTitle("¡¡¡¡SEGURO!!!!")
                .setMessage("¿Estas seguro que quieres borrar el siguiente cliente: \n" +
                        "Nombre: " + c.getNombre() + "\n" +
                        "Apellido: " + c.getApellido() + "\n" +
                        "Edad: " + c.getEdad() + "\n" +
                        "Telefono: " + c.getTel())
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        handler.deleteCustomer(c);
                        Toast.makeText(v.getContext(), "Borrado correctamente", Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                        customerList = handler.readCustomer();
                        ca = new CustomerAdapter(v.getContext(),R.layout.cliente_list_item,customerList);
                        lv.setAdapter(ca);
                    }
                }).setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(v.getContext(), "Cancelado", Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }
                }).show();

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