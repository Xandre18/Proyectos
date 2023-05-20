package com.example.pfc.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.pfc.BaseDatos.DBHandler;
import com.example.pfc.Objetos.ProductoCantidad;
import com.example.pfc.Objetos.Venta;
import com.example.pfc.R;
import com.google.zxing.BarcodeFormat;
import com.journeyapps.barcodescanner.BarcodeEncoder;

import java.util.ArrayList;

public class InfoVenta extends AppCompatActivity {

    DBHandler handler;
    ArrayList<ProductoCantidad> lista;
    String stringQR;
    ImageView volver , qrReserva;
    TextView tvQR;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_venta);
        getSupportActionBar().hide();
        handler = new DBHandler(this);

        volver = findViewById(R.id.volver);
        qrReserva = findViewById(R.id.qrReserva);
        tvQR = findViewById(R.id.tvqr);
        Venta v = (Venta) getIntent().getSerializableExtra("OBJdata");
        lista = handler.getProductosCantidadVenta(v.getCodigo());

        stringQR = generarStringQR(lista);

        tvQR.setText(stringQR);

        try {
            BarcodeEncoder barcodeEncoder = new BarcodeEncoder();
            Bitmap bitmap = barcodeEncoder.encodeBitmap(stringQR, BarcodeFormat.QR_CODE, 750, 750);
            qrReserva.setImageBitmap(bitmap);
        }catch (Exception e){
            e.getStackTrace();
        }

        volver.setOnClickListener(view -> {
            Intent intent = new Intent(this , MainActivity.class);
            startActivity(intent);
            finish();
        });




    }


    public String generarStringQR( ArrayList<ProductoCantidad> prodCantList){
        String aux = "";

        for(int i =0 ; i < lista.size();i++){
            aux = aux + handler.getNombreByID(lista.get(i).getProducto()) + " --> " + lista.get(i).getCantidad() + "\n";
        }
        return aux;
    }
}