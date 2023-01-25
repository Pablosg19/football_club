package com.example.footballclub.tareas.liga;

import com.example.footballclub.clases.Liga;
import com.example.footballclub.modelo.LigaDB;

import java.util.ArrayList;
import java.util.concurrent.Callable;

public class TareaObtenerLigasBusqueda implements Callable<ArrayList<Liga>> {
    private String filtro = null;

    public TareaObtenerLigasBusqueda(String filtro) {
        this.filtro = filtro;
    }

    @Override
    public ArrayList<Liga> call() throws Exception {
        ArrayList<Liga> ligas = LigaDB.obtenerLigasBusqueda(filtro);
        return ligas;
    }
}
