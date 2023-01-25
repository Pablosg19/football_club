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
import com.example.footballclub.activities.PrincipalActivity;
import com.example.footballclub.clases.Equipo;
import com.example.footballclub.clases.Liga;
import com.example.footballclub.controladores.EquipoController;
import com.example.footballclub.controladores.LigaController;

import java.util.ArrayList;

public class ActualizarEquipoActivity2 extends AppCompatActivity {

    private Intent intent;
    private ArrayList<Equipo> equipos;
    private EditText edt_ActualizarNombreEquipo;
    private EditText edt_ActualizarCiudadEquipo;
    private EditText edt_ActualizarNumTitulos;
    private EditText edt_ActualizarNombreLiga;
    private Button bt_ActualizarEquipo;
    private String nombreEquipoAntiguo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actualizar_equipo2);
        edt_ActualizarNombreEquipo = (EditText) findViewById(R.id.edt_Actualizar2NombreEquipo);
        edt_ActualizarCiudadEquipo = (EditText) findViewById(R.id.edt_Actualizar2NombreCiudad);
        edt_ActualizarNumTitulos = (EditText) findViewById(R.id.edt_Actualizar2NumTitulos);
        edt_ActualizarNombreLiga = (EditText) findViewById(R.id.edt_Actualizar2NombreLiga);
        bt_ActualizarEquipo = (Button) findViewById(R.id.bt_ActualizarEquipo2);

        intent = getIntent();
        nombreEquipoAntiguo = intent.getStringExtra(ActualizarEquipoActivity.NOMBRE_EQUIPO);
        equipos = EquipoController.obtenerEquipos();
        for (Equipo e: equipos) {
            if (nombreEquipoAntiguo.equals(e.getNombreEquipo())){
                edt_ActualizarNombreEquipo.setText(e.getNombreEquipo());
                edt_ActualizarCiudadEquipo.setText(e.getCiudadEquipo());
                edt_ActualizarNumTitulos.setText(String.valueOf(e.getNumTitulos()));
                String nombreLiga = null;
                ArrayList<Liga> ligas = LigaController.obtenerLigas();
                for (Liga l: ligas){
                    if (l.getIdLiga() == e.getIdLiga()){
                        nombreLiga = l.getNombreLiga();
                    }
                }
                edt_ActualizarNombreLiga.setText(nombreLiga);
            }
        }
    }

    public void volverActualizarEquipo(View view) {
        Intent intent = new Intent(this, ActualizarEquipoActivity.class);
        startActivity(intent);
    }

    public void actualizarEquipoAcitivty2(View view) {
        String nombreEquipo = String.valueOf(edt_ActualizarNombreEquipo.getText());
        String ciudadEquipo = String.valueOf(edt_ActualizarCiudadEquipo.getText());
        String numTitulos = String.valueOf(edt_ActualizarNumTitulos.getText());
        String nombreLiga = String.valueOf(edt_ActualizarNombreLiga.getText());
        boolean error = false;
        if (nombreEquipo.isEmpty()){
            edt_ActualizarNombreEquipo.setError("Debes rellenar todos los campos");
            error = true;
        }
        if (ciudadEquipo.isEmpty()){
            edt_ActualizarCiudadEquipo.setError("Debes rellenar todos los campos");
            error = true;
        }
        if (numTitulos.isEmpty()){
            edt_ActualizarNumTitulos.setError("Debes rellenar todos los campos");
            error = true;
        }
        if (nombreLiga.isEmpty()){
            edt_ActualizarNombreLiga.setError("Debes rellenar todos los campos");
            error = true;
        }
        if (error){
            return;
        }
        ArrayList<Equipo> equipos = EquipoController.obtenerEquipos();
        for (Equipo e: equipos) {
            if(nombreEquipo.equals(e.getNombreEquipo())){
                if (!nombreEquipoAntiguo.equals(nombreEquipo)){
                    edt_ActualizarNombreEquipo.setError("Este equipo ya existe");
                    return;
                }
            }
        }
        int idLiga = 0;
        ArrayList<Liga> ligas = LigaController.obtenerLigas();
        for (Liga l: ligas) {
            if(nombreLiga.equals(l.getNombreLiga())){
                idLiga = l.getIdLiga();
            }
        }

        AlertDialog.Builder actualizarEquipo = new AlertDialog.Builder(this);
        actualizarEquipo.setTitle("¿Quieres actualizar el equipo?");
        int finalIdLiga = idLiga;
        actualizarEquipo.setPositiveButton("Si", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Equipo equipoActualizar = new Equipo(0,nombreEquipo,ciudadEquipo,Integer.valueOf(numTitulos), finalIdLiga);
                boolean actualizarEquipoOK = EquipoController.actualizarEquipo(equipoActualizar,nombreEquipoAntiguo);
                mostrarMensaje(actualizarEquipoOK);
            }
        });
        actualizarEquipo.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        actualizarEquipo.show();
    }
    public void mostrarMensaje(boolean actualizarEquipoOK){
        if (actualizarEquipoOK){
            Toast.makeText(this,"El equipo ha sido actualizado correctamente",Toast.LENGTH_LONG).show();
            Intent intent = new Intent(this, PrincipalActivity.class);
            startActivity(intent);
        }
        else{
            Toast.makeText(this,"No se ha podido actualizar el equipo",Toast.LENGTH_LONG).show();
        }
    }
}