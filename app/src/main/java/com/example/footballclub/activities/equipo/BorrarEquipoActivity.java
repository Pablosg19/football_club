package com.example.footballclub.activities.equipo;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.footballclub.R;
import com.example.footballclub.activities.AjustesActivity;
import com.example.footballclub.activities.ListaEquiposAdapter;
import com.example.footballclub.activities.PrincipalActivity;
import com.example.footballclub.clases.Equipo;
import com.example.footballclub.controladores.EquipoController;

import java.util.ArrayList;

public class BorrarEquipoActivity extends AppCompatActivity {

    private EditText edt_BorrarNombreEquipo;
    private RecyclerView rv_BorrarEquipo;
    private ListaEquiposAdapter borrarEquiposAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_borrar_equipo);
        edt_BorrarNombreEquipo = (EditText) findViewById(R.id.edt_borrarNombreEquipo);
        rv_BorrarEquipo = (RecyclerView) findViewById(R.id.rv_actualizarEquipo);

        borrarEquiposAdapter = new ListaEquiposAdapter(this);

        rv_BorrarEquipo.setAdapter(borrarEquiposAdapter);
        rv_BorrarEquipo.setLayoutManager(new LinearLayoutManager(this));
    }

    public void busquedaEquipoBorrar(View view) {
        String filtro = String.valueOf(edt_BorrarNombreEquipo.getText());
        borrarEquiposAdapter = new ListaEquiposAdapter(this);
        ArrayList<Equipo> equipos = EquipoController.obtenerEquiposBusqueda(filtro);
        if(equipos != null){
            borrarEquiposAdapter.setListaEquipos(equipos);
        }
        else {
            edt_BorrarNombreEquipo.setError("No se existe el equipo introducido");
        }
        rv_BorrarEquipo.setAdapter(borrarEquiposAdapter);
        rv_BorrarEquipo.setLayoutManager(new LinearLayoutManager(this));
    }

    public void borrarEquipo(View view) {
        String nombreEquipo = String.valueOf(edt_BorrarNombreEquipo.getText());
        ArrayList<Equipo> equipos = EquipoController.obtenerEquiposBusqueda(nombreEquipo);
        if (equipos == null){
            edt_BorrarNombreEquipo.setError("Debes rellenar este campo");
            return;
        }
        if (equipos.size() != 1){
            edt_BorrarNombreEquipo.setError("Debes hacer una busqueda más especifica");
            return;
        }
        for (Equipo e : equipos) {
            nombreEquipo = e.getNombreEquipo();
        }
        AlertDialog.Builder borrarEquipo = new AlertDialog.Builder(this);
        borrarEquipo.setTitle("¿Quieres borrar el curso?");
        String finalNombreEquipo = nombreEquipo;
        borrarEquipo.setPositiveButton("Si", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which){
                boolean borrarEquipoOK = EquipoController.borrarEquipo(finalNombreEquipo);
                mostrarMensaje(borrarEquipoOK);
            }
        });
        borrarEquipo.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        borrarEquipo.show();
    }

    public void mostrarMensaje(boolean borradoOK) {
        if(borradoOK){
            Toast.makeText(this,"El equipo ha sido borrado correctamente",Toast.LENGTH_LONG).show();
            Intent intent = new Intent(this, PrincipalActivity.class);
            startActivity(intent);
        }
        else{
            Toast.makeText(this,"No se ha podido borrar el equipo introducido", Toast.LENGTH_LONG).show();
        }
    }

    public void volverAjustes(View view) {
        Intent intent = new Intent(this, AjustesActivity.class);
        startActivity(intent);
    }
}