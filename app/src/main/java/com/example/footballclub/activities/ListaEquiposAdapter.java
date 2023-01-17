package com.example.footballclub.activities;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.footballclub.R;
import com.example.footballclub.clases.Equipo;
import com.example.footballclub.clases.Liga;
import com.example.footballclub.controladores.LigaController;
import com.example.footballclub.modelo.LigaDB;

import java.util.ArrayList;

public class ListaEquiposAdapter extends RecyclerView.Adapter<EquipoViewHolder> {
    private Context c;
    private ArrayList<Equipo> listaEquipos;
    private LayoutInflater mInflater;

    public ListaEquiposAdapter(Context c, ArrayList<Equipo> listaEquipos){
        this.c = c;
        this.listaEquipos = listaEquipos;
        mInflater = LayoutInflater.from(c);
    }
    public ListaEquiposAdapter(Context c){
        this.c = c;
        mInflater = LayoutInflater.from(c);
    }

    public Context getC() {
        return c;
    }

    public void setC(Context c) {
        this.c = c;
    }

    public ArrayList<Equipo> getListaEquipos() {
        return listaEquipos;
    }

    public void setListaEquipos(ArrayList<Equipo> listaEquipos) {
        this.listaEquipos = listaEquipos;
    }

    public LayoutInflater getmInflater() {
        return mInflater;
    }

    public void setmInflater(LayoutInflater mInflater) {
        this.mInflater = mInflater;
    }

    @NonNull
    @Override
    public EquipoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View mItemView = mInflater.inflate(R.layout.item_rv_equipo, parent, false);
        return new EquipoViewHolder(mItemView, this);
    }

    @Override
    public void onBindViewHolder(@NonNull EquipoViewHolder holder, int position) {
        if (listaEquipos != null){
            Equipo equipo_actual = listaEquipos.get(position);
            holder.txt_rv_nombreEquipo.setText(equipo_actual.getNombreEquipo());
            holder.txt_rv_ciudadEquipo.setText("Ciudad: " + equipo_actual.getCiudadEquipo());
            holder.txt_rv_numTitulos.setText("Número de titulos: " + equipo_actual.getNumTitulos());
            String liga = null;
            ArrayList<Liga> ligas = LigaController.obtenerLigas();
            for (Liga l : ligas) {
                if (equipo_actual.getIdLiga() == l.getIdLiga()){
                    liga = l.getNombreLiga();
                }
            }

            holder.txt_rv_ligaEquipo.setText("Liga: " + liga);
        }
    }

    @Override
    public int getItemCount() {
        if (listaEquipos != null){
            return listaEquipos.size();
        }
        else{
            return 0;
        }
    }
}
