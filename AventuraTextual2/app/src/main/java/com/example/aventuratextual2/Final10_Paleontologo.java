package com.example.aventuratextual2;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import java.util.ArrayList;

public class Final10_Paleontologo extends AppCompatActivity {

    private ImageView fondo, personaje, cuadroTexto, fantasma;

    private int contador = 0, escena = 0;

    ArrayList<String> historia = new ArrayList<String>();

    MediaPlayer cajademusica;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_final10_paleontologo);

        Typewriter txtView2 = findViewById(R.id.type_textWriter);
        txtView2.setCharacterDelay(25); //delay con el que se escriben los caracteres

        //Sonido
        cajademusica = MediaPlayer.create(this, R.raw.theraptorattack);
        cajademusica.start();

        fondo = (ImageView) findViewById(R.id.cuevamarron);
        personaje = (ImageView) findViewById(R.id.personaje);
        cuadroTexto = (ImageView) findViewById(R.id.cuadrotexto);
        fantasma = (ImageView) findViewById(R.id.fantasma);

        personaje.setVisibility(View.INVISIBLE);
        cuadroTexto.setVisibility(View.INVISIBLE);
        fantasma.setVisibility(View.INVISIBLE);

        historia.add("La puerta de la izquierda daba a unas cuevas mucho más profundas que las catacumbas. En cuanto pusieron un pie en el lugar, un escalofrío les recorrió el cuerpo.");
        historia.add("PUAJAJAJA…. Primero tocáis mi libro, os reís de mis dinosaurios y ahora pretendéis robármelos");
        historia.add("No… solo queríamos salir de aquí…");
        historia.add("Sois uno niñatos entrometidos. Pasareis a formar parte de mi colección privada para toda la eternidad");
        historia.add("Pues tu Madre");
        historia.add("Ahora voy a tener que pasar la eternidad entre dinosaurios y arañas");
        historia.add("Teníamos que haber sido más amables…");
        historia.add("El paleontólogo transformó a los jóvenes en fósiles de dinosaurio con una inyección que él mismo había inventado para vengarse de los que lo trataban como loco.");
        historia.add("Y así pasaron a formar parte de la colección de víctimas del paleontólogo y nunca más se supo de ellos.");
        historia.add("");

        fondo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (escena){
                    case 0:
                        cuadroTexto.setVisibility(View.VISIBLE);
                        break;
                    case 1:
                        fantasma.setVisibility(View.VISIBLE);
                        break;
                    case 2:
                        fantasma.setVisibility(View.INVISIBLE);
                        personaje.setVisibility(View.VISIBLE);
                        personaje.setImageResource(R.drawable.tanainsultandoadre);
                        break;
                    case 3:
                        personaje.setVisibility(View.INVISIBLE);
                        fantasma.setVisibility(View.VISIBLE);
                        break;
                    case 4:
                        fantasma.setVisibility(View.INVISIBLE);
                        personaje.setVisibility(View.VISIBLE);
                        personaje.setImageResource(R.drawable.quejicadre);
                        break;
                    case 5:
                        personaje.setImageResource(R.drawable.sadnaila);
                        break;
                    case 6:
                        personaje.setImageResource(R.drawable.sadtana);
                        break;
                    case 7:
                    case 8:
                        personaje.setVisibility(View.INVISIBLE);
                        break;
                    case 9:
                        cajademusica.stop();
                        Intent inten = new Intent(Final10_Paleontologo.this, MainActivity2_Final.class);
                        inten.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(inten);
                        finish();
                        break;
                }
                String text = historia.get(contador);
                txtView2.setCharacterDelay(25);
                txtView2.animatedText(text);
                escena++;
                contador++;
            }
        });
    }
}