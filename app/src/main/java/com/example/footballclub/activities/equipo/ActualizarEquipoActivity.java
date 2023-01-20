package com.example.footballclub.activities.equipo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.footballclub.R;
import com.example.footballclub.activities.AjustesActivity;
import com.example.footballclub.activities.ListaEquiposAdapter;
import com.example.footballclub.clases.Equipo;
import com.example.footballclub.controladores.EquipoController;

import java.util.ArrayList;

public class ActualizarEquipoActivity extends AppCompatActivity {

    public static final String NOMBRE_EQUIPO = "comexamplefootballclubactivitiesequiponombreequipo";
    private EditText edt_actualizarNombreEquipo;
    private RecyclerView rv_ActualizarEquipo;
    private ListaEquiposAdapter actualizarEquiposAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actualizar_equipo);
        edt_actualizarNombreEquipo = (EditText) findViewById(R.id.edt_actualizarNombreEquipo);
        rv_ActualizarEquipo = (RecyclerView) findViewById(R.id.rv_actualizarEquipo);

        actualizarEquiposAdapter = new ListaEquiposAdapter(this);

        rv_ActualizarEquipo.setAdapter(actualizarEquiposAdapter);
        rv_ActualizarEquipo.setLayoutManager(new LinearLayoutManager(this));
    }

    public void actualizarEquipo(View view) {
        String nombreEquipo = String.valueOf(edt_actualizarNombreEquipo.getText());
        ArrayList<Equipo> equipos = EquipoController.obtenerEquiposBusqueda(nombreEquipo);
        Equipo equipoActualizar = null;
        if (equipos == null){
            edt_actualizarNombreEquipo.setError("Debes rellenar este campo");
            return;
        }
        if (equipos.size() != 1){
            edt_actualizarNombreEquipo.setError("Debes hacer una busqueda más especifica");
            return;
        }
        for (Equipo e: equipos) {
            nombreEquipo = e.getNombreEquipo();
        }
        Intent intent = new Intent(this,ActualizarEquipoActivity2.class);
        intent.putExtra(NOMBRE_EQUIPO, nombreEquipo);
        startActivity(intent);
    }

    public void busquedaEquipoActualizar(View view) {
        String filtro = String.valueOf(edt_actualizarNombreEquipo.getText());
        actualizarEquiposAdapter = new ListaEquiposAdapter(this);
        ArrayList<Equipo> equipos = EquipoController.obtenerEquiposBusqueda(filtro);
        if(equipos != null){
            actualizarEquiposAdapter.setListaEquipos(equipos);
        }
        else {
            edt_actualizarNombreEquipo.setError("No se existe el equipo introducido");
        }
        rv_ActualizarEquipo.setAdapter(actualizarEquiposAdapter);
        rv_ActualizarEquipo.setLayoutManager(new LinearLayoutManager(this));
    }

    public void volverAjustes(View view) {
        Intent intent = new Intent(this, AjustesActivity.class);
        startActivity(intent);
    }
}