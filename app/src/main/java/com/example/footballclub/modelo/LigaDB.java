package com.example.footballclub.modelo;

import android.util.Log;

import com.example.footballclub.clases.Liga;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class LigaDB {
    public static ArrayList<Liga> obtenerLigas(){
        Connection conexion = ConfiguracionDB.conectarConBaseDeDatos();
        if(conexion == null){
            return null;
        }
        ArrayList<Liga> ligas = new ArrayList<Liga>();
        try {
            Statement sentencia = conexion.createStatement();
            String ordenSQL = "SELECT * FROM ligas ORDER BY idliga;";
            ResultSet resultado = sentencia.executeQuery(ordenSQL);
            while (resultado.next()){
                int idliga = resultado.getInt("idliga");
                String NombreLiga = resultado.getString("NombreLiga");
                String PaisLiga = resultado.getString("PaisLiga");
                Date FechaInicio = resultado.getDate("FechaInicio");
                Liga l = new Liga(idliga,NombreLiga,PaisLiga,FechaInicio);
                ligas.add(l);
            }
            resultado.close();
            sentencia.close();
            conexion.close();
            return ligas;
        } catch (SQLException e){
            Log.i("sql", "error sql");
            return ligas;
        }
    }
    //-------------------------------------------------------------------------
    public static boolean guardarNuevaLiga(Liga l){
        Connection conexion = ConfiguracionDB.conectarConBaseDeDatos();
        if(conexion == null){
            return false;
        }
        try {
            String ordenSQL = "INSERT INTO ligas ('idliga','NombreLiga','PaisLiga','FechaInicio') VALUES (?,?,?,?);";
            PreparedStatement sentencia = conexion.prepareStatement(ordenSQL);
            sentencia.setInt(1,l.getIdLiga());
            sentencia.setString(2,l.getNombreLiga());
            sentencia.setString(3,l.getPaisLiga());
            sentencia.setDate(4, l.getFechaInicio());
            int filasAfectadas = sentencia.executeUpdate();
            sentencia.close();
            conexion.close();
            if(filasAfectadas > 0){
                return true;
            }
            else {
                return false;
            }
        } catch (SQLException e){
            return false;
        }
    }
    //-------------------------------------------------------------------------
    public static boolean borrarLiga(String nombreLiga){
        Connection conexion = ConfiguracionDB.conectarConBaseDeDatos();
        if(conexion == null){
            return false;
        }
        try {
            String ordenSQL = "DELETE FROM 'ligas' WHERE ('NombreLiga' = ?);";
            PreparedStatement sentencia = conexion.prepareStatement(ordenSQL);
            sentencia.setString(1,nombreLiga);
            int filasAfectadas = sentencia.executeUpdate();
            sentencia.close();
            conexion.close();
            if(filasAfectadas > 0){
                return true;
            }
            else {
                return false;
            }
        } catch (SQLException e){
            return false;
        }
    }
    //-------------------------------------------------------------------------
    public static boolean actualizarLiga(Liga l, String nombreLiga){
        Connection conexion = ConfiguracionDB.conectarConBaseDeDatos();
        if(conexion == null){
            return false;
        }
        try{
            String ordenSQL = "UPDATE ligas SET NombreLiga = ?, PaisLiga = ?, FechaInicio = ? WHERE NombreLiga = ?;";
            PreparedStatement sentencia = conexion.prepareStatement(ordenSQL);
            sentencia.setString(1,l.getNombreLiga());
            sentencia.setString(2,l.getPaisLiga());
            sentencia.setDate(3, l.getFechaInicio());
            sentencia.setString(4,nombreLiga);
            int filasAfectadas = sentencia.executeUpdate();
            sentencia.close();
            conexion.close();
            if(filasAfectadas > 0){
                return true;
            }
            else {
                return false;
            }
        } catch (SQLException e){
            return false;
        }
    }
}
