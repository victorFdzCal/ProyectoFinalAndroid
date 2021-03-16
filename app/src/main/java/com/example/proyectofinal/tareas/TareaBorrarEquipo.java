package com.example.proyectofinal.tareas;

import com.example.proyectofinal.clases.Equipo;
import com.example.proyectofinal.modelos.EquipoDB;

import java.util.concurrent.Callable;

public class TareaBorrarEquipo implements Callable<Boolean> {
    private Equipo e = null;

    public TareaBorrarEquipo(Equipo e) {
        this.e = e;
    }

    @Override
    public Boolean call() throws Exception {
        boolean borradoOK = EquipoDB.borrarEquipo(e);
        return borradoOK;
    }
}
