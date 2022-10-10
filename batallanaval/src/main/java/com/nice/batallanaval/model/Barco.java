package com.nice.batallanaval.model;

import java.util.Hashtable;
import java.util.List;

public class Barco {

    private String tipoBarco;
    private Integer tamano;
    private Hashtable<String,String> coordenada;
    private Boolean hundido = false;

    public Barco(Integer tamano,Boolean hundido){
        this.tamano = tamano;
        this.hundido=hundido;
    }
    public Barco(){

    }

    public String getTipoBarco() {
        return tipoBarco;
    }

    public void setTipoBarco(String tipoBarco) {
        this.tipoBarco = tipoBarco;
    }

    public Integer getTamano() {
        return tamano;
    }

    public void setTamano(Integer tamano) {
        this.tamano = tamano;
    }

    public Hashtable<String, String> getCoordenada() {
        return coordenada;
    }

    public void setCoordenada(Hashtable<String, String> coordenada) {
        this.coordenada = coordenada;
    }

    public Boolean getHundido() {
        return hundido;
    }

    public void setHundido(Boolean hundido) {
        this.hundido = hundido;
    }


    @Override
    public String toString() {
        return "Barco{" +
                "tipoBarco='" + tipoBarco + '\'' +
                ", tamano=" + tamano +
                ", coordenada=" + coordenada +
                ", hundido=" + hundido +
                '}';
    }
}
