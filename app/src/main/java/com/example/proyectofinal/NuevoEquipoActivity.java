package com.example.proyectofinal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.proyectofinal.clases.Equipo;
import com.example.proyectofinal.controladores.EquipoController;

public class NuevoEquipoActivity extends AppCompatActivity {

    EditText edtNombreE = null;
    EditText edtCiudad = null;
    EditText edtFundacion = null;
    ImageView imgEscudo = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nuevo_equipo);
        edtNombreE = (EditText)findViewById(R.id.edtNombreE);
        edtCiudad = (EditText) findViewById(R.id.edtCiudad);
        edtFundacion = (EditText) findViewById(R.id.edtFundacion);
    }

    public void nuevoEquipo(View view) {
        String nombre = String.valueOf(edtNombreE.getText());
        String ciudad = String.valueOf(edtCiudad.getText());
        String fundacion = String.valueOf(edtFundacion.getText());
        Equipo e = new Equipo(nombre,ciudad,fundacion);
        boolean insercionOK = EquipoController.insertarEquipo(e);
        if (insercionOK){
            mostrarToast("Inserción correcta");
        }else{
            mostrarToast("Fallo al insertar equipo");
        }
    }

    private void mostrarToast(String s) {
        Toast.makeText(this,s,Toast.LENGTH_SHORT).show();
    }

}