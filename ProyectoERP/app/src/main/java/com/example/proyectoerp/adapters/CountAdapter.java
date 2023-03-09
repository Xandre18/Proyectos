package com.example.proyectoerp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.proyectoerp.R;
import com.example.proyectoerp.objects.ContControler;

import java.util.ArrayList;

public class CountAdapter extends BaseAdapter {
    Context c;
    int loyout;
    ArrayList<ContControler> contList;

    public CountAdapter(Context c, int loyout, ArrayList<ContControler> contList) {
        this.c = c;
        this.loyout = loyout;
        this.contList = contList;
    }


    @Override
    public int getCount() {
        return contList.size();
    }

    @Override
    public ContControler getItem(int position) {
        return contList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ContControler cc = getItem(position);
        if(convertView == null){
            convertView = LayoutInflater.from(this.c).inflate(this.loyout,parent,false);
        }

        TextView tvId = convertView.findViewById(R.id.tvId);
        TextView tvCantidad = convertView.findViewById(R.id.tvCantidad);
        TextView tvSaldo = convertView.findViewById(R.id.tvSaldo);

        tvId.setText("ID: " + cc.getId());
        tvCantidad.setText("Cant:" + cc.getMount());
        tvSaldo.setText("Saldo: " + cc.getBalance());
        return convertView;
    }
}
