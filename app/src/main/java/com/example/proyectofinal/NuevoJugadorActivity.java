package com.example.proyectofinal;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.proyectofinal.clases.Equipo;
import com.example.proyectofinal.clases.Jugador;
import com.example.proyectofinal.controladores.EquipoController;
import com.example.proyectofinal.controladores.JugadorController;

import java.util.ArrayList;

public class NuevoJugadorActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener
 {

    EditText edtNombre;
    EditText edtApellido;
    EditText edtEdad;
    EditText edtPosicion;
    EditText edtNacionalidad;
    Spinner spEquipos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nuevo_jugador);
        edtNombre = findViewById(R.id.edtNombreJ);
        edtApellido = findViewById(R.id.edtApellidos);
        edtEdad = findViewById(R.id.edtEdad);
        edtPosicion = findViewById(R.id.edtPosicion);
        edtNacionalidad = findViewById(R.id.edtNacionalidad);
        spEquipos = (Spinner) findViewById(R.id.spEquipo);
        ArrayList<Equipo> listaEquipos = EquipoController.obtenerEquipos();
        ArrayAdapter<Equipo> arrayAdapter = new ArrayAdapter<Equipo>(getApplicationContext(),R.layout.spinner_item,listaEquipos);
        arrayAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        spEquipos.setAdapter(arrayAdapter);
    }

    public void nuevoJugador(View view) {
        String nombre = String.valueOf(edtNombre.getText());
        String apellidos = String.valueOf(edtApellido.getText());
        String edad = String.valueOf(edtEdad.getText());
        String posicion = String.valueOf(edtPosicion.getText());
        String nacionalidad = String.valueOf(edtNacionalidad.getText());
        Equipo equipo = (Equipo) spEquipos.getSelectedItem();
        Jugador j = new Jugador(nombre,apellidos,nacionalidad,edad,posicion,equipo.getIdEquipo());
        Boolean insertarOK = JugadorController.insertarJugador(j);
        if (insertarOK){
            mostrarToast("Jugador insertado correctamente");
        }
        else {
            mostrarToast("Fallo al insertar el jugador");
        }
    }

     private void mostrarToast(String s) {
         Toast.makeText(this,s,Toast.LENGTH_SHORT).show();
     }

     @Override
     public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

     }

     @Override
     public void onNothingSelected(AdapterView<?> parent) {

     }
 }