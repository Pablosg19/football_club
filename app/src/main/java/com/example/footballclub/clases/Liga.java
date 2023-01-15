package com.example.footballclub.clases;

import java.sql.Date;
import java.util.Objects;

public class Liga {
    // Atributos
    private int idLiga;
    private String nombreLiga;
    private String paisLiga;
    private Date fechaInicio;
    // Constructor
    public Liga(int idLiga, String nombreLiga, String paisLiga, Date fechaInicio) {
        this.idLiga = idLiga;
        this.nombreLiga = nombreLiga;
        this.paisLiga = paisLiga;
        this.fechaInicio = fechaInicio;
    }
    // Getters y Setters
    public int getIdLiga() {
        return idLiga;
    }

    public void setIdLiga(int idLiga) {
        this.idLiga = idLiga;
    }

    public String getNombreLiga() {
        return nombreLiga;
    }

    public void setNombreLiga(String nombreLiga) {
        this.nombreLiga = nombreLiga;
    }

    public String getPaisLiga() {
        return paisLiga;
    }

    public void setPaisLiga(String paisLiga) {
        this.paisLiga = paisLiga;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }
    // HasCode and Equals
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Liga)) return false;
        Liga liga = (Liga) o;
        return idLiga == liga.idLiga;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idLiga);
    }
}
