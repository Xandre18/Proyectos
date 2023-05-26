package com.example.pfc.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pfc.Objetos.Venta;
import com.example.pfc.R;

import org.w3c.dom.Text;

import java.util.List;

public class VentasAdapter extends RecyclerView.Adapter<VentasAdapter.ViewHolder> {

    private List<Venta> mData;
    private LayoutInflater mInflater;
    private Context context;
    final VentasAdapter.OnItemClickListener listener;

    public interface OnItemClickListener {
        void onItemClick(Venta v);
    }
    public VentasAdapter(List<Venta> mData , Context context , VentasAdapter.OnItemClickListener listener){
        this.mInflater = LayoutInflater.from(context);
        this.mData = mData;
        this.context = context;
        this.listener = listener;
    }
    @NonNull
    @Override
    public VentasAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = mInflater.inflate(R.layout.venta_list_item,null);
        return new VentasAdapter.ViewHolder(v);
    }
    @Override
    public void onBindViewHolder(@NonNull VentasAdapter.ViewHolder holder, int position) {
        holder.bindData(mData.get(position));


    }
    @Override
    public int getItemCount() {
        return mData.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView fecha , codV;
        public ViewHolder(@NonNull View v) {
            super(v);
          codV = v.findViewById(R.id.codigo);
          fecha = v.findViewById(R.id.fechaV);
        }

        void bindData(final Venta venta ){
            codV.setText("Codigo de la venta: " + venta.getCodigo());
            fecha.setText("Fecha: " + venta.getFecha());

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onItemClick(venta);
                }
            });
        }
    }


}
