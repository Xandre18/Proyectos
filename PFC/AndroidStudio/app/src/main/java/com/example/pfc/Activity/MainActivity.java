package com.example.pfc.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;


import com.example.pfc.BaseDatos.DBHandler;
import com.example.pfc.Fragments.Perfil;
import com.example.pfc.Fragments.Inicio;
import com.example.pfc.Fragments.Ventas;
import com.example.pfc.Objetos.Cliente;
import com.example.pfc.R;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private DrawerLayout drawerLayout;
    Toolbar toolbar;
    DBHandler dbHandler;
    ArrayList<Cliente> cList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
         toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        drawerLayout = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);

        navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout,toolbar,R.string.abrir_drawer,R.string.cerrar_drawer);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new Inicio()).commit();
            navigationView.setCheckedItem(R.id.nav_catalogo);
        }


    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.nav_catalogo:
                toolbar.setTitle("Catálogo");
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new Inicio()).commit();
                break;
            case R.id.nav_perfil:
                toolbar.setTitle("Mi perfil");
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new Perfil()).commit();
                break;
            case R.id.nav_compras:
                toolbar.setTitle("Mis compras");
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new Ventas()).commit();
                break;
        }

        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }
    public void cerrarSesion(){
        dbHandler = new DBHandler(this);
        cList = dbHandler.getClientes();
        for(int i = 0; i< cList.size();i++){
            if(cList.get(i).isSesion()){
                dbHandler.setSesionCol(cList.get(i).getId(), false);
            }
        }
    }

    @Override
    public void onBackPressed(){
        if(drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        }else{
            super.onBackPressed();
            cerrarSesion();
        }
    }
}