package com.example.proyectoerp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import java.util.Timer;
import java.util.TimerTask;

public class Portada extends AppCompatActivity {
    ImageView portada;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.portada);

        portada = findViewById(R.id.portada);
        //Ocultar toolbar
        getSupportActionBar().hide();

        Timer t = new Timer();

        t.schedule(new TimerTask() {
            @Override
            public void run() {
                Intent intent = new Intent(Portada.this, Login.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                finish();
            }
        },4000);

        portada.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                t.cancel();
                Intent intent = new Intent(Portada.this, Login.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                finish();
            }
        });
    }
}