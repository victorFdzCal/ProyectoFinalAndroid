package com.example.proyectofinal.clases;

import android.content.Intent;
import android.graphics.Bitmap;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.proyectofinal.DetallesEquiposActivity;
import com.example.proyectofinal.R;

import java.io.ByteArrayOutputStream;

public class EquipoViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    public static final String EXTRA_NOMBRE_EQUIPO = "a";
    public static final String EXTRA_IMAGEN_ESCUDO = "b";
    public static final String EXTRA_ID_EQUIPO = "c";
    public static final String EXTRA_CIUDAD_EQUIPO = "d";
    public static final String EXTRA_FUNDACION_EQUIPO = "e";
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
        Intent intent = new Intent(leAdapter.getC(), DetallesEquiposActivity.class);
        Equipo equipoObtenido = this.leAdapter.getListaEquipos().get(mPosition);
        intent.putExtra(EXTRA_NOMBRE_EQUIPO,equipoObtenido.getNombre());
        System.out.println(equipoObtenido.getNombre());
        Log.i("Nombre",equipoObtenido.getNombre());
        intent.putExtra(EXTRA_ID_EQUIPO, equipoObtenido.getIdEquipo());
        intent.putExtra(EXTRA_CIUDAD_EQUIPO, equipoObtenido.getCiudad());
        intent.putExtra(EXTRA_FUNDACION_EQUIPO, equipoObtenido.getFundacion());
        Bitmap escudo = equipoObtenido.getEscudo();
        if (escudo != null){
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            escudo.compress(Bitmap.CompressFormat.PNG,0,baos);
            byte[] byteArrayEscudo = baos.toByteArray();
            intent.putExtra(EXTRA_IMAGEN_ESCUDO,byteArrayEscudo);
        }
        leAdapter.notifyDataSetChanged();
        leAdapter.getC().startActivity(intent);
    }
}
