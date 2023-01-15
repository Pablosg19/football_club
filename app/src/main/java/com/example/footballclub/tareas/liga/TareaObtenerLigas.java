package com.example.footballclub.tareas.liga;

import com.example.footballclub.clases.Liga;
import com.example.footballclub.modelo.LigaDB;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.Callable;

public class TareaObtenerLigas implements Callable<ArrayList<Liga>> {
    @Override
    public ArrayList<Liga> call() throws Exception {
        ArrayList<Liga> ligas = LigaDB.obtenerLigas();
        return ligas;
    }
}
