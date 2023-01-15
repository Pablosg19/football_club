package com.example.footballclub.modelo;

import android.util.Log;

import com.example.footballclub.clases.Equipo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class EquipoDB {
    public static ArrayList<Equipo> obtenerEquipos()
    {
        Connection conexion = ConfiguracionDB.conectarConBaseDeDatos();
        if(conexion == null)
        {
            return null;
        }
        ArrayList<Equipo> equipos = new ArrayList<Equipo>();
        try
        {
            Statement sentencia = conexion.createStatement();
            String ordenSQL = "SELECT * FROM equipos ORDER BY idEquipos";
            ResultSet resultado = sentencia.executeQuery(ordenSQL);
            while (resultado.next())
            {
                int idEquipo = resultado.getInt("idEquipo");
                String nombreEquipo = resultado.getString("NombreEquipo");
                String ciudadEquipo = resultado.getString("CiudadEquipo");
                int titulos = resultado.getInt("Titulos");
                int idLiga = resultado.getInt("Ligas_idLiga");
                Equipo e = new Equipo(idEquipo,nombreEquipo,ciudadEquipo,titulos,idLiga);
                equipos.add(e);
            }
            resultado.close();
            sentencia.close();
            conexion.close();
            return equipos;
        } catch (SQLException e)
        {
            Log.i("sql", "error sql");
            return equipos;
        }
    }
    //-------------------------------------------------------------------------
    public static boolean guardarNuevoEquipo(Equipo e) {
        Connection conexion = ConfiguracionDB.conectarConBaseDeDatos();
        if (conexion == null)
        {
            return false;
        }
        try{
            String ordenSQL = "INSERT INTO equipos ('idEquipo', 'NombreEquipo', 'CiudadEquipo','Titulos','ligas_idliga') VALUES(?,?,?,?,?,?);";
            PreparedStatement sentencia = conexion.prepareStatement(ordenSQL);
            sentencia.setInt(1,e.getIdEquipo());
            sentencia.setString(2,e.getNombreEquipo());
            sentencia.setString(3,e.getCiudadEquipo());
            sentencia.setInt(4,e.getNumTitulos());
            sentencia.setInt(5,e.getIdLiga());
            int filasAfectadas = sentencia.executeUpdate();
            sentencia.close();
            conexion.close();
            if (filasAfectadas > 0){
                return true;
            }
            else {
                return false;
            }
        }catch (SQLException e1){
            return false;
        }
    }
    //-------------------------------------------------------------------------
    public static boolean borrarEquipo(String equipo){
        Connection conexion = ConfiguracionDB.conectarConBaseDeDatos();
        if(conexion == null){
            return false;
        }
        try{
            String ordenSQL = "DELETE FROM 'equipos' WHERE ('NombreEquipo' = ?);";
            PreparedStatement sentencia = conexion.prepareStatement(ordenSQL);
            sentencia.setString(1,equipo);
            int filasAfectadas = sentencia.executeUpdate();
            sentencia.close();
            conexion.close();
            if(filasAfectadas > 0){
                return true;
            }
            else{
                return false;
            }
        }catch (SQLException e){
            return false;
        }
    }
    //-------------------------------------------------------------------------
    public static boolean actualizarEquipo(Equipo e, String nombreEquipo){
        Connection conexion = ConfiguracionDB.conectarConBaseDeDatos();
        if (conexion == null){
            return false;
        }
        try {
            String ordenSQL = "UPDATE equipos SET NombreEquipo = ?, CiudadEquipo = ?, NumTitulos = ? WHERE NombreEquipo = ?;";
            PreparedStatement sentencia = conexion.prepareStatement(ordenSQL);
            sentencia.setString(1,e.getNombreEquipo());
            sentencia.setString(2,e.getCiudadEquipo());
            sentencia.setInt(3,e.getNumTitulos());
            sentencia.setString(4,nombreEquipo);
            int filasAfectadas = sentencia.executeUpdate();
            sentencia.close();
            conexion.close();
            if(filasAfectadas > 0){
                return true;
            }
            else{
                return false;
            }
        } catch (SQLException e1){
            return false;
        }
    }
}
