package com.example.proyectofinal.modelos;

import android.graphics.Bitmap;
import android.util.Log;

import com.example.proyectofinal.clases.Jugador;
import com.example.proyectofinal.utilidades.ImagenesBlobBitmap;

import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class JugadorDB {
    public static ArrayList<Jugador> obtenerJugadores(){
        Connection conexion = BaseDB.conectarConBaseDeDatos();
        if(conexion == null)
        {
            Log.i("equipos","no he podido conectar con la base de datos");
            return null;
        }
        ArrayList<Jugador> jugadoresDevueltos = new ArrayList<Jugador>();
        try {
            Statement sentencia = conexion.createStatement();
            String ordenSQL = "SELECT * FROM jugador";
            ResultSet resultado = sentencia.executeQuery(ordenSQL);
            while (resultado.next()){
                int id = resultado.getInt("idjugador");
                String nombre = resultado.getString("nombre");
                String apellidos = resultado.getString("apellidos");
                String nacionalidad = resultado.getString("nacionalidad");
                String edad = resultado.getString("fechaNacimiento");
                String posicion = resultado.getString("posicion");
                int idEquipo = resultado.getInt("equipo_idequipo");
                Blob blobFoto = resultado.getBlob("foto");
                if (blobFoto == null){
                    Jugador j = new Jugador(id,nombre,apellidos,nacionalidad,edad,posicion,idEquipo);
                    jugadoresDevueltos.add(j);
                }else{
                    Bitmap foto = ImagenesBlobBitmap.blobToBitmap(blobFoto,100,100);
                    Jugador j = new Jugador(id,nombre,apellidos,nacionalidad,edad,posicion,idEquipo,foto);
                    jugadoresDevueltos.add(j);
                }
            }
            resultado.close();
            sentencia.close();
            conexion.close();
            return jugadoresDevueltos;
        } catch (SQLException throwables) {
            Log.i("sql","error sql");
            return null;
        }
    }

    public static boolean insertarJugador(Jugador j){
        Connection conexion = BaseDB.conectarConBaseDeDatos();
        if(conexion == null)
        {
            return false;
        }
        try {
            PreparedStatement pst = conexion.prepareStatement("INSERT INTO jugador (nombre,apellidos,nacionalidad,fechaNacimiento,posicion,equipo_idequipo) VALUES (?,?,?,?,?,?);");
            pst.setString(1,j.getNombre());
            pst.setString(2,j.getApellidos());
            pst.setString(3,j.getNacionalidad());
            pst.setString(4,j.getEdad());
            pst.setString(5,j.getPosicion());
            pst.setInt(6,j.getIdEquipo());
            int filasAfectadas = pst.executeUpdate();
            pst.close();
            conexion.close();
            if (filasAfectadas > 0){
                return true;
            } else{
                return false;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return false;
        }
    }

    public static boolean borrarJugador(Jugador j){
        Connection conexion = BaseDB.conectarConBaseDeDatos();
        if(conexion == null)
        {
            return false;
        }
        try {
            PreparedStatement pst = conexion.prepareStatement("DELETE FROM jugador WHERE idjugador = ?;");
            pst.setInt(1,j.getIdJugador());
            int filasAfectadas = pst.executeUpdate();
            pst.close();
            conexion.close();
            if (filasAfectadas > 0){
                return true;
            } else{
                return false;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return false;
        }
    }

    public static boolean actualizarJugador(Jugador j){
        Connection conexion = BaseDB.conectarConBaseDeDatos();
        if(conexion == null)
        {
            return false;
        }
        try {
            PreparedStatement pst = conexion.prepareStatement("UPDATE jugador SET nombre = ?, apellidos = ?, nacionalidad = ?, fechaNacimiento = ?, posicion = ?, equipo_idequipo = ? WHERE idjugador = ?;");
            pst.setString(1,j.getNombre());
            pst.setString(2,j.getApellidos());
            pst.setString(3,j.getNacionalidad());
            pst.setString(4,j.getEdad());
            pst.setString(5,j.getPosicion());
            pst.setInt(6,j.getIdEquipo());
            pst.setInt(7,j.getIdJugador());
            int filasAfectadas = pst.executeUpdate();
            pst.close();
            conexion.close();
            if(filasAfectadas > 0)
            {
                return true;
            }
            else {
                return false;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return false;
        }
    }

    public static Jugador buscarJugador(String apellido){
        Connection conexion = BaseDB.conectarConBaseDeDatos();
        if(conexion == null)
        {
            return null;
        }
        Jugador jugadorEncontrado = null;
        try {
            PreparedStatement pst = conexion.prepareStatement("SELECT * FROM jugador WHERE apellido LIKE ?;");
            pst.setString(1,apellido);
            ResultSet resultado = pst.executeQuery();
            while(resultado.next()){
                int idJugador = resultado.getInt("idjugador");
                String nombre = resultado.getString("nombre");
                String apellidos = resultado.getString("apellidos");
                String nacionalidad = resultado.getString("nacionaldidad");
                String edad = resultado.getString("fechaNacimiento");
                String posicion = resultado.getString("posicion");
                int idEquipo = resultado.getInt("equipo_idequipo");
                jugadorEncontrado = new Jugador(idJugador,nombre,apellidos,nacionalidad,edad,posicion,idEquipo);
            }
            resultado.close();
            pst.close();
            conexion.close();
            return jugadorEncontrado;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return null;
        }
    }

    public static ArrayList<Jugador> cargarJugadores(int idEquipo){
        Connection conexion = BaseDB.conectarConBaseDeDatos();
        if(conexion == null)
        {
            return null;
        }else {
            ArrayList<Jugador> jugadoresDevueltos = new ArrayList<Jugador>();
            try {
                PreparedStatement pst = conexion.prepareStatement("SELECT * FROM jugador WHERE equipo_idequipo LIKE ?;");
                pst.setInt(1,idEquipo);
                ResultSet resultado = pst.executeQuery();
                while (resultado.next()){
                    int id = resultado.getInt("idjugador");
                    String nombre = resultado.getString("nombre");
                    String apellidos = resultado.getString("apellidos");
                    String nacionalidad = resultado.getString("nacionalidad");
                    String edad = resultado.getString("fechaNacimiento");
                    String posicion = resultado.getString("posicion");
                    Blob blobFoto = resultado.getBlob("foto");
                    if (blobFoto == null){
                        Jugador j = new Jugador(id,nombre,apellidos,nacionalidad,edad,posicion,idEquipo);
                        jugadoresDevueltos.add(j);
                    }else{
                        Bitmap foto = ImagenesBlobBitmap.blobToBitmap(blobFoto,100,100);
                        Jugador j = new Jugador(id,nombre,apellidos,nacionalidad,edad,posicion,idEquipo,foto);
                        jugadoresDevueltos.add(j);
                    }
                }
                resultado.close();
                pst.close();
                conexion.close();
                return jugadoresDevueltos;
            } catch (SQLException throwables) {
                Log.i("sql","error sql");
                return null;
            }
        }

    }
}