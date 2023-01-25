package com.example.footballclub.activities;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.footballclub.R;
import com.example.footballclub.clases.Liga;

import java.util.ArrayList;

public class ListaLigasAdapter extends RecyclerView.Adapter<LigaViewHolder> {

    private Context c;
    private ArrayList<Liga> listaLigas;
    private LayoutInflater mInflater;

    public ListaLigasAdapter(Context c, ArrayList<Liga> listaLigas){
        this.c = c;
        this.listaLigas = listaLigas;
        mInflater = LayoutInflater.from(c);
    }
    public ListaLigasAdapter(Context c){
        this.c = c;
        mInflater = LayoutInflater.from(c);
    }

    public Context getC() {
        return c;
    }

    public void setC(Context c) {
        this.c = c;
    }

    public ArrayList<Liga> getListaLigas() {
        return listaLigas;
    }

    public void setListaLigas(ArrayList<Liga> listaLigas) {
        this.listaLigas = listaLigas;
        notifyDataSetChanged();
    }

    public LayoutInflater getmInflater() {
        return mInflater;
    }

    public void setmInflater(LayoutInflater mInflater) {
        this.mInflater = mInflater;
    }

    @NonNull
    @Override
    public LigaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View mItemView = mInflater.inflate(R.layout.item_rv_liga, parent, false);
        return new LigaViewHolder(mItemView, this);
    }

    @Override
    public void onBindViewHolder(@NonNull LigaViewHolder holder, int position) {
        if (listaLigas != null){
            Liga liga_actual = listaLigas.get(position);
            holder.txt_rv_nombreLiga.setText(liga_actual.getNombreLiga());
            holder.txt_rv_paisLiga.setText("Pais: " + liga_actual.getPaisLiga());
            holder.txt_rv_fechaInicio.setText("Fecha de inicio: " + liga_actual.getFechaInicio());
            holder.img_liga.setImageResource(R.drawable.competicion);
        }
    }

    @Override
    public int getItemCount() {
        if (listaLigas != null){
            return listaLigas.size();
        }
        else{
            return 0;
        }
    }
}
