package com.nice.batallanaval.model;

public class TipoBarco {

    private Integer tamano;
    private String tipoBarco;

    public TipoBarco(Integer tamano,String tipoBarco){
        this.tamano=tamano;
        this.tipoBarco=tipoBarco;
    }

    public Integer getTamano() {
        return tamano;
    }

    public void setTamano(Integer tamano) {
        this.tamano = tamano;
    }

    public String getTipoBarco() {
        return tipoBarco;
    }

    public void setTipoBarco(String tipoBarco) {
        this.tipoBarco = tipoBarco;
    }

    @Override
    public String toString() {
        return "TipoBarco{" +
                "tamano=" + tamano +
                ", tipoBarco='" + tipoBarco + '\'' +
                '}';
    }
}
