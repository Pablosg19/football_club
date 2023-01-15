package com.example.footballclub.tareas.usuario;

import com.example.footballclub.clases.Usuario;
import com.example.footballclub.modelo.UsuariosDB;

import java.util.concurrent.Callable;

public class TareaNuevoUsuario implements Callable<Boolean> {
    private Usuario user = null;

    public TareaNuevoUsuario(Usuario user){
        this.user = user;
    }

    @Override
    public Boolean call() throws Exception {
        try {
            boolean nuevoUsuarioOK = UsuariosDB.guardarNuevoUsuario(user);
            return nuevoUsuarioOK;
        } catch (Exception e){
            return false;
        }
    }
}
