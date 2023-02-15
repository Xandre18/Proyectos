package com.example.aventuratextual2;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;

import java.util.ArrayList;

public class PasilloCalaveras10 extends AppCompatActivity {

    private ImageView fondo, personaje, cuadroTexto, fantasma;

    private Button izquierda, derecha;

    private int contador = 0, escena = 0, contaux = 0;
    private int decision = 3;

    private boolean pasar = true;

    public static int subidon;
    public static int cordura;

    //Progress Bar de subidon y cordura
    private ProgressBar subid;
    private ProgressBar cordur;

    MediaPlayer cajademusica;

    ArrayList<String> historia = new ArrayList<String>();
    ArrayList<String> historiaIzquierda = new ArrayList<String>();
    ArrayList <String> aux = historia;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pasillo_calaveras10);

        //Coger los valores de subidon y cordura del anterior activity
        Bundle extras = getIntent().getExtras();
        subidon = extras.getInt("subidon");
        cordura = extras.getInt("cordura");

        //Sonido
        cajademusica = MediaPlayer.create(this, R.raw.musica);
        cajademusica.start();

        //Progress bar Subidon
        subid = findViewById(R.id.subidonBar);
        //Progress bar cordura
        cordur = findViewById(R.id.corduraBar);

        subid.setProgress(subidon);
        cordur.setProgress(cordura);

        Typewriter txtView2 = findViewById(R.id.type_textWriter);
        txtView2.setCharacterDelay(25); //delay con el que se escriben los caracteres

        fondo = (ImageView) findViewById(R.id.pasadizocalaveras);
        personaje = (ImageView) findViewById(R.id.personaje);
        cuadroTexto = (ImageView) findViewById(R.id.cuadrotexto);
        fantasma = (ImageView) findViewById(R.id.fantasma);

        izquierda = (Button) findViewById(R.id.buttonizquierda);
        derecha = (Button) findViewById(R.id.buttonderecha);

        personaje.setVisibility(View.INVISIBLE);
        cuadroTexto.setVisibility(View.INVISIBLE);
        fantasma.setVisibility(View.INVISIBLE);
        izquierda.setVisibility(View.INVISIBLE);
        derecha.setVisibility(View.INVISIBLE);

        historia.add("Llegan a un pasillo en el cual las paredes están adornadas con calaveras de todo tipo.");
        historia.add("¿Habéis visto esas calaveras en las paredes? ¿Qué os parece si salimos de aquí?");
        historia.add("Continuemos hasta el fondo de este pasillo a ver si encontramos la salida.");
        historia.add("¿Y si queremos salir no es mejor volver por donde vinimos?");
        historia.add("Y encontrarnos con el fantasma otra vez? Ni de coña.");
        historia.add("Continúan caminando hasta el final del pasillo, ojeando las paredes de vez en cuando.");
        historia.add("Al final de este se encuentran dos puertas.");
        historia.add("¿Qué puerta deben escoger?");
        historia.add("");


        historiaIzquierda.add("Esto no tiene pinta de salida y si vamos por el otro lado además la puerta no tiene barrotes.");
        historiaIzquierda.add("");

        fondo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(pasar){
                    switch (escena){
                        case 0:
                            cuadroTexto.setVisibility(View.VISIBLE);
                            break;
                        case 1:
                            personaje.setVisibility(View.VISIBLE);
                            personaje.setImageResource(R.drawable.nailaechandolabronca);
                            break;
                        case 2:
                            personaje.setImageResource(R.drawable.tana);
                            break;
                        case 3:
                            personaje.setImageResource(R.drawable.quejicadre);
                            break;
                        case 4:
                            personaje.setImageResource(R.drawable.nailaechandolabronca);
                            break;
                        case 5:
                        case 6:
                            personaje.setVisibility(View.INVISIBLE);
                            break;
                        case 7:
                            pasar = false;
                            fondo.setImageResource(R.drawable.puertaseleccionmazmorra);
                            izquierda.setVisibility(View.VISIBLE);
                            derecha.setVisibility(View.VISIBLE);
                            break;
                        case 8:
                            //Decision
                            //1 = izquierda
                            //0 = derecha
                            //No van estos intents, La aplicación peta y vuelve al principio.
                            if(decision == 1){
                                if(cordura < 60){
                                    cajademusica.stop();
                                    Intent intent = new Intent(PasilloCalaveras10.this, Final10_Paleontologo.class);
                                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                    startActivity(intent);
                                    finish();
                                } else if (cordura > 60) {
                                    contaux = contador;
                                    contador = 0;
                                    aux = historiaIzquierda;
                                    personaje.setVisibility(View.VISIBLE);
                                    personaje.setImageResource(R.drawable.nailaechandolabronca);
                                    cajademusica.stop();
                                }
                            } else if (decision == 0){

                                Intent intent = new Intent(PasilloCalaveras10.this, Escena11.class);
                                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                intent.putExtra("subidon", subidon);
                                intent.putExtra("cordura", cordura);
                                startActivity(intent);
                                finish();
                                cajademusica.stop();
                            }
                            break;
                        case 9:
                            Intent intent = new Intent(PasilloCalaveras10.this, Escena11.class);
                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            intent.putExtra("subidon", subidon);
                            intent.putExtra("cordura", cordura);
                            startActivity(intent);
                            finish();
                            break;
                    }
                    String text = aux.get(contador);
                    txtView2.setCharacterDelay(25);
                    txtView2.animatedText(text);
                    escena++;
                    contador++;
                }
            }
        });

        izquierda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                decision = 1;
                pasar = true;
                izquierda.setVisibility(View.INVISIBLE);
                derecha.setVisibility(View.INVISIBLE);

                personaje.setVisibility(View.VISIBLE);
                personaje.setImageResource(R.drawable.quejicadre);
                txtView2.animatedText("¿Qué os parece la puerta de la izquierda?");

            }
        });

        derecha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                decision = 0;
                pasar = true;
                izquierda.setVisibility(View.INVISIBLE);
                derecha.setVisibility(View.INVISIBLE);
                personaje.setVisibility(View.VISIBLE);
                personaje.setImageResource(R.drawable.naila);
                txtView2.animatedText("Mejor pillamos la derecha que la puerta está en mejor estado.");

            }
        });

    }

}