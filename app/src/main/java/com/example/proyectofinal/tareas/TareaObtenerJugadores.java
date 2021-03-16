package com.example.proyectofinal.tareas;

import com.example.proyectofinal.clases.Jugador;
import com.example.proyectofinal.modelos.JugadorDB;

import java.util.ArrayList;

public class TareaObtenerJugadores implements java.util.concurrent.Callable {


    @Override
    public ArrayList<Jugador> call() throws Exception {
        ArrayList<Jugador> jugadores = JugadorDB.obtenerJugadores();
        return jugadores;
    }
}
