package com.example.footballclub.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.footballclub.R;
import com.example.footballclub.modelo.ConfiguracionDB;

import java.sql.Connection;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void registrarActivity(View view) {
        Intent intent = new Intent(this, RegistrarActivity.class);
        startActivity(intent);
    }
}