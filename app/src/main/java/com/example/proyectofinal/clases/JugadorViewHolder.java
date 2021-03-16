package com.example.proyectofinal.clases;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.proyectofinal.DetallesJugadorActivity;
import com.example.proyectofinal.R;


public class JugadorViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    //public static final String EXTRA_OBJETO_JUGADOR = "";
    public static final String EXTRA_OBJETO_JUGADOR = "";
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
        Jugador jugador = ljAdapter.getListaJugadores().get(mPosition);
        ljAdapter.notifyDataSetChanged();
        Intent intent = new Intent(ljAdapter.getC(), DetallesJugadorActivity.class);
        intent.putExtra(EXTRA_OBJETO_JUGADOR,jugador);
        ljAdapter.getC().startActivity(intent);
    }
}
