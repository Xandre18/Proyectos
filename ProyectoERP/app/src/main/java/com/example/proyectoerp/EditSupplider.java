package com.example.proyectoerp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.proyectoerp.objects.Supplier;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class EditSupplider extends AppCompatActivity {

    FloatingActionButton editSupplier;
    EditText inputProducto, inputCompañia, inputEmail, inputPhone, inputDir;
    DBHandler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_supplider);

        Bundle bndl = getIntent().getExtras();

        editSupplier = findViewById(R.id.editSupplider);
        inputCompañia = findViewById(R.id.inputCompañia);
        inputDir = findViewById(R.id.inputDir);
        inputEmail = findViewById(R.id.inputEmail);
        inputPhone = findViewById(R.id.inputPhone);
        inputProducto = findViewById(R.id.inputProducto);

        inputCompañia.setText(bndl.getString("comp"));
        inputDir.setText(bndl.getString("dir"));
        inputEmail.setText(bndl.getString("mail"));
        inputPhone.setText(bndl.getString("tlf"));
        inputProducto.setText(bndl.getString("prod"));

        editSupplier.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String prod = inputProducto.getText().toString();
                String comp = inputCompañia.getText().toString();
                String dir = inputDir.getText().toString();
                String mail = inputEmail.getText().toString();
                String tlf = inputPhone.getText().toString();

                Supplier s = new Supplier(prod,comp,mail,tlf,dir);

                int original = bndl.getInt("id");

                handler = new DBHandler(EditSupplider.this);

                handler.updateSupplider(original,s);


                Intent intent = new Intent(EditSupplider.this, MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);


            }
        });



    }
}