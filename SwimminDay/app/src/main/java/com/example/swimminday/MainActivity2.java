package com.example.swimminday;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;

public class MainActivity2 extends AppCompatActivity {
    private TextInputEditText nombre, altura, peso, edad;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        getSupportActionBar().hide();
         nombre = findViewById(R.id.inputName);
         altura =findViewById(R.id.inputAlt);
         peso = findViewById(R.id.inputPeso);
         edad = findViewById(R.id.inputEdad);

        FloatingActionButton enviar = findViewById(R.id.bnt);

        enviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name, alt, pes, ed;
                if (nombre.getText().toString().isEmpty() || altura.getText().toString().isEmpty() || peso.getText().toString().isEmpty()
                    || edad.getText().toString().isEmpty()){
                    Toast.makeText(MainActivity2.this, "Faltan requisitos por rellenar", Toast.LENGTH_LONG).show();
                }else{
                    escribirUser();
                    Intent intent = new Intent(MainActivity2.this, MainActivity3.class);
                    intent.putExtra("fecha" , "null");
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                    finish();
                }

            }
        });
    }
    public void escribirUser(){
        String name = nombre.getText().toString();
        String alt = altura.getText().toString();
        String pes = peso.getText().toString();
        String ed = edad.getText().toString();

        SharedPreferences pref = getSharedPreferences("Nadadores", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString("name" , name);
        editor.putString("alt", alt);
        editor.putString("pes", pes);
        editor.putString("ed", ed);
        editor.commit();
    };
}