package com.example.proyectofinal.clases;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.proyectofinal.DetallesEquiposActivity;
import com.example.proyectofinal.R;

public class EquipoViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    public static final String EXTRA_OBJETO_EQUIPO = "";
    public TextView txtRvNombreE = null;
    public TextView txtRvCiudadE = null;
    public TextView txtRvFundacionE = null;
    public ImageView imgEscudo = null;

    final ListaEquiposAdapter leAdapter;

    public EquipoViewHolder(@NonNull View itemView, ListaEquiposAdapter listaEquiposAdapter) {
        super(itemView);
        txtRvCiudadE = (TextView)  itemView.findViewById(R.id.txtCiudadE);
        txtRvFundacionE = (TextView) itemView.findViewById(R.id.txtFundacionE);
        txtRvNombreE = (TextView) itemView.findViewById(R.id.txtNombreE);
        imgEscudo = (ImageView) itemView.findViewById(R.id.imgEscudo);
        this.leAdapter = listaEquiposAdapter;
        itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int mPosition = getLayoutPosition();
        Equipo equipo = this.leAdapter.getListaEquipos().get(mPosition);
        leAdapter.notifyDataSetChanged();
        Intent intent = new Intent(leAdapter.getC(), DetallesEquiposActivity.class);
        intent.putExtra(EXTRA_OBJETO_EQUIPO,equipo);
        leAdapter.getC().startActivity(intent);
    }
}
