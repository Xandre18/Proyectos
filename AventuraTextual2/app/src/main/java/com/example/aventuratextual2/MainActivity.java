package com.example.aventuratextual2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.Image;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    private ImageView fondo, cuadroTexto; //Fondo de pantalla y cuadro de texto

    private int contador = 0; //contador para almacenar las veces que se pulsa la pantalla y las frases que se mostrarán del ArrayList

    MediaPlayer cajademusica;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fondo = (ImageView) findViewById(R.id.imageView);
        cuadroTexto = (ImageView) findViewById(R.id.imageView2);
        cajademusica = MediaPlayer.create(this, R.raw.sonidocajamusica);
        cajademusica.start();

        //ArrayList con las frases de la historia en orden para que se vayan reproduciendo
        ArrayList <String> historia = new ArrayList<String>();
        historia.add("Es el día de Halloween, un grupo de amigos decide celebrarlo, pero ya son un poco mayores " +
                "para los disfraces y este año les apetece hacer algo diferente.");
        historia.add("Llevaban días pensando qué hacer para que fuera especial y tener un recuerdo memorable.");
        historia.add("Se les ocurrió que era una buena idea celebrarlo en el cementerio, contando historias de terror, probando a hacer rituales y ,por supuesto, bebiendo.");

        cuadroTexto.setVisibility(View.INVISIBLE);

        //Texto de la fecha
        Typewriter txtView = findViewById(R.id.type_Characters);
        txtView.setCharacterDelay(60);
        txtView.animatedText("31 de Octubre");

        //Timer para que pasados 4 segundos desaparezca la fecha
        super.onStart();
        final Timer t = new Timer();
        t.schedule(new TimerTask() {
            @Override
            public void run() {
                txtView.setVisibility(Typewriter.INVISIBLE);
            }
        }, 4000);

        //Cada vez que se pulse el fondo se ira sumando a un contador y reproducirá el texto guardado en el ArrayList segun el contador
        //Si las veces que se pulsa la pantalla llega a 3 se pasará al siguiente activity.
        fondo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(contador < 3){
                    cuadroTexto.setVisibility(View.VISIBLE);
                    String text = historia.get(contador);
                    Typewriter texto = findViewById(R.id.type_textView);
                    texto.setCharacterDelay(25);
                    texto.animatedText(text);
                    contador++;
                } else if(contador == 3){
                    cajademusica.stop();
                    Intent intent = new Intent(MainActivity.this, MainActivity2.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                    finish();
                }
            }
        });
    }
}