package com.example.footballclub.controladores;

import com.example.footballclub.clases.Liga;
import com.example.footballclub.tareas.liga.TareaActualizarLiga;
import com.example.footballclub.tareas.liga.TareaBorrarLiga;
import com.example.footballclub.tareas.liga.TareaNuevaLiga;
import com.example.footballclub.tareas.liga.TareaObtenerLigas;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

public class LigaController {

    public static ArrayList<Liga> obtenerLigas(){
        ArrayList<Liga> ligas = null;
        FutureTask tarea = new FutureTask(new TareaObtenerLigas());
        ExecutorService es = Executors.newSingleThreadExecutor();
        es.submit(tarea);
        try {
            ligas = (ArrayList<Liga>) tarea.get();
            es.shutdown();
            try {
                if (!es.awaitTermination(2000, TimeUnit.MILLISECONDS)){
                    es.shutdownNow();
                }
            }  catch (InterruptedException e) {
                es.shutdownNow();
            }
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return ligas;
    }
    //----------------------------------------------------------------------
    public static boolean nuevaLiga(Liga l){
        FutureTask tarea = new FutureTask(new TareaNuevaLiga(l));
        ExecutorService es = Executors.newSingleThreadExecutor();
        es.submit(tarea);
        boolean nuevaLigaOK = false;
        try {
            nuevaLigaOK = (boolean) tarea.get();
            es.shutdown();
            try{
                if (!es.awaitTermination(800,TimeUnit.MILLISECONDS)){
                    es.shutdownNow();
                }
            } catch (InterruptedException e) {
                es.shutdownNow();
            }
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        finally {
            return nuevaLigaOK;
        }
    }
    //----------------------------------------------------------------------
    public static boolean borrarLiga(String nombreLiga){
        FutureTask tarea = new FutureTask(new TareaBorrarLiga(nombreLiga));
        ExecutorService es = Executors.newSingleThreadExecutor();
        es.submit(tarea);
        boolean borrarLigaOK = false;
        try {
            borrarLigaOK = (boolean) tarea.get();
            es.shutdown();
            try {
                if (!es.awaitTermination(800, TimeUnit.MILLISECONDS)) {
                    es.shutdownNow();
                }
            } catch (InterruptedException e) {
                es.shutdownNow();
            }
        } catch (
                ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        finally {
            return borrarLigaOK;
        }
    }
    //----------------------------------------------------------------------
    public static boolean actualizarLiga(Liga l, String nombreLiga){
        FutureTask tarea = new FutureTask(new TareaActualizarLiga(l,nombreLiga));
        ExecutorService es = Executors.newSingleThreadExecutor();
        es.submit(tarea);
        boolean actualizarLigaOK = false;
        try {
            actualizarLigaOK = (boolean) tarea.get();
            es.shutdown();
            try {
                if (!es.awaitTermination(800, TimeUnit.MILLISECONDS)) {
                    es.shutdownNow();
                }
            } catch (InterruptedException e) {
                es.shutdownNow();
            }
        } catch (
                ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        finally {
            return actualizarLigaOK;
        }
    }
}
