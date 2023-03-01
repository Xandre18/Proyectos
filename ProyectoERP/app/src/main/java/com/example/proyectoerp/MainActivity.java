package com.example.proyectoerp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv = findViewById(R.id.textView);
        Bundle b = getIntent().getExtras();

        if(b.getBoolean("isAdmin")){
            tv.setText("Admin");
        }else{
            tv.setText("User");
        }
    }
}