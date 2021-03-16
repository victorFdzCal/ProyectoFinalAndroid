package com.example.proyectofinal.tareas;

import com.example.proyectofinal.clases.Jugador;
import com.example.proyectofinal.modelos.JugadorDB;

import java.util.concurrent.Callable;

public class TareaBorrarJugador implements Callable<Boolean> {
    private Jugador j;

    public TareaBorrarJugador(Jugador jSeleccionado) {
        this.j = jSeleccionado;
    }

    @Override
    public Boolean call() throws Exception {
        boolean borradoOK = JugadorDB.borrarJugador(j);
        return borradoOK;
    }
}
