package com.example.proyectoerp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.proyectoerp.objects.ContControler;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class AddCont extends AppCompatActivity {

    FloatingActionButton btnAdd;
    EditText cant;
    DBHandler handler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_cont);

        btnAdd = findViewById(R.id.btnAdd);
        cant = findViewById(R.id.cant);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int mount = Integer.parseInt(cant.getText().toString());
                handler = new DBHandler(AddCont.this);

                handler.addCont(new ContControler(mount,true));

                Toast.makeText(AddCont.this, "Movimiento añadido correctamente....", Toast.LENGTH_SHORT).show();
            }
        });

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);



    }

}