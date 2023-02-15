package com.example.aventuratextual2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;


public class MainActivity2 extends AppCompatActivity {

    private ImageView fondo2, cuadrotexto2, imagedre;

    private int contador = 0;

    private Button botonSi, botonNo;

    private int decision = 2;
    MediaPlayer cajademusica;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        fondo2 = (ImageView) findViewById(R.id.casa);
        cuadrotexto2 = (ImageView) findViewById(R.id.cuadrotexto);
        imagedre = (ImageView) findViewById(R.id.imagedre);
        botonSi = (Button) findViewById(R.id.buttonsi);
        botonNo = (Button) findViewById(R.id.buttonno);

        cajademusica = MediaPlayer.create(this, R.raw.sonidocajamusica);
        cajademusica.start();

        imagedre.setVisibility(View.INVISIBLE);
        cuadrotexto2.setVisibility(View.INVISIBLE);
        botonSi.setVisibility(View.INVISIBLE);
        botonNo.setVisibility(View.INVISIBLE);

        Typewriter txtView2 = findViewById(R.id.type_textWriter);
        txtView2.setCharacterDelay(25); //delay con el que se escriben los caracteres

        //Se crea un switch que va almacenando en un contador las veces que se pulsa la pantalla y según las veces que se pulse
        //reproduce un texto y la toma de decisiones
        fondo2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (contador){
                    case 0:
                        cuadrotexto2.setVisibility(View.VISIBLE);
                        txtView2.animatedText("Los amigos se reúnen en casa de uno de ellos.");
                        contador++;
                        break;
                    case 1:
                        fondo2.setImageResource(R.drawable.interiorcasa);
                        txtView2.animatedText("¿Al final hoy se lía?");
                        imagedre.setVisibility(View.VISIBLE);
                        contador++;
                        break;
                    case 2:
                        botonSi.setVisibility(View.VISIBLE);
                        botonNo.setVisibility(View.VISIBLE);
                        contador++;
                        break;
                    case 3:
                        if(decision == 1){
                            txtView2.animatedText("Yo también, me parece un planazo.");
                            imagedre.setImageResource(R.drawable.naila);
                            contador++;
                        } else if(decision == 0) {
                            txtView2.animatedText("¿Y si nos quedamos aquí de tranquis, hacemos la previa, vemos una peli y si tal luego vamos " +
                                    "a la fiesta de un amigo?");
                            imagedre.setImageResource(R.drawable.naila);
                            contador++;
                        }
                        break;
                    case 4:
                        if(decision == 1){
                            txtView2.animatedText("Decidido, pillad las cosas que vamos yo os espero en el golfito.");
                            imagedre.setImageResource(R.drawable.quejicadre);
                            contador++;
                        } else if(decision == 0){
                            txtView2.animatedText("Venga dale.");
                            imagedre.setImageResource(R.drawable.quejicadre);
                            contador++;
                        }
                        break;
                    case 5:
                        if(decision == 1){
                            //pasar a la siguiente activity
                            cajademusica.stop();
                            Intent intent = new Intent(MainActivity2.this, cementerio.class);
                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            startActivity(intent);
                            finish();

                        } else if(decision == 0){
                            imagedre.setVisibility(View.INVISIBLE);
                            txtView2.animatedText("Al final se quedaron dormidos viendo la peli y se perdieron una increíble noche de Halloween.");
                            contador++;
                        }
                        break;
                    case 6:
                        cajademusica.stop();
                        Intent intent = new Intent(MainActivity2.this, MainActivity2_Final.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent);
                        finish();
                        break;
                }
            }
        });

        //Boton para la decision de si Hoy se lia, si pulsa SI decision pasa a valer uno
        botonSi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                botonSi.setVisibility(View.INVISIBLE);
                botonNo.setVisibility(View.INVISIBLE);
                txtView2.animatedText("Venga, que yo tengo ganas de hacer algo distinto.");
                imagedre.setImageResource(R.drawable.tana);
                decision = 1;

            }
        });

        //Boton para la decision de si Hoy se lia, si pulsa NO decision pasa a valer 0
        botonNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                botonSi.setVisibility(View.INVISIBLE);
                botonNo.setVisibility(View.INVISIBLE);
                txtView2.animatedText("Buah tío, no sé.");
                imagedre.setImageResource(R.drawable.tana);
                decision = 0;
            }
        });


    }
}