package com.example.pfc.Activity;

import androidx.appcompat.app.AppCompatActivity;

import com.example.pfc.BaseDatos.DBHandler;
import com.example.pfc.Objetos.Cliente;
import com.example.pfc.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;

public class Login extends AppCompatActivity {
    EditText eUsuario, ePwd;
    FloatingActionButton btnLogin;
    DBHandler dbHandler;
    ArrayList<Cliente> cList;
    ImageView registro;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportActionBar().hide();
        dbHandler = new DBHandler(this);
        //Obteniendo el objeto de SharedPreferences con el nombre "itiUsers" y el modo privado
        SharedPreferences pref = getSharedPreferences("itiUsers", Context.MODE_PRIVATE);
        if(!pref.getBoolean("itiUsers", false)){
            Cliente c = new Cliente("78598014E", "631600462", "Xandre", "Martinez", "byxass18@gmail.com", "Fraga do rei nº 59", "root", "root", true);
            dbHandler.addCliente(c);
            SharedPreferences.Editor editor = pref.edit();
            editor.putBoolean("itiUsers", true);
            editor.commit();
        }
        //Obteniendo la lista de usuarios desde la base de datos
        cList = dbHandler.getClientes();
        //Obteniendo los elementos de la vista
        eUsuario = findViewById(R.id.eUsuario);
        ePwd = findViewById(R.id.epwd);
        btnLogin = findViewById(R.id.login);
        registro = findViewById(R.id.singUp);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String usuario = eUsuario.getText().toString();
                String pwd = ePwd.getText().toString();
                for(int i = 0; i< cList.size();i++){
                    if(usuario.equals(cList.get(i).getUsuario()) && pwd.equals(cList.get(i).getContraseña()) ){
                        if(cList.get(i).isAdmin()){
                            Intent intent = new Intent(Login.this, MainActivity.class);
                            SharedPreferences pref = getSharedPreferences("admin", Context.MODE_PRIVATE);
                            SharedPreferences.Editor editor = pref.edit();
                            editor.putBoolean("isAdmin",true);
                            editor.putString("user",cList.get(i).getUsuario());
                            editor.commit();

                            //Limpiando el historial de actividades y comenzando la actividad MainActivity
                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            Toast.makeText(Login.this, "Inicio de sesion correcto", Toast.LENGTH_SHORT).show();
                            startActivity(intent);

                        }else{
                            //TODO: Programar el inicio de sesion de los usuarios que no son administradores


                        }
                    }else{
                        Toast.makeText(Login.this, "El usuario o la contraseña son incorrectos", Toast.LENGTH_SHORT).show();
                        Toast.makeText(Login.this, "Para registrarte utiliza el icono de arriba a la derecha", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        registro.setOnClickListener(view -> {
            Intent intent = new Intent(Login.this, AddCliente.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
        });



    }
}