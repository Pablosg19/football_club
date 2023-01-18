package com.example.footballclub.tareas.equipo;

import com.example.footballclub.clases.Equipo;
import com.example.footballclub.modelo.EquipoDB;

import java.util.ArrayList;
import java.util.concurrent.Callable;

public class TareaObtenerEquiposBusqueda implements Callable<ArrayList<Equipo>> {
    private String filtro = null;

    public TareaObtenerEquiposBusqueda(String filtro) {
        this.filtro = filtro;
    }

    @Override
    public ArrayList<Equipo> call() throws Exception {
        ArrayList<Equipo> equipos = EquipoDB.obtenerEquiposBusqueda(filtro);
        return equipos;
    }
}
