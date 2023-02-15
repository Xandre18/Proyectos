package com.example.aventuratextual2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity2_Final extends AppCompatActivity {

    private ImageView fondo;

    private Button salir, jugar;
    MediaPlayer halloween;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_activity2_final);

        fondo = (ImageView) findViewById(R.id.imgPortada);

        halloween = MediaPlayer.create(this,R.raw.thisishalloween);
        halloween.start();

        salir = (Button) findViewById(R.id.buttonsalir);
        jugar = (Button) findViewById(R.id.buttonjugar);

        jugar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Este boton vuelve al primer activity del juego
                halloween.stop();
                Intent intent = new Intent(MainActivity2_Final.this, MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                finish();
            }
        });

        salir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Este boton cierra el activity actual y toda la aplicación
                halloween.stop();
                finish();
                Intent intent = new Intent(Intent.ACTION_MAIN);
                intent.addCategory(Intent.CATEGORY_HOME);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });
    }
}