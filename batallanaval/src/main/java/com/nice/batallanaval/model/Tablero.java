package com.nice.batallanaval.model;

import java.util.Arrays;
import java.util.Hashtable;
import java.util.List;

public class Tablero {
    private Hashtable<String,Object> tablero;
    private Boolean gameover;

    private String columnaLetras[];
    private String filaNumeros[];
    private Jugador jugador;
    private Integer numeroTablero;


    public Hashtable<String, Object> getTablero() {
        return tablero;
    }

    public void setTablero(Hashtable<String, Object> tablero) {
        this.tablero = tablero;
    }

    public Boolean getGameover() {
        return gameover;
    }

    public void setGameover(Boolean gameover) {
        this.gameover = gameover;
    }



    public String[] getColumnaLetras() {
        return columnaLetras;
    }

    public void setColumnaLetras(String[] columnaLetras) {
        this.columnaLetras = columnaLetras;
    }

    public String[] getFilaNumeros() {
        return filaNumeros;
    }

    public void setFilaNumeros(String[] filaNumeros) {
        this.filaNumeros = filaNumeros;
    }

    public Jugador getJugador() {
        return jugador;
    }

    public void setJugador(Jugador jugador) {
        this.jugador = jugador;
    }

    public Integer getNumeroTablero() {
        return numeroTablero;
    }

    public void setNumeroTablero(Integer numeroTablero) {
        this.numeroTablero = numeroTablero;
    }

    @Override
    public String toString() {
        return "Tablero{" +
                "tablero=" + tablero +
                ", gameover=" + gameover +
                ", columnaLetras=" + Arrays.toString(columnaLetras) +
                ", filaNumeros=" + Arrays.toString(filaNumeros) +
                ", jugador=" + jugador +
                ", numeroTablero=" + numeroTablero +
                '}';
    }
}
