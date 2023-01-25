package com.example.footballclub.activities.liga;

import static com.example.footballclub.activities.equipo.ActualizarEquipoActivity.NOMBRE_EQUIPO;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.example.footballclub.R;
import com.example.footballclub.activities.AjustesActivity;
import com.example.footballclub.activities.ListaEquiposAdapter;
import com.example.footballclub.activities.ListaLigasAdapter;
import com.example.footballclub.activities.equipo.ActualizarEquipoActivity2;
import com.example.footballclub.clases.Equipo;
import com.example.footballclub.clases.Liga;
import com.example.footballclub.controladores.EquipoController;
import com.example.footballclub.controladores.LigaController;

import java.util.ArrayList;

public class ActualizarLigaActivity extends AppCompatActivity {

    public static final String NOMBRE_LIGA = "comexamplefootballclubactivitiesequiponombreliga";
    private EditText edt_actualizarLigaNombre;
    private RecyclerView rv_ActualizarLiga;
    private ListaLigasAdapter actualizarLigaAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actualizar_liga);
        edt_actualizarLigaNombre = (EditText) findViewById(R.id.edt_actualizarLigaNombre);
        rv_ActualizarLiga = (RecyclerView) findViewById(R.id.rv_actualizarLiga);

        actualizarLigaAdapter = new ListaLigasAdapter(this);
        ArrayList<Liga> ligas = LigaController.obtenerLigas();
        if(ligas != null){
            actualizarLigaAdapter.setListaLigas(ligas);
        }
        rv_ActualizarLiga.setAdapter(actualizarLigaAdapter);
        rv_ActualizarLiga.setLayoutManager(new LinearLayoutManager(this));
    }

    public void actualizarLiga(View view) {
        String nombreLiga = String.valueOf(edt_actualizarLigaNombre.getText());
        ArrayList<Liga> ligas = LigaController.obtenerLigasBusqueda(nombreLiga);
        if (ligas == null){
            edt_actualizarLigaNombre.setError("Debes rellenar este campo");
            return;
        }
        if (ligas.size() != 1){
            edt_actualizarLigaNombre.setError("Debes hacer una busqueda más especifica");
            return;
        }
        for (Liga l : ligas) {
            nombreLiga = l.getNombreLiga();
        }
        Intent intent = new Intent(this, ActualizarLigaActivity2.class);
        intent.putExtra(NOMBRE_LIGA, nombreLiga);
        startActivity(intent);
    }

    public void busquedaLigaActualizar(View view) {
        String filtro = String.valueOf(edt_actualizarLigaNombre.getText());
        actualizarLigaAdapter = new ListaLigasAdapter(this);
        ArrayList<Liga> ligas = LigaController.obtenerLigasBusqueda(filtro);
        Log.i("ligas",String.valueOf(ligas.size()));
        if(ligas != null){
            actualizarLigaAdapter.setListaLigas(ligas);
        }
        else {
            edt_actualizarLigaNombre.setError("No se existe la liga introducida");
        }
        rv_ActualizarLiga.setAdapter(actualizarLigaAdapter);
        rv_ActualizarLiga.setLayoutManager(new LinearLayoutManager(this));
    }

    public void volverAjustes(View view) {
        Intent intent = new Intent(this, AjustesActivity.class);
        startActivity(intent);
    }
}