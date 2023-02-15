package com.example.swimminday;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;

import org.w3c.dom.Entity;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class MainActivity4 extends AppCompatActivity {
        EditText efecha , etime, eDistancia;
    ImageView calendario;
    Button crear;
    final Calendar myCalendar= Calendar.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);
        getSupportActionBar().hide();
        crear = findViewById(R.id.Crear);
        calendario = findViewById(R.id.calendario);
        efecha = findViewById(R.id.inputFecha);
        etime = findViewById(R.id.inputTime);
        eDistancia = findViewById(R.id.inputDistancia);
        DatePickerDialog.OnDateSetListener date =new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int day) {
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH,month);
                myCalendar.set(Calendar.DAY_OF_MONTH,day);
                updateLabel();
            }
        };
        efecha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DatePickerDialog(MainActivity4.this,date,myCalendar.get(Calendar.YEAR),myCalendar.get(Calendar.MONTH),myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        crear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               String fecha = efecha.getText().toString();
               int tiempo = Integer.parseInt(etime.getText().toString());
               int distacia = Integer.parseInt(eDistancia.getText().toString());
               Intent intent = new Intent(MainActivity4.this, MainActivity3.class);
               intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
               intent.putExtra("fecha", fecha);
               intent.putExtra("tiempo", tiempo);
               intent.putExtra("distancia", distacia);
               startActivity(intent);
               finish();

            }
        });


    }

    private void updateLabel(){
        String myFormat="dd/MM/yy";
        SimpleDateFormat dateFormat=new SimpleDateFormat(myFormat, Locale.US);
        efecha.setText(dateFormat.format(myCalendar.getTime()));
    }
}