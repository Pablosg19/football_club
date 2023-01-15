package com.example.footballclub.tareas.equipo;

import com.example.footballclub.clases.Equipo;
import com.example.footballclub.modelo.EquipoDB;

import java.util.concurrent.Callable;

public class TareaActualizarEquipo implements Callable<Boolean> {
    private Equipo e = null;
    private String nombreEquipo = null;

    public TareaActualizarEquipo(Equipo e, String nombreEquipo){
        this.e = e;
        this.nombreEquipo = nombreEquipo;
    }
    @Override
    public Boolean call() throws Exception {
        try {
            boolean actualizarEquipoOK = EquipoDB.actualizarEquipo(e,nombreEquipo);
            return actualizarEquipoOK;
        } catch (Exception e1){
            return false;
        }
    }
}
