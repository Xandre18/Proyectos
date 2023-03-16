package com.example.proyectoerp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.proyectoerp.objects.Supplier;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class AddSupplier extends AppCompatActivity {
    // Declaración de variables para los elementos de la interfaz gráfica
    FloatingActionButton addSupplier;
    EditText inputProducto, inputCompañia, inputEmail, inputPhone, inputDir;
    DBHandler handler;

    // Método onCreate que se ejecuta al crear la actividad
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_supplier);

        // Asignación de las variables a los elementos de la interfaz gráfica mediante sus IDs
        addSupplier = findViewById(R.id.addSupplier);
        inputCompañia = findViewById(R.id.inputCompañia);
        inputDir = findViewById(R.id.inputDir);
        inputEmail = findViewById(R.id.inputEmail);
        inputPhone = findViewById(R.id.inputPhone);
        inputProducto = findViewById(R.id.inputProducto);

        // Configuración del botón de añadir proveedor
        addSupplier.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Comprobación de si se han rellenado todos los campos
                if(inputCompañia.getText().toString().isEmpty() || inputDir.getText().toString().isEmpty() || inputEmail.getText().toString().isEmpty() || inputPhone.getText().toString().isEmpty() || inputProducto.getText().toString().isEmpty()){
                    Toast.makeText(AddSupplier.this, "Faltan requisitos por rellenar", Toast.LENGTH_LONG).show();
                }else{

                    // Obtención de los datos introducidos por el usuario
                    String producto , compañia, email, phone, dir;
                    producto = inputProducto.getText().toString();
                    compañia = inputCompañia.getText().toString();
                    email = inputEmail.getText().toString();
                    phone = inputPhone.getText().toString();
                    dir = inputDir.getText().toString();

                    // Creación del objeto Supplier con los datos obtenidos y llamada al método addSupplier de la clase DBHandler para añadirlo a la base de datos
                    Supplier s = new Supplier(producto, compañia,email,phone,dir);
                    handler = new DBHandler(AddSupplier.this);
                    handler.addSupplier(s);

                    // Mostramos un mensaje de confirmación al usuario
                    Toast.makeText(AddSupplier.this, "Añadido correctamente", Toast.LENGTH_SHORT).show();

                    // Limpiamos los campos de la interfaz gráfica
                    inputProducto.setText("");
                    inputEmail.setText("");
                    inputPhone.setText("");
                    inputDir.setText("");
                    inputCompañia.setText("");

                    // Redirigimos al usuario a la actividad principal
                    Intent intent = new Intent(AddSupplier.this, MainActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                }
            }
        });
    }
}