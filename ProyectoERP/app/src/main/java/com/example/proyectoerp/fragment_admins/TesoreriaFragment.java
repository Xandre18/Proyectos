package com.example.proyectoerp.fragment_admins;

import android.app.AlertDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.proyectoerp.DBHandler;
import com.example.proyectoerp.R;
import com.example.proyectoerp.objects.ContControler;

public class TesoreriaFragment extends Fragment {

    View v;
    DBHandler handler;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
         v = inflater.inflate(R.layout.fragment_tesoreria, container, false);

        SharedPreferences initBalance = getContext().getSharedPreferences("iniBalance", Context.MODE_PRIVATE);
        if(!initBalance.getBoolean("iniBalance", false)){
            showDialog();
            SharedPreferences.Editor editor = initBalance.edit();
            editor.putBoolean("iniBalance", true);
            editor.commit();
        }else
            Toast.makeText(v.getContext(), "Juan", Toast.LENGTH_SHORT).show();
        return v;
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
                dialog.dismiss();
            }
        });


    }
}