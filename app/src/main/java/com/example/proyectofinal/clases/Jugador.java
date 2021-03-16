package com.example.proyectofinal.clases;

import android.graphics.Bitmap;
import android.os.Build;

import androidx.annotation.RequiresApi;

import java.io.Serializable;
import java.util.Objects;

public class Jugador implements Serializable {
    private int idJugador;
    private String nombre;
    private String apellidos;
    private String nacionalidad;
    private String edad;
    private String posicion;
    private int idEquipo;
    private Bitmap foto;

    public Jugador(int idJugador, String nombre, String apellidos, String nacionalidad, String edad, String posicion, int idEquipo, Bitmap foto) {
        this.idJugador = idJugador;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.nacionalidad = nacionalidad;
        this.edad = edad;
        this.posicion = posicion;
        this.idEquipo = idEquipo;
        this.foto = foto;
    }

    public Jugador(int idJugador, String nombre, String apellidos, String nacionalidad, String edad, String posicion, int idEquipo) {
        this.idJugador = idJugador;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.nacionalidad = nacionalidad;
        this.edad = edad;
        this.posicion = posicion;
        this.idEquipo = idEquipo;
        this.foto = null;
    }

    public Jugador(String nombre, String apellidos, String nacionalidad, String edad, String posicion, int idEquipo) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.nacionalidad = nacionalidad;
        this.edad = edad;
        this.posicion = posicion;
        this.idEquipo = idEquipo;
    }

    public int getIdJugador() {
        return idJugador;
    }

    public void setIdJugador(int idJugador) {
        this.idJugador = idJugador;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getNacionalidad() {
        return nacionalidad;
    }

    public void setNacionalidad(String nacionalidad) {
        this.nacionalidad = nacionalidad;
    }

    public String getEdad() {
        return edad;
    }

    public void setEdad(String edad) {
        this.edad = edad;
    }

    public String getPosicion() {
        return posicion;
    }

    public void setPosicion(String posicion) {
        this.posicion = posicion;
    }

    public int getIdEquipo() {
        return idEquipo;
    }

    public void setIdEquipo(int idEquipo) {
        this.idEquipo = idEquipo;
    }

    public Bitmap getFoto() {
        return foto;
    }

    public void setFoto(Bitmap foto) {
        this.foto = foto;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Jugador jugador = (Jugador) o;
        return idJugador == jugador.idJugador;
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public int hashCode() {
        return Objects.hash(idJugador);
    }

    @Override
    public String toString() {
        return "Jugador{" +
                "idJugador=" + idJugador +
                ", nombre='" + nombre + '\'' +
                ", apellidos='" + apellidos + '\'' +
                ", nacionalidad='" + nacionalidad + '\'' +
                ", edad='" + edad + '\'' +
                ", posicion='" + posicion + '\'' +
                ", idEquipo=" + idEquipo +
                ", foto=" + foto +
                '}';
    }
}
