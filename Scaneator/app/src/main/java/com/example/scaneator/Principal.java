package com.example.scaneator;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.journeyapps.barcodescanner.BarcodeEncoder;

public class Principal extends AppCompatActivity {
    ImageView imgQR, imgBarras, imgGenQR, result;
    TextView tvScaner, tvScanBarras, tvGenQR ;
    EditText genBar , genQR;
    FloatingActionButton btnSalir;
    Button btnGenerar1, btnGenerar2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        getSupportActionBar().hide();
        //Declaracion de las variables de que necesitamos
        btnGenerar1 = findViewById(R.id.bntGen1);
        btnGenerar1.setVisibility(View.INVISIBLE);
        btnGenerar2 = findViewById(R.id.bntGen2);
        btnGenerar2.setVisibility(View.INVISIBLE);

        genBar = findViewById(R.id.editText);
        genBar.setVisibility(View.INVISIBLE);
        genQR = findViewById(R.id.editText2);
        genQR.setVisibility(View.INVISIBLE);

        btnSalir = findViewById(R.id.bntSalir);
        btnSalir.setVisibility(View.INVISIBLE);
        imgQR = findViewById(R.id.imgQR);
        imgBarras = findViewById(R.id.imgBarras);
        imgGenQR  = findViewById(R.id.imgGenQR);
        result = findViewById(R.id.result);
        result.setVisibility(View.INVISIBLE);

        tvScaner = findViewById(R.id.tvEscanear);
        tvScanBarras = findViewById(R.id.tvScanBaras);
        tvGenQR = findViewById(R.id.tvGenQR);


        //Onclick que pone en invisible todas las imagenes y textView y pone en visible un boton y un editText
        imgGenQR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imgQR.setVisibility(View.INVISIBLE);
                imgBarras.setVisibility(View.INVISIBLE);
                imgGenQR.setVisibility(View.INVISIBLE);
                tvScaner.setVisibility(View.INVISIBLE);
                tvScanBarras.setVisibility(View.INVISIBLE);
                tvGenQR.setVisibility(View.INVISIBLE);
                genQR.setVisibility(View.VISIBLE);
                btnGenerar2.setVisibility(View.VISIBLE);
            }

        });
        //Genera el codigo QR y lo muestra
        btnGenerar2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                genQR.setVisibility(View.INVISIBLE);
                btnGenerar2.setVisibility(View.INVISIBLE);
                btnSalir.setVisibility(View.VISIBLE);
                try {
                    BarcodeEncoder barcodeEncoder = new BarcodeEncoder();
                    Bitmap bitmap = barcodeEncoder.encodeBitmap(genQR.getText().toString(), BarcodeFormat.QR_CODE, 500, 500);
                    result.setImageBitmap(bitmap);
                    result.setVisibility(View.VISIBLE);
                } catch (WriterException e) {
                    e.printStackTrace();
                }
            }


        });
        //Onclick que pone en invisible todas las imagenes y textView y pone en visible un boton y un editText
        imgBarras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imgQR.setVisibility(View.INVISIBLE);
                imgBarras.setVisibility(View.INVISIBLE);
                imgGenQR.setVisibility(View.INVISIBLE);
                tvScaner.setVisibility(View.INVISIBLE);
                tvScanBarras.setVisibility(View.INVISIBLE);
                tvGenQR.setVisibility(View.INVISIBLE);
                genBar.setVisibility(View.VISIBLE);
                btnGenerar1.setVisibility(View.VISIBLE);

            }
        });
        //Genera un codigo de barras y lo muestra
        btnGenerar1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                genBar.setVisibility(View.INVISIBLE);
                btnGenerar1.setVisibility(View.INVISIBLE);
                btnSalir.setVisibility(View.VISIBLE);
                try {
                    BarcodeEncoder barcodeEncoder = new BarcodeEncoder();
                    Bitmap bitmap = barcodeEncoder.encodeBitmap(genBar.getText().toString(), BarcodeFormat.UPC_A, 500, 500);
                    result.setImageBitmap(bitmap);
                    result.setVisibility(View.VISIBLE);
                } catch (WriterException e) {
                    e.printStackTrace();
                }
            }
        });
        //Onclick para abrir la camara y escanear cualquier codigo
        imgQR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                imgQR.setVisibility(View.INVISIBLE);
                imgBarras.setVisibility(View.INVISIBLE);
                imgGenQR.setVisibility(View.INVISIBLE);
                tvScaner.setVisibility(View.INVISIBLE);
                tvScanBarras.setVisibility(View.INVISIBLE);
                tvGenQR.setVisibility(View.INVISIBLE);


                IntentIntegrator integrator = new IntentIntegrator(Principal.this);
                integrator.setDesiredBarcodeFormats(IntentIntegrator.ALL_CODE_TYPES);
                integrator.setPrompt("Escanear Código");
                integrator.setCameraId(0);
                integrator.setBeepEnabled(true);
                integrator.setBarcodeImageEnabled(true);
                integrator.initiateScan();
            }

        });

        //Boton para volver a la pantalla principal
        btnSalir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imgQR.setVisibility(View.VISIBLE);
                imgBarras.setVisibility(View.VISIBLE);
                imgGenQR.setVisibility(View.VISIBLE);
                tvScaner.setVisibility(View.VISIBLE);
                tvScanBarras.setVisibility(View.VISIBLE);
                tvGenQR.setVisibility(View.VISIBLE);
                btnSalir.setVisibility(View.INVISIBLE);
                tvScanBarras.setText("Generar Codigo de barras");
                result.setVisibility(View.INVISIBLE);
                imgBarras.setImageResource(R.drawable.codigodebarras);
                genQR.setText("");
                genBar.setText("");
            }
        });
    }
    //Muestra el resultado de escanear un código de barras o un QR
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        IntentResult resultado = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (resultado != null) {
            if (resultado.getContents() == null) {
                Toast.makeText(this, "Cancelado", Toast.LENGTH_LONG).show();
            }else {
                tvScanBarras.setVisibility(View.VISIBLE);
                btnSalir.setVisibility(View.VISIBLE);
                tvScanBarras.setText(resultado.getContents().toString());
            }
        }else{
            super.onActivityResult(requestCode, resultCode, data);
        }
    }
}