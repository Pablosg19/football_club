package com.example.footballclub.controladores;

import com.example.footballclub.clases.Equipo;
import com.example.footballclub.tareas.equipo.TareaActualizarEquipo;
import com.example.footballclub.tareas.equipo.TareaBorrarEquipo;
import com.example.footballclub.tareas.equipo.TareaNuevoEquipo;
import com.example.footballclub.tareas.equipo.TareaObtenerEquipos;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

public class EquipoController {

    public static ArrayList<Equipo> obtenerEquipos(){
        ArrayList<Equipo> equipos = null;
        FutureTask tarea = new FutureTask(new TareaObtenerEquipos());
        ExecutorService es = Executors.newSingleThreadExecutor();
        es.submit(tarea);
        try {
            equipos = (ArrayList<Equipo>) tarea.get();
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
        return equipos;
    }
    //----------------------------------------------------------------------
    public static boolean nuevoEquipo(Equipo e){
        FutureTask tarea = new FutureTask(new TareaNuevoEquipo(e));
        ExecutorService es = Executors.newSingleThreadExecutor();
        es.submit(tarea);
        boolean nuevoEquipoOK = false;
        try {
            nuevoEquipoOK = (boolean) tarea.get();
            es.shutdown();
            try{
                if (!es.awaitTermination(800,TimeUnit.MILLISECONDS)){
                    es.shutdownNow();
                }
            } catch (InterruptedException e1) {
                es.shutdownNow();
            }
        } catch (ExecutionException e1) {
            e1.printStackTrace();
        } catch (InterruptedException e1) {
            e1.printStackTrace();
        }
        finally {
            return nuevoEquipoOK;
        }
    }
    //----------------------------------------------------------------------
    public static boolean borrarEquipo(String nombreEquipo){
        FutureTask tarea = new FutureTask(new TareaBorrarEquipo(nombreEquipo));
        ExecutorService es = Executors.newSingleThreadExecutor();
        es.submit(tarea);
        boolean borrarEquipoOK = false;
        try {
            borrarEquipoOK = (boolean) tarea.get();
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
            return borrarEquipoOK;
        }
    }
    //----------------------------------------------------------------------
    public static boolean actualizarEquipo(Equipo e, String nombreEquipo){
        FutureTask tarea = new FutureTask(new TareaActualizarEquipo(e,nombreEquipo));
        ExecutorService es = Executors.newSingleThreadExecutor();
        es.submit(tarea);
        boolean actualizarEquipoOK = false;
        try {
            actualizarEquipoOK = (boolean) tarea.get();
            es.shutdown();
            try {
                if (!es.awaitTermination(800, TimeUnit.MILLISECONDS)) {
                    es.shutdownNow();
                }
            } catch (InterruptedException e1) {
                es.shutdownNow();
            }
        } catch (
                ExecutionException e1) {
            e1.printStackTrace();
        } catch (InterruptedException e1) {
            e1.printStackTrace();
        }
        finally {
            return actualizarEquipoOK;
        }
    }

}
