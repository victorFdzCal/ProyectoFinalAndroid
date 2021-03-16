package com.example.proyectofinal.controladores;

import android.widget.TextView;

import com.example.proyectofinal.clases.Equipo;
import com.example.proyectofinal.tareas.TareaActualizarEquipo;
import com.example.proyectofinal.tareas.TareaActualizarJugador;
import com.example.proyectofinal.tareas.TareaBorrarEquipo;
import com.example.proyectofinal.tareas.TareaInsertarEquipo;
import com.example.proyectofinal.tareas.TareaMostrarEquipos;
import com.example.proyectofinal.tareas.TareaObtenerEquipos;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

public class EquipoController {
    public static ArrayList<Equipo> obtenerEquipos(){
        ArrayList<Equipo> equiposDevueltos = null;
        FutureTask t = new FutureTask(new TareaObtenerEquipos());
        ExecutorService es = Executors.newSingleThreadExecutor();
        es.submit(t);
        try {
            equiposDevueltos = (ArrayList<Equipo>) t.get();
            es.shutdown();
            if(!es.awaitTermination(800, TimeUnit.MILLISECONDS)){
                es.shutdownNow();
            }
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return equiposDevueltos;
    }

    public static void mostrarEquipos(TextView txtEquipos){
        FutureTask t = new FutureTask(new TareaMostrarEquipos());
        ExecutorService es = Executors.newSingleThreadExecutor();
        es.submit(t);
        try {
            ArrayList<Equipo> equiposDevueltos = (ArrayList<Equipo>) t.get();
            es.shutdown();
            if (!es.awaitTermination(800,TimeUnit.MILLISECONDS)){
                es.shutdownNow();
            }
            String textoEquipos = "EQUIPOS \n";
            if (equiposDevueltos != null){
                for (Equipo e : equiposDevueltos){
                    textoEquipos = e.toString() + "\n";
                }
                txtEquipos.setText(textoEquipos);
            } else {
                txtEquipos.setText("No se recuperaron los equipos");
            }
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static boolean insertarEquipo(Equipo e){
        FutureTask t = new FutureTask(new TareaInsertarEquipo(e));
        ExecutorService es = Executors.newSingleThreadExecutor();
        es.submit(t);
        boolean insercionOK = false;
        try {
            insercionOK = (boolean) t.get();
            es.shutdown();
            if (!es.awaitTermination(800,TimeUnit.MILLISECONDS)){
                es.shutdownNow();
            }
        } catch (ExecutionException executionException) {
            executionException.printStackTrace();
        } catch (InterruptedException interruptedException) {
            interruptedException.printStackTrace();
        }
        finally {
            return insercionOK;
        }
    }

    public static boolean borrarEquipo(Equipo eSeleccionado){
        FutureTask t = new FutureTask(new TareaBorrarEquipo(eSeleccionado));
        ExecutorService es = Executors.newSingleThreadExecutor();
        es.submit(t);
        boolean borradoOK = false;
        try {
            borradoOK = (boolean) t.get();
            es.shutdown();
            if (!es.awaitTermination(800,TimeUnit.MILLISECONDS)){
                es.shutdownNow();
            }
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        finally {
            return borradoOK;
        }
    }

    public static boolean actualizarEquipo(Equipo e){
        FutureTask t = new FutureTask(new TareaActualizarEquipo(e));
        ExecutorService es = Executors.newSingleThreadExecutor();
        es.submit(t);
        boolean actualizadoOK = false;
        try {
            actualizadoOK = (boolean) t.get();
            es.shutdown();
            if (!es.awaitTermination(800, TimeUnit.MILLISECONDS)){
                es.shutdown();
            }
        } catch (ExecutionException ex) {
            ex.printStackTrace();
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
        return actualizadoOK;
    }
}