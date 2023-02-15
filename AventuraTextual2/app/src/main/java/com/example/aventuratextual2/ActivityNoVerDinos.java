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

public class ActivityNoVerDinos extends AppCompatActivity {
    private ImageView fondo, cuadroTexto , personaje, pExta;
    private Typewriter txt;
    private Button btnBeber , btnNoBeber;
    private int contador = 0;
    private int decision = -1;
    private boolean pasar = true;

    public static int subidon;
    public static int cordura;

    private ProgressBar subid;
    private ProgressBar cordur;

    MediaPlayer cajademusica;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_no_ver_dinos);

        //Coger los valores de subidon y cordura del anterior activity
        Bundle extras = getIntent().getExtras();
        subidon = extras.getInt("subidon");
        cordura = extras.getInt("cordura");

        //Progress bar Subidon
        subid = findViewById(R.id.subidonBar);
        //Progress bar cordura
        cordur = findViewById(R.id.corduraBar);

        //Ponemos las ProgressBar con el progreso del anterior activity
        subid.setProgress(subidon);
        cordur.setProgress(cordura);

        cajademusica = MediaPlayer.create(this, R.raw.sonidocajamusica2);
        cajademusica.start();

        fondo = (ImageView) findViewById(R.id.pasadizo);
        cuadroTexto = findViewById(R.id.ctNoVerDino);
        personaje = findViewById(R.id.dre);
        pExta = findViewById(R.id.fpaleo);
        txt = findViewById(R.id.textView2);
        btnBeber = findViewById(R.id.beber);
        btnNoBeber = findViewById(R.id.noBeber);

        cuadroTexto.setVisibility(ImageView.INVISIBLE);
        personaje.setVisibility(ImageView.INVISIBLE);
        pExta.setVisibility(ImageView.INVISIBLE);
        btnNoBeber.setVisibility(ImageView.INVISIBLE);
        btnBeber.setVisibility(ImageView.INVISIBLE);

        txt.setCharacterDelay(25); // Delay para la velocidad del texto

        fondo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(pasar){
                    switch (contador){
                        case 0:
                            cuadroTexto.setVisibility(ImageView.VISIBLE);
                            txt.animatedText("Se empieza a escuchar de fondo al paleontólogo maldiciendo.");
                            contador ++;
                            break;
                        case 1:
                            pExta.setVisibility(ImageView.VISIBLE);
                            txt.animatedText("Deshonra, deshonra sobre toda tu familia, deshonra sobre ti, deshonra sobre tu vaca.");
                            contador++;
                            break;
                        case 2:
                            pExta.setVisibility(ImageView.INVISIBLE);
                            personaje.setVisibility(ImageView.VISIBLE);
                            txt.animatedText("Ya está el pesado hablando solo.");
                            contador++;
                            break;
                        case 3:
                            personaje.setImageResource(R.drawable.tana);
                            txt.animatedText("Habría que haber sido más amable, a saber qué nos pasará ahora.");
                            contador++;
                            break;
                        case 4:
                            personaje.setImageResource(R.drawable.naila);
                            txt.animatedText("Te imaginas que nos acaba de maldecir el loco de los dinosaurios.");
                            contador++;
                            break;
                        case 5:
                            personaje.setImageResource(R.drawable.tana);
                            txt.animatedText("Me da igual yo creo en las estrellas y ninguna tiene que ver con dinosaurios. ¿Bebemos?");
                            pasar = false;
                            btnBeber.setVisibility(ImageView.VISIBLE);
                            btnNoBeber.setVisibility(ImageView.VISIBLE);
                            contador++;
                            break;
                        case 6:
                            if(decision == 0){
                                personaje.setImageResource(R.drawable.tanainsultandoadre);
                                txt.animatedText("Qué aburrido.");
                                contador ++;
                            }else if(decision == 1){
                                personaje.setImageResource(R.drawable.naila);
                                txt.animatedText("Dale.");
                                contador ++;
                            }
                            break;
                        case 7:
                            if(decision == 0){
                                personaje.setImageResource(R.drawable.naila);
                                txt.animatedText("Aburrido no. Responsable.");
                                contador ++;
                            }else if (decision == 1){
                                personaje.setImageResource(R.drawable.tana);
                                txt.animatedText("Sigamos adelante.");
                                contador ++;
                            }
                            break;
                        case 8:
                            if(decision == 0){
                                personaje.setImageResource(R.drawable.tana);
                                txt.animatedText("Sigamos adelante.");
                                contador ++;
                            }else if (decision == 1){
                                cajademusica.stop();
                                Intent intnt = new Intent(ActivityNoVerDinos.this, Activity9.class);
                                intnt.putExtra("subidon", subidon);
                                intnt.putExtra("cordura", cordura);
                                startActivity(intnt);
                            }

                          break;
                        case 9:
                            if(decision == 0){
                                cajademusica.stop();
                                Intent intnt = new Intent(ActivityNoVerDinos.this, Activity9.class);
                                intnt.putExtra("subidon", subidon);
                                intnt.putExtra("cordura", cordura);
                                startActivity(intnt);
                            }

                    }
                }
            }
        });

        btnBeber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnBeber.setVisibility(ImageView.INVISIBLE);
                btnNoBeber.setVisibility(ImageView.INVISIBLE);
                decision = 1;
                pasar = true;
                subeSubidon();
                subid.setProgress(subidon);
                personaje.setImageResource(R.drawable.dre);
                txt.animatedText("Fooondoooo , foooondoooo.");
            }
        });

        btnNoBeber.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnBeber.setVisibility(ImageView.INVISIBLE);
                btnNoBeber.setVisibility(ImageView.INVISIBLE);
                decision = 0;
                pasar = true;
                personaje.setImageResource(R.drawable.dre);
                txt.animatedText("Vosotras beber si quereis, pero yo tengo que conducir luego.");
            }
        }));
    }

    public static void subeSubidon(){
        int valorSubida = (int) (Math.random()*10+1);
        subidon += valorSubida;
    }

    public static void bajaSubidon(){
        int valorBajada = (int) (Math.random()*10+1);
        if (subidon != 0) {
            if(subidon < 10){
                valorBajada = (int) (Math.random()*subidon+1);
                subidon -= valorBajada;
            }else{
                subidon -= valorBajada;
            }
        }
    }

    public static void bajaCordura() {
        if(cordura > 20){
            int valorBajada = (int) (Math.random()*30+5); //Si no baja muy poco
            cordura = cordura - valorBajada;
        }
    }

    public static void corduraRandom(){
        if(bajaCorduraRandom()){
            int valorBajada = (int) (Math.random()*30+1);
            cordura = cordura - valorBajada;
        }

    }

    //Metodo que saca un número entre el uno y el 5 si el número es menor o igual a 3
    //Entonces la cordura baja y si no se mantiene.
    public static boolean bajaCorduraRandom(){
        int baja = (int) (Math.random()*5+1);

        if(baja <= 3){
            return true;
        } else {
            return false;
        }

    }
}