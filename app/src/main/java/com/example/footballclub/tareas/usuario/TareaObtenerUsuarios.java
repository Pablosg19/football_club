package com.example.footballclub.tareas.usuario;

import com.example.footballclub.clases.Usuario;
import com.example.footballclub.modelo.UsuariosDB;

import java.util.ArrayList;
import java.util.concurrent.Callable;

public class TareaObtenerUsuarios implements Callable<ArrayList<Usuario>> {
    @Override
    public ArrayList<Usuario> call() throws Exception {
        ArrayList<Usuario> usuarios = UsuariosDB.obtenerUsuarios();
        return usuarios;
    }
}
