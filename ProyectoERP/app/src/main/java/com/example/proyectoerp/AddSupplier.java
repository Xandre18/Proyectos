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
    FloatingActionButton addSupplier;
    EditText inputProducto, inputCompañia, inputEmail, inputPhone, inputDir;
    DBHandler handler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_supplier);

        addSupplier = findViewById(R.id.addSupplier);
        inputCompañia = findViewById(R.id.inputCompañia);
        inputDir = findViewById(R.id.inputDir);
        inputEmail = findViewById(R.id.inputEmail);
        inputPhone = findViewById(R.id.inputPhone);
        inputProducto = findViewById(R.id.inputProducto);

        addSupplier.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(inputCompañia.getText().toString().isEmpty() || inputDir.getText().toString().isEmpty() || inputEmail.getText().toString().isEmpty() || inputPhone.getText().toString().isEmpty() || inputProducto.getText().toString().isEmpty()){
                    Toast.makeText(AddSupplier.this, "Faltan requisitos por rellenar", Toast.LENGTH_LONG).show();
                }else{
                    String producto , compañia, email, phone, dir;

                    producto = inputProducto.getText().toString();
                    compañia = inputCompañia.getText().toString();
                    email = inputEmail.getText().toString();
                    phone = inputPhone.getText().toString();
                    dir = inputDir.getText().toString();
                    Supplier s = new Supplier(producto, compañia,email,phone,dir);
                    handler = new DBHandler(AddSupplier.this);
                    handler.addSupplier(s);
                    Toast.makeText(AddSupplier.this, "Añadido correctamente", Toast.LENGTH_SHORT).show();

                    inputProducto.setText("");
                    inputEmail.setText("");
                    inputPhone.setText("");
                    inputDir.setText("");
                    inputCompañia.setText("");

                    Intent intent = new Intent(AddSupplier.this, MainActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);

                }
            }
        });

    }
}