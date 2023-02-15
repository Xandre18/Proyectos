package com.example.aventuratextual2;

import static java.lang.Math.*;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.google.firebase.crashlytics.buildtools.reloc.org.apache.commons.logging.Log;

import java.util.ArrayList;
// PONER FONDO BACHATOSAURIO Y MUSICA JURASSIC PARK

public class VerDinos extends AppCompatActivity {


    //Fondo de pantalla, cuadro de texto y personaje
    private ImageView fondo, cuadroTexto, personaje, npc;
    //sonidos
    MediaPlayer metal, xfile;
    //ints contadores/controladores
    private int contador = 0, escena = 0, contaux = 0, decision = 3;
    //Subidon
    public static int subidon = 0, cordura;
    private ProgressBar subid, cordur;
    // pasar controla si se puede pasar de escena tocando el fondo o no
    boolean pasar = true;
    //Arraylist con la historia lineal y según decisiones
    ArrayList<String> historia = new ArrayList<>();
    ArrayList<String> aux = historia;

    MediaPlayer musica;
    MediaPlayer tos;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_dinos);
        //Coger los valores de subidon y cordura del anterior activity
        Bundle extras = getIntent().getExtras();
        subidon = extras.getInt("subidon");
        cordura = extras.getInt("cordura");

        musica = MediaPlayer.create(this, R.raw.jurassicpark);
        musica.start();
        musica.setVolume((float) 1.0, 0.4F);
        tos = MediaPlayer.create(this, R.raw.tos);

        Typewriter texto = findViewById(R.id.type_textView);
        //Imagenes
        fondo = (ImageView) findViewById(R.id.fondo);
        cuadroTexto = (ImageView) findViewById(R.id.cuadrotexto);
        personaje = (ImageView) findViewById(R.id.personaje);
        npc = (ImageView) findViewById(R.id.npc);
        personaje.setVisibility(View.INVISIBLE);
        npc.setVisibility(View.INVISIBLE);
        cuadroTexto.setVisibility(View.INVISIBLE);
        //Barras
        subid = findViewById(R.id.subidonBar);
        cordur = findViewById(R.id.corduraBar);
        subid.setProgress(subidon);
        cordur.setProgress(cordura);
        //Sonidos
        metal = MediaPlayer.create(this,R.raw.metalefecto);
        xfile = MediaPlayer.create(this,R.raw.xfiles);


        //HISTORIA
        historia.add("¡¡¡¡Aquí está mi nuevo descubrimiento!!!! ¡¡¡¡¡EL BACHATOSAURIO!!!!! PUAJAJAJA *cof cof cof*");
        historia.add("Hay que dejar de fumar eh chulo. A todo esto que ganas de un piti especiado.");
        historia.add("Le dijo la sartén al cazo.");
        historia.add("A mi no me empieces con refranes de la prehistoria que ya tengo suficiente con el fantasma este.");
        historia.add("(...)");
        historia.add("Que si que si, fumadores todos, ¿cómo que un Bachatasaurio qué es eso?");
        historia.add("Pero qué tipo de dinosaurio es este, te lo has inventado.");
        historia.add("Eso dijeron los demás paleontólogos…");
        historia.add("Es un dinosaurio con plumas rosa peligro y patas rojo sangre.");
        historia.add("Ya estamos con los daltónicos.");
        historia.add("Épico.");
        historia.add("Pero cómo lo sabes solo con los huesos.");
        historia.add("Intente simularlo extrayendo el ADN e inseminando una vaca.");
        historia.add("Después de eso me quitaron el título de paleontología y me acusaron de crímenes contra la naturaleza.");
        historia.add("¿Y te mataron por eso?");
        historia.add("No, me resbalé con la leche de la vaca.");
        historia.add("Ves como se parece al de mi clase.");
        historia.add("Creo que prefiero las arañas.");
        historia.add("Entonces ese debe ser un genio.");
        historia.add("Creo que se está haciendo un poco tarde. Si nos disculpas que se nos enfría la cena.\n");
        historia.add("");


        fondo.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                //controla si se debe pasar a la siguiente "escena"
                if (pasar) {
                    //contiene los cambios de visibilidad, controla el contador (en caso de opcion), el array que pasa la historia y las opciones
                    switch (escena) {
                        case 0:
                            //fantasma
                            cuadroTexto.setVisibility(View.VISIBLE);
                            npc.setVisibility(View.VISIBLE);
                            tos.setVolume((float) 1.0, 1.0F);
                            musica.setVolume((float) 0.4, 0.1F);
                            tos.start();
                            break;
                        case 1:
                            tos.stop();
                            musica.setVolume((float) 1.0, 1.0F);
                            //xandre
                            personaje.setVisibility(View.VISIBLE);
                            npc.setVisibility(View.INVISIBLE);
                            personaje.setImageResource(R.drawable.dre);
                            break;

                        case 2:
                            //naila
                            personaje.setVisibility(View.VISIBLE);
                            npc.setVisibility(View.INVISIBLE);
                            personaje.setImageResource(R.drawable.naila);
                            break;

                        case 3:
                            //xandre
                            personaje.setImageResource(R.drawable.quejicadre);
                            break;

                        case 4:
                            //fantasma
                            personaje.setVisibility(View.INVISIBLE);
                            npc.setVisibility(View.VISIBLE);
                            break;
                        case 5:
                            //tana
                            personaje.setVisibility(View.VISIBLE);
                            npc.setVisibility(View.INVISIBLE);
                            personaje.setImageResource(R.drawable.tanainsultandoadre);
                            break;
                        case 6:
                            //naila
                            personaje.setImageResource(R.drawable.naila);
                            break;
                        case 7:
                            //fantasma
                            personaje.setVisibility(View.INVISIBLE);
                            npc.setVisibility(View.VISIBLE);
                            break;

                        case 9:
                            //naila
                            personaje.setVisibility(View.VISIBLE);
                            npc.setVisibility(View.INVISIBLE);
                            personaje.setImageResource(R.drawable.naila);
                            break;

                        case 10:
                            //xandre
                            personaje.setImageResource(R.drawable.dre);
                            break;
                        case 11:
                            //aitana
                            personaje.setImageResource(R.drawable.tana);
                            break;

                        case 12:
                            //fantasma
                            personaje.setVisibility(View.INVISIBLE);
                            npc.setVisibility(View.VISIBLE);
                            break;

                        case 14:
                            //aitana
                            personaje.setVisibility(View.VISIBLE);
                            npc.setVisibility(View.INVISIBLE);
                            personaje.setImageResource(R.drawable.tanainsultandoadre);
                            break;
                        case 15:
                            //fantasma
                            personaje.setVisibility(View.INVISIBLE);
                            npc.setVisibility(View.VISIBLE);
                            break;
                        case 16:
                            //xandre
                            personaje.setVisibility(View.VISIBLE);
                            npc.setVisibility(View.INVISIBLE);
                            personaje.setImageResource(R.drawable.happydre);
                            break;
                        case 17:
                            //naila
                            personaje.setImageResource(R.drawable.nailaechandolabronca);
                            break;
                        case 18:
                            //fantasma
                            personaje.setVisibility(View.INVISIBLE);
                            npc.setVisibility(View.VISIBLE);
                            break;
                        case 19:
                            //naila
                            personaje.setVisibility(View.VISIBLE);
                            npc.setVisibility(View.INVISIBLE);
                            personaje.setImageResource(R.drawable.naila);
                            break;
                        case 20:
                            musica.stop();
                            Intent intent = new Intent(VerDinos.this, Activity9.class);
                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            intent.putExtra("subidon", subidon);
                            intent.putExtra("cordura", cordura);
                            startActivity(intent);
                            finish();
                            break;
                        default:
                            break;
                    }
                    //recoge el texto del array que contiene la historia (auxiliar) y lo imprime en pantalla
                    String text = aux.get(contador);
                    texto.setCharacterDelay(25);
                    texto.animatedText(text);
                    //por defecto escena y contador van de la mano
                    escena++;
                    contador++;
                }
            }



        });


    }
    public static void subeSubidon(){
        int valorSubida = (int) (random()*10+1);
        subidon += valorSubida;

    }
    public static void bajaSubidon(){
        int valorBajada = (int) (random()*10+1);
        if (subidon != 0) {
            if(subidon < 10){
                valorBajada = (int) (random()*subidon+1);
                subidon -= valorBajada;
            }else{
                subidon -= valorBajada;
            }
        }
    }

    public static void bajaCordura() {
        if(cordura > 20){
            int valorBajada = (int) (random()*30+1);
            cordura = cordura - valorBajada;
        }
    }

    public static void corduraRandom(){
        if(bajaCorduraRandom()){
            int valorBajada = (int) (random()*30+1);
            cordura = cordura - valorBajada;
        }

    }

    //Metodo que saca un número entre el uno y el 5 si el número es menor o igual a 3
    //Entonces la cordura baja y si no se mantiene.
    public static boolean bajaCorduraRandom(){
        int baja = (int) (random()*5+1);

        if(baja <= 3){
            return true;
        } else {
            return false;
        }

    }
}