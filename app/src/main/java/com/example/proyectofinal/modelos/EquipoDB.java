package com.example.proyectofinal.modelos;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;

import com.example.proyectofinal.utilidades.ImagenesBlobBitmap;
import com.example.proyectofinal.clases.Equipo;

import java.io.File;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class EquipoDB {
    public static ArrayList<Equipo> obtenerEquipos(){
        Connection conexion = BaseDB.conectarConBaseDeDatos();
        if(conexion == null)
        {
            return null;
        }
        ArrayList<Equipo> equiposDevueltos = new ArrayList<Equipo>();
        try {
            Statement sentencia = conexion.createStatement();
            String ordenSQL = "select * from equipo;";
            ResultSet resultado = sentencia.executeQuery(ordenSQL);
            while (resultado.next()){
                int idEquipo = resultado.getInt("idequipo");
                String nombre = resultado.getString("nombre");
                String fundacion = resultado.getString("fundacion");
                String ciudad = resultado.getString("ciudad");
                Blob blobFoto = resultado.getBlob("escudo");
                if (blobFoto == null){
                    Equipo e = new Equipo(idEquipo,nombre,ciudad,fundacion);
                    equiposDevueltos.add(e);
                }else{
                    byte[] byteFoto = ImagenesBlobBitmap.blobToByte(blobFoto);
                    Bitmap escudo = ImagenesBlobBitmap.byteToBitmap(byteFoto);
                    Equipo e = new Equipo(idEquipo,nombre,ciudad,fundacion,escudo);
                    equiposDevueltos.add(e);
                }
            }
            resultado.close();
            sentencia.close();
            conexion.close();
            return equiposDevueltos;
        } catch (SQLException throwables) {
            Log.i("sql","Error SQL");
            return null;
        }
    }

    public static boolean insertarEquipo(Equipo e){
        Connection conexion = BaseDB.conectarConBaseDeDatos();
        if(conexion == null)
        {
            return false;
        }
        try {
            String ordenSQL = "INSERT INTO equipo (nombre, fundacion, ciudad) VALUES (?,?,?);";
            PreparedStatement pst = conexion.prepareStatement(ordenSQL);
            pst.setString(1,e.getNombre());
            pst.setString(2,e.getFundacion());
            pst.setString(3,e.getCiudad());
            int filasAfectadas = pst.executeUpdate();
            pst.close();
            conexion.close();
            if(filasAfectadas > 0){
                return true;
            }else{
                return false;
            }
        } catch (SQLException throwables) {
            return false;
        }
    }

    public static boolean actualizarEquipo(Equipo e){
        Connection conexion = BaseDB.conectarConBaseDeDatos();
        if(conexion == null)
        {
            return false;
        }
        try {
            String ordenSQL = "UPDATE equipo SET nombre = ?, fundacion = ?, ciudad = ? WHERE (idEquipo = ?);";
            PreparedStatement pst = conexion.prepareStatement(ordenSQL);
            pst.setString(1,e.getNombre());
            pst.setString(2,e.getFundacion());
            pst.setString(3,e.getCiudad());
            pst.setInt(4,e.getIdEquipo());
            int filasAfectadas = pst.executeUpdate();
            pst.close();
            conexion.close();
            if(filasAfectadas > 0){
                return true;
            }else{
                return false;
            }
        } catch (SQLException throwables) {
            return false;
        }
    }

    public static Equipo buscarEquipo(int idEquipo){
        Connection conexion = BaseDB.conectarConBaseDeDatos();
        if(conexion == null)
        {
            return null;
        }
        Equipo equipoEncontrado = null;
        try {
            String ordenSQL = "SELECT * FROM equipo WHERE idequipo like ?;";
            PreparedStatement pst = conexion.prepareStatement(ordenSQL);
            pst.setInt(1,idEquipo);
            ResultSet resultadoSQL = pst.executeQuery();
            while (resultadoSQL.next()){
                String nombreEquipo = resultadoSQL.getString("nombre");
                String ciudad = resultadoSQL.getString("ciudad");
                String fundacion = resultadoSQL.getString("fundacion");
                equipoEncontrado = new Equipo(idEquipo,nombreEquipo,ciudad,fundacion);
            }
            resultadoSQL.close();
            pst.close();
            conexion.close();
            return equipoEncontrado;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return null;
        }
    }

    public static boolean borrarEquipo(Equipo e){
        Connection conexion = BaseDB.conectarConBaseDeDatos();
        if (conexion == null) {
            return false;
        }
        //----------------------------
        try {
            String ordensql = "DELETE FROM equipo WHERE idequipo = ?;";
            PreparedStatement pst = conexion.prepareStatement(ordensql);
            pst.setInt(1, e.getIdEquipo());
            int filasAfectadas = pst.executeUpdate();
            pst.close();
            conexion.close();
            if (filasAfectadas > 0) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException ex) {
            return false;
        }
    }
}
