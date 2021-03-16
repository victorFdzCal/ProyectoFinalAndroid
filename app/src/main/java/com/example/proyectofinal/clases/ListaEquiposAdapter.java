package com.example.proyectofinal.clases;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.proyectofinal.R;

import java.util.ArrayList;

public class ListaEquiposAdapter extends RecyclerView.Adapter<EquipoViewHolder>  {

    private Context c;
    private ArrayList<Equipo> listaEquipos;
    private LayoutInflater mInflater;

    public ListaEquiposAdapter(Context c, ArrayList<Equipo> listaEquipos){
        this.c = c;
        this.listaEquipos = listaEquipos;
        mInflater = LayoutInflater.from(c);
    }

    @NonNull
    @Override
    public EquipoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View mItemView = mInflater.inflate(R.layout.item_rv_equipos,parent,false);
        return new EquipoViewHolder(mItemView,this);
    }

    @Override
    public void onBindViewHolder(@NonNull EquipoViewHolder holder, int position) {
        Equipo equipoActual = listaEquipos.get(position);
        if (equipoActual.getEscudo() == null){
            holder.imgEscudo.setImageResource(R.drawable.escudo);
        }else{
            holder.imgEscudo.setImageBitmap(equipoActual.getEscudo());
        }
        holder.txtRvNombreE.setText(equipoActual.getNombre());
        holder.txtRvFundacionE.setText("Fundación: "  + equipoActual.getFundacion());
        holder.txtRvCiudadE.setText("Ciudad: " + equipoActual.getCiudad());
    }

    @Override
    public int getItemCount(){
        return listaEquipos.size();
    }

    public Context getC() {
        return c;
    }

    public void setC(Context c) {
        this.c = c;
    }

    public ArrayList<Equipo> getListaEquipos() {
        return listaEquipos;
    }

    public void setListaEquipos(ArrayList<Equipo> listaEquipos) {
        this.listaEquipos = listaEquipos;
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
