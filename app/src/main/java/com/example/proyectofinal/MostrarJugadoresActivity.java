package com.example.proyectofinal;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.example.proyectofinal.clases.Equipo;
import com.example.proyectofinal.clases.EquipoViewHolder;
import com.example.proyectofinal.clases.Jugador;
import com.example.proyectofinal.clases.ListaJugadoresAdapter;
import com.example.proyectofinal.controladores.EquipoController;
import com.example.proyectofinal.controladores.JugadorController;
import com.example.proyectofinal.modelos.JugadorDB;

import java.util.ArrayList;

public class MostrarJugadoresActivity extends AppCompatActivity {

    RecyclerView rvJugadores;
    ArrayList<Jugador> listaJugadores;
    Spinner spEquipos;
    Equipo e;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mostrar_jugadores);
        rvJugadores = findViewById(R.id.rvJugadores);
        spEquipos = (Spinner) findViewById(R.id.spEquiposMostrarJugadores);
        ArrayList<Equipo> listaEquipos = EquipoController.obtenerEquipos();
        ArrayAdapter<Equipo> arrayAdapter = new ArrayAdapter<Equipo>(getApplicationContext(),R.layout.spinner_item,listaEquipos);
        arrayAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        spEquipos.setAdapter(arrayAdapter);
    }

    public void addJugador(View view) {
        Intent intent = new Intent(this,NuevoJugadorActivity.class);
        startActivity(intent);
    }

    public void mostrarJugadores(View view) {
        e = (Equipo) spEquipos.getSelectedItem();
        listaJugadores = JugadorController.cargarJugadores(e.getIdEquipo());
        ListaJugadoresAdapter mAdapter = new ListaJugadoresAdapter(this,listaJugadores);
        rvJugadores.setAdapter(mAdapter);
        rvJugadores.setLayoutManager(new LinearLayoutManager(this));
    }
}