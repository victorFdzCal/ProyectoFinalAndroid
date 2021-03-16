package com.example.proyectofinal.tareas;

import com.example.proyectofinal.clases.Jugador;
import com.example.proyectofinal.modelos.JugadorDB;

import java.util.concurrent.Callable;

public class TareaActualizarJugador implements Callable<Boolean> {
    Jugador j;

    public TareaActualizarJugador(Jugador j) {
        this.j = j;
    }

    @Override
    public Boolean call() throws Exception {
        Boolean actualizarOK = JugadorDB.actualizarJugador(j);
        return actualizarOK;
    }
}
