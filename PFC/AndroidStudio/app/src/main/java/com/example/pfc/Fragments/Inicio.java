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
import com.example.pfc.R;

import java.util.ArrayList;
import java.util.List;


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
//            ArrayList<Integer> cantidades = new ArrayList<>();
//            for(int i = 0; i < productosAdapter.getItemCount(); i++){
//                View itemView = recyclerView.getChildAt(i);
//                TextView tvCant = itemView.findViewById(R.id.cantidad);
//                int cantidad = Integer.parseInt(tvCant.getText().toString());
//                cantidades.add(cantidad);
//            }
            ArrayList<Integer> cantidades = new ArrayList<>();
            int stockFinal = 0;
            for(int i = 0; i< productoList.size();i ++){

                int cantidad = productoList.get(i).getCantidad();
                stockFinal = productoList.get(i).getStock() - cantidad;
                dbHandler.updateStock(productoList.get(i).getId(), stockFinal);
                cantidades.add(cantidad);

            }

            Log.d("cantidades", "Cantidades: " + cantidades.toString());


        });




        return v;
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