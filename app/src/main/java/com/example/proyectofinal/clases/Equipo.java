package com.example.proyectofinal.clases;

import android.graphics.Bitmap;
import android.os.Build;

import androidx.annotation.RequiresApi;

import java.io.Serializable;
import java.util.Objects;

public class Equipo implements Serializable {
    private int idEquipo;
    private String nombre;
    private String ciudad;
    private String fundacion;
    private Bitmap escudo;

    public Equipo(int idEquipo, String nombre, String ciudad, String fundacion, Bitmap escudo) {
        this.idEquipo = idEquipo;
        this.nombre = nombre;
        this.ciudad = ciudad;
        this.fundacion = fundacion;
        this.escudo = escudo;
    }

    public Equipo(String nombre, String ciudad, String fundacion){
        this.nombre = nombre;
        this.ciudad = ciudad;
        this.fundacion = fundacion;
        this.idEquipo = 0;
        this.escudo = null;
    }

    public Equipo(int idEquipo, String nombreEquipo, String ciudad, String fundacion) {
        this.idEquipo = idEquipo;
        this.nombre = nombreEquipo;
        this.ciudad = ciudad;
        this.fundacion = fundacion;
        this.escudo = null;
    }

    public int getIdEquipo() {
        return idEquipo;
    }

    public void setIdEquipo(int idEquipo) {
        this.idEquipo = idEquipo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getFundacion() {
        return fundacion;
    }

    public void setFundacion(String fundacion) {
        this.fundacion = fundacion;
    }

    public Bitmap getEscudo() {
        return escudo;
    }

    public void setEscudo(Bitmap escudo) {
        this.escudo = escudo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Equipo equipo = (Equipo) o;
        return idEquipo == equipo.idEquipo;
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public int hashCode() {
        return Objects.hash(idEquipo);
    }

    @Override
    public String toString() {
        return nombre;
    }
}
