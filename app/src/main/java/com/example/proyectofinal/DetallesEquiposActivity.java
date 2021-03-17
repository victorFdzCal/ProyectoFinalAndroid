package com.example.proyectofinal;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.proyectofinal.clases.Equipo;
import com.example.proyectofinal.clases.EquipoViewHolder;
import com.example.proyectofinal.clases.Jugador;
import com.example.proyectofinal.clases.ListaJugadoresAdapter;
import com.example.proyectofinal.controladores.EquipoController;
import com.example.proyectofinal.modelos.JugadorDB;
import com.example.proyectofinal.utilidades.ImagenesBlobBitmap;

import java.util.ArrayList;

public class DetallesEquiposActivity extends AppCompatActivity {
    public static final String EXTRA_OBJETO_EQUIPO = "";
    TextView txtNombre;
    TextView txtCiudad;
    TextView txtFundacion;
    ImageView imgEquipo;
    Equipo e;

    public Equipo getE() {
        return e;
    }

    public void setE(Equipo e) {
        this.e = e;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalles_equipos);
        txtNombre = findViewById(R.id.txtNombreEquipo);
        txtCiudad = findViewById(R.id.txtCiudadEquipo);
        txtFundacion = findViewById(R.id.txtFundacionEquipo);
        imgEquipo = (ImageView) findViewById(R.id.imgEscudoEquipo);
        Intent intent = getIntent();
        if (intent != null){
            String nombre = intent.getStringExtra(EquipoViewHolder.EXTRA_NOMBRE_EQUIPO);
            if (nombre != null){
                System.out.println(nombre);
                Log.i("Nombre",nombre);
            }else{
                System.out.println("null");
                Log.i("Nombre","null");
            }

            int idEquipo = intent.getIntExtra(EquipoViewHolder.EXTRA_ID_EQUIPO,0);
            String ciudad = intent.getStringExtra(EquipoViewHolder.EXTRA_CIUDAD_EQUIPO);
            String fundacion = intent.getStringExtra(EquipoViewHolder.EXTRA_FUNDACION_EQUIPO);
            e = new Equipo(idEquipo,nombre,ciudad,fundacion);
            txtNombre.setText(nombre);
            System.out.println(nombre);
            Log.i("Nombre",nombre);
            txtFundacion.setText(fundacion);
            txtCiudad.setText(ciudad);
            byte[] byteArrayLogo = (byte[]) intent.getByteArrayExtra(EquipoViewHolder.EXTRA_IMAGEN_ESCUDO);
            if (byteArrayLogo == null){
                imgEquipo.setImageResource(R.drawable.escudo);
            }else{
                imgEquipo.setImageBitmap(ImagenesBlobBitmap.byteToBitmap(byteArrayLogo));
            }
        }
    }

    public void editarEquipo(View view) {
        Intent intent = new Intent(this,ModificarEquipoActivity.class);
        intent.putExtra(EXTRA_OBJETO_EQUIPO,e);
        startActivity(intent);
    }

    public void borrarEquipo(View view) {
        AlertDialog.Builder alertaConfirmacion = new AlertDialog.Builder(this);
        alertaConfirmacion.setTitle("¿Estás seguro de borrar el equipo?");
        alertaConfirmacion.setPositiveButton("Sí", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                boolean borradoOK = EquipoController.borrarEquipo(e);
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
}