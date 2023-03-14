package com.example.proyectoerp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.proyectoerp.fragment_admins.ClientesFragment;
import com.example.proyectoerp.fragment_admins.CorreoFragment;
import com.example.proyectoerp.fragment_admins.HomeFragment;
import com.example.proyectoerp.fragment_admins.ProveedoresFragment;
import com.example.proyectoerp.fragment_admins.SettingsFragment;
import com.example.proyectoerp.fragment_admins.TesoreriaFragment;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private DrawerLayout drawerLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SharedPreferences pref = getSharedPreferences("admin", Context.MODE_PRIVATE);
        Boolean isadmin = pref.getBoolean("isAdmin", false);
        if(isadmin){
            Toolbar toolbar = findViewById(R.id.toolbar);
            setSupportActionBar(toolbar);

            drawerLayout = findViewById(R.id.drawer_layout);
            NavigationView navigationView = findViewById(R.id.nav_view);
            navigationView.setNavigationItemSelectedListener(this);

            ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout,toolbar,R.string.open_nav,R.string.close_nav);
            drawerLayout.addDrawerListener(toggle);
            toggle.syncState();

            if (savedInstanceState == null) {
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new HomeFragment()).commit();
                navigationView.setCheckedItem(R.id.nav_home);
            }
        }else{
            Toast.makeText(this, "juan", Toast.LENGTH_SHORT).show();

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
                break;
            case R.id.nav_settings:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new SettingsFragment()).commit();
        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }
}