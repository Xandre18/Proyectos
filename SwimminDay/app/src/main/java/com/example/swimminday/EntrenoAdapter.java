package com.example.swimminday;

import android.content.Context;
import android.content.ReceiverCallNotAllowedException;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class EntrenoAdapter extends RecyclerView.Adapter<EntrenoAdapter.ViewHolder>
        implements View.OnClickListener {
    public List<Entrenamiento> ndata;
    public LayoutInflater nInflater;
    public Context context;
    private View.OnClickListener escuchador;

    public EntrenoAdapter(List<Entrenamiento> itemList, Context context){
        this.nInflater = LayoutInflater.from(context);
        this.context = context;
        this.ndata = itemList;
    }
    @Override
    public int getItemCount(){return ndata.size();}

    @Override
    public EntrenoAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View v = nInflater.inflate(R.layout.list_element, null);
        v.setOnClickListener(this);
        return new EntrenoAdapter.ViewHolder(v);
    }
    public void setOnClickListener(View.OnClickListener lisener){
        this.escuchador = lisener;
    }
    @Override
    public void onBindViewHolder(final EntrenoAdapter.ViewHolder holder, final int position){
        holder.bindData(ndata.get(position));

    }

    public void setItem(List<Entrenamiento> item){ndata= item;};

    @Override
    public void onClick(View view) {
        if(escuchador!= null){
            escuchador.onClick(view);
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView iconImage;
        TextView fecha, tiempo, distancia;
        ViewHolder(View itemView){
            super(itemView);
            iconImage= itemView.findViewById(R.id.imgCard);
            fecha = itemView.findViewById(R.id.fecha);
            tiempo = itemView.findViewById(R.id.tiempo);
            distancia = itemView.findViewById(R.id.distancia);


        }
        void bindData(final Entrenamiento item){
          fecha.setText("Fecha: " + item.getDate());
          tiempo.setText("Tiempo: " + item.getTiempo() + "mins");
          distancia.setText("Distacia: " +item.getDistancia() + "m");
        }
    }
}
