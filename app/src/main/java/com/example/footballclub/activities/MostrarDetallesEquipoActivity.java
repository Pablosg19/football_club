package com.example.footballclub.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.footballclub.R;
import com.example.footballclub.bitmap.ImagenesBlobBitmap;
import com.example.footballclub.clases.Equipo;
import com.example.footballclub.clases.Liga;
import com.example.footballclub.controladores.LigaController;
import com.example.footballclub.modelo.ConfiguracionDB;

import java.util.ArrayList;

public class MostrarDetallesEquipoActivity extends AppCompatActivity {

    TextView txt_mostrar_nombreEquipo;
    TextView txt_mostrar_ciudadEquipo;
    TextView txt_mostrar_numTitulos;
    TextView txt_mostrar_ligaEquipo;
    ImageView img_mostrarFotoEquipo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mostrar_detalles_equipo);
        txt_mostrar_nombreEquipo = (TextView) findViewById(R.id.txt_mostrar_nombreEquipo);
        txt_mostrar_ciudadEquipo = (TextView) findViewById(R.id.txt_mostrar_ciudadEquipo);
        txt_mostrar_numTitulos = (TextView) findViewById(R.id.txt_mostrar_numTitulos);
        txt_mostrar_ligaEquipo = (TextView) findViewById(R.id.txt_mostrar_ligaEquipo);
        img_mostrarFotoEquipo = (ImageView) findViewById(R.id.img_Equipo);
        Intent intent = getIntent();
        if (intent != null){
            Equipo e = (Equipo) intent.getParcelableExtra(EquipoViewHolder.EXTRA_OBJETO_EQUIPO);
            byte[] foto = intent.getByteArrayExtra(EquipoViewHolder.EXTRA_OBJETO_IMG_EQUIPO);
            if (foto != null){
                Bitmap fotoEquipo = ImagenesBlobBitmap.bytes_to_bitmap(foto, ConfiguracionDB.ALTO_IMAGENES_BITMAP,ConfiguracionDB.ANCHO_IMAGENES_BITMAP);
                img_mostrarFotoEquipo.setImageBitmap(fotoEquipo);
            }
            else{
                img_mostrarFotoEquipo.setImageResource(R.mipmap.ic_launcher_logo);
            }
            txt_mostrar_nombreEquipo.setText(e.getNombreEquipo());
            txt_mostrar_ciudadEquipo.setText("Ciudad: " + e.getCiudadEquipo());
            txt_mostrar_numTitulos.setText("Número de titulos: " + e.getNumTitulos());
            String liga = null;
            ArrayList<Liga> ligas = LigaController.obtenerLigas();
            for (Liga l : ligas) {
                if (e.getIdLiga() == l.getIdLiga()){
                    liga = l.getNombreLiga();
                }
            }
            txt_mostrar_ligaEquipo.setText("Liga: " + liga);
        }
    }

    public void volverActivityPrincipal(View view) {
        Intent intent = new Intent(this, PrincipalActivity.class);
        startActivity(intent);
    }
}