package com.example.footballclub.tareas.liga;

import com.example.footballclub.clases.Liga;
import com.example.footballclub.modelo.LigaDB;

import java.util.concurrent.Callable;

public class TareaNuevaLiga implements Callable<Boolean> {
    private Liga l = null;

    public TareaNuevaLiga(Liga l){
        this.l = l;
    }

    @Override
    public Boolean call() throws Exception {
        try {
            boolean nuevaLigaOK = LigaDB.guardarNuevaLiga(l);
            return nuevaLigaOK;
        } catch (Exception e){
            return false;
        }
    }
}
