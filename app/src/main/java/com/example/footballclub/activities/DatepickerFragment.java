package com.example.footballclub.activities;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.widget.DatePicker;

import androidx.fragment.app.DialogFragment;

import com.example.footballclub.activities.liga.AnadirLigaActivity;

import java.util.Calendar;

public class DatepickerFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener{
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final Calendar c = Calendar.getInstance();
        int anyo = c.get(Calendar.YEAR);
        int mes = c.get(Calendar.MONTH);
        int dia = c.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog dpd1 = new DatePickerDialog(getActivity(),this,anyo, mes, dia);
        return dpd1;

    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        AnadirLigaActivity fechaInicio = (AnadirLigaActivity) getActivity();
        fechaInicio.crearFecha(year, month, dayOfMonth);
    }
}
