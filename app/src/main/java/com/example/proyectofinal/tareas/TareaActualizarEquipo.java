package com.example.proyectofinal.tareas;

import com.example.proyectofinal.clases.Equipo;
import com.example.proyectofinal.modelos.EquipoDB;

import java.util.concurrent.Callable;

public class TareaActualizarEquipo implements Callable<Boolean> {
    Equipo e;
    public TareaActualizarEquipo(Equipo e) {
        this.e = e;
    }

    @Override
    public Boolean call() throws Exception {
        boolean actualizarOK = EquipoDB.actualizarEquipo(e);
        return actualizarOK;
    }
}
