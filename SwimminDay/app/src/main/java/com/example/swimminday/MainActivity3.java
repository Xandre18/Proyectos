package com.example.swimminday;


import static com.google.android.material.internal.ContextUtils.getActivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class MainActivity3 extends AppCompatActivity {
    List<Entrenamiento> entrenamientos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        getSupportActionBar().hide();

        TextView tvName = findViewById(R.id.tvName);
        TextView tvPeso = findViewById(R.id.tvpeso);
        TextView tvAltura = findViewById(R.id.tvalt);
        TextView tvEdad = findViewById(R.id.tvedad);
        FloatingActionButton btnAñadir = findViewById(R.id.bnt);


        SharedPreferences prefs =getSharedPreferences("Nadadores", Context.MODE_PRIVATE);

        String ValortvName = prefs.getString("name", "Default");
        String ValortvPeso = prefs.getString("pes", "Default");
        String ValortvAltura = prefs.getString("alt", "Default");
        String Valortvedad = prefs.getString("ed", "Default");

        tvName.setText(ValortvName);
        tvAltura.setText(ValortvAltura + "cm");
        tvPeso.setText(ValortvPeso + "Km");
        tvEdad.setText(Valortvedad + " años");
        init();

        btnAñadir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity3.this, MainActivity4.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                finish();
            }
        });


    }
    public void init(){
        entrenamientos = new ArrayList<>();
        entrenamientos.add(new Entrenamiento("15/12/2022", 50, 500 ));
        entrenamientos.add(new Entrenamiento("16/12/2022", 60, 750 ));
        entrenamientos.add(new Entrenamiento("17/12/2022", 120, 1500 ));
        entrenamientos.add(new Entrenamiento("18/12/2022", 50, 500 ));
        entrenamientos.add(new Entrenamiento("19/12/2022", 40, 300 ));
        entrenamientos.add(new Entrenamiento("19/12/2022", 40, 300 ));
        entrenamientos.add(new Entrenamiento("19/12/2022", 40, 300 ));
        entrenamientos.add(new Entrenamiento("19/12/2022", 40, 300 ));
        entrenamientos.add(new Entrenamiento("19/12/2022", 40, 300 ));

        Bundle bndl = getIntent().getExtras();
        String fecha = bndl.getString("fecha");

        if(!fecha.equals("null")){
            int time = bndl.getInt("tiempo");
            int distancia = bndl.getInt("distancia");
            entrenamientos.add(new Entrenamiento(fecha,time, distancia));
        }

        EntrenoAdapter adaptador = new EntrenoAdapter(entrenamientos, this);
        RecyclerView recycle = findViewById(R.id.res);
        recycle.setHasFixedSize(true);
        recycle.setLayoutManager(new LinearLayoutManager(this));
        recycle.setAdapter(adaptador);

        adaptador.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity3.this);
                builder.setMessage(entrenamientos.get(recycle.getChildAdapterPosition(view)).toString());
                AlertDialog juan = builder.create();
                juan.show();
            }
        });
    }
}