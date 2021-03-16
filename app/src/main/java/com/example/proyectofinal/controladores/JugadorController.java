package com.example.proyectofinal.controladores;

import com.example.proyectofinal.clases.Jugador;
import com.example.proyectofinal.tareas.TareaActualizarJugador;
import com.example.proyectofinal.tareas.TareaBorrarJugador;
import com.example.proyectofinal.tareas.TareaCargarJugadores;
import com.example.proyectofinal.tareas.TareaInsertarJugador;
import com.example.proyectofinal.tareas.TareaObtenerJugadores;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

public class JugadorController {

    public static ArrayList<Jugador> obtenerJugadores(){
        ArrayList<Jugador> jugadoresDevueltos = null;
        FutureTask t = new FutureTask(new TareaObtenerJugadores());
        ExecutorService es = Executors.newSingleThreadExecutor();
        try {
            jugadoresDevueltos = (ArrayList<Jugador>) t.get();
            es.shutdown();
            if (!es.awaitTermination(800, TimeUnit.MILLISECONDS)){
                es.shutdown();
            }
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return jugadoresDevueltos;
    }

    public static boolean insertarJugador (Jugador j){
        FutureTask t = new FutureTask(new TareaInsertarJugador(j));
        ExecutorService es = Executors.newSingleThreadExecutor();
        es.submit(t);
        boolean insercionOK = false;
        try {
            insercionOK = (boolean) t.get();
            es.shutdown();
            if (!es.awaitTermination(800, TimeUnit.MILLISECONDS)){
                es.shutdown();
            }
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return insercionOK;
    }

    public static boolean borrarJugador(Jugador jSeleccionado){
        FutureTask t = new FutureTask(new TareaBorrarJugador(jSeleccionado));
        ExecutorService es = Executors.newSingleThreadExecutor();
        es.submit(t);
        boolean borradoOK = false;
        try {
            borradoOK = (boolean) t.get();
            es.shutdown();
            if (!es.awaitTermination(800, TimeUnit.MILLISECONDS)){
                es.shutdown();
            }
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return borradoOK;
    }

    public static boolean actualizarJugador(Jugador j){
        FutureTask t = new FutureTask(new TareaActualizarJugador(j));
        ExecutorService es = Executors.newSingleThreadExecutor();
        es.submit(t);
        boolean actualizadoOK = false;
        try {
            actualizadoOK = (boolean) t.get();
            es.shutdown();
            if (!es.awaitTermination(800, TimeUnit.MILLISECONDS)){
                es.shutdown();
            }
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return actualizadoOK;
    }

    public static ArrayList<Jugador> cargarJugadores(int idEquipo){
        ArrayList<Jugador> jugadoresDevueltos = null;
        FutureTask t = new FutureTask(new TareaCargarJugadores(idEquipo));
        ExecutorService es = Executors.newSingleThreadExecutor();
        try {
            jugadoresDevueltos = (ArrayList<Jugador>) t.get();
            es.shutdown();
            if (!es.awaitTermination(8000, TimeUnit.MILLISECONDS)){
                es.shutdown();
            }
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return jugadoresDevueltos;
    }
}
