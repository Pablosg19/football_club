package com.example.footballclub.tareas.liga;

import com.example.footballclub.clases.Liga;
import com.example.footballclub.modelo.LigaDB;

import java.util.concurrent.Callable;

public class TareaActualizarLiga implements Callable<Boolean> {
    private Liga l = null;
    private String nombreLiga = null;

    public TareaActualizarLiga(Liga l, String nombreLiga){
        this.l = l;
        this.nombreLiga = nombreLiga;
    }

    @Override
    public Boolean call() throws Exception {
        try{
            boolean actualizarLigaOK = LigaDB.actualizarLiga(l,nombreLiga);
            return actualizarLigaOK;
        } catch (Exception e){
            return false;
        }
    }
}
