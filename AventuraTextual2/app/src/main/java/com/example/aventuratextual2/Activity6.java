package com.example.aventuratextual2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;

public class Activity6 extends AppCompatActivity {
    //Declaración de variables
    private ImageView fondo, cuadroTexto , personaje ; //Fondo de pantalla y cuadro de texto
    private Typewriter texto;
    private int contador =0;
    private  boolean pasar = true;
    private Button bsi, bno;
    private int decision = -1;

    private static int subidon;
    private static int cordura = 100;

    private ProgressBar subid;
    private ProgressBar cordur;

    MediaPlayer cajademusica;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_6);

        //Coger los valores de subidon y cordura del anterior activity
        Bundle extras = getIntent().getExtras();
        subidon = extras.getInt("subidon");

        //Sonidos
        cajademusica = MediaPlayer.create(this, R.raw.sonidocajamusica2);
        cajademusica.start();

        //Vincular cada layout con su id
        fondo = findViewById(R.id.fondo);
        cuadroTexto = findViewById(R.id.cuadrotxt);
        personaje = findViewById(R.id.xandre);
        bno = findViewById(R.id.buttonno);
        bsi = findViewById(R.id.buttonsi);
        texto = findViewById(R.id.textView);

        //Progress bar Subidon
        subid = findViewById(R.id.subidonBar);
        //Progress bar cordura
        cordur = findViewById(R.id.corduraBar);

        subid.setProgress(subidon);
        cordur.setProgress(cordura);

        //Poner los layouts invisible para que solo se vea el fondo
        cuadroTexto.setVisibility(ImageView.INVISIBLE);
        personaje.setVisibility(ImageView.INVISIBLE);
        bno.setVisibility(Button.INVISIBLE);
        bsi.setVisibility(Button.INVISIBLE);

        texto.setCharacterDelay(25); // Delay para la velocidad del texto
        fondo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //if para controlar que tome una decisión en el case 1
                if(pasar){
                    switch (contador){
                        case 0:
                            cuadroTexto.setVisibility(ImageView.VISIBLE);
                            personaje.setVisibility(ImageView.VISIBLE);
                            texto.animatedText("Venga chicos ¿Y si vamos todos? Así tiene más gracia, voy yo primero no os rayeis.");
                            contador ++;
                            break;
                        case 1:
                            personaje.setImageResource(R.drawable.tana);
                            texto.animatedText("Si quieres que entre ahí, tengo que beber más.");
                            pasar = false;
                            bno.setVisibility(Button.VISIBLE);
                            bsi.setVisibility(Button.VISIBLE);
                            contador ++;
                            break;

                        case 2:
                            //no
                            if(decision == 0){
                                personaje.setImageResource(R.drawable.tana);
                                texto.animatedText("Que no es eso hombre, así es mas divertido, pero bueno da igual.");
                                contador ++;
                                //si
                            }else if(decision == 1){
                                personaje.setImageResource(R.drawable.naila);
                                texto.animatedText("Xandre coge el ron.");
                                contador ++;
                            }
                            break;
                        case 3:
                            //no
                            if(decision == 0){
                                personaje.setImageResource(R.drawable.naila);
                                texto.animatedText("Xandre coge el ron.");
                                contador ++;
                                //si
                            }else if(decision == 1){
                                personaje.setImageResource(R.drawable.quejicadre);
                                texto.animatedText("Yo nunca me dejo el ron.");
                                contador ++;
                            }
                            break;
                        case 4:
                            //no
                            if(decision == 0 ){
                                personaje.setImageResource(R.drawable.quejicadre);
                                texto.animatedText("Yo nunca me dejo el ron.");
                                contador ++;
                                //si
                            }else if (decision == 1){
                                //cambiar intent
                                personaje.setImageResource(R.drawable.quejicadre);
                                texto.animatedText("Vamos.");
                                contador ++;
                                cajademusica.stop();
                                Intent intent = new Intent(Activity6.this, InteriorMausoleo7.class);
                                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                intent.putExtra("subidon", subidon);
                                intent.putExtra("cordura", cordura);
                                startActivity(intent);
                                finish();

                            }
                            break;

                        case 5:
                                cajademusica.stop();
                                Intent intento = new Intent(Activity6.this, InteriorMausoleo7.class);
                                                                intento.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                                                intento.putExtra("subidon", subidon);
                                                                intento.putExtra("cordura", cordura);
                                                                startActivity(intento);
                                                                finish();
                    }
                }
            }
        });

        //Boton por si decide no beber
        bno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bno.setVisibility(Button.INVISIBLE);
                bsi.setVisibility(Button.INVISIBLE);
                decision = 0;
                pasar = true;
                texto.animatedText("Aitana, no seas miedica no va a pasar nada, vamos.");
                personaje.setImageResource(R.drawable.quejicadre);
               // Descomentar si queremos que baje
               // bajaSubidon();
               // subid.setProgress(subidon);

            }
        });
        //Boton por si decide beber
        bsi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bno.setVisibility(Button.INVISIBLE);
                bsi.setVisibility(Button.INVISIBLE);
                decision = 1;
                pasar = true;
                texto.animatedText("Dale.");
                personaje.setImageResource(R.drawable.quejicadre);
                subeSubidon();
                subid.setProgress(subidon);

            }
        });
    }

    public static void subeSubidon(){
        int valorSubida = (int) (Math.random()*25+1);
        subidon += valorSubida;
    }

    public static void bajaSubidon(){
        int valorBajada = (int) (Math.random()*25+1);
        if (subidon != 0) {
            if(subidon < 25){
                valorBajada = (int) (Math.random()*subidon+1);
                subidon -= valorBajada;
            }else{
                subidon -= valorBajada;
            }
        }
    }

    public static void bajaCordura() {
        if(cordura > 20){
            int valorBajada = (int) (Math.random()*30+1);
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