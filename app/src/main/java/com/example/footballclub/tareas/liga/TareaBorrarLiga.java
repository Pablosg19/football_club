package com.example.footballclub.tareas.liga;

import com.example.footballclub.modelo.LigaDB;

import java.util.concurrent.Callable;

public class TareaBorrarLiga implements Callable<Boolean> {
    private String nombreLiga = null;

    public TareaBorrarLiga(String nombreLiga){
        this.nombreLiga = nombreLiga;
    }

    @Override
    public Boolean call() throws Exception {
        try{
            boolean borrarLigaOK = LigaDB.borrarLiga(nombreLiga);
            return borrarLigaOK;
        }catch (Exception e){
            return false;
        }
    }
}
