package com.example.proyectofinal;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.ClipData;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.proyectofinal.clases.Equipo;
import com.example.proyectofinal.clases.ListaEquiposAdapter;
import com.example.proyectofinal.controladores.EquipoController;

import java.util.ArrayList;
import java.util.Collections;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    private void mostrarToast(String s) {
        Toast.makeText(this,s, Toast.LENGTH_SHORT).show();
    }


    public void equipos(View view) {
        Intent intent = new Intent(this,MostrarEquiposActivity.class);
        startActivity(intent);
    }

    public void jugadores(View view) {
        Intent intent = new Intent(this,MostrarJugadoresActivity.class);
        startActivity(intent);
    }
}