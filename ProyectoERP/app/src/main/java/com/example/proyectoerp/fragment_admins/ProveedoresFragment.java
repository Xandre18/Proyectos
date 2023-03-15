package com.example.proyectoerp.fragment_admins;

import android.app.AlertDialog;
import android.content.Context;
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
import com.example.proyectoerp.AddSupplier;
import com.example.proyectoerp.DBHandler;
import com.example.proyectoerp.EditCustomer;
import com.example.proyectoerp.EditSupplider;
import com.example.proyectoerp.R;
import com.example.proyectoerp.adapters.CustomerAdapter;
import com.example.proyectoerp.adapters.SuppliderAdapter;
import com.example.proyectoerp.objects.Cliente;
import com.example.proyectoerp.objects.Supplier;

import java.util.ArrayList;


public class ProveedoresFragment extends Fragment {

    View v;
    DBHandler handler;
    ListView lv;
    SuppliderAdapter sa;
    ArrayList<Supplier> supList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_proveedores, container, false);
        setHasOptionsMenu(true);
        lv = v.findViewById(R.id.lvSupplier);
        Context c = v.getContext();
        handler = new DBHandler(c);
        supList = handler.readSupplier();
        sa = new SuppliderAdapter(c,R.layout.supplier_list_item,supList);
        lv.setAdapter(sa);
        lv.setClickable(true);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Supplier s = supList.get(position);
                String compañia = s.getCompany();
                String tlf = s.getTlfn();
                String email = s.getEmail();
                String dir = s.getAddress();
                String producto = s.getProduct();
                int ident = s.getId();
                Intent intent = new Intent(v.getContext(), EditSupplider.class);
                intent.putExtra("id", ident);
                intent.putExtra("comp", compañia);
                intent.putExtra("tlf" , tlf);
                intent.putExtra("mail", email);
                intent.putExtra("dir", dir);
                intent.putExtra("prod", producto);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });

        lv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Supplier s = supList.get(position);
                confirmDelete(s);

                return true;
            }
        });


        return v;
    }

    public void confirmDelete(Supplier s){
        new AlertDialog.Builder(v.getContext()).setTitle("¡¡¡¡SEGURO!!!!")
                .setMessage("Estas seguro que quieres borrar este proveedor  \n" +
                        "Producto: " + s.getProduct() + "\n" +
                        "Comañia: " + s.getCompany() + "\n" +
                        "Email: " + s.getEmail() + "\n" +
                        "Dirección: : " + s.getAddress())
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        handler.deleteSupplider(s);
                        Toast.makeText(v.getContext(), "Borrado correctamente", Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                        supList = handler.readSupplier();
                        sa = new SuppliderAdapter(v.getContext(),R.layout.supplier_list_item,supList);
                        lv.setAdapter(sa);
                    }
                })
                .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(v.getContext(), "Cancelado", Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }
                }).show();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.anhadir, menu);
        super.onCreateOptionsMenu(menu,inflater);
    }

    @Override
    public boolean onOptionsItemSelected( @NonNull MenuItem item ) {
        switch (item.getItemId()){
            case R.id.add:
                Intent intent = new Intent(v.getContext(), AddSupplier.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }

}