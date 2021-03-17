package com.example.proyectofinal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.audiofx.DynamicsProcessing;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.proyectofinal.clases.Equipo;
import com.example.proyectofinal.clases.EquipoViewHolder;
import com.example.proyectofinal.controladores.EquipoController;

import static com.example.proyectofinal.DetallesEquiposActivity.EXTRA_OBJETO_EQUIPO;

public class ModificarEquipoActivity extends AppCompatActivity {

    EditText edtNombre;
    EditText edtFundacion;
    EditText edtCiudad;
    Equipo e;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modificar_equipo);
        edtNombre = findViewById(R.id.edtNombreEquipoEdit);
        edtFundacion = findViewById(R.id.edtFundacionEdit);
        edtCiudad = findViewById(R.id.edtCiudadEquipoEdit);
        Intent intent = getIntent();
        if (intent != null){
            e = (Equipo) intent.getSerializableExtra(EXTRA_OBJETO_EQUIPO);
            if (e != null){
                edtNombre.setText(e.getNombre());
                edtFundacion.setText(e.getFundacion());
                edtCiudad.setText(e.getCiudad());
            }
        }
    }

    private void mostrarToast(String s) {
        Toast.makeText(this,s,Toast.LENGTH_SHORT).show();
    }

    public void editEquipo(View view) {
        e.setNombre(String.valueOf(edtNombre.getText()));
        e.setFundacion(String.valueOf(edtFundacion.getText()));
        e.setCiudad(String.valueOf(edtCiudad.getText()));
        boolean edicionOK = EquipoController.actualizarEquipo(e);
        if (edicionOK){
            mostrarToast("Equipo actualizado correctamente");
        }else{
            mostrarToast("Fallo al actualizar equipo");
        }
    }
}