package com.example.footballclub.activities;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.footballclub.R;
import com.example.footballclub.clases.Equipo;
import com.example.footballclub.clases.Liga;

import java.util.ArrayList;

public class LigaViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

    public static final String EXTRA_OBJETO_LIGA = "com.example.footballclub.objeto_liga";
    public TextView txt_rv_nombreLiga;
    public TextView txt_rv_paisLiga;
    public TextView txt_rv_fechaInicio;
    public ImageView img_liga;
    ListaLigasAdapter leAdapter;

    public LigaViewHolder(@NonNull View itemView, ListaLigasAdapter leAdapter){
        super(itemView);
        txt_rv_nombreLiga = (TextView) itemView.findViewById(R.id.txt_rv_nombreLiga);
        txt_rv_paisLiga =(TextView) itemView.findViewById(R.id.txt_rv_paisLiga);
        txt_rv_fechaInicio = (TextView) itemView.findViewById(R.id.txt_rv_fechaInicio);
        img_liga = (ImageView) itemView.findViewById(R.id.img_rv_imagenLiga);
        this.leAdapter = leAdapter;
        itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int position = getAdapterPosition();
        ArrayList<Liga> ligas = this.leAdapter.getListaLigas();
        Liga l = ligas.get(position);
        Intent intent = new Intent(leAdapter.getC(), MostrarDetallesEquipoActivity.class);
        intent.putExtra(EXTRA_OBJETO_LIGA, l);
        leAdapter.getC().startActivity(intent);
    }
}
