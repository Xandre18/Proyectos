package com.example.proyectoerp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.proyectoerp.fragment_admins.ClientesFragment;
import com.example.proyectoerp.objects.Cliente;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class EditCustomer extends AppCompatActivity {

    FloatingActionButton editCustomer; // Botón para editar el cliente
    EditText inputNombre,  inputApellido,inputEdad, inputTel, inputEmail; // Campos de entrada para editar los datos del cliente
    DBHandler handler; // Manejador de la base de datos

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_customer); // Asignar el diseño de la actividad a la vista
        Bundle bndl = getIntent().getExtras(); // Obtener los datos del cliente que se van a editar

        // Asignar las vistas a las variables correspondientes
        editCustomer = findViewById(R.id.editCustomer);
        inputNombre = findViewById(R.id.inputNombre);
        inputApellido = findViewById(R.id.inputApellido);
        inputEdad = findViewById(R.id.inputEdad);
        inputTel = findViewById(R.id.inputTel);
        inputEmail = findViewById(R.id.inputEmail);

        // Establecer los valores actuales del cliente en los campos de entrada
        inputNombre.setText(bndl.getString("nom"));
        inputApellido.setText(bndl.getString("ape"));
        inputEdad.setText(bndl.getString("ed"));
        inputTel.setText(bndl.getInt("tlf")+"");
        inputEmail.setText(bndl.getString("mail"));

        // Manejar el evento de clic en el botón de edición del cliente
        editCustomer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Obtener los valores actualizados del cliente de los campos de entrada
                String nombre = inputNombre.getText().toString();
                String apellido = inputApellido.getText().toString();
                String edad = inputEdad.getText().toString();
                String email = inputEmail.getText().toString();
                int tel = Integer.parseInt(inputTel.getText().toString());

                // Crear un objeto de Cliente con los nuevos datos
                Cliente c = new Cliente(nombre, apellido, edad, email,tel);

                // Actualizar el cliente en la base de datos utilizando el manejador
                handler = new DBHandler(EditCustomer.this);
                int original = bndl.getInt("tlf");
                handler.updateCustomer(original, c);

                // Volver a la actividad principal y limpiar la pila de actividades
                Intent intent = new Intent(EditCustomer.this, MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });
    }
}