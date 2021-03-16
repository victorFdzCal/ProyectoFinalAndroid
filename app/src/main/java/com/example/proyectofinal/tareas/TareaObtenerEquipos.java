package com.example.proyectofinal.tareas;

import com.example.proyectofinal.clases.Equipo;
import com.example.proyectofinal.modelos.EquipoDB;

import java.util.ArrayList;

public class TareaObtenerEquipos implements java.util.concurrent.Callable<ArrayList<Equipo>> {
    @Override
    public ArrayList<Equipo> call() throws Exception {
        ArrayList<Equipo> equipos = EquipoDB.obtenerEquipos();
        return equipos;
    }
}
