package com.example.footballclub.activities;

import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.footballclub.R;
import com.example.footballclub.clases.Equipo;

import java.util.ArrayList;

public class EquipoViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    public static final String EXTRA_OBJETO_EQUIPO = "com.example.footballclub.objeto_equipo";
    public static String EXTRA_USUARIO_INICIADO = MainActivity.USUARIO_INTRODUCIDO;
    public TextView txt_rv_nombreEquipo;
    public TextView txt_rv_ciudadEquipo;
    public TextView txt_rv_numTitulos;
    public TextView txt_rv_ligaEquipo;
    ListaEquiposAdapter leAdapter;

    public EquipoViewHolder(@NonNull View itemView, ListaEquiposAdapter leAdapter){
        super(itemView);
        txt_rv_nombreEquipo = (TextView) itemView.findViewById(R.id.txt_rv_nombreEquipo);
        txt_rv_ciudadEquipo =(TextView) itemView.findViewById(R.id.txt_rv_ciudadEquipo);
        txt_rv_numTitulos = (TextView) itemView.findViewById(R.id.txt_rv_numTitulos);
        txt_rv_ligaEquipo = (TextView) itemView.findViewById(R.id.txt_rv_ligaEquipo);
        this.leAdapter = leAdapter;
        itemView.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        int position = getAdapterPosition();
        ArrayList<Equipo> equipos = this.leAdapter.getListaEquipos();
        Equipo e = equipos.get(position);
        Intent intent = new Intent(leAdapter.getC(), MostrarDetallesEquipoActivity.class);
        intent.putExtra(EXTRA_USUARIO_INICIADO, EXTRA_USUARIO_INICIADO);
        intent.putExtra(EXTRA_OBJETO_EQUIPO, e);
        leAdapter.getC().startActivity(intent);
    }
}
