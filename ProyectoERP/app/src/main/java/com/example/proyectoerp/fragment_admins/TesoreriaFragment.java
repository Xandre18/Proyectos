package com.example.proyectoerp.fragment_admins;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.proyectoerp.AddCont;
import com.example.proyectoerp.AddCustomer;
import com.example.proyectoerp.DBHandler;
import com.example.proyectoerp.R;
import com.example.proyectoerp.adapters.CountAdapter;
import com.example.proyectoerp.objects.ContControler;

import java.util.ArrayList;

public class TesoreriaFragment extends Fragment {

    View v;
    DBHandler handler;
    ArrayList<ContControler> contList;
    ListView lv;
    CountAdapter ca;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflar el layout del fragmento
        v = inflater.inflate(R.layout.fragment_tesoreria, container, false);
        lv = v.findViewById(R.id.lvTesoreria);

        handler = new DBHandler(v.getContext());

        // Verificar si el balance inicial ya fue ingresado
        SharedPreferences initBalance = getContext().getSharedPreferences("iniBalance", Context.MODE_PRIVATE);
        if(!initBalance.getBoolean("iniBalance", false)){
            showDialog(); // Si no ha sido ingresado, mostrar diálogo para ingresar balance inicial
            SharedPreferences.Editor editor = initBalance.edit();
            editor.putBoolean("iniBalance", true);
            editor.commit();
        }else{
            // Si el balance ya ha sido ingresado, mostrar la lista de cuentas existentes
            contList = handler.readCont();
            ca = new CountAdapter(v.getContext(),R.layout.tesoreria_list_item,contList);
            lv.setAdapter(ca);
            lv.setClickable(true);
        }

        // Habilitar el menú de opciones
        setHasOptionsMenu(true);
        return v;
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
                Intent intent = new Intent(v.getContext(), AddCont.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }


    private void showDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
        LayoutInflater inflater = getLayoutInflater();
        View vAux = inflater.inflate(R.layout.alert_dialog_set_balance,null);

        builder.setView(vAux);

        AlertDialog dialog = builder.create();
        dialog.show();

        Button acept = vAux.findViewById(R.id.btnAcept);
        EditText balance = vAux.findViewById(R.id.inputBalance);
        acept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handler = new DBHandler(vAux.getContext());
                int capital = Integer.parseInt(balance.getText().toString());
                handler.addFistCont(new ContControler(capital));
                contList = handler.readCont();
                ca = new CountAdapter(v.getContext(),R.layout.tesoreria_list_item,contList);
                lv.setAdapter(ca);
                lv.setClickable(true);
                dialog.dismiss();
            }
        });


    }
}