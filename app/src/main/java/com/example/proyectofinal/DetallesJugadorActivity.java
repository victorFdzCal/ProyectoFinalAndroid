package com.example.proyectofinal;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.proyectofinal.clases.Equipo;
import com.example.proyectofinal.clases.Jugador;
import com.example.proyectofinal.clases.JugadorViewHolder;
import com.example.proyectofinal.controladores.EquipoController;
import com.example.proyectofinal.controladores.JugadorController;
import com.example.proyectofinal.modelos.EquipoDB;

public class DetallesJugadorActivity extends AppCompatActivity {

    ImageView imgFoto;
    TextView txtNombre;
    TextView txtNacionalidad;
    TextView txtEdad;
    TextView txtEquipo;
    TextView txtPosicion;
    static Jugador j;

    public static Jugador getJ() {
        return j;
    }

    public static void setJ(Jugador j) {
        DetallesJugadorActivity.j = j;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalles_jugador);
        imgFoto = findViewById(R.id.imgFotoDetallesJugador);
        txtNombre = findViewById(R.id.txtNombreJugadorDetalles);
        txtNacionalidad = findViewById(R.id.txtNacionalidadDJugador);
        txtEdad = findViewById(R.id.txtEdadDJugador);
        txtEquipo = findViewById(R.id.txtEquipoDJugador);
        txtPosicion = findViewById(R.id.txtPosicionDJugador);
        Intent intent = getIntent();
        if (intent != null){
            j = (Jugador) intent.getSerializableExtra(JugadorViewHolder.EXTRA_OBJETO_JUGADOR);
            txtNombre.setText(j.getNombre() + " " + j.getApellidos());
            txtNacionalidad.setText(j.getNacionalidad());
            txtEdad.setText(j.getEdad());
            txtPosicion.setText(j.getPosicion());
            Equipo e = EquipoDB.buscarEquipo(j.getIdEquipo());
            txtEquipo.setText(e.getNombre());
            if (j.getFoto() == null){
                imgFoto.setImageResource(R.drawable.jugador);
            }else{
                imgFoto.setImageBitmap(j.getFoto());
            }
        }
    }

    public void borrarJugador(View view) {
        AlertDialog.Builder alertaConfirmacion = new AlertDialog.Builder(this);
        alertaConfirmacion.setTitle("¿Estás seguro de borrar el jugador?");
        alertaConfirmacion.setPositiveButton("Sí", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                boolean borradoOK = JugadorController.borrarJugador(j);
                if (borradoOK){
                    mostrarToast("Equipo borrado correctamente");
                }else {
                    mostrarToast("Fallo al borrar equipo");
                }
            }
        });
        alertaConfirmacion.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                mostrarToast("Se ha cancelado el borrado");
            }
        });
        alertaConfirmacion.show();
    }

    private void mostrarToast(String s) {
        Toast.makeText(this,s,Toast.LENGTH_SHORT).show();
    }

    public void editarJugador(View view) {
        Intent intent = new Intent();
    }
}