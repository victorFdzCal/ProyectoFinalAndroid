package com.example.proyectofinal;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.proyectofinal.clases.Equipo;
import com.example.proyectofinal.clases.Jugador;
import com.example.proyectofinal.controladores.EquipoController;
import com.example.proyectofinal.controladores.JugadorController;

import java.util.ArrayList;

public class ModificarJugadorActivity extends AppCompatActivity {

    EditText edtNombre;
    EditText edtApellido;
    EditText edtNacionalidad;
    EditText edtEdad;
    EditText edtPosicion;
    Spinner spEquipos;
    Jugador j;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modificar_jugador);
        edtNombre = findViewById(R.id.edtNombreJugadorEditar);
        edtApellido = findViewById(R.id.edtApellidoJugadorEditar);
        edtEdad = findViewById(R.id.edtEdadJugadorEditar);
        edtPosicion = findViewById(R.id.edtPosicionJugadorEditar);
        spEquipos = (Spinner) findViewById(R.id.spEquiposEditarJugador);
        ArrayList<Equipo> listaEquipos = EquipoController.obtenerEquipos();
        ArrayAdapter<Equipo> arrayAdapter = new ArrayAdapter<Equipo>(getApplicationContext(),R.layout.spinner_item,listaEquipos);
        arrayAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        spEquipos.setAdapter(arrayAdapter);
        j = DetallesJugadorActivity.getJ();
        edtNombre.setText(j.getNombre());
        edtApellido.setText(j.getApellidos());
        edtPosicion.setText(j.getPosicion());
        edtNacionalidad.setText(j.getNacionalidad());
        edtEdad.setText(j.getEdad());
    }

    public void editJugador(View view) {
        j.setNombre(String.valueOf(edtNombre.getText()));
        j.setApellidos(String.valueOf(edtApellido.getText()));
        j.setEdad(String.valueOf(edtEdad.getText()));
        j.setPosicion(String.valueOf(edtPosicion.getText()));
        j.setNacionalidad(String.valueOf(edtNacionalidad.getText()));
        Equipo e = (Equipo) spEquipos.getSelectedItem();
        j.setIdEquipo(e.getIdEquipo());
        boolean modificadoOK = JugadorController.actualizarJugador(j);
        if (modificadoOK){
            mostrarToast("Jugador modificado correctamente");
        }else{
            mostrarToast("Fallo al modificar el jugador");
        }
    }

    private void mostrarToast(String s) {
        Toast.makeText(this,s,Toast.LENGTH_SHORT).show();
    }

}