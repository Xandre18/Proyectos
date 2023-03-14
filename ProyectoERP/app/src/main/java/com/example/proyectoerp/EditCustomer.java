package com.example.proyectoerp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.proyectoerp.objects.Cliente;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class EditCustomer extends AppCompatActivity {

    FloatingActionButton editCustomer;
    EditText inputNombre,  inputApellido,inputEdad, inputTel, inputEmail;
    DBHandler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_customer);
        Bundle bndl = getIntent().getExtras();


        editCustomer = findViewById(R.id.editCustomer);
        inputNombre = findViewById(R.id.inputNombre);
        inputApellido = findViewById(R.id.inputApellido);
        inputEdad = findViewById(R.id.inputEdad);
        inputTel = findViewById(R.id.inputTel);
        inputEmail = findViewById(R.id.inputEmail);

        inputNombre.setText(bndl.getString("nom"));
        inputApellido.setText(bndl.getString("ape"));
        inputEdad.setText(bndl.getString("ed"));
        inputTel.setText(bndl.getInt("tlf")+"");
        inputEmail.setText(bndl.getString("mail"));




        editCustomer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nombre = inputNombre.getText().toString();
                String apellido = inputApellido.getText().toString();
                String edad = inputEdad.getText().toString();
                String email = inputEmail.getText().toString();
                int tel = Integer.parseInt(inputTel.getText().toString());

                Cliente c = new Cliente(nombre, apellido, edad, email,tel);

                handler = new DBHandler(EditCustomer.this);
                int original = bndl.getInt("tlf");
                handler.updateCustomer(original, c);

                Intent intent = new Intent(EditCustomer.this, MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);

            }
        });



    }
}