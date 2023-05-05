package com.example.pfc.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pfc.Objetos.Producto;
import com.example.pfc.R;

import java.util.List;

public class ProductosAdapter extends RecyclerView.Adapter<ProductosAdapter.ViewHolder> {
    private List<Producto> mData;
    private LayoutInflater mInflater;
    private Context context;

    public ProductosAdapter(List<Producto> mData, Context context) {
        this.mInflater = LayoutInflater.from(context);
        this.mData = mData;
        this.context = context;
    }

    @NonNull
    @Override
    public ProductosAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.producto_list_item, null);
        return new ProductosAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductosAdapter.ViewHolder holder, int position) {
        holder.bindData(mData.get(position));
    }


    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView imgProducto;
        TextView nproducto, precioP, cantidad;
        ImageButton menos , mas;
        int numCant;

        ViewHolder(View v){
            super(v);
            imgProducto = v.findViewById(R.id.imgProducto);
            nproducto = v.findViewById(R.id.nombreP);
            precioP = v.findViewById(R.id.precioP);
            cantidad = v.findViewById(R.id.cantidad);
            menos = v.findViewById(R.id.btnMenos);
            mas = v.findViewById(R.id.btnMas);


        }
        void bindData(final Producto item){

            imgProducto.setImageResource(item.getImg());
            nproducto.setText(item.getNombre());
            precioP.setText(item.getPrecio() + " €");
            numCant = Integer.parseInt(cantidad.getText().toString());
            menos.setOnClickListener(view -> {
                if(numCant != 0){
                    numCant --;
                    cantidad.setText("" + numCant);

                }
            });
            mas.setOnClickListener(view -> {
                if(numCant < item.getStock()){
                    numCant ++;
                    cantidad.setText("" + numCant);
                }
            });

        }
    }




}
