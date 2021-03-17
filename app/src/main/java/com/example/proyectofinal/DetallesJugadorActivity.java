package com.example.proyectofinal;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.proyectofinal.clases.Equipo;
import com.example.proyectofinal.clases.EquipoViewHolder;
import com.example.proyectofinal.clases.Jugador;
import com.example.proyectofinal.clases.JugadorViewHolder;
import com.example.proyectofinal.controladores.EquipoController;
import com.example.proyectofinal.controladores.JugadorController;
import com.example.proyectofinal.modelos.EquipoDB;
import com.example.proyectofinal.utilidades.ImagenesBlobBitmap;

public class DetallesJugadorActivity extends AppCompatActivity {

    public static final String EXTRA_OBJETO_JUGADOR = "";
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
            String nombre = intent.getStringExtra(JugadorViewHolder.EXTRA_NOMBRE_JUGADOR);
            String apellidos = intent.getStringExtra(JugadorViewHolder.EXTRA_APELLIDOS_JUGADOR);
            String edad = intent.getStringExtra(JugadorViewHolder.EXTRA_EDAD_JUGADOR);
            String nacionalidad = intent.getStringExtra(JugadorViewHolder.EXTRA_NACIONALIDAD_JUGADOR);
            String posicion = intent.getStringExtra(JugadorViewHolder.EXTRA_POSICION_JUGADOR);
            Log.i("nacionalidad",nacionalidad);
            int id = intent.getIntExtra(JugadorViewHolder.EXTRA_ID_JUGADOR,0);
            int idEquipo = intent.getIntExtra(JugadorViewHolder.EXTRA_IDEQUIPO_JUGADOR,0);
            j = new Jugador(id,nombre,apellidos,nacionalidad,edad,posicion,idEquipo);
            txtNombre.setText(nombre + " " + apellidos);
            txtNacionalidad.setText(nacionalidad);
            txtEdad.setText(edad);
            txtPosicion.setText(posicion);
            Equipo e = EquipoController.cargarEquipo(idEquipo);
            txtEquipo.setText(e.getNombre());
            byte[] byteArrayLogo = intent.getByteArrayExtra(JugadorViewHolder.EXTRA_FOTO_JUGADOR);
            if (byteArrayLogo == null){
                imgFoto.setImageResource(R.drawable.jugador);
            }else{
                imgFoto.setImageBitmap(ImagenesBlobBitmap.byteToBitmap(byteArrayLogo));
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
                    mostrarToast("Jugador borrado correctamente");
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
        Intent intent = new Intent(this,ModificarJugadorActivity.class);
        intent.putExtra(EXTRA_OBJETO_JUGADOR,j);
        startActivity(intent);
    }
}