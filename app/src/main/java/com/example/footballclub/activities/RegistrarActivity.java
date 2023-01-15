package com.example.footballclub.activities;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.example.footballclub.R;
import com.example.footballclub.clases.Usuario;
import com.example.footballclub.controladores.UsuarioController;

import java.io.StringReader;

public class RegistrarActivity extends AppCompatActivity {

    private EditText edtUsuario;
    private EditText edtContraseña;
    private CheckBox terminos;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar);
        edtUsuario = (EditText) findViewById(R.id.edt_usuarioRegistrar);
        edtContraseña = (EditText) findViewById(R.id.edt_contraseña);
        terminos = (CheckBox) findViewById(R.id.cb_terminos);

    }

    public void añadirUsuario(View view) {
        String nombreUser = String.valueOf(edtUsuario.getText());
        String contraseña = String.valueOf(edtContraseña.getText());
        boolean error = false;
        if (nombreUser.isEmpty()){
            edtUsuario.setError("Debes escribir el nombre de usuario");
            error = true;
        }
        else if ( nombreUser.length() > 15){
            edtUsuario.setError("El nombre de usuario tiene un máximo de 15 carácteres");
            error = true;
        }
        if (contraseña.isEmpty()){
            edtContraseña.setError("Debes escribir una contraseña");
             error = true;
        } else if (contraseña.length() < 6 || contraseña.length() > 10) {
            edtContraseña.setError("La contraseña debe comprender entre 6 y 10 carácteres");
            error = true;
        }
        if(!terminos.isChecked()){
            terminos.setError("Debes aceptar los términos y condiciones");
            error = true;
        }
        if (error){
            return;
        }
        AlertDialog.Builder confirmarUsuario = new AlertDialog.Builder(this);
        confirmarUsuario.setTitle("¿Desea crear este usuario?");
        confirmarUsuario.setPositiveButton("Si", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Usuario user = new Usuario(nombreUser, contraseña);
                boolean nuevoUsuarioOK = UsuarioController.nuevoUsuario(user);
                mostrarMensaje(nuevoUsuarioOK);
            }
        });
        confirmarUsuario.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        confirmarUsuario.show();
    }
    public void mostrarMensaje(boolean nuevoUsuarioOK){
        if(nuevoUsuarioOK){
            Toast.makeText(this,"Usuario creado correctamente", Toast.LENGTH_LONG).show();
        }
        else {
            Toast.makeText(this,"No se ha podido crear el usuario", Toast.LENGTH_LONG).show();
        }
    }
}