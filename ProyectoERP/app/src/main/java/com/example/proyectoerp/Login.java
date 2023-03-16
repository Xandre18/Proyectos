package com.example.proyectoerp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.proyectoerp.objects.User;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class Login extends AppCompatActivity {
    EditText eUserName, ePaswd;
    FloatingActionButton btnLogin;
    DBHandler dbHandler;
    ArrayList<User> usersList;
    //Método onCreate que se ejecuta cuando la actividad es creada
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Estableciendo la vista de la actividad
        setContentView(R.layout.activity_login);
        //Inicializando el objeto de la clase DBHandler
        dbHandler = new DBHandler(this);
        //Obteniendo el objeto de SharedPreferences con el nombre "itiUsers" y el modo privado
        SharedPreferences pref = getSharedPreferences("itiUsers", Context.MODE_PRIVATE);

        //Si la clave "itiUsers" no existe en las SharedPreferences, entonces agregar dos usuarios (admin y user) a la base de datos y guardar la clave "itiUsers" en las SharedPreferences
        if(!pref.getBoolean("itiUsers", false)){
            dbHandler.addUser(new User("admin", "admin", true));
            dbHandler.addUser(new User("user", "user", false));
            SharedPreferences.Editor editor = pref.edit();
            editor.putBoolean("itiUsers", true);
            editor.commit();
        }

        //Obteniendo la lista de usuarios desde la base de datos
        usersList = dbHandler.leerUsers();

        //Obteniendo los elementos de la vista
        eUserName = findViewById(R.id.editUserName);
        ePaswd = findViewById(R.id.editPswd);
        btnLogin = findViewById(R.id.btnLogin);

        //Configurando el botón de login para validar el usuario y la contraseña ingresados por el usuario
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = eUserName.getText().toString();
                String pwd = ePaswd.getText().toString();
                boolean esAdmin = false;
                for(int i = 0; i< usersList.size();i++){
                    if(user.equals(usersList.get(i).getName()) && pwd.equals(usersList.get(i).getPaswd())){

                        //Si el usuario y la contraseña son correctos, entonces se verifica si el usuario es administrador o no
                        if(usersList.get(i).isAdmin()){
                            //Si es administrador, entonces se crea un intent para iniciar la actividad MainActivity y se guarda el estado de administrador en las SharedPreferences
                            Intent intent = new Intent(Login.this, MainActivity.class);
                            esAdmin = usersList.get(i).isAdmin();
                            SharedPreferences pref = getSharedPreferences("admin", Context.MODE_PRIVATE);
                            SharedPreferences.Editor editor = pref.edit();
                            editor.putBoolean("isAdmin",true);
                            editor.commit();

                            //Guardando el nombre del usuario en las SharedPreferences
                            SharedPreferences pref2 = getSharedPreferences("userName", Context.MODE_PRIVATE);
                            SharedPreferences.Editor editor2 = pref2.edit();
                            editor2.putString("user", user);
                            editor2.commit();

                            //Limpiando el historial de actividades y comenzando la actividad MainActivity
                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            startActivity(intent);
                        }else {
                            //Si no es administrador, entonces se crea un intent para iniciar la actividad MainActivity y se guarda el
                           Intent intent = new Intent(Login.this, MainActivity.class);
                           esAdmin = usersList.get(i).isAdmin();
                           SharedPreferences pref = getSharedPreferences("admin", Context.MODE_PRIVATE);
                           SharedPreferences.Editor editor = pref.edit();
                           editor.putBoolean("isAdmin",false);
                           editor.commit();
                           intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                           startActivity(intent);
                       }

                   }
               }
            }
        });





    }
}