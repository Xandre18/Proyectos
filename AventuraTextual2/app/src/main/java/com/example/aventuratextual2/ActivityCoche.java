package com.example.aventuratextual2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;

public class ActivityCoche extends AppCompatActivity {
    private int contador;
    private ImageView fondo, cuadroTexto, personaje ;
    private Typewriter txt;

    MediaPlayer cajademusica;
    MediaPlayer trafico;
    MediaPlayer accidente;
    MediaPlayer musica;

    public static int subidon;
    public static int cordura;

    private ProgressBar subid;
    private ProgressBar cordur;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coche);
        boolean mate;

        Bundle extras = getIntent().getExtras();
        subidon = extras.getInt("subidon");
        cordura = extras.getInt("cordura");
        mate = extras.getBoolean("mate");

        fondo = findViewById(R.id.fondoCoche);
        cuadroTexto = findViewById(R.id.cuadrotexto);
        personaje = findViewById(R.id.imagedre);
        txt = findViewById(R.id.type_textWriter);
        fondo.setImageResource(R.drawable.cementeriotumba);

        //Progress bar Subidon
        subid = findViewById(R.id.subidonBar);
        //Progress bar cordura
        cordur = findViewById(R.id.corduraBar);

        //Ponemos las ProgressBar con el progreso del anterior activity
        subid.setProgress(subidon);
        cordur.setProgress(cordura);

        //Sonidos
        cajademusica = MediaPlayer.create(this, R.raw.barbiegirll);
        trafico = MediaPlayer.create(this, R.raw.direcciongeneraldetrafico);
        accidente = MediaPlayer.create(this, R.raw.accidente);
        musica = MediaPlayer.create(this, R.raw.sonidocajamusica2);
        musica.start();

        cuadroTexto.setVisibility(ImageView.INVISIBLE);
        personaje.setVisibility(ImageView.INVISIBLE);

        txt.setCharacterDelay(25); // Delay para la velocidad del texto


        fondo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(subidon > 60){
                    switch (contador){
                        case 0:
                            personaje.setVisibility(ImageView.VISIBLE);
                            cuadroTexto.setVisibility(ImageView.VISIBLE);
                            txt.setVisibility(Typewriter.VISIBLE);
                            txt.animatedText("Bueno bro se hizo tarde nosotros nos vamos.");
                            contador ++;
                            break;
                        case 1:
                            personaje.setImageResource(R.drawable.naila);
                            txt.animatedText("Bye bye.");
                            contador ++;
                            break;
                        case 2:
                            personaje.setImageResource(R.drawable.tana);
                            txt.animatedText("Estuvo bien conocerte pero deberíamos volver ya.");
                            contador ++;
                            break;
                        case 3:
                            musica.stop();
                            fondo.setImageResource(R.drawable.coche);
                            personaje.setVisibility(View.INVISIBLE);
                            cajademusica.start();
                            txt.animatedText("Se dirigen al coche de Xandre. Se suben y ponen música.");
                            contador ++;
                            break;
                        case 4:
                            personaje.setVisibility(ImageView.INVISIBLE);
                            txt.animatedText("Los chavales se dirigen a sus casas, pero de repente se les cruza una vaca por el camino y tienen un grave accidente de coche.");
                            cajademusica.pause();
                            accidente.start();
                            contador ++;
                            break;
                        case 5:
                            personaje.setVisibility(ImageView.VISIBLE);
                            personaje.setImageResource(R.drawable.israel);
                            txt.animatedText("JAJAJA, os caería bien pero el mate ese no era buena idea tomarlo.");
                            contador ++;
                            break;
                        case 6:
                            accidente.stop();
                            personaje.setVisibility(ImageView.INVISIBLE);
                            txt.animatedText("Por conducir bebidos estaban condenados a deambular por el cementerio toda la eternidad. Quedando atrapados con los fantasmas del cementerio.");
                            contador ++;
                            break;
                        case 7:
                            trafico.start();
                            txt.animatedText("Recuerda, si bebes no conduzcas. Dirección General de Tráfico.");
                            contador++;
                            break;
                        case 8:
                            trafico.stop();
                            Intent intnt = new Intent(ActivityCoche.this, MainActivity2_Final.class);
                            startActivity(intnt);
                            break;
                    }
                }else{
                    switch (contador){
                        case 0:
                            cuadroTexto.setVisibility(ImageView.VISIBLE);
                            txt.animatedText("Los muchachos se despiden del fantasma y se dirigen al coche.");
                            contador++;
                            break;
                        case 1:
                            fondo.setImageResource(R.drawable.coche);
                            personaje.setVisibility(ImageView.VISIBLE);
                            txt.setVisibility(Typewriter.VISIBLE);
                            if (mate){
                                txt.animatedText("Ese mate me mato. Yo no estoy para conducir ahora.");
                            }else{
                                txt.animatedText("Ese ron me mato. Yo no estoy para conducir ahora.");
                            }
                            contador++;
                            break;
                        case 2:
                            personaje.setImageResource(R.drawable.naila);
                            txt.animatedText("Pues no queda otra que dormir en el coche.");
                            contador++;
                            break;
                        case 3:
                            personaje.setImageResource(R.drawable.tana);
                            txt.animatedText("Y una mierda, yo me voy andando.");
                            contador ++;
                            break;
                        case 4:
                            personaje.setImageResource(R.drawable.quejicadre);
                            txt.animatedText("Como quieras yo voy a sobar. Además está amaneciendo.");
                            contador ++;
                            break;
                        case 5:
                            personaje.setImageResource(R.drawable.naila);
                            txt.animatedText("Aitana es mejor dormir en el coche que caminar por el bosque sola. Te puedes encontrar con algún otro fantasma como los anteriores. ¿Estás segura de que quieres eso?");
                            contador++;
                            break;
                        case 6:
                            personaje.setImageResource(R.drawable.tana);
                            txt.animatedText("Tienes razón. Mejor me quedo aquí.");
                            contador ++;
                            break;
                        case 7:
                            cajademusica.stop();
                            personaje.setVisibility(ImageView.INVISIBLE);
                            txt.animatedText("Se pasaron toda la mañana durmiendo en el coche después de una buena noche de Halloween.");
                            contador ++;
                            break;
                        case 8:
                            musica.stop();
                            Intent intnt = new Intent(ActivityCoche.this, MainActivity2_Final.class);
                            startActivity(intnt);
                            break;

                    }
                }
            }
        });
    }
}