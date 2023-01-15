package com.example.footballclub.modelo;

import com.example.footballclub.clases.Equipo;
import com.example.footballclub.clases.Usuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UsuariosDB {
    public static boolean guardarNuevoUsuario(Usuario user) {
        Connection conexion = ConfiguracionDB.conectarConBaseDeDatos();
        if (conexion == null)
        {
            return false;
        }
        try{
            String ordenSQL = "INSERT INTO usuarios ('nombreUsuario', 'contraseña') VALUES(?,?);";
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
}
