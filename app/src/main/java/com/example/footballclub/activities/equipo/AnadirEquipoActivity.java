package com.example.footballclub.activities.equipo;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.footballclub.R;
import com.example.footballclub.activities.AjustesActivity;
import com.example.footballclub.activities.MainActivity;
import com.example.footballclub.activities.PrincipalActivity;
import com.example.footballclub.clases.Equipo;
import com.example.footballclub.clases.Liga;
import com.example.footballclub.controladores.EquipoController;
import com.example.footballclub.controladores.LigaController;
import com.example.footballclub.modelo.LigaDB;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class AnadirEquipoActivity extends AppCompatActivity {

    private EditText edt_anadirNombreEquipo;
    private EditText edt_anadirCiudadEquipo;
    private EditText edt_anadirNumTitulos;
    private EditText edt_anadirNombreLiga;
    private Button bt_anadirEquipo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anadir_equipo);
        edt_anadirNombreEquipo = (EditText) findViewById(R.id.edt_anadirNombreEquipo);
        edt_anadirCiudadEquipo = (EditText) findViewById(R.id.edt_anadirCiudadEquipo);
        edt_anadirNumTitulos = (EditText) findViewById(R.id.edt_anadirNumTitulos);
        edt_anadirNombreLiga = (EditText) findViewById(R.id.edt_anadirNombreLiga);
        bt_anadirEquipo = (Button) findViewById(R.id.bt_crearEquipo);

    }

    public void addEquipo(View view) {
        String nombreEquipo = String.valueOf(edt_anadirNombreEquipo.getText());
        String ciudadEquipo = String.valueOf(edt_anadirCiudadEquipo.getText());
        String numTitulos = String.valueOf(edt_anadirNumTitulos.getText());
        String nombreLiga = String.valueOf(edt_anadirNombreLiga.getText());
        int idLigaEquipo = 0;
        ArrayList<Liga> ligas = LigaController.obtenerLigas();
        for (Liga l: ligas) {
            if(l.getNombreLiga().equals(nombreLiga)){
                idLigaEquipo = l.getIdLiga();
            }
        }
        boolean error = false;
        if (nombreEquipo.isEmpty()){
            edt_anadirNombreEquipo.setError("");
            bt_anadirEquipo.setError("Debes completar todos los campos");
            error = true;
        }
        if (ciudadEquipo.isEmpty()){
            edt_anadirCiudadEquipo.setError("");
            bt_anadirEquipo.setError("Debes completar todos los campos");
            error = true;
        }
        if (numTitulos.isEmpty()){
            edt_anadirNumTitulos.setError("");
            bt_anadirEquipo.setError("Debes completar todos los campos");
            error = true;
        }
        if (nombreLiga.isEmpty()){
            edt_anadirNombreLiga.setError("");
            bt_anadirEquipo.setError("Debes completar todos los campos");
            error = true;
        }
        if (idLigaEquipo == 0){
            edt_anadirNombreLiga.setError("No existe la liga introducida");
            error = true;
        }
        if(error){
            return;
        }
        ArrayList<Equipo> equipos = EquipoController.obtenerEquipos();
        for (Equipo e : equipos){
            if (nombreEquipo.equals(e.getNombreEquipo())){
                edt_anadirNombreEquipo.setText("Este equipo ya existe");
                return;
            }
        }
        AlertDialog.Builder crearEquipo = new AlertDialog.Builder(this);
        crearEquipo.setTitle("¿Crear equipo?");
        int finalIdLigaEquipo = idLigaEquipo;
        crearEquipo.setPositiveButton("Si", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Equipo e = new Equipo(0,nombreEquipo, ciudadEquipo, Integer.valueOf(numTitulos), finalIdLigaEquipo);
                boolean anadirEquipoOK = EquipoController.nuevoEquipo(e);
                mostrarMensaje(anadirEquipoOK);
            }
        });
        crearEquipo.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        crearEquipo.show();
    }
    public void mostrarMensaje(boolean anadirOK){
        if (anadirOK){
            Toast.makeText(this, "Equipo creado correctamente",Toast.LENGTH_LONG).show();
            Intent intent = new Intent(this, PrincipalActivity.class);
            startActivity(intent);
        }
        else {
            Toast.makeText(this, "No se ha podido crear el equipo",Toast.LENGTH_LONG).show();
        }
    }

    public void volverActivityPrincipal(View view) {
        Intent intent = new Intent(this, AjustesActivity.class);
        startActivity(intent);
    }
}