package com.example.proyectoerp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private DrawerLayout drawerLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar); //Ignore red line errors
        setSupportActionBar(toolbar);

        drawerLayout = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout,toolbar,R.string.open_nav,R.string.close_nav);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();



        Bundle b = getIntent().getExtras();
        if(b.getBoolean("isAdmin")){

        }else{

        }
    }

    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.nav_home:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new HomeFragment()).commit();
                break;
            case R.id.nav_tesoreria:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new TesoreriaFragment()).commit();
                break;
            case R.id.nav_clientes:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new ClientesFragment()).commit();
                break;
            case R.id.nav_proveedores:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new ProveedoresFragment()).commit();
                break;
            case R.id.nav_correo:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new CorreoFragment()).commit();
        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }
}