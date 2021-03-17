package com.example.proyectofinal.tareas;

import com.example.proyectofinal.clases.Equipo;
import com.example.proyectofinal.modelos.EquipoDB;

import java.util.concurrent.Callable;

public class TareaCargarEquipos implements Callable {
    private int idEquipo;
    public TareaCargarEquipos(int idEquipo) {
        this.idEquipo = idEquipo;
    }

    @Override
    public Object call() throws Exception {
        Equipo equipo = EquipoDB.buscarEquipo(idEquipo);
        return equipo;
    }
}
