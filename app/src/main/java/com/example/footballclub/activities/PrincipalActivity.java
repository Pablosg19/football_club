package com.example.footballclub.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

import com.example.footballclub.R;
import com.example.footballclub.clases.Equipo;
import com.example.footballclub.controladores.EquipoController;
import com.example.footballclub.modelo.EquipoDB;

import java.util.ArrayList;
import java.util.List;

public class PrincipalActivity extends AppCompatActivity {

    private Intent intent;
    private TextView usuario;
    private RecyclerView rvEquipos;
    private ListaEquiposAdapter equiposAdapter;

    private EditText edt_busqueda;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);

        usuario = (TextView) findViewById(R.id.txtnombreUsuarioIniciado);
        intent = getIntent();
        usuario.setText(intent.getStringExtra(MainActivity.USUARIO_INTRODUCIDO));
        rvEquipos = (RecyclerView) findViewById(R.id.rvEquipos);
        edt_busqueda = (EditText) findViewById(R.id.edtBuscar);

        equiposAdapter = new ListaEquiposAdapter(this);
        ArrayList<Equipo> equipos = EquipoController.obtenerEquipos();
        if(equipos != null){
            equiposAdapter.setListaEquipos(equipos);
        }
        rvEquipos.setAdapter(equiposAdapter);
        rvEquipos.setLayoutManager(new LinearLayoutManager(this));
    }

    public void cerrarSesion(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void realizarBusqueda(View view) {
        String filtro = String.valueOf(edt_busqueda.getText());
        equiposAdapter = new ListaEquiposAdapter(this);
        ArrayList<Equipo> equipos = EquipoController.obtenerEquiposBusqueda(filtro);
        if(equipos != null){
            equiposAdapter.setListaEquipos(equipos);
        }
        rvEquipos.setAdapter(equiposAdapter);
        rvEquipos.setLayoutManager(new LinearLayoutManager(this));
    }

}