package com.example.proyectofinal.clases;

import android.content.Context;
import android.database.DataSetObserver;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.proyectofinal.R;
import com.example.proyectofinal.controladores.EquipoController;
import com.example.proyectofinal.modelos.BaseDB;
import com.example.proyectofinal.modelos.EquipoDB;

import java.util.ArrayList;
import java.util.PrimitiveIterator;

public class ListaJugadoresAdapter extends RecyclerView.Adapter<JugadorViewHolder> {
    private Context c;
    private ArrayList<Jugador> listaJugadores;
    private LayoutInflater mInflater;

    public ListaJugadoresAdapter(Context c, ArrayList<Jugador> listaJugadores){
        this.c = c;
        this.listaJugadores = listaJugadores;
        mInflater = LayoutInflater.from(c);
    }

    @NonNull
    @Override
    public JugadorViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View mItemView = mInflater.inflate(R.layout.item_rv_jugadores,parent,false);
        return new JugadorViewHolder(mItemView,this);
    }

    @Override
    public void onBindViewHolder(@NonNull JugadorViewHolder holder, int position) {
        Jugador jugadorActual = listaJugadores.get(position);
        if (jugadorActual.getFoto() == null){
            holder.imgFoto.setImageResource(R.drawable.jugador);
        }else{
            holder.imgFoto.setImageBitmap(jugadorActual.getFoto());
        }
        holder.txtNombreJugador.setText(jugadorActual.getNombre() + " " + jugadorActual.getApellidos());
        holder.txtPosicion.setText("Posición: " + jugadorActual.getPosicion());
        holder.txtEdad.setText("Edad: " + jugadorActual.getEdad());
        Equipo e = EquipoController.cargarEquipo(jugadorActual.getIdEquipo());
        holder.txtEquipo.setText("Equipo: " + e.getNombre());
    }

    @Override
    public int getItemCount() {
        return listaJugadores.size();
    }

    public ArrayList<Jugador> getListaJugadores(){return listaJugadores;}

    public Context getC(){return c;}

    public void setC(Context c) {
        this.c = c;
    }


    public void setListaJugadores(ArrayList<Jugador> listaJugadores) {
        this.listaJugadores = listaJugadores;
    }

    public LayoutInflater getmInflater() {
        return mInflater;
    }

    public void setmInflater(LayoutInflater mInflater) {
        this.mInflater = mInflater;
    }

    public void notifyItemMoved(int adapterPosition) {
    }
}
