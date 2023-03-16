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

    FloatingActionButton btnAdd; // Referencia al botón flotante de agregar
    EditText cant; // Referencia al campo de texto para ingresar la cantidad
    DBHandler handler; // Instancia de la base de datos personalizada

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_cont); // Establecer el diseño de la actividad

        btnAdd = findViewById(R.id.btnAdd); // Obtener la referencia del botón flotante desde el diseño
        cant = findViewById(R.id.cant); // Obtener la referencia del campo de texto desde el diseño

        // Establecer un escuchador de clics para el botón de agregar
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Obtener la cantidad ingresada como un número entero
                int mount = Integer.parseInt(cant.getText().toString());
                // Instanciar la base de datos personalizada
                handler = new DBHandler(AddCont.this);
                // Agregar el movimiento a la base de datos usando el objeto ContControler
                handler.addCont(new ContControler(mount,true));
                // Mostrar un mensaje de confirmación
                Toast.makeText(AddCont.this, "Movimiento añadido correctamente....", Toast.LENGTH_SHORT).show();
            }
        });

        // Habilitar la flecha hacia atrás en la barra de acción
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

}