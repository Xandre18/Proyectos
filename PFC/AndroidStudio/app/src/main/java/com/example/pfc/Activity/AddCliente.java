package com.example.pfc.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.pfc.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class AddCliente extends AppCompatActivity {
    ImageView back , next ;
    ConstraintLayout uno , dos;
    EditText eDni , eTlf , eNombre, eApellido, eMail, eDir,  eUser, ePwd;
    FloatingActionButton registrar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_cliente);
        getSupportActionBar().setTitle("Registro 1/2");

        eDni = findViewById(R.id.eDni);
        eTlf = findViewById(R.id.etlf);
        eNombre = findViewById(R.id.eName);
        eApellido = findViewById(R.id.eApellido);
        eMail = findViewById(R.id.eMail);
        eDir =  findViewById(R.id.eDir);
        eUser = findViewById(R.id.eUser);
        ePwd =  findViewById(R.id.epwd);
        registrar = findViewById(R.id.registrar);
        back = findViewById(R.id.back);
        next = findViewById(R.id.next);
        uno = findViewById(R.id.DP);
        dos = findViewById(R.id.credenciales);

        back.setOnClickListener(view -> {
            uno.setVisibility(View.VISIBLE);
            dos.setVisibility(View.INVISIBLE);
            getSupportActionBar().setTitle("Registro 1/2");
        });

        next.setOnClickListener(view -> {
            getSupportActionBar().setTitle("Registro 2/2");
            uno.setVisibility(View.INVISIBLE);
            dos.setVisibility(View.VISIBLE);
        });

        registrar.setOnClickListener(view -> {


        });


    }
}