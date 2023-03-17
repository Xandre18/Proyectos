package com.example.proyectoerp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.proyectoerp.fragment_admins.ProveedoresFragment;
import com.example.proyectoerp.objects.Supplier;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class EditSupplider extends AppCompatActivity {

    // Variables miembro de la clase
    FloatingActionButton editSupplier;
    EditText inputProducto, inputCompañia, inputEmail, inputPhone, inputDir;
    DBHandler handler;

    // Método onCreate() que se ejecuta cuando se crea la actividad
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_supplider);

        // Se obtiene el paquete de extras enviado a esta actividad
        Bundle bndl = getIntent().getExtras();

        // Se obtienen las referencias de los elementos visuales
        editSupplier = findViewById(R.id.editSupplider);
        inputCompañia = findViewById(R.id.inputCompañia);
        inputDir = findViewById(R.id.inputDir);
        inputEmail = findViewById(R.id.inputEmail);
        inputPhone = findViewById(R.id.inputPhone);
        inputProducto = findViewById(R.id.inputProducto);

        // Se establecen los valores de los EditText a partir de los datos del paquete de extras
        inputCompañia.setText(bndl.getString("comp"));
        inputDir.setText(bndl.getString("dir"));
        inputEmail.setText(bndl.getString("mail"));
        inputPhone.setText(bndl.getString("tlf"));
        inputProducto.setText(bndl.getString("prod"));

        // Se configura el listener del botón de edición de proveedores
        editSupplier.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Se obtienen los valores de los EditText
                String prod = inputProducto.getText().toString();
                String comp = inputCompañia.getText().toString();
                String dir = inputDir.getText().toString();
                String mail = inputEmail.getText().toString();
                String tlf = inputPhone.getText().toString();

                // Se crea un objeto Supplier a partir de los valores de los EditText
                Supplier s = new Supplier(prod,comp,mail,tlf,dir);

                // Se obtiene el identificador original del proveedor a partir del paquete de extras
                int original = bndl.getInt("id");

                // Se crea un objeto DBHandler para poder actualizar la información del proveedor en la base de datos
                handler = new DBHandler(EditSupplider.this);

                // Se actualiza la información del proveedor en la base de datos
                handler.updateSupplider(original,s);

                // Se crea un Intent para regresar a la actividad principal y se agrega la bandera FLAG_ACTIVITY_CLEAR_TOP para borrar la pila de actividades
                Intent intent = new Intent(EditSupplider.this, ProveedoresFragment.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);

            }
        });

    }
}