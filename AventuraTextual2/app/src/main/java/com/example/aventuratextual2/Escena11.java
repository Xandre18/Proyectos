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

public class Escena11 extends AppCompatActivity {
    //Fondo de pantalla, cuadro de texto y personaje
    private ImageView fondo, cuadroTexto, personaje,npc, tumballaves;
    //Botones
    private Button botonbuena, botonmala, botonbeber, botonnobeber, botonnaila, botonxandre, botonaitana;
    //sonidos
    MediaPlayer siuu;
    MediaPlayer musica;
    MediaPlayer cajademusica;
    //ints contadores/controladores
    private int contador = 0, escena = 0, contaux = 0, decision = 3;
    //Subidon
    public static int subidon = 0, cordura = 40;
    private ProgressBar subid, cordur;
    // pasar controla si se puede pasar de escena tocando el fondo o no
    boolean pasar = true;


    //Arraylist con la historia lineal y según decisiones
    ArrayList<String> historia = new ArrayList<>();
    ArrayList<String> buena = new ArrayList<>();
    ArrayList<String> mala = new ArrayList<>();
    ArrayList<String> tomar = new ArrayList<>();
    ArrayList<String> notomar = new ArrayList<>();
    ArrayList<String> matesb = new ArrayList<>();
    ArrayList<String> matesm = new ArrayList<>();
    ArrayList<String> naila = new ArrayList<>();
    ArrayList<String> xandre = new ArrayList<>();
    ArrayList<String> aitana = new ArrayList<>();

    ArrayList<String> aux = historia;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_escena11);
        //Coger los valores de subidon y cordura del anterior activity
        Bundle extras = getIntent().getExtras();
        subidon = extras.getInt("subidon");
        cordura = extras.getInt("cordura");

        Typewriter texto = findViewById(R.id.type_textView);
        //Imagenes
        fondo = (ImageView) findViewById(R.id.fondo);
        cuadroTexto = (ImageView) findViewById(R.id.cuadrotexto);
        personaje = (ImageView) findViewById(R.id.personaje);
        tumballaves = (ImageView) findViewById(R.id.imageView4);
        tumballaves.setVisibility(View.INVISIBLE);
        personaje.setVisibility(View.INVISIBLE);
        npc = (ImageView) findViewById(R.id.npc);
        npc.setVisibility(View.INVISIBLE);
        cuadroTexto.setVisibility(View.INVISIBLE);
        //Barras
        subid = findViewById(R.id.subidonBar);
        cordur = findViewById(R.id.corduraBar);
        subid.setProgress(subidon);
        cordur.setProgress(cordura);
        //Sonidos
        siuu = MediaPlayer.create(this,R.raw.siuu);
        musica = MediaPlayer.create(this, R.raw.ghostbusters);
        cajademusica = MediaPlayer.create(this, R.raw.sonidocajamusica2);
        cajademusica.start();
        //Botones
        botonmala = (Button) findViewById(R.id.buttonmala);
        botonbuena = (Button) findViewById(R.id.buttonbuena);
        botonmala.setVisibility(View.INVISIBLE);
        botonbuena.setVisibility(View.INVISIBLE);
        botonbeber = (Button) findViewById(R.id.buttonbeber);
        botonnobeber = (Button) findViewById(R.id.buttonnobeber);
        botonbeber.setVisibility(View.INVISIBLE);
        botonnobeber.setVisibility(View.INVISIBLE);
        botonxandre = (Button) findViewById(R.id.buttonxandre);
        botonnaila = (Button) findViewById(R.id.buttonnaila);
        botonaitana = (Button) findViewById(R.id.buttonaitana);
        botonxandre.setVisibility(View.INVISIBLE);
        botonnaila.setVisibility(View.INVISIBLE);
        botonaitana.setVisibility(View.INVISIBLE);
        //


        //HISTORIA
        historia.add("Tengo una buena y una mala noticia. ¿Cuál quereis oír  primero?");
        historia.add("No se te habrán caído cuando te sentaste en la tumba esa.");
        historia.add("Puede ser. Vamos a ver que el cementerio está ahí al lado.");
        historia.add("Espero que estén, porque no quiero volver a entrar en el mausoleo.");
        historia.add("");
        historia.add("Ves estaban aquí.");
        historia.add("Cogen las llaves y se escucha un sonido fantasmagórico.");
        historia.add("Qué coño fue eso.");
        historia.add("Che pibes soy Israel.");
        historia.add("Te voy a llamar Palestina.");
        historia.add("Que boluda. A lo que iba salí porque el culo gordo ese se sentó en mi tumba.");
        historia.add("Bien cómoda bro.");
        historia.add("Y el acento argentino donde está.");
        historia.add("Che pibe a veces se me olvida, que lo estoy aprendiendo que a las minitas les gusta.");
        historia.add("Se nota que no tuviste mucho éxito con ellas.");
        historia.add("Yo nunca tuve suelte´ en el amol´.");
        historia.add("Ni la va a tener porque tu ere´ un bagre feo loco. DIABLO!!!!");
        historia.add("Che pibes unos mates?");
        historia.add("Tuve una vida feliz, era butanero y envidiado por todos, querido por muchos. Pero nunca encontré a mi alma gemela, tuve muchas amigas, MUCHAS, de hecho me llamaban el embajador de la friendzone…"); //, servido en la amistad quite esto porque ni no no cabe el texto
        historia.add("Por algún motivo me he quedado aquí, atrapado deambulando, y tras mucho pensar creo que puede ser por eso.");

        // MALA O BUENA
        buena.add("Ya salimos de ese horrendo sitio.");
        buena.add("¿Y la mala?");
        buena.add("No se donde están las llaves del coche.");
        buena.add("¿Estás de broma no?");
        buena.add("Yo no bromeo con el golfito.");
        mala.add("No se donde están las llaves del coche.");
        mala.add("¿Estás de broma no?");
        mala.add("Yo no bromeo con el golfito.");
        mala.add("¿Y la buena?");
        mala.add("Ya salimos de ese horrendo sitio.");
        // TOMAR O NO TOMAR
        tomar.add("Esto está malísimo."); //xandre
        tomar.add("Pues a mi me gusta."); //aitana
        tomar.add("Eres un exagerado tampoco está tan mal."); //naila
        tomar.add("Tras tomar un par de mates el fantasma les empieza a contar la historia de su vida.");
        notomar.add("Los mates de ultratumba no parece buena idea."); //naila
        notomar.add("Y menos de un falso argentino."); //xandre
        notomar.add("");
        // ESCENA TOMAR MATES
        matesm.add("Por eso estoy buscando el amor tras la muerte. ¿Quien de vosotras quiere quedarse conmigo?");
        matesb.add("Bueno Palestina, no te preocupes, fijo que haces buenas migas con el fantasma ese del mausoleo.");
        matesb.add("Ese pibe no me parece buena onda, y de amigos estoy sobrado.");
        matesb.add("");
        // QUIENSEQUEDA
        naila.add("Con que dejan a la MILF aquí conmigo, bueno ya iba siendo tu hora.");
        naila.add("Creo que prefería al de los dinosaurios.");
        naila.add("");
        xandre.add("Claramente esto no es lo que estaba pensando cuando dije eso.");
        xandre.add("Che pibe ahora pasaremos una noche re locos.");
        xandre.add("");
        aitana.add("Con que nos hemos quedado tú y yo solitos. Te acompaño a tu nueva casa.");
        aitana.add("Voy a atormentar a esos dos toda la vida, porque no se me ocurre peor tortura que esta.");
        aitana.add("");




        fondo.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                //controla si se debe pasar a la siguiente "escena"
                if (pasar) {
                    //contiene los cambios de visibilidad, controla el contador (en caso de opcion), el array que pasa la historia y las opciones
                    switch (escena) {
                        case 0:
                            cuadroTexto.setVisibility(View.VISIBLE);
                            personaje.setVisibility(View.VISIBLE);
                            botonbuena.setVisibility(View.VISIBLE);
                            botonmala.setVisibility(View.VISIBLE);
                            pasar = false;

                            break;
                        case 1:
                            contaux = contador;
                            contador = 0;
                            if (decision == 1) {
                                aux = buena;
                            }else if (decision == 0){
                                //aitana

                                aux = mala;
                            }
                            //xandre
                            personaje.setImageResource(R.drawable.dre);

                            break;
                        case 2:
                            if (decision == 1) {
                                //naila
                                personaje.setImageResource(R.drawable.naila);
                            }else if (decision == 0){
                                //aitana
                                personaje.setImageResource(R.drawable.sadtana);
                            }
                            break;

                        case 3:
                            //Xandre
                            personaje.setImageResource(R.drawable.quejicadre);
                            break;

                        case 4:
                            if (decision == 1) {
                                //aitana
                                personaje.setImageResource(R.drawable.sadtana);
                            }else if (decision == 0){
                                //naila
                                personaje.setImageResource(R.drawable.naila);
                            }
                            break;
                        case 5:
                            //xandre
                            personaje.setImageResource(R.drawable.quejicadre);
                            break;
                        case 6:
                            //aitana
                            decision = 3;
                            contador = contaux;
                            aux = historia;
                            personaje.setImageResource(R.drawable.tana);
                            break;
                        case 7:
                            personaje.setImageResource(R.drawable.dre);
                            break;
                        case 8:
                            //naila
                            personaje.setImageResource(R.drawable.nailaechandolabronca);
                            break;
                        case 9:
                            //cambio fondo
                            fondo.setImageResource(R.drawable.cementeriotumba);
                            tumballaves.setVisibility(View.VISIBLE);
                            cuadroTexto.setVisibility(View.INVISIBLE);
                            personaje.setVisibility(View.INVISIBLE);
                            break;
                        case 10:
                            //aitana
                            personaje.setVisibility(View.VISIBLE);
                            cuadroTexto.setVisibility(View.VISIBLE);
                            personaje.setImageResource(R.drawable.happytana);
                            break;

                        case 11:
                            cajademusica.stop();
                            //ruido suuu
                            siuu.start();
                            personaje.setVisibility(View.INVISIBLE);
                            break;
                        case 12:
                            //aitana
                            personaje.setVisibility(View.VISIBLE);
                            personaje.setImageResource(R.drawable.sustotana);
                            if(siuu.isPlaying()){
                                siuu.stop();
                            }
                            break;
                        case 13:
                            //Israel
                            musica.start();
                            personaje.setVisibility(View.INVISIBLE);
                            npc.setVisibility(View.VISIBLE);
                            break;
                        case 14:
                            //aitana
                            personaje.setVisibility(View.VISIBLE);
                            npc.setVisibility(View.INVISIBLE);
                            personaje.setImageResource(R.drawable.tana);
                            break;

                        case 15:
                            //israel
                            personaje.setVisibility(View.INVISIBLE);
                            npc.setVisibility(View.VISIBLE);
                            break;

                        case 16:
                            //xandre
                            personaje.setVisibility(View.VISIBLE);
                            npc.setVisibility(View.INVISIBLE);
                            personaje.setImageResource(R.drawable.dre);
                            break;
                        case 17:
                            //naila
                            personaje.setImageResource(R.drawable.nailaechandolabronca);
                            break;
                        case 18:
                            //israel
                            personaje.setVisibility(View.INVISIBLE);
                            npc.setVisibility(View.VISIBLE);
                            break;
                        case 19:
                            //aitana
                            personaje.setVisibility(View.VISIBLE);
                            npc.setVisibility(View.INVISIBLE);
                            personaje.setImageResource(R.drawable.tana);
                            break;
                        case 20:
                            //israel
                            personaje.setVisibility(View.INVISIBLE);
                            npc.setVisibility(View.VISIBLE);
                            break;
                        case 21:
                            //xandre
                            personaje.setVisibility(View.VISIBLE);
                            npc.setVisibility(View.INVISIBLE);
                            personaje.setImageResource(R.drawable.dre);
                            break;
                        case 22:
                            //israel y mates
                            personaje.setVisibility(View.INVISIBLE);
                            npc.setVisibility(View.VISIBLE);
                            botonbeber.setVisibility(View.VISIBLE);
                            botonnobeber.setVisibility(View.VISIBLE);
                            pasar = false;
                            break;
                        case 23:
                            contaux = contador;
                            contador = 0;
                            if (decision == 0) {
                                //xandre
                                personaje.setImageResource(R.drawable.quejicadre);
                                aux = tomar;
                            }else if (decision == 1){
                                //naila
                                personaje.setImageResource(R.drawable.nailaechandolabronca);
                                aux = notomar;
                            }
                            break;
                        case 24:
                            if (decision == 0) {
                                //aitana
                                personaje.setImageResource(R.drawable.happytana);
                            }else if (decision == 1){
                                //xandre
                                personaje.setImageResource(R.drawable.quejicadre);
                            }
                            break;
                        case 25:
                            if (decision == 0) {
                                //naila
                                personaje.setImageResource(R.drawable.happynaila);
                            }else if (decision == 1){
                                //cambiar a escena coche
                                musica.stop();
                                Intent intent = new Intent(Escena11.this, ActivityCoche.class);
                                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                intent.putExtra("subidon", subidon);
                                intent.putExtra("cordura", cordura);
                                intent.putExtra("mate", false);
                                startActivity(intent);
                                finish();
                            }
                            break;
                        case 26:
                            decision = 3;
                            //narrador
                            personaje.setVisibility(View.INVISIBLE);
                            break;
                        case 27:
                            //ESCENA TOMAR
                            //Israel
                            contador = contaux;
                            aux = historia;
                            npc.setVisibility(View.VISIBLE);
                            break;

                        case 29:
                            //Israel
                            contador = 0;
                            if (cordura < 40) {
                                aux = matesm;
                                pasar = false;
                                npc.setImageResource(R.drawable.israel_creepy);
                                botonnaila.setVisibility(View.VISIBLE);
                                botonxandre.setVisibility(View.VISIBLE);
                                botonaitana.setVisibility(View.VISIBLE);
                            }else{
                                npc.setVisibility(View.INVISIBLE);
                                personaje.setVisibility(View.VISIBLE);
                                personaje.setImageResource(R.drawable.happytana);
                                aux = matesb;
                            }
                            break;
                        case 30:
                            if (cordura < 40) {
                                contador = 0;
                                personaje.setVisibility(View.INVISIBLE);
                                npc.setVisibility(view.VISIBLE);
                                if(decision == 0){
                                    aux = aitana;
                                }else if ( decision == 1){
                                    aux = xandre;
                                }else if (decision == 2){
                                    aux = naila;
                                }

                            }else{
                                npc.setVisibility(View.VISIBLE);
                                personaje.setVisibility(View.INVISIBLE);
                            }
                            break;
                        case 31:
                            if (cordura < 40) {
                                npc.setVisibility(View.INVISIBLE);
                                personaje.setVisibility(View.VISIBLE);
                                if(decision == 0){
                                    personaje.setImageResource(R.drawable.sustotana);
                                }else if ( decision == 1){
                                    personaje.setImageResource(R.drawable.quejicadre);
                                }else if (decision == 2){
                                    personaje.setImageResource(R.drawable.sustonaila);
                                }

                            }else{
                                //cambiar a escena coche
                                musica.stop();
                                Intent intento = new Intent(Escena11.this, ActivityCoche.class);
                                intento.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                intento.putExtra("subidon", subidon);
                                intento.putExtra("cordura", cordura);
                                intento.putExtra("mate", true);
                                startActivity(intento);
                                finish();
                            }
                            break;

                        case 32:
                            musica.stop();
                            Intent intenta = new Intent(Escena11.this, MainActivity2_Final.class);
                            intenta.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            intenta.putExtra("subidon", subidon);
                            intenta.putExtra("cordura", cordura);
                            startActivity(intenta);
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
        botonmala.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                decision = 0;
                pasar = true;
                botonbuena.setVisibility(View.INVISIBLE);
                botonmala.setVisibility(View.INVISIBLE);
                personaje.setImageResource(R.drawable.nailaechandolabronca);
                texto.animatedText("Primero la mala.");
            }
        });
        botonbuena.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                decision = 1;
                pasar = true;
                botonbuena.setVisibility(View.INVISIBLE);
                botonmala.setVisibility(View.INVISIBLE);
                personaje.setImageResource(R.drawable.tana);
                texto.animatedText("La buena primero.");

            }
        });
        //2 beber
        botonbeber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                decision = 0;
                pasar = true;
                botonnobeber.setVisibility(View.INVISIBLE);
                botonbeber.setVisibility(View.INVISIBLE);
                personaje.setImageResource(R.drawable.tana);
                texto.animatedText("Venga, que puede salir mal.");
                subeSubidon();
                bajaCordura();
                personaje.setVisibility(View.VISIBLE);
                npc.setVisibility(View.INVISIBLE);
                subid.setProgress(subidon);
                cordur.setProgress(cordura);
            }
        });
        botonnobeber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                decision = 1;
                pasar = true;
                botonnobeber.setVisibility(View.INVISIBLE);
                botonbeber.setVisibility(View.INVISIBLE);
                personaje.setImageResource(R.drawable.tana);
                texto.animatedText("Paso que igual nos envenena.");
                personaje.setVisibility(View.VISIBLE);
                npc.setVisibility(View.INVISIBLE);
                bajaSubidon();
                subid.setProgress(subidon);
            }
        });
        //3 beber
        botonaitana.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                decision = 0;
                pasar = true;
                botonaitana.setVisibility(View.INVISIBLE);
                botonnaila.setVisibility(View.INVISIBLE);
                botonxandre.setVisibility(View.INVISIBLE);
                personaje.setVisibility(View.VISIBLE);
                npc.setVisibility(view.INVISIBLE);
                personaje.setImageResource(R.drawable.sustodre);
                texto.animatedText("Ahí te quedas. YO ME PIRO.");

            }
        });
        botonxandre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                decision = 1;
                pasar = true;
                botonaitana.setVisibility(View.INVISIBLE);
                botonnaila.setVisibility(View.INVISIBLE);
                botonxandre.setVisibility(View.INVISIBLE);
                personaje.setVisibility(View.VISIBLE);
                npc.setVisibility(view.INVISIBLE);
                personaje.setImageResource(R.drawable.sustotana);
                texto.animatedText("NAILA CORRE TENGO YO LAS LLAVES.");

            }
        });
        botonnaila.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                decision = 2;
                pasar = true;
                botonaitana.setVisibility(View.INVISIBLE);
                botonnaila.setVisibility(View.INVISIBLE);
                botonxandre.setVisibility(View.INVISIBLE);
                personaje.setVisibility(View.VISIBLE);
                npc.setVisibility(view.INVISIBLE);
                personaje.setImageResource(R.drawable.sustodre);
                texto.animatedText("Que se quede la vieja que ya ha vivido suficiente. ADIÓS.");
            }
        });

    }

    public static void subeSubidon(){
        int valorSubida = (int) (Math.random()*30+1);
        subidon += valorSubida;

    }
    public static void bajaSubidon(){
        int valorBajada = (int) (Math.random()*30+1);
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