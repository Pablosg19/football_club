package com.example.footballclub.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.footballclub.R;
import com.example.footballclub.activities.equipo.ActualizarEquipoActivity;
import com.example.footballclub.activities.equipo.AnadirEquipoActivity;
import com.example.footballclub.activities.equipo.BorrarEquipoActivity;
import com.example.footballclub.activities.liga.ActualizarLigaActivity;
import com.example.footballclub.activities.liga.AnadirLigaActivity;

public class AjustesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ajustes);
    }

    public void irAtras(View view) {
        Intent intent = new Intent(this,PrincipalActivity.class);
        startActivity(intent);
    }

    public void irAñadirEquipo(View view) {
        Intent intent = new Intent(this, AnadirEquipoActivity.class);
        startActivity(intent);
    }

    public void irBorrarEquipo(View view) {
        Intent intent = new Intent(this, BorrarEquipoActivity.class);
        startActivity(intent);
    }

    public void irActualizarEquipo(View view) {
        Intent intent = new Intent(this, ActualizarEquipoActivity.class);
        startActivity(intent);
    }

    public void irAñadirLiga(View view) {
        Intent intent = new Intent(this, AnadirLigaActivity.class);
        startActivity(intent);
    }

    public void irActualizarLiga(View view) {
        Intent intent = new Intent(this, ActualizarLigaActivity.class);
        startActivity(intent);
    }
}