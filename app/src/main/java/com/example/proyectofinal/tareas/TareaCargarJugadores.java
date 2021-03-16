package com.example.proyectofinal.tareas;

import com.example.proyectofinal.clases.Jugador;
import com.example.proyectofinal.modelos.JugadorDB;

import java.util.ArrayList;

public class TareaCargarJugadores implements java.util.concurrent.Callable {
    private int idEquipo;
    public TareaCargarJugadores(int idEquipo) {
        this.idEquipo = idEquipo;
    }

    @Override
    public ArrayList<Jugador> call() throws Exception {
        ArrayList<Jugador> jugadores = JugadorDB.cargarJugadores(idEquipo);
        return jugadores;
    }
}
