package com.nice.batallanaval.model;

public class Coordenada {
    private String fila;
    private String columna;
    private String estado;

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }



    public String getFila() {
        return fila;
    }

    public void setFila(String fila) {
        this.fila = fila;
    }

    public String getColumna() {
        return columna;
    }

    public void setColumna(String columna) {
        this.columna = columna;
    }

    @Override
    public String toString() {
        return "Coordenada{" +
                "fila='" + fila + '\'' +
                ", columna='" + columna + '\'' +
                ", estado='" + estado + '\'' +
                '}';
    }
}
