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

public class cementerio extends AppCompatActivity {
    //Fondo de pantalla, cuadro de texto y personaje
        private ImageView fondo, cuadroTexto, personaje, tumba;
    //Botones
        private Button botonsi, botonno, botonbeber, botonmovil, botonsi1, botonno1;
    //sonidos
    MediaPlayer metal, xfile;
    MediaPlayer cajademusica;
    //ints contadores/controladores
        private int contador = 0, escena = 0, contaux = 0, decision = 3;
    //Subidon
        public static int subidon = 0;
        private ProgressBar subid;
    // pasar controla si se puede pasar de escena tocando el fondo o no
        boolean pasar = true;
    //Arraylist con la historia lineal y según decisiones
        ArrayList<String> historia = new ArrayList<>();
        ArrayList<String> beber1 = new ArrayList<>();
        ArrayList<String> movil1 = new ArrayList<>();
        ArrayList<String> aux = historia;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cementerio);
        Typewriter texto = findViewById(R.id.type_textView);
        //Imagenes
            fondo = (ImageView) findViewById(R.id.fondo);
            cuadroTexto = (ImageView) findViewById(R.id.cuadrotexto);
            personaje = (ImageView) findViewById(R.id.personaje);
            tumba = (ImageView) findViewById(R.id.imageView4);
            personaje.setVisibility(View.INVISIBLE);
            cuadroTexto.setVisibility(View.INVISIBLE);
            tumba.setVisibility(View.INVISIBLE);
        //Barras
            subid = findViewById(R.id.subidonBar);
        //Sonidos
            metal = MediaPlayer.create(this,R.raw.metalefecto);
            xfile = MediaPlayer.create(this,R.raw.xfiles);
            cajademusica = MediaPlayer.create(this, R.raw.sonidocajamusica2);
            cajademusica.start();
        //Botones
            botonno = (Button) findViewById(R.id.buttonno);
            botonsi = (Button) findViewById(R.id.buttonsi);
            botonno.setVisibility(View.INVISIBLE);
            botonsi.setVisibility(View.INVISIBLE);
            botonbeber = (Button) findViewById(R.id.buttonbeber);
            botonmovil = (Button) findViewById(R.id.buttonmovil);
            botonbeber.setVisibility(View.INVISIBLE);
            botonmovil.setVisibility(View.INVISIBLE);
            botonno1 = (Button) findViewById(R.id.buttonno1);
            botonsi1 = (Button) findViewById(R.id.buttonsi1);
            botonno1.setVisibility(View.INVISIBLE);
            botonsi1.setVisibility(View.INVISIBLE);
        //


        //HISTORIA

            historia.add("Tienes niveles de cordura y de subidón representados en las barritas de arriba en el orden correspondiente. Estas bajaran y subiran respectivamente según las decisiones que tomes. No te preocupes porque se pueden recuperar de la misma manera");
            historia.add("");
            historia.add("Es una noche oscura en la que la única luz que ilumina el cielo es la de una brillante luna llena.");
            historia.add("");
            historia.add("No sabía que había luna llena.");
            historia.add("Le da ambiente a esta noche de Halloween.");
            historia.add("Igual nos pasa algún suceso paranormal esta noche. Quien sabe…");
            historia.add("A mí no me rayes, que yo he venido a pasarlo bien, nada de movidas chungas de brujas del medievo, Naila. Ni de movidas astrológicas o así, que te veo venir Aitana.");
            historia.add("Venga chicos haya paz (Joder lo que tengo aguantar). ¿Quién quiere un cubata?");
            historia.add("Venga sentémonos.");
            historia.add("Dale aquí mismo.");
            historia.add("");
            historia.add("Eso encima de una tumba tío, no sé yo si es buena idea.\n");
            historia.add("No creo que le importe.");
            historia.add("Xandre se sienta encima de la tumba; Aitana y Naila colocan las cosas cerca en corrillo.");
            historia.add("Ves como no era buena idea JAJAJA.");
            historia.add("Que va, serían las llaves del golfito.");
            historia.add("¿Qué os parece si jugamos a prueba o verdad?");
            historia.add("Mejor a prueba o beber, que si no solo decimos verdad y es un rollo.");
            historia.add("Perfecto, una excusa pa beber. Que esta Santa Teresa hay que bajarla.");
            historia.add("Va, Xandre. Danos tu móvil desbloqueado 5 minutos o bebe.");
            historia.add("Va, ahora tú Naila, te reto a entrar al mausoleo.");
            historia.add("Acepto pero primero unos chupitos.");
            historia.add("Venga, entremos.");
            historia.add("");
        // MOVIL O BEBER
            movil1.add("Entra ahí y pon (...) y luego (...).");
            movil1.add("Va y ahora (...) JAJAJAJA.");
            beber1.add("Xandre aburrido.");
            beber1.add("É o que hai.");


        fondo.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
               //controla si se debe pasar a la siguiente "escena"
                if (pasar) {
                    //contiene los cambios de visibilidad, controla el contador (en caso de opcion), el array que pasa la historia y las opciones
                    switch (escena) {
                        case 0:
                            cuadroTexto.setVisibility(View.VISIBLE);
                            break;
                        case 1:
                            fondo.setImageResource(R.drawable.cemetary);
                            cuadroTexto.setVisibility(View.INVISIBLE);
                            break;

                        case 2:
                            cuadroTexto.setVisibility(View.VISIBLE);
                            break;

                        case 3:
                            fondo.setImageResource(R.drawable.cementerio2);
                            cuadroTexto.setVisibility(View.INVISIBLE);
                            break;

                        case 4:
                            cuadroTexto.setVisibility(View.VISIBLE);
                            personaje.setVisibility(View.VISIBLE);
                            break;
                        case 5:
                            personaje.setImageResource(R.drawable.tana);
                            break;
                        case 6:
                            personaje.setImageResource(R.drawable.naila);
                            cajademusica.pause();
                            xfile.start();
                            break;
                        case 7:
                            cajademusica.start();
                            personaje.setImageResource(R.drawable.quejicadre);
                            if (xfile.isPlaying()){
                                xfile.stop();
                            }
                            break;
                        case 8:
                            pasar = false;
                            personaje.setImageResource(R.drawable.nailaechandolabronca);
                            botonno.setVisibility(View.VISIBLE);
                            botonsi.setVisibility(View.VISIBLE);
                            break;
                        case 9:
                            //aitana
                            personaje.setImageResource(R.drawable.tana);
                            break;

                        case 10:
                            //xandre
                            personaje.setImageResource(R.drawable.dre);
                            break;
                        case 11:
                            //muestra tumba
                            cuadroTexto.setVisibility(View.INVISIBLE);
                            personaje.setVisibility(View.INVISIBLE);
                            fondo.setImageResource(R.drawable.cementeriotumba);
                            tumba.setVisibility(View.VISIBLE);
                            break;

                        case 12:
                            //naila
                            cuadroTexto.setVisibility(View.VISIBLE);
                            personaje.setVisibility(View.VISIBLE);
                            personaje.setImageResource(R.drawable.nailaechandolabronca);
                            break;

                        case 13:
                            //xandre
                            decision = 3;
                            personaje.setImageResource(R.drawable.dre);
                            break;
                        case 14:
                            cajademusica.pause();
                            personaje.setVisibility(View.INVISIBLE);
                            tumba.setVisibility(View.INVISIBLE);
                            fondo.setImageResource(R.drawable.cementerio2);
                            metal.start();
                            break;
                        case 15:
                            cajademusica.start();
                            //naila
                            personaje.setImageResource(R.drawable.happynaila);
                            personaje.setVisibility(View.VISIBLE);
                            if (xfile.isPlaying()){
                                xfile.stop();
                            }
                            break;
                        case 16:
                            //xandre
                            personaje.setImageResource(R.drawable.dre);
                            break;
                        case 17:
                            //aitana
                            personaje.setImageResource(R.drawable.tana);
                            break;
                        case 18:
                            //naila
                            personaje.setImageResource(R.drawable.naila);
                            break;
                        case 19:
                            //xandre
                            personaje.setImageResource(R.drawable.quejicadre);
                            break;
                        case 20:
                            //aitana
                            //decision movil o beber
                            pasar = false;
                            botonbeber.setVisibility(View.VISIBLE);
                            botonmovil.setVisibility(View.VISIBLE);
                            personaje.setImageResource(R.drawable.tana);
                            break;
                        case 21:
                            //if con decision
                            contaux = contador;
                            contador = 0;
                            //0 beber, 1 movil
                            if (decision == 1) {
                                //aitana
                                aux = movil1;
                                personaje.setImageResource(R.drawable.happytana);
                            }else if (decision == 0){
                                //naila
                                personaje.setImageResource(R.drawable.naila);
                                aux = beber1;
                            }
                            break;
                        case 22:
                            if (decision == 1) {
                                //naila
                                personaje.setImageResource(R.drawable.happynaila);

                            }else if (decision == 0){
                                //xandre
                                personaje.setImageResource(R.drawable.dre);
                            }
                            break;
                        case 23:
                            decision = 3;
                            contador = contaux;
                            aux = historia;
                            //xandre
                            personaje.setImageResource(R.drawable.quejicadre);
                            break;
                        case 24:
                            //naila
                            pasar = false;
                            botonsi1.setVisibility(View.VISIBLE);
                            botonno1.setVisibility(View.VISIBLE);
                            personaje.setImageResource(R.drawable.naila);
                            break;

                        case 25:
                            //aitana
                            personaje.setImageResource(R.drawable.tana);
                            break;

                        case 26:
                            cajademusica.stop();
                            Intent intent = new Intent(cementerio.this, Activity6.class);
                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            intent.putExtra("subidon", subidon);
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

        //botones
            //1 beber
            botonno.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    decision = 0;
                    pasar = true;
                    botonsi.setVisibility(View.INVISIBLE);
                    botonno.setVisibility(View.INVISIBLE);
                    personaje.setImageResource(R.drawable.quejicadre);
                    texto.animatedText("Nah ya bebemos en un rato.");
                }
            });
            botonsi.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    decision = 1;
                    pasar = true;
                    botonsi.setVisibility(View.INVISIBLE);
                    botonno.setVisibility(View.INVISIBLE);
                    personaje.setImageResource(R.drawable.quejicadre);
                    texto.animatedText("Trae la Santa Teresa.");
                    subeSubidon();
                    subid.setProgress(subidon);
                }
            });
            //2 beber
            botonbeber.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    decision = 0;
                    pasar = true;
                    botonmovil.setVisibility(View.INVISIBLE);
                    botonbeber.setVisibility(View.INVISIBLE);
                    personaje.setImageResource(R.drawable.quejicadre);
                    texto.animatedText("Bebo, que no os dejo el móvil que a saber que haceis.");
                    subeSubidon();
                    subid.setProgress(subidon);
                }
            });
            botonmovil.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    decision = 1;
                    pasar = true;
                    botonmovil.setVisibility(View.INVISIBLE);
                    botonbeber.setVisibility(View.INVISIBLE);
                    personaje.setImageResource(R.drawable.quejicadre);
                    texto.animatedText("Cogedlo pero no os paseis.");
                    bajaSubidon();
                    subid.setProgress(subidon);
                }
            });
            //3 beber
            botonno1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    decision = 0;
                    pasar = true;
                    botonsi1.setVisibility(View.INVISIBLE);
                    botonno1.setVisibility(View.INVISIBLE);
                    personaje.setImageResource(R.drawable.quejicadre);
                    texto.animatedText("Tío es beber o reto, no las dos; luego el alcohólico soy yo.");
                    bajaSubidon();
                    subid.setProgress(subidon);
                }
            });
            botonsi1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    decision = 1;
                    pasar = true;
                    botonsi1.setVisibility(View.INVISIBLE);
                    botonno1.setVisibility(View.INVISIBLE);
                    personaje.setImageResource(R.drawable.quejicadre);
                    texto.animatedText("Dale que hay que bajar la teresita.");
                    subeSubidon();
                    subid.setProgress(subidon);
                }
            });

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


}



