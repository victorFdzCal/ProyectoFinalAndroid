package com.example.proyectofinal.tareas;

import com.example.proyectofinal.clases.Jugador;
import com.example.proyectofinal.modelos.JugadorDB;

import java.util.concurrent.Callable;

public class TareaInsertarJugador implements Callable<Boolean> {
    private Jugador j;

    public TareaInsertarJugador(Jugador j) {
        this.j = j;
    }

    @Override
    public Boolean call() throws Exception {
        boolean insertarOK = JugadorDB.insertarJugador(j);
        return insertarOK;
    }
}
