package com.example.footballclub.controladores;

import com.example.footballclub.clases.Usuario;
import com.example.footballclub.tareas.usuario.TareaNuevoUsuario;
import com.example.footballclub.tareas.usuario.TareaObtenerUsuarios;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

public class UsuarioController {
    public static boolean nuevoUsuario(Usuario user){
        FutureTask tarea = new FutureTask(new TareaNuevoUsuario(user));
        ExecutorService es = Executors.newSingleThreadExecutor();
        es.submit(tarea);
        boolean nuevoUsuarioOK = false;
        try {
            nuevoUsuarioOK = (boolean) tarea.get();
            es.shutdown();
            try{
                if (!es.awaitTermination(800, TimeUnit.MILLISECONDS)){
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
            return nuevoUsuarioOK;
        }
    }
    //-----------------------------------------------------------------------------
    public static ArrayList<Usuario> obtenerUsuarios(){
        ArrayList<Usuario> usuarios = null;
        FutureTask tarea = new FutureTask(new TareaObtenerUsuarios());
        ExecutorService es = Executors.newSingleThreadExecutor();
        es.submit(tarea);
        try {
            usuarios = (ArrayList<Usuario>) tarea.get();
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
        return usuarios;
    }
}
