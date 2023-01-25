package com.example.footballclub.activities.liga;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.footballclub.R;
import com.example.footballclub.activities.AjustesActivity;
import com.example.footballclub.activities.DatepickerFragment;
import com.example.footballclub.activities.PrincipalActivity;
import com.example.footballclub.clases.Liga;
import com.example.footballclub.controladores.LigaController;

import java.util.ArrayList;
import java.sql.Date;

public class AnadirLigaActivity extends AppCompatActivity {

    private EditText edtFechaInicio;
    private EditText edtNombreLiga;
    private EditText edtPaisLiga;
    private Date fechaInicio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anadir_liga);
        edtFechaInicio = (EditText) findViewById(R.id.edt_anadirLigaFechaInicio);
        edtNombreLiga = (EditText) findViewById(R.id.edt_anadirLigaNombre);
        edtPaisLiga = (EditText) findViewById(R.id.edt_anadirLigaPais);
    }

    public void mostrarCalendario(View view) {
        DatepickerFragment calendario1 = new DatepickerFragment();
        calendario1.show(getSupportFragmentManager(),"DatePicker");
    }
    public void crearFecha(int year, int month, int day){
        String txtYear = String.valueOf(year);
        month = month+1;
        String txtMonth = String.valueOf(month);
        String txtDay = String.valueOf(day);
        String txtFecha = txtYear +"-" + txtMonth + "-" + txtDay;
        edtFechaInicio.setText(txtFecha);
        fechaInicio = Date.valueOf(txtFecha);
    }

    public void volverAtras(View view) {
        Intent intent = new Intent(this, AjustesActivity.class);
        startActivity(intent);
    }

    public void addLiga(View view) {
        String nombreLiga = String.valueOf(edtNombreLiga.getText());
        String paisLiga = String.valueOf(edtPaisLiga.getText());
        String fecha = String.valueOf(edtFechaInicio.getText());

        boolean error = false;
        if (nombreLiga.isEmpty()){
            edtNombreLiga.setError("Debes rellenar este campo");
            error = true;
        }
        if (paisLiga.isEmpty()){
            edtPaisLiga.setError("Debes rellenar este campo");
            error = true;
        }
        if (fecha.isEmpty()){
            edtFechaInicio.setError("Debes rellenar este campo");
            error = true;
        }
        if(error){
            return;
        }
        ArrayList<Liga> ligas = LigaController.obtenerLigas();
        for (Liga l : ligas){
            if (nombreLiga.equals(l.getNombreLiga())){
                edtNombreLiga.setError("Esta liga ya existe");
                return;
            }
        }
        AlertDialog.Builder crearLiga = new AlertDialog.Builder(this);
        crearLiga.setTitle("¿Crear esta liga?");
        crearLiga.setPositiveButton("Si", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Liga l = new Liga(0,nombreLiga, paisLiga, fechaInicio);
                boolean anadirLigaOK = LigaController.nuevaLiga(l);
                mostrarMensaje(anadirLigaOK);
            }
        });
        crearLiga.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        crearLiga.show();
    }
    public void mostrarMensaje(boolean anadirOK){
        if (anadirOK){
            Toast.makeText(this, "Liga creada correctamente",Toast.LENGTH_LONG).show();
            Intent intent = new Intent(this, PrincipalActivity.class);
            startActivity(intent);
        }
        else {
            Toast.makeText(this, "No se ha podido crear la liga",Toast.LENGTH_LONG).show();
        }
    }
}