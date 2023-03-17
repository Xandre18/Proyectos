package com.example.proyectoerp.fragment_admins;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.proyectoerp.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class CorreoFragment extends Fragment {

    View v;
    Spinner destinatarios;
    FloatingActionButton enviar;
    EditText asunto , mensaje;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_correo, container, false);
        enviar = v.findViewById(R.id.send);
        destinatarios = v.findViewById(R.id.destinatarios);
        asunto = v.findViewById(R.id.asunto);
        mensaje = v.findViewById(R.id.mensaje);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(v.getContext(),R.array.selectorDestinatarios,  android.R.layout.simple_spinner_item);

        destinatarios.setAdapter(adapter);

        enviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(asunto.getText().toString().isEmpty() || mensaje.getText().toString().isEmpty()){
                    Toast.makeText(v.getContext(), "Faltan campos por rellenar", Toast.LENGTH_SHORT).show();
                }else{
                    String juan = destinatarios.getSelectedItem().toString();
                    if(juan.equals("Todos")){
                        asunto.setText("");
                        mensaje.setText("");
                        destinatarios.setSelection(0);
                        Toast.makeText(v.getContext(), "Correo enviado a todos", Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(v.getContext(), "Correo enviado a todos los " + juan, Toast.LENGTH_SHORT).show();
                        asunto.setText("");
                        mensaje.setText("");
                        destinatarios.setSelection(0);
                    }
                }
            }
        });
        return v;
    }
}