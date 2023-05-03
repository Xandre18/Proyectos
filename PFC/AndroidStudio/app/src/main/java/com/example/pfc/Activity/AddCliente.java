package com.example.pfc.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.pfc.BaseDatos.DBHandler;
import com.example.pfc.Objetos.Cliente;
import com.example.pfc.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Objects;

public class AddCliente extends AppCompatActivity {
    ImageView back , next ;
    ConstraintLayout uno , dos;
    EditText eDni , eTlf , eNombre, eApellido, eMail, eDir,  eUser, ePwd;
    FloatingActionButton registrar;
    DBHandler dbHandler;
    static ArrayList<Cliente> clienteList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_cliente);
        getSupportActionBar().setTitle("Registro 1/2");
        Toast.makeText(this, "Rellene las dos páginas del formulario para registrarse", Toast.LENGTH_SHORT).show();
        eDni = findViewById(R.id.eDni);
        eTlf = findViewById(R.id.etlf);
        eNombre = findViewById(R.id.eName);
        eApellido = findViewById(R.id.eApellido);
        eMail = findViewById(R.id.eMail);
        eDir =  findViewById(R.id.eDir);
        eUser = findViewById(R.id.eUser);
        ePwd =  findViewById(R.id.epwd);
        registrar = findViewById(R.id.registrar);
        back = findViewById(R.id.back);
        next = findViewById(R.id.next);
        uno = findViewById(R.id.DP);
        dos = findViewById(R.id.credenciales);

        
        dbHandler = new DBHandler(this);
        clienteList = dbHandler.getClientes();
        
        back.setOnClickListener(view -> {
            uno.setVisibility(View.VISIBLE);
            dos.setVisibility(View.INVISIBLE);
            getSupportActionBar().setTitle("Registro 1/2");
        });

        next.setOnClickListener(view -> {
            uno.setVisibility(View.INVISIBLE);
            dos.setVisibility(View.VISIBLE);
            getSupportActionBar().setTitle("Registro 2/2");
        });

        registrar.setOnClickListener(view -> {
            String dni , tlf, nombre , apellido, email , direccion, usuario, contrasenha;
            dni = eDni.getText().toString().trim();
            tlf = eTlf.getText().toString();
            nombre = eNombre.getText().toString();
            apellido = eApellido.getText().toString();
            email = eMail.getText().toString();
            direccion = eDir.getText().toString();
            usuario = eUser.getText().toString();
            contrasenha = ePwd.getText().toString();

            if(dni.isEmpty() || tlf.isEmpty() || nombre.isEmpty() || apellido.isEmpty() ||  email.isEmpty() || direccion.isEmpty() || usuario.isEmpty() || contrasenha.isEmpty()){
                Toast.makeText(this, "Faltan campos por rellenar. Porfavor compruebe los campos", Toast.LENGTH_SHORT).show();
            }else{
                boolean dniOk = validarDNI(dni);
                boolean emailOk = validarEmail(email);
                boolean tflOk = validarTelefono(tlf);
                boolean userOk = validarUsuario(usuario);

                if(dniOk && emailOk && tflOk && userOk){
                    Cliente c = new Cliente(dni, tlf, nombre, apellido, email, direccion, usuario, contrasenha);
                    dbHandler.addCliente(c);
                    Toast.makeText(this, "Registro correcto", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(AddCliente.this, Login.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                }else{
                    if(!dniOk){
                        eDni.setText("");
                        Toast.makeText(this, "DNI no valido", Toast.LENGTH_SHORT).show();
                    }
                    if(!emailOk){
                        eMail.setText("");
                        Toast.makeText(this, "Email no valido", Toast.LENGTH_SHORT).show();
                    }
                    if(!tflOk){
                        eTlf.setText("");
                        Toast.makeText(this, "Teléfono no valido", Toast.LENGTH_SHORT).show();
                    }
                    if(!userOk){
                        eUser.setText("");
                        Toast.makeText(this, "Usuario existente", Toast.LENGTH_SHORT).show();
                    }
                }

            }

        });


    }

    public static boolean validarDNI(String dni) {
        final String letras = "TRWAGMYFPDXBNJZSQVHLCKE";
        boolean valido = false;
        try {
            // Comprobar que la longitud de la cadena es 9
            if (dni.length() == 9) {
                // Extraer la letra y los números del DNI
                char letra = Character.toUpperCase(dni.charAt(8));
                int numeros = Integer.parseInt(dni.substring(0, 8));

                // Calcular la letra correspondiente a los números
                int resto = numeros % 23;
                char letraCalculada = letras.charAt(resto);

                // Comparar la letra del DNI con la letra calculada
                if (letra == letraCalculada) {
                    valido = true;
                }
            }
        } catch (NumberFormatException e) {
            // Si hay un error al parsear la cadena como número, el DNI no es válido
        }
        return valido;
    }
    //Devuelve true si el email es valido;
    public static boolean validarEmail(String email) {
        String regex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";
        return email.matches(regex);
    }
    //Devuelve true si el telefono es valido;
    public static boolean validarTelefono(String telefono) {
        String regex = "^\\d{9}$";
        return telefono.matches(regex);
    }
    //Devuelve true si el usuario no coincide con ninguno de la base de datos;
    public boolean validarUsuario(String usuario) {
//        dbHandler = new DBHandler(AddCliente.this);
//        clienteList = dbHandler.getClientes();
        for(int i = 0; i < clienteList.size(); i++){
            if(Objects.equals(usuario, clienteList.get(i).getUsuario())){
                return false;
            }
        }
        return true;
    };
}