package com.example.pfc.Fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pfc.Adapters.ProductosAdapter;
import com.example.pfc.BaseDatos.DBHandler;
import com.example.pfc.Objetos.Producto;
import com.example.pfc.Objetos.ProductoCantidad;
import com.example.pfc.Objetos.Venta;
import com.example.pfc.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import kotlin.contracts.Returns;


public class Inicio extends Fragment {
    View v;
    List<Producto> productoList;
    DBHandler dbHandler;
    ProductosAdapter productosAdapter;
    RecyclerView recyclerView;
    Button comprar;
    TextView tvCant;


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(R.id.productosList);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        productosAdapter = new ProductosAdapter(productoList, getActivity());
        recyclerView.setAdapter(productosAdapter);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_inicio, container, false);

        dbHandler = new DBHandler(v.getContext());
        productoList = dbHandler.getProductos();
        comprar = v.findViewById(R.id.btnComprar);
        if(productoList.size() == 0){
            init(v);
        }else{
            setearAdaptador();
        }

        comprar.setOnClickListener(view -> {
            Toast.makeText(v.getContext(), "lalalalalalala", Toast.LENGTH_SHORT).show();
            ArrayList<Integer> cantidades = new ArrayList<>();
            ArrayList<Integer> productsId = new ArrayList<>();
            ArrayList<ProductoCantidad> prodCantList = new ArrayList<>();
            int stockFinal = 0;
            for(int i = 0; i< productoList.size();i ++){

                int cantidad = productoList.get(i).getCantidad();
                stockFinal = productoList.get(i).getStock() - cantidad;
                dbHandler.updateStock(productoList.get(i).getId(), stockFinal);
                cantidades.add(cantidad);
                productsId.add(productoList.get(i).getId());

            }

            Log.d("cantidades", "Cantidades: " + cantidades.toString());
            int contador = 0;
            boolean ceroProductos;
            for (int i = 0; i< cantidades.size(); i++){
                if(cantidades.get(i) == 0){
                    contador++;
                }
            }

            ceroProductos = contador == productoList.size();
            if (ceroProductos){
                Toast.makeText(v.getContext(), "Debes añadir por lo menos 1 producto", Toast.LENGTH_SHORT).show();
            }else {
                Venta venta = new Venta(dbHandler.getIDuserConectado(), obtenerFechaActual());
                prodCantList = getProdCant(productsId, cantidades);
                dbHandler.addVenta(venta, prodCantList);
            }


        });




        return v;
    }
    private String obtenerFechaActual() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        String fechaActual = dateFormat.format(new Date());
        return fechaActual;
    }

    public ArrayList<ProductoCantidad> getProdCant(ArrayList<Integer> productos, ArrayList<Integer> cantidades) {
        ArrayList<ProductoCantidad> lisTicked = new ArrayList<>();

        for (int i = 0; i < productos.size(); i++) {
            int producto = productos.get(i);
            int cantidad = cantidades.get(i);

            if (cantidad > 0) {
                ProductoCantidad productoCantidad = new ProductoCantidad(producto, cantidad);
                lisTicked.add(productoCantidad);
            }
        }

        return lisTicked;
    }

    public void init(View v){

        Producto p1 = new Producto(123456789, 200, 2, R.drawable.lapiz, "Lapiz");
        Producto p2 = new Producto(987654321, 200, 2, R.drawable.boligrafo, "Bolígrafo");
        Producto p3 = new Producto(159753852, 200, 1, R.drawable.goma, "Goma");
        Producto p4 = new Producto(357951789, 200, 10, R.drawable.estuche, "Estuche");
        Producto p5 = new Producto(789123456, 200, 3, R.drawable.regla, "Regla");
        Producto p6 = new Producto(456789123, 200, 5, R.drawable.compas, "Compas");
        Producto p7 = new Producto(789456123, 200, 10, R.drawable.mochila, "Mochila");
        Producto p8 = new Producto(321654987, 200, 1, R.drawable.pegamento, "Pegamento");
        Producto p9 = new Producto(963852741, 200, 3, R.drawable.tijeras, "Tijeras");
        dbHandler.addProducto(p1);
        dbHandler.addProducto(p2);
        dbHandler.addProducto(p3);
        dbHandler.addProducto(p4);
        dbHandler.addProducto(p5);
        dbHandler.addProducto(p6);
        dbHandler.addProducto(p7);
        dbHandler.addProducto(p8);
        dbHandler.addProducto(p9);
        productoList = dbHandler.getProductos();
        setearAdaptador();
    }

    public void setearAdaptador(){
        productosAdapter = new ProductosAdapter(productoList, v.getContext());
        recyclerView = v.findViewById(R.id.productosList);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(v.getContext()));
        recyclerView.setAdapter(productosAdapter);
    }


}