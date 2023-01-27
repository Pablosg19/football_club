package com.example.footballclub.modelo;

import android.graphics.Bitmap;
import android.util.Log;

import com.example.footballclub.bitmap.ImagenesBlobBitmap;
import com.example.footballclub.clases.Equipo;

import java.sql.Blob;
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
            Log.i("sql","no conecta la base de datos");
            return null;
        }
        ArrayList<Equipo> equipos = new ArrayList<Equipo>();
        try
        {
            Statement sentencia = conexion.createStatement();
            String ordenSQL = "SELECT * FROM equipos ORDER BY nombreEquipo;";
            ResultSet resultado = sentencia.executeQuery(ordenSQL);
            while (resultado.next())
            {
                int idEquipo = resultado.getInt("idEquipo");
                String nombreEquipo = resultado.getString("NombreEquipo");
                String ciudadEquipo = resultado.getString("CiudadEquipo");
                int titulos = resultado.getInt("numTitulos");
                int idLiga = resultado.getInt("idLiga");
                Blob fotoEquipo = resultado.getBlob("fotoEquipo");
                Bitmap bm_foto;
                Equipo e;
                if(fotoEquipo != null){
                    byte[] bfoto = ImagenesBlobBitmap.blob_to_bytes(fotoEquipo);
                    bm_foto = ImagenesBlobBitmap.decodeSampledBitmapFrombyteArray(bfoto, ConfiguracionDB.ANCHO_IMAGENES_BITMAP, ConfiguracionDB.ALTO_IMAGENES_BITMAP);
                    e = new Equipo(idEquipo,nombreEquipo,ciudadEquipo,titulos,idLiga,bm_foto);
                }
                else{
                    e = new Equipo(idEquipo,nombreEquipo,ciudadEquipo,titulos,idLiga, null);

                }
                equipos.add(e);
            }
            resultado.close();
            sentencia.close();
            conexion.close();
            return equipos;
        } catch (SQLException e)
        {
            e.printStackTrace();
            Log.i("sql","error sql obtenerEquiposDB");
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
            String ordenSQL = "INSERT INTO equipos (idEquipo, NombreEquipo, CiudadEquipo,NumTitulos, idliga, fotoEquipo) VALUES('0',?,?,?,?,?);";
            PreparedStatement sentencia = conexion.prepareStatement(ordenSQL);
            sentencia.setString(1,e.getNombreEquipo());
            sentencia.setString(2,e.getCiudadEquipo());
            sentencia.setInt(3,e.getNumTitulos());
            sentencia.setInt(4,e.getIdLiga());
            if (e.getFotoEquipo() != null){
                byte[] bl1 = ImagenesBlobBitmap.bitmap_to_bytes_png(e.getFotoEquipo());
                sentencia.setBytes(5,bl1);
            }
            else{
                sentencia.setBytes(5, null);
            }
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
            String ordenSQL = "DELETE FROM equipos WHERE (NombreEquipo = ?);";
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
            String ordenSQL = "UPDATE equipos SET NombreEquipo = ?, CiudadEquipo = ?, numTitulos = ?, fotoEquipo = ? WHERE NombreEquipo = ?;";
            PreparedStatement sentencia = conexion.prepareStatement(ordenSQL);
            sentencia.setString(1,e.getNombreEquipo());
            sentencia.setString(2,e.getCiudadEquipo());
            sentencia.setInt(3,e.getNumTitulos());
            if (e.getFotoEquipo() != null){
                byte[] bl1 = ImagenesBlobBitmap.bitmap_to_bytes_png(e.getFotoEquipo());
                sentencia.setBytes(4,bl1);
            }
            sentencia.setString(5,nombreEquipo);
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
    //-------------------------------------------------------------------------
    public static ArrayList<Equipo> obtenerEquiposBusqueda(String filtro){
        Connection conexion = ConfiguracionDB.conectarConBaseDeDatos();
        if(conexion == null)
        {
            Log.i("sql","no conecta la base de datos");
            return null;
        }
        if (filtro == null){
            Log.i("filtro","nulo");
        }
        else{
            Log.i("filtro", filtro);
        }
        ArrayList<Equipo> equipos = new ArrayList<Equipo>();
        try
        {
            Log.i("filtro",filtro);
            filtro = "%"+filtro+"%";
            String ordenSQL = "SELECT idEquipo, NombreEquipo, CiudadEquipo, NumTitulos, equipos.idLiga, fotoEquipo FROM equipos INNER JOIN Ligas WHERE equipos.idLiga = ligas.idliga and (NombreEquipo LIKE ? or NombreLiga LIKE ?);";
            PreparedStatement sentencia = conexion.prepareStatement(ordenSQL);
            sentencia.setString(1, filtro);
            sentencia.setString(2, filtro);
            ResultSet resultado = sentencia.executeQuery();

            while (resultado.next())
            {
                int idEquipo = resultado.getInt("idEquipo");
                String nombreEquipo = resultado.getString("NombreEquipo");
                String ciudadEquipo = resultado.getString("CiudadEquipo");
                int titulos = resultado.getInt("numTitulos");
                int idLiga = resultado.getInt("idLiga");
                Blob fotoEquipo = resultado.getBlob("fotoEquipo");
                Bitmap bm_foto;
                Equipo e;
                if(fotoEquipo != null){
                    byte[] bfoto = ImagenesBlobBitmap.blob_to_bytes(fotoEquipo);
                    bm_foto = ImagenesBlobBitmap.decodeSampledBitmapFrombyteArray(bfoto, ConfiguracionDB.ANCHO_IMAGENES_BITMAP, ConfiguracionDB.ALTO_IMAGENES_BITMAP);
                    e = new Equipo(idEquipo,nombreEquipo,ciudadEquipo,titulos,idLiga,bm_foto);
                }
                else{
                    e = new Equipo(idEquipo,nombreEquipo,ciudadEquipo,titulos,idLiga, null);

                }
                equipos.add(e);
            }
            resultado.close();
            sentencia.close();
            conexion.close();
            return equipos;
        } catch (SQLException e)
        {
            e.printStackTrace();
            Log.i("sql","error sql obtenerEquiposBusquedaDB");
            return equipos;
        }
    }
}
