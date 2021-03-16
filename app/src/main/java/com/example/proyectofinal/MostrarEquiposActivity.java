package com.example.proyectofinal;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.proyectofinal.clases.Equipo;
import com.example.proyectofinal.clases.ListaEquiposAdapter;
import com.example.proyectofinal.controladores.EquipoController;

import java.util.ArrayList;

public class MostrarEquiposActivity extends AppCompatActivity {

    private RecyclerView rvEquipos;
    private ArrayList<Equipo> listaEquipos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mostrar_equipos);
        rvEquipos = (RecyclerView)findViewById(R.id.rvEquipos);
        listaEquipos = EquipoController.obtenerEquipos();
        ListaEquiposAdapter mAdapter = new ListaEquiposAdapter(this,listaEquipos);
        rvEquipos.setAdapter(mAdapter);
        rvEquipos.setLayoutManager(new LinearLayoutManager(this));
    }

    public void addEquipo(View view) {
        Intent intent = new Intent(this,NuevoEquipoActivity.class);
        startActivity(intent);
    }
}