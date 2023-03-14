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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        dbHandler = new DBHandler(this);
        SharedPreferences pref = getSharedPreferences("itiUsers", Context.MODE_PRIVATE);

        if(!pref.getBoolean("itiUsers", false)){
            dbHandler.addUser(new User("admin", "admin", true));
            dbHandler.addUser(new User("user", "user", false));
            SharedPreferences.Editor editor = pref.edit();
            editor.putBoolean("itiUsers", true);
            editor.commit();
        }

        usersList = dbHandler.leerUsers();

        eUserName = findViewById(R.id.editUserName);
        ePaswd = findViewById(R.id.editPswd);
        btnLogin = findViewById(R.id.btnLogin);


        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = eUserName.getText().toString();
                String pwd = ePaswd.getText().toString();
                boolean esAdmin = false;
               for(int i = 0; i< usersList.size();i++){
                   if(user.equals(usersList.get(i).getName()) && pwd.equals(usersList.get(i).getPaswd())){

                       if(usersList.get(i).isAdmin()){
                           Intent intent = new Intent(Login.this, MainActivity.class);
                           esAdmin = usersList.get(i).isAdmin();
                           SharedPreferences pref = getSharedPreferences("admin", Context.MODE_PRIVATE);
                           SharedPreferences.Editor editor = pref.edit();
                           editor.putBoolean("isAdmin",true);
                           editor.commit();
                           intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                           startActivity(intent);
                       }else {
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