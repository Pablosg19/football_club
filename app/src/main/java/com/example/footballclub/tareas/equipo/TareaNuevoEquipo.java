package com.example.footballclub.tareas.equipo;

import com.example.footballclub.clases.Equipo;
import com.example.footballclub.modelo.EquipoDB;

import java.util.concurrent.Callable;

public class TareaNuevoEquipo implements Callable<Boolean> {
    private Equipo e = null;
    public TareaNuevoEquipo(Equipo e){
        this.e = e;
    }

    @Override
    public Boolean call() throws Exception {
        try {
            boolean nuevoEquipoOK = EquipoDB.guardarNuevoEquipo(e);
            return nuevoEquipoOK;
        } catch (Exception e1){
            return false;
        }
    }
}
