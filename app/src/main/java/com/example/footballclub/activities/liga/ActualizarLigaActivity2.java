package com.example.footballclub.activities.liga;

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
import com.example.footballclub.clases.Liga;
import com.example.footballclub.controladores.LigaController;

import java.sql.Date;
import java.util.ArrayList;

public class ActualizarLigaActivity2 extends AppCompatActivity {

    private Intent intent;
    private ArrayList<Liga> ligas;
    private EditText edt_ActualizarNombreLiga;
    private EditText edt_ActualizarPaisLiga;
    private EditText edt_ActualizarFechaInicio;
    private Button bt_ActualizarLiga;
    private String nombreLigaAntigua;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actualizar_liga2);
        edt_ActualizarNombreLiga = (EditText) findViewById(R.id.edt_ActualizarLiga2_nombreLiga);
        edt_ActualizarPaisLiga = (EditText) findViewById(R.id.edt_ActualizarLiga2_paisLiga);
        edt_ActualizarFechaInicio = (EditText) findViewById(R.id.edt_ActualizarLiga2_fechaInicio);
        bt_ActualizarLiga = (Button) findViewById(R.id.bt_ActualizarLiga);

        intent = getIntent();
        nombreLigaAntigua = intent.getStringExtra(ActualizarLigaActivity.NOMBRE_LIGA);
        ligas = LigaController.obtenerLigas();
        for (Liga l: ligas) {
            if (nombreLigaAntigua.equals(l.getNombreLiga())){
                edt_ActualizarNombreLiga.setText(l.getNombreLiga());
                edt_ActualizarPaisLiga.setText(l.getPaisLiga());
                edt_ActualizarFechaInicio.setText(String.valueOf(l.getFechaInicio()));
            }
        }
    }

    public void actualizarLigaAcitivty2(View view) {
        String nombreLiga = String.valueOf(edt_ActualizarNombreLiga.getText());
        String paisLiga = String.valueOf(edt_ActualizarPaisLiga.getText());
        String fechaInicio = String.valueOf(edt_ActualizarFechaInicio.getText());

        boolean error = false;
        if (nombreLiga.isEmpty()){
            edt_ActualizarNombreLiga.setError("Debes rellenar todos los campos");
            error = true;
        }
        if (paisLiga.isEmpty()){
            edt_ActualizarPaisLiga.setError("Debes rellenar todos los campos");
            error = true;
        }
        if (fechaInicio.isEmpty()){
            edt_ActualizarFechaInicio.setError("Debes rellenar todos los campos");
            error = true;
        }
        if (error){
            return;
        }
        ArrayList<Liga> ligas = LigaController.obtenerLigas();
        for (Liga l: ligas) {
            if(nombreLiga.equals(l.getNombreLiga())){
                if (!nombreLigaAntigua.equals(nombreLiga)){
                    edt_ActualizarNombreLiga.setError("Este equipo ya existe");
                    return;
                }
            }
        }
        AlertDialog.Builder actualizarLiga = new AlertDialog.Builder(this);
        actualizarLiga.setTitle("¿Quieres actualizar la liga?");
        actualizarLiga.setPositiveButton("Si", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Liga ligaActualizar = new Liga(0,nombreLiga,paisLiga, Date.valueOf(fechaInicio));
                boolean actualizarLigaOK = LigaController.actualizarLiga(ligaActualizar, nombreLigaAntigua);
                mostrarMensaje(actualizarLigaOK);
            }
        });
        actualizarLiga.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        actualizarLiga.show();
    }

    public void mostrarMensaje(boolean actualizarLigaOK){
        if (actualizarLigaOK){
            Toast.makeText(this,"La liga ha sido actualizado correctamente",Toast.LENGTH_LONG).show();
            Intent intent = new Intent(this, PrincipalActivity.class);
            startActivity(intent);
        }
        else{
            Toast.makeText(this,"No se ha podido actualizar la liga",Toast.LENGTH_LONG).show();
        }
    }

    public void volverActualizarLiga(View view) {
        Intent intent = new Intent(this, ActualizarLigaActivity.class);
        startActivity(intent);
    }
}