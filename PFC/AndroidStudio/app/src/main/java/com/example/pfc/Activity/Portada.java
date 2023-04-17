package com.example.pfc.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.pfc.R;

import java.util.Timer;
import java.util.TimerTask;

public class Portada extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.portada);
        //Ocultar toolbar
        getSupportActionBar().hide();
        //Timer para pasar de activity
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




    }
}