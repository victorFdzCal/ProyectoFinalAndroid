package com.example.proyectofinal.clases;

import android.content.Intent;
import android.graphics.Bitmap;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.proyectofinal.DetallesJugadorActivity;
import com.example.proyectofinal.R;

import java.io.ByteArrayOutputStream;


public class JugadorViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    public static final String EXTRA_NOMBRE_JUGADOR = "a";
    public static final String EXTRA_APELLIDOS_JUGADOR = "b";
    public static final String EXTRA_POSICION_JUGADOR = "c";
    public static final String EXTRA_NACIONALIDAD_JUGADOR = "d";
    public static final String EXTRA_EDAD_JUGADOR = "e";
    public static final String EXTRA_ID_JUGADOR = "h";
    public static final String EXTRA_IDEQUIPO_JUGADOR = "g";
    public static final String EXTRA_FOTO_JUGADOR = "f";
    public TextView txtNombreJugador;
    public TextView txtPosicion;
    public TextView txtEquipo;
    public TextView txtEdad;
    public ImageView imgFoto;

    final ListaJugadoresAdapter ljAdapter;

    public JugadorViewHolder(@NonNull View itemView, ListaJugadoresAdapter ljAdapter) {
        super(itemView);
        this.ljAdapter = ljAdapter;
        txtNombreJugador = itemView.findViewById(R.id.txtRvNombreJugador);
        txtPosicion = itemView.findViewById(R.id.txtRvPosicion);
        txtEquipo = itemView.findViewById(R.id.txtRvEquipo);
        txtEdad = itemView.findViewById(R.id.txtRvEdad);
        imgFoto = itemView.findViewById(R.id.imgRvFoto);
        itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int mPosition = getLayoutPosition();
        Jugador jugadorObtenido = ljAdapter.getListaJugadores().get(mPosition);
        String nombre = jugadorObtenido.getNombre();
        String apellidos = jugadorObtenido.getApellidos();
        String posicion = jugadorObtenido.getPosicion();
        String nacionalidad = jugadorObtenido.getNacionalidad();
        String edad = jugadorObtenido.getEdad();
        int idJugador = jugadorObtenido.getIdJugador();
        int idEquipo = jugadorObtenido.getIdEquipo();

        ljAdapter.notifyDataSetChanged();
        Intent intent = new Intent(ljAdapter.getC(), DetallesJugadorActivity.class);
        Bitmap foto = jugadorObtenido.getFoto();
        if (foto != null){
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            foto.compress(Bitmap.CompressFormat.PNG,0,baos);
            byte[] byteArrayFoto = baos.toByteArray();
            intent.putExtra(EXTRA_FOTO_JUGADOR, byteArrayFoto);
        }
        intent.putExtra(EXTRA_NOMBRE_JUGADOR,nombre);
        intent.putExtra(EXTRA_APELLIDOS_JUGADOR,apellidos);
        intent.putExtra(EXTRA_POSICION_JUGADOR,posicion);
        intent.putExtra(EXTRA_NACIONALIDAD_JUGADOR,nacionalidad);
        intent.putExtra(EXTRA_EDAD_JUGADOR,edad);
        intent.putExtra(EXTRA_ID_JUGADOR,idJugador);
        intent.putExtra(EXTRA_IDEQUIPO_JUGADOR,idEquipo);
        ljAdapter.getC().startActivity(intent);
    }
}
