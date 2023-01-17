package com.example.footballclub.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.footballclub.R;
import com.example.footballclub.clases.Usuario;
import com.example.footballclub.controladores.UsuarioController;

import java.security.PublicKey;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    public static final String USUARIO_INTRODUCIDO = "comexmplefootballclubusuariointroducido";
    private EditText edtUsuario;
    private EditText edtContraseña;
    private TextView datosIncorrectos;

    ArrayList<Usuario> usuarios = new ArrayList<Usuario>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        edtUsuario = (EditText) findViewById(R.id.edt_usuario);
        edtContraseña = (EditText) findViewById(R.id.edt_contraseña);
        datosIncorrectos = (TextView) findViewById(R.id.txtError);

        edtUsuario.setText("");
        edtContraseña.setText("");
        datosIncorrectos.setVisibility(View.INVISIBLE);
    }

    public void registrarActivity(View view) {
        Intent intent = new Intent(this, RegistrarActivity.class);
        startActivity(intent);
    }

    public void iniciarSesion(View view) {
        String usuario = String.valueOf(edtUsuario.getText());
        String contraseña = String.valueOf(edtContraseña.getText());
        boolean error = false;
        if (usuario.isEmpty()){
            edtUsuario.setError("Debes escribir el nombre de usuario");
            error = true;
        }
        if (contraseña.isEmpty()){
            edtContraseña.setError("Debes escribir una contraseña");
            error = true;
        }
        if (error){
            return;
        }
        usuarios = UsuarioController.obtenerUsuarios();
        for (Usuario user : usuarios) {
            if (usuario.equals(user.getNombreUsuario()) && contraseña.equals(user.getContraseña())){
                datosIncorrectos.setVisibility(View.INVISIBLE);
                Intent intent = new Intent(this, PrincipalActivity.class);
                intent.putExtra(USUARIO_INTRODUCIDO,user.getNombreUsuario());
                startActivity(intent);
                return;
            }
        }
        datosIncorrectos.setVisibility(View.VISIBLE);
    }
}