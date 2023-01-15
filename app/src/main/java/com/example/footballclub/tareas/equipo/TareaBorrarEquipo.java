package com.example.footballclub.tareas.equipo;

import com.example.footballclub.modelo.EquipoDB;

import java.util.concurrent.Callable;

public class TareaBorrarEquipo implements Callable<Boolean> {
    private String nombreEquipo = null;

    public TareaBorrarEquipo(String nombreEquipo){
        this.nombreEquipo = nombreEquipo;
    }

    @Override
    public Boolean call() throws Exception {
        try {
            boolean borrarEquipoOK = EquipoDB.borrarEquipo(nombreEquipo);
            return borrarEquipoOK;
        } catch (Exception e){
            return false;
        }
    }
}
