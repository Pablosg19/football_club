package com.example.footballclub.modelo;

import android.util.Log;

import com.example.footballclub.clases.Usuario;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class UsuariosDB {
    public static boolean guardarNuevoUsuario(Usuario user) {
        Connection conexion = ConfiguracionDB.conectarConBaseDeDatos();
        if (conexion == null)
        {
            return false;
        }
        try{
            String ordenSQL = "INSERT INTO usuarios (nombreUsuario, contraseña) VALUES(?,?);";
            PreparedStatement sentencia = conexion.prepareStatement(ordenSQL);
            sentencia.setString(1,user.getNombreUsuario());
            sentencia.setString(2, user.getContraseña());
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
    //-------------------------------------------------------------------------------------
    public static ArrayList<Usuario> obtenerUsuarios(){
        Connection conexion = ConfiguracionDB.conectarConBaseDeDatos();
        if(conexion == null){
            return null;
        }
        ArrayList<Usuario> usuarios = new ArrayList<Usuario>();
        try {
            Statement sentencia = conexion.createStatement();
            String ordenSQL = "SELECT * FROM usuarios ORDER BY nombreUsuario;";
            ResultSet resultado = sentencia.executeQuery(ordenSQL);
            while (resultado.next()){
                String usuario = resultado.getString("nombreUsuario");
                String contraseña = resultado.getString("contraseña");
                Usuario user = new Usuario(usuario, contraseña);
                usuarios.add(user);
            }
            resultado.close();
            sentencia.close();
            conexion.close();
            return usuarios;
        } catch (SQLException e){
            Log.i("sql","error sql obtenerUsuariosDB");
            return usuarios;
        }
    }
}
