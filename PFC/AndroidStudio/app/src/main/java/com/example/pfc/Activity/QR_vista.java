package com.example.pfc.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;

import com.example.pfc.R;
import com.google.zxing.BarcodeFormat;
import com.journeyapps.barcodescanner.BarcodeEncoder;

public class QR_vista extends AppCompatActivity {
    ImageView qrcode;
    Button volver;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qr_vista);

        getSupportActionBar().hide();

        qrcode = findViewById(R.id.qrcode);
        volver = findViewById(R.id.back);

        Intent intent = getIntent();
        String stringQR = intent.getStringExtra("QRCODE");
        try {
            BarcodeEncoder barcodeEncoder = new BarcodeEncoder();
            Bitmap bitmap = barcodeEncoder.encodeBitmap(stringQR, BarcodeFormat.QR_CODE, 750, 750);
            qrcode.setImageBitmap(bitmap);
        }catch (Exception e){
            e.getStackTrace();
        }

        volver.setOnClickListener(v -> {
            Intent intent2 = new Intent(QR_vista.this , MainActivity.class);
            intent2.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent2);
        });



    }
}