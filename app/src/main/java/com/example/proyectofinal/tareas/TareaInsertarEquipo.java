package com.example.proyectofinal.tareas;

import com.example.proyectofinal.clases.Equipo;
import com.example.proyectofinal.modelos.EquipoDB;

import java.util.concurrent.Callable;

public class TareaInsertarEquipo implements Callable<Boolean> {
    private Equipo e = null;

    public TareaInsertarEquipo(Equipo e) {
        this.e = e;
    }

    @Override
    public Boolean call() throws Exception {
        boolean borradoOK = EquipoDB.insertarEquipo(e);
        return borradoOK;
    }
}
