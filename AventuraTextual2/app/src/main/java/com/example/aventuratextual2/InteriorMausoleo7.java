package com.example.aventuratextual2;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.ProgressBar;
import android.widget.VideoView;

import java.util.ArrayList;

public class InteriorMausoleo7 extends AppCompatActivity {

    private ImageView fondo, cuadrotexto, personaje;

    private VideoView videoView;

    private int contador = 0, escena = 0, contaux = 0;

    private Button button1, button2, buttonizquierda, buttonderecha;

    private int decision = 3;

    MediaPlayer cajademusica;

    private boolean pasar = true;
    public static int subidon;
    public static int cordura;

    private ProgressBar subid;
    private ProgressBar cordur;

    ArrayList <String> historia = new ArrayList<String>();
    ArrayList <String> cogerLibro = new ArrayList<String>();
    ArrayList <String> noCogerLibro = new ArrayList<String>();
    ArrayList <String> izquierda = new ArrayList<String>();
    ArrayList <String> derecha = new ArrayList<String>();
    ArrayList <String> aux = historia;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_interior_mausoleo7);

        //Coger los valores de subidon y cordura del anterior activity
        Bundle extras = getIntent().getExtras();
        subidon = extras.getInt("subidon");
        cordura = extras.getInt("cordura");

        //Sonido
        cajademusica = MediaPlayer.create(this, R.raw.sonidomusica);
        cajademusica.start();

        //Video
        videoView = (VideoView) findViewById(R.id.videoView);
        Uri uri = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.zuzto);
        videoView.setVisibility(View.INVISIBLE);

        //Progress bar Subidon
        subid = findViewById(R.id.subidonBar);
        //Progress bar cordura
        cordur = findViewById(R.id.corduraBar);

        //Ponemos las ProgressBar con el progreso del anterior activity
        subid.setProgress(subidon);
        cordur.setProgress(cordura);

        //Imagenes
        fondo = (ImageView) findViewById(R.id.interiormausoleo);
        cuadrotexto = (ImageView) findViewById(R.id.cuadrotexto);
        personaje = (ImageView) findViewById(R.id.personaje);

        //Botones
        button1 = (Button) findViewById(R.id.buttonsi);
        button2= (Button) findViewById(R.id.buttonno);
        buttonizquierda = (Button) findViewById(R.id.buttonizquierda);
        buttonderecha = (Button) findViewById(R.id.buttonderecha);

        //Ponemos en invisible todos los botones y las imagenes,  menos el fondo
        personaje.setVisibility(View.INVISIBLE);
        cuadrotexto.setVisibility(View.INVISIBLE);
        button1.setVisibility(View.INVISIBLE);
        button2.setVisibility(View.INVISIBLE);
        buttonizquierda.setVisibility(View.INVISIBLE);
        buttonderecha.setVisibility(View.INVISIBLE);

        Typewriter txtView2 = findViewById(R.id.type_textWriter);
        txtView2.setCharacterDelay(25); //delay con el que se escriben los caracteres

        //Historia
        historia.add("El interior está sucio y lleno de telarañas, hacía mucho que nadie entraba aquí.");
        historia.add("Hay un libro encima de una repisa. ¿Lo coges?");
        cogerLibro.add("A ver que es.");
        cogerLibro.add("No sé, tiene escritas cosas chungas en latín creo, no entiendo nada, vaya mierda.");
        cogerLibro.add("Me llevo el libro.");
        cogerLibro.add("Mejor déjalo donde estaba.");
        noCogerLibro.add("Aitana coge el libro y empieza a ojearlo. Las tapas están hechas de piel de cordero, las hojas son de un color tostado y están algo estropeadas.");
        noCogerLibro.add("Está escrito en sánscrito o alguna lengua así, porque latin no es, ¡Como mola!");
        noCogerLibro.add("Nos quedamos el libro.");
        noCogerLibro.add("Será mejor dejar el libro donde estaba.");
        historia.add("Una de estas dos puerta conduce a la catacumbas ¿Cuál escogemos?");
        izquierda.add("Sigue la luz.");
        izquierda.add("Lo de seguir las luces nunca sale bien que puede ser la policía.");
        izquierda.add("O las luces de Navidad de Abel Caballero.");
        izquierda.add("A ver dejaros de tonterías ya que estamos aquí habrá que bajar.");
        izquierda.add("Comienzan a bajar las escaleras lentamente, aproximándose a la tenue luz del fondo.");
        izquierda.add("Podía apreciarse la expresión de terror en los rostros de aquellos muchachos al recorrer el tenebroso pasadizo.");
        izquierda.add("");
        derecha.add("¿Qué coño es eso?");
        derecha.add("Qué miedooo!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        derecha.add("Mira un perro inválido.");
        derecha.add("Vale, después de que firulais casi nos matara del susto habrá que seguir este pasadizo, no?");
        derecha.add("Yo estoy con Naila, tira pa dentro.");
        derecha.add("¿No os parece raro que apareciera eso después de tocar el libro?");
        derecha.add("");


        fondo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(pasar){
                    switch(escena){
                        case 0:
                            cuadrotexto.setVisibility(View.VISIBLE);
                            break;
                        case 1:
                            pasar = false;
                            button1.setVisibility(View.VISIBLE);
                            button2.setVisibility(View.VISIBLE);
                            break;
                        case 2:
                            contaux = contador;
                            contador = 0;
                            // 1 = coger el libro, 0 = no coge el libro
                            //Si la decision es igual a una si coge el libro
                            if(decision == 1){
                                aux = cogerLibro;
                                personaje.setVisibility(View.VISIBLE);
                                personaje.setImageResource(R.drawable.tanainsultandoadre);
                                //Si la decision es igual a dos no coge el libro
                            } else if(decision == 0){
                                aux = noCogerLibro;
                                personaje.setVisibility(View.INVISIBLE);
                            }
                            break;
                        case 3:
                            if(decision == 1){
                                personaje.setImageResource(R.drawable.quejicadre);
                            } else if(decision == 0){
                                personaje.setVisibility(View.VISIBLE);
                                personaje.setImageResource(R.drawable.tana);
                            }
                            break;
                        case 4:
                            if(decision == 1){
                                personaje.setImageResource(R.drawable.tana);
                            }else if (decision == 0){
                                personaje.setImageResource(R.drawable.tanainsultandoadre);
                            }
                            break;
                        case 5:
                            if(decision == 1){
                                personaje.setImageResource(R.drawable.nailaechandolabronca);
                            }else if(decision == 0){
                                personaje.setImageResource(R.drawable.nailaechandolabronca);
                            }
                            break;
                        case 6:
                            decision = 3;
                            contador = contaux;
                            aux = historia;
                            personaje.setImageResource(R.drawable.tanainsultandoadre);
                            fondo.setImageResource(R.drawable.puertaseleccion);
                            pasar = false;
                            buttonizquierda.setVisibility(View.VISIBLE);
                            buttonderecha.setVisibility(View.VISIBLE);
                            break;
                        case 7:
                            //Decision = 0 izquierda
                            //Decision = 1 derecha
                            contaux = contador;
                            contador = 0;
                            if(decision == 0){
                                aux = izquierda;
                                personaje.setVisibility(View.VISIBLE);
                                personaje.setImageResource(R.drawable.tana);
                            } else if(decision == 1){
                                cajademusica.start();
                                aux = derecha;
                                cuadrotexto.setVisibility(View.VISIBLE);
                                personaje.setVisibility(View.VISIBLE);
                                personaje.setImageResource(R.drawable.sustonaila);

                            }
                            break;
                        case 8:
                            //Decision = 0 izquierda
                            //Decision = 1 derecha
                            if(decision == 0){
                                personaje.setImageResource(R.drawable.quejicadre);
                            } else if(decision == 1){

                                personaje.setVisibility(View.VISIBLE);
                                personaje.setImageResource(R.drawable.sustotana);
                            }
                            break;
                        case 9:
                            //Decision = 0 izquierda
                            //Decision = 1 derecha
                            if(decision == 0){
                                personaje.setImageResource(R.drawable.happytana);
                            } else if(decision == 1){
                                personaje.setImageResource(R.drawable.quejicadre);
                            }
                            break;
                        case 10:
                            //Decision = 0 izquierda
                            //Decision = 1 derecha
                            if(decision == 0){
                                personaje.setImageResource(R.drawable.nailaechandolabronca);
                            } else if(decision == 1){
                                personaje.setImageResource(R.drawable.naila);
                            }
                            break;
                        case 11:
                            //Decision = 0 izquierda
                            //Decision = 1 derecha
                            if(decision == 0){
                                personaje.setVisibility(View.INVISIBLE);
                            } else if(decision == 1){
                                personaje.setImageResource(R.drawable.quejicadre);
                            }
                            break;
                        case 12:
                            //Decision = 0 izquierda
                            //Decision = 1 derecha
                            if(decision == 0){
                                personaje.setVisibility(View.INVISIBLE);
                            } else if(decision == 1){
                                personaje.setImageResource(R.drawable.tanainsultandoadre);
                            }
                            break;
                        case 13:
                            cajademusica.stop();
                            Intent intent = new Intent(InteriorMausoleo7.this, Pasadizo8.class);
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

        //Button decision si coge el libro
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                decision = 1;
                pasar = true;
                button1.setVisibility(View.INVISIBLE);
                button2.setVisibility(View.INVISIBLE);
                txtView2.animatedText("Aitana coge el libro y empieza a ojearlo. Las tapas están hechas de piel de cordero, las hojas son de un color tostado y están algo estropeadas.");
                personaje.setVisibility(View.INVISIBLE);

            }
        });

        //Button decision no coge el libro
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                decision = 0;
                pasar = true;
                button1.setVisibility(View.INVISIBLE);
                button2.setVisibility(View.INVISIBLE);
                txtView2.animatedText("No me rayéis con vuestras movidas chungas.");
                personaje.setVisibility(View.VISIBLE);
                personaje.setImageResource(R.drawable.quejicadre);

            }
        });

        buttonizquierda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                decision = 0;
                pasar = true;
                buttonizquierda.setVisibility(View.INVISIBLE);
                buttonderecha.setVisibility(View.INVISIBLE);
                personaje.setVisibility(View.INVISIBLE);
                txtView2.animatedText("Xandre abre la puerta que lleva a un pasadizo oscuro. No se aprecia mucho pero se ve una luz al final.");

            }
        });


        buttonderecha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                decision = 1;
                pasar = true;
                buttonizquierda.setVisibility(View.INVISIBLE);
                buttonderecha.setVisibility(View.INVISIBLE);
                bajaCordura();
                cajademusica.pause();
                cordur.setProgress(cordura);
                videoView.setVisibility(View.VISIBLE);
                videoView.setVideoURI(uri);
                videoView.start();
                txtView2.animatedText("");
                personaje.setVisibility(View.INVISIBLE);
                cuadrotexto.setVisibility(View.INVISIBLE);
            }
        });

        videoView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!videoView.isPlaying()){
                    videoView.suspend();
                    videoView.setVisibility(VideoView.GONE);
                }
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