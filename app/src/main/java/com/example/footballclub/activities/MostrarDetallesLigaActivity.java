package com.example.footballclub.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.footballclub.R;
import com.example.footballclub.activities.liga.ActualizarLigaActivity;
import com.example.footballclub.clases.Equipo;
import com.example.footballclub.clases.Liga;

public class MostrarDetallesLigaActivity extends AppCompatActivity {

    private TextView txt_detalles_nombreLiga;
    private TextView txt_detalles_paisLiga;
    private TextView txt_detalles_fechaInicio;
    private ImageView img_detalles_liga;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mostrar_detalles_liga);
        txt_detalles_nombreLiga = (TextView) findViewById(R.id.txt_detallesLiga_nombreLiga);
        txt_detalles_paisLiga = (TextView) findViewById(R.id.txt_detallesLiga_paisLiga);
        txt_detalles_fechaInicio = (TextView) findViewById(R.id.txt_detallesLiga_fechaInicio);
        img_detalles_liga = (ImageView) findViewById(R.id.img_detalles_liga);
        Intent intent = getIntent();
        if (intent != null) {
            Liga l = (Liga) intent.getSerializableExtra(LigaViewHolder.EXTRA_OBJETO_LIGA);
            txt_detalles_nombreLiga.setText(l.getNombreLiga());
            txt_detalles_paisLiga.setText("País: " + l.getPaisLiga());
            txt_detalles_fechaInicio.setText("Fecha de inicio: " + l.getFechaInicio());
            img_detalles_liga.setImageResource(R.drawable.competicion);
        }
    }

    public void volverActivityLiga(View view) {
        Intent intent = new Intent(this, ActualizarLigaActivity.class);
        startActivity(intent);
    }
}