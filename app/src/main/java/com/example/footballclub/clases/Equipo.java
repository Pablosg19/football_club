package com.example.footballclub.clases;

import android.graphics.Bitmap;
import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
import java.util.Objects;

public class Equipo implements Parcelable {
    // Atributos
    private int idEquipo;
    private String nombreEquipo;
    private String ciudadEquipo;
    private int numTitulos;
    private int idLiga;
    private Bitmap fotoEquipo;
    // Constructor
    public Equipo(int idEquipo,String nombreEquipo, String ciudadEquipo, int numTitulos, int idLiga) {
        this.idEquipo = idEquipo;
        this.nombreEquipo = nombreEquipo;
        this.ciudadEquipo = ciudadEquipo;
        this.numTitulos = numTitulos;
        this.idLiga = idLiga;
        this.fotoEquipo = null;
    }
    public Equipo(int idEquipo,String nombreEquipo, String ciudadEquipo, int numTitulos, int idLiga, Bitmap fotoEquipo) {
        this.idEquipo = idEquipo;
        this.nombreEquipo = nombreEquipo;
        this.ciudadEquipo = ciudadEquipo;
        this.numTitulos = numTitulos;
        this.idLiga = idLiga;
        this.fotoEquipo = fotoEquipo;
    }

    protected Equipo(Parcel in) {
        idEquipo = in.readInt();
        nombreEquipo = in.readString();
        ciudadEquipo = in.readString();
        numTitulos = in.readInt();
        idLiga = in.readInt();
    }

    public static final Creator<Equipo> CREATOR = new Creator<Equipo>() {
        @Override
        public Equipo createFromParcel(Parcel in) {
            return new Equipo(in);
        }

        @Override
        public Equipo[] newArray(int size) {
            return new Equipo[size];
        }
    };

    // Getters y Setters
    public int getIdEquipo() { return idEquipo; }
    public void setIdEquipo(int idEquipo) { this.idEquipo = idEquipo; }
    public String getNombreEquipo() {
        return nombreEquipo;
    }

    public void setNombreEquipo(String nombreEquipo) {
        this.nombreEquipo = nombreEquipo;
    }

    public String getCiudadEquipo() {
        return ciudadEquipo;
    }

    public void setCiudadEquipo(String ciudadEquipo) {
        this.ciudadEquipo = ciudadEquipo;
    }

    public int getNumTitulos() {
        return numTitulos;
    }

    public void setNumTitulos(int numTitulos) {
        this.numTitulos = numTitulos;
    }

    public int getIdLiga() {
        return idLiga;
    }

    public void setIdLiga(int idLiga) {
        this.idLiga = idLiga;
    }

    public Bitmap getFotoEquipo() {
        return fotoEquipo;
    }

    public void setFotoEquipo(Bitmap fotoEquipo) {
        this.fotoEquipo = fotoEquipo;
    }

    // HasCode and Equals
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Equipo)) return false;
        Equipo equipo = (Equipo) o;
        return idEquipo == equipo.idEquipo && nombreEquipo.equals(equipo.nombreEquipo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idEquipo, nombreEquipo);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(idEquipo);
        dest.writeString(nombreEquipo);
        dest.writeString(ciudadEquipo);
        dest.writeInt(numTitulos);
        dest.writeInt(idLiga);
    }
}
