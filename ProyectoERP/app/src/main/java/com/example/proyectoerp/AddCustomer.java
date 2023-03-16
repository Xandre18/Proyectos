package com.example.proyectoerp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.proyectoerp.objects.Cliente;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class AddCustomer extends AppCompatActivity {
    // Declaración de variables
    FloatingActionButton addCustomer;
    EditText inputNombre,  inputApellido,inputEdad, inputTel, inputEmail;
    DBHandler handler;

    // Método que se ejecuta al crear la actividad
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_customer);

        // Vinculación de los elementos del layout con las variables
        addCustomer = findViewById(R.id.addCustomer);
        inputNombre = findViewById(R.id.inputNombre);
        inputApellido = findViewById(R.id.inputApellido);
        inputEdad = findViewById(R.id.inputEdad);
        inputTel = findViewById(R.id.inputTel);
        inputEmail = findViewById(R.id.inputEmail);

        // Acción que se realiza al pulsar el botón de añadir cliente
        addCustomer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Verificación de que los campos están completos
                if(inputNombre.getText().toString().isEmpty() || inputApellido.getText().toString().isEmpty() || inputEdad.getText().toString().isEmpty()
                        || inputEmail.getText().toString().isEmpty() || inputTel.getText().toString().isEmpty()){
                    Toast.makeText(AddCustomer.this, "Faltan requisitos por rellenar", Toast.LENGTH_LONG).show();
                }else{
                    // Creación de un nuevo cliente a partir de los datos introducidos
                    String nombre = inputNombre.getText().toString();
                    String apellido = inputApellido.getText().toString();
                    String edad = inputEdad.getText().toString();
                    String email = inputEmail.getText().toString();
                    int tel = Integer.parseInt(inputTel.getText().toString());
                    Cliente c = new Cliente(nombre, apellido, edad, email, tel);

                    // Inserción del cliente en la base de datos
                    handler = new DBHandler(AddCustomer.this);
                    handler.addCustomer(c);

                    // Mensaje de éxito y borrado de los campos
                    Toast.makeText(AddCustomer.this, "Añadido correctamente", Toast.LENGTH_SHORT).show();
                    inputNombre.setText("");
                    inputApellido.setText("");
                    inputEdad.setText("");
                    inputTel.setText("");
                    inputEmail.setText("");

                    // Redirección a la actividad principal
                    Intent intent = new Intent(AddCustomer.this, MainActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                }
            }
        });
    }
}