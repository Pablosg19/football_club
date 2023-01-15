package com.example.footballclub.tareas.equipo;

import com.example.footballclub.clases.Equipo;
import com.example.footballclub.modelo.EquipoDB;

import java.util.ArrayList;
import java.util.concurrent.Callable;

public class TareaObtenerEquipos implements Callable<ArrayList<Equipo>> {

    @Override
    public ArrayList<Equipo> call() throws Exception {
        ArrayList<Equipo> equipos = EquipoDB.obtenerEquipos();
        return equipos;
    }
}
