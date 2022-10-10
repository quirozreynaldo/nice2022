package com.nice.batallanaval.bl;

import com.nice.batallanaval.model.Barco;
import com.nice.batallanaval.model.Jugador;
import com.nice.batallanaval.model.Tablero;
import com.nice.batallanaval.model.TipoBarco;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.Hashtable;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;


public class CampoBatalla {
    private Jugador jugador1 = new Jugador();
    private Tablero tablero1 = new Tablero();
    private Hashtable<String, Barco> listaBarcos1 = new Hashtable<>();

    private Jugador jugador2 = new Jugador();
    private Tablero tablero2 = new Tablero();
    private Hashtable<String, Barco> listaBarcos2 = new Hashtable<>();
    private String quienJuega ="jugador1";
    Integer contador1=17;
    Integer contador2=17;
    Scanner teclado = new Scanner(System.in);
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_RESET = "\u001B[0m";
    public void imprimeinstruccion(){
        System.out.println(" ");

        System.out.println(" PORTAAVIONES= p [posiciones=5]");
        System.out.println(" ACORAZADO   = a [posiciones=4]");
        System.out.println(" CRUCERO     = c [posiciones=3]");
        System.out.println(" SUBMARINO   = s [posiciones=3]");
        System.out.println(" DESTRUCTOR  = d [posiciones=2]");
        System.out.println(" ");
        System.out.println(" Introduzca fila del 1..10 y columna de la A..J: 1A");
        System.out.println(" ");
    }


    public void inicializaJuego() {
        clearConsole();
        System.out.println(ANSI_GREEN+"+----------------------------+");
        System.out.println("|    BATALLA NAVAL NICE      |");
        System.out.println("| Autor: R. Mariana Mogro R. |");
        System.out.println("+----------------------------+"+ANSI_RESET);
        imprimeinstruccion();
        creaJugadores();
        creaTablero1(10,10);
        creaTablero2(10,10);
        listaBarcos1 = creaListaDeBarcos();
        listaBarcos2 = creaListaDeBarcos();
        tablero1.setJugador(jugador1);
        tablero2.setJugador(jugador2);
        creaTablerConBarcos(tablero1,listaBarcos1);
        creaTablerConBarcos(tablero2,listaBarcos2);
        archivoCoodenadas(listaBarcos1);
        imprimeTablero(tablero1);
    }
    public void creaJugadores(){
        String nombreJugador1 ="Jugador1";
        String nombreJugador2 ="jugador2";
        Scanner teclado = new Scanner(System.in);
        System.out.print("Escriba nombre del jugador 1: ");
        nombreJugador1 = teclado.nextLine();
        System.out.print("Escriba nombre del jugador 2: ");
        nombreJugador2 = teclado.nextLine();
        jugador1.setNombre(nombreJugador1);
        jugador2.setNombre(nombreJugador2);
    }
    public void controlHundido (Barco barco){
        Boolean estahudido= true;
        for (Map.Entry<String, String> stringStringEntry : barco.getCoordenada().entrySet()) {
            if (stringStringEntry.getValue().equalsIgnoreCase("activo")){
                estahudido=false;
                break;
            }

        }
        if (estahudido==true){
            barco.setHundido(true);
        }

    }
    public String juega1(String coordenada){
        /* -----------JUGADOR 1------------------------*/
        System.out.print( jugador1.getNombre()+ " escribe la coordenada: ");
        coordenada = teclado.nextLine();
        coordenada = coordenada.toUpperCase();
        coordenada = formatoCoordenada(coordenada);
        Object object = tablero1.getTablero().get(coordenada);
        if (object instanceof String) {
            tablero1.getTablero().put(coordenada, "*");
            quienJuega="jugador2";
        } else if (object instanceof Barco) {
            if (((Barco) object).getCoordenada().get(coordenada).equalsIgnoreCase("activo")){
                contador1--;
                ((Barco) object).getCoordenada().put(coordenada, "inactivo");
                controlHundido((Barco) object);
                //quienJuega="jugador1";
                quienJuega="jugador2";
            }else{
                quienJuega="jugador2";
            }


            if (contador1==0){
                imprimeTablero(tablero1);
                System.out.println(ANSI_GREEN+"*************************");
                System.out.println("     JUEGO FINALIZADO    ");
                System.out.println("     GANADOR: "+jugador1.getNombre());
                System.out.println("*************************"+ANSI_RESET);
                teclado.nextLine();
                coordenada="salir";
               // break;
            }
        }else{
            System.out.println("Coordenada fuera de tablero vuelva a introducir");

            try {
                TimeUnit.SECONDS.sleep(3L);
            }catch (Exception ex){

            }

        }
        imprimeTablero(tablero1);
        System.out.println("** Presiona Enter **");
        teclado.nextLine();
        if (quienJuega.equalsIgnoreCase("jugador2")){
            imprimeTablero(tablero2);
        }else{
            imprimeTablero(tablero1);
        }

      return coordenada;
    }
    public String juega2(String coordenada){
        /* -----------JUGADOR 2------------------------*/
        System.out.print( jugador2.getNombre()+ " escribe la coordenada: ");
        coordenada = teclado.nextLine();
        coordenada = coordenada.toUpperCase();
        coordenada = formatoCoordenada(coordenada);
        Object object2 = tablero2.getTablero().get(coordenada);
        if (object2 instanceof String) {
            tablero2.getTablero().put(coordenada, "*");
            quienJuega="jugador1";
        } else if (object2 instanceof Barco) {

            if (((Barco) object2).getCoordenada().get(coordenada).equalsIgnoreCase("activo")){
                contador2--;
                ((Barco) object2).getCoordenada().put(coordenada, "inactivo");
                controlHundido((Barco) object2);
                quienJuega="jugador1";
            }else{
                quienJuega="jugador1";
            }
            if (contador2==0){
                imprimeTablero(tablero2);
                System.out.println("game over");
                System.out.println("ganaste: "+jugador2.getNombre());
                teclado.nextLine();
                coordenada="salir";
               // break;
            }
        }else{
            System.out.println("Coordenada fuera de tablero vuelva a introducir");
            try {
                TimeUnit.SECONDS.sleep(3L);
            }catch (Exception ex){

            }

        }

        imprimeTablero(tablero2);
        System.out.println("** Presiona Enter **");
        teclado.nextLine();
        if (quienJuega.equalsIgnoreCase("jugador2")){
            imprimeTablero(tablero2);
        }else{
            imprimeTablero(tablero1);
        }
        return coordenada;
    }

    public void iniciaBatalla(){
        String coordenada="";

        do {

            switch (quienJuega) {

                case "jugador1":
                     coordenada=juega1(coordenada);
                    break;

                case "jugador2":
                    coordenada=juega2(coordenada);
                    break;

            }
            if (coordenada.equalsIgnoreCase("salir")){
                break;
            }

        } while(!coordenada.equalsIgnoreCase("salir"));
    }
    public String formatoCoordenada(String coordenada){
        String invertido= "";
        char [] cadena_div =coordenada.toCharArray();
        String numero="";
        String letra="";
        for (char a:cadena_div){
            if(Character.isDigit(a)){
                numero+=a;
            }else{
                letra+=a;
            }
        }
        invertido=numero+letra;
        return invertido;
    }


    public void creaTablero1(Integer tamanoFilas, Integer tamanoColunas) {
        Hashtable<String, Object> tableroHash = new Hashtable<>();
        String columnaLetras[] = letras(tamanoColunas);
        String filaNumeros[] = numeros(tamanoFilas);

        for (String columna : columnaLetras) {
            for (String fila : filaNumeros) {
                tableroHash.put(fila + columna, " ");
            }
        }
        tablero1.setTablero(tableroHash);
        tablero1.setGameover(false);
        tablero1.setColumnaLetras(columnaLetras);
        tablero1.setFilaNumeros(filaNumeros);
        tablero1.setNumeroTablero(1);
    }
    public void creaTablero2(Integer tamanoFilas, Integer tamanoColunas) {
        Hashtable<String, Object> tableroHash = new Hashtable<>();
        String columnaLetras[] = letras(tamanoColunas);
        String filaNumeros[] = numeros(tamanoFilas);

        for (String columna : columnaLetras) {
            for (String fila : filaNumeros) {
                tableroHash.put(fila + columna, " ");
            }
        }
        tablero2.setTablero(tableroHash);
        tablero2.setGameover(false);
        tablero2.setColumnaLetras(columnaLetras);
        tablero2.setFilaNumeros(filaNumeros);
        tablero2.setNumeroTablero(2);
    }

    public void creaTablerConBarcos(Tablero tablero, Hashtable<String, Barco> listaBarcos) {
        for (Map.Entry<String, Barco> stringBarcoEntry : listaBarcos.entrySet()) {
            tablero.getTablero().put(stringBarcoEntry.getKey(),stringBarcoEntry.getValue());
        }

    }

    private Hashtable<String, Barco> creaListaDeBarcos() {
        Hashtable<String, Barco> listaBarcos = new Hashtable<>();
        TipoBarco barcos[] = {new TipoBarco(5, "portaaviones"), new TipoBarco(4, "acorazado"), new TipoBarco(3, "crucero"), new TipoBarco(3, "submarino"), new TipoBarco(2, "destructor")};
        for (TipoBarco tipoBarco : barcos) {
            Barco barco = new Barco();
            barco.setTamano(tipoBarco.getTamano());
            barco.setTipoBarco(tipoBarco.getTipoBarco());
            Hashtable<String, String> coordenadasDelBarco = new Hashtable<>();

            String coordenasBarco[] = null;
            Boolean coordenaRepetida=false;

            for (int i = 1; i < 1000; i++) {
                coordenasBarco = coordenadas(tipoBarco.getTamano());
                 coordenaRepetida = false;
                for (String coordenadaBarco : coordenasBarco) {
                    if (listaBarcos.containsKey(coordenadaBarco)) {
                        coordenaRepetida = true;
                        break;
                    }
                }
                if (coordenaRepetida == false) {
                    break;
                }
            }

            for (String filaColumna : coordenasBarco) {
                coordenadasDelBarco.put(filaColumna, "activo");
            }
            barco.setCoordenada(coordenadasDelBarco);
            for (String filaColumna : coordenasBarco) {
                listaBarcos.put(filaColumna, barco);
            }
        }
        return listaBarcos;
    }

    public int getRandomNumber(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }

    public String getRandomStrinf() {
        String list[] = new String[1];
        String[] strArr = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J"};
        Random rand = new Random();
        int res = rand.nextInt(strArr.length);
        list[0] = strArr[res];
        return list[0];
    }

    public String[] coordenadas(Integer numero) {
        String list[] = new String[1];
        String lista[] = new String[numero];
        String[] strArr = {"H", "V"};
        String letra = getRandomStrinf();
        Random rand = new Random();
        int res = rand.nextInt(strArr.length);
        list[0] = strArr[res];

        if (list[0].equals("H")) {// H=horizontal
            Integer indice = getRandomNumber(1, 10);
            char myChar = 'a';
            int valor_letra = getRandomNumber(65, 74- numero);
            for (int i = 0; i < numero; i++) {
                int valorLetra = (valor_letra + i);
                myChar = (char) valorLetra;
                lista[i] = indice + String.valueOf(myChar);
            }
        } else {
        //    "es vertical numeros"
            Integer indice = getRandomNumber(1, numero);
            for (int i = 0; i < numero; i++) {
                lista[i] = indice + letra;
                indice++;
            }
        }
        return lista;

    }



    public void imprimeTablero(Tablero tablero) {

        clearConsole();
        System.out.println("NOMBRE DEL JUGADOR: "+ANSI_GREEN+"[ "+tablero.getJugador().getNombre()+ " ]"+ANSI_RESET);
        String columnaLetras[] = tablero.getColumnaLetras();
        String filaNumeros[] = tablero.getFilaNumeros();
        System.out.print("|" + "  ");
        for (int i = 0; i < columnaLetras.length; i++) {
            System.out.print("|  " + columnaLetras[i] + "  ");
        }
        System.out.print("\n");
        for (String fila : filaNumeros) {
            System.out.print( fila.equals("10")? "|" + fila:"| " + fila);
            for (String columna : columnaLetras) {
                String cerox = "";
                try {
                    Object object = tablero.getTablero().get(fila + columna);
                    if (object instanceof String) {
                        cerox = " " + (String) tablero.getTablero().get(fila + columna) + "  ";

                    } else if (object instanceof Barco) {


                        if (((Barco) object).getCoordenada().containsKey(fila+columna) &&((Barco) object).getCoordenada().get(fila+columna).equalsIgnoreCase("inactivo") ){
                            //cerox =" X  ";
                            //System.out.println(((Barco) object).getCoordenada().toString());
                            if (((Barco) object).getHundido() == true) {
                                cerox =ANSI_RED+" " +  "H"+ "  "+ ANSI_RESET;
                            }else{
                                cerox =" " +  ((Barco) object).getTipoBarco().substring(0,1)+ "  ";
                                //cerox =" " +  "X"+ "  ";
                            }

                        }else{
                            //cerox =" " +  ((Barco) object).getTipoBarco().substring(0,1)+ "  ";
                            cerox =" " +  " "+ "  ";

                        }

                    }
                } catch (Exception ex) {
                    cerox = "    ";
                }
                System.out.print("| " + cerox);
            }
            System.out.println(" ");
        }
    }

    public static String[] letras(Integer numero) {

        char myChar = 'a';
        String lista[] = new String[numero];
        for (int i = 0; i < numero; i++) {
            int valorLetra = (65 + i);
            myChar = (char) valorLetra;
            lista[i] = String.valueOf(myChar);
        }
        return lista;
    }

    public static String[] numeros(Integer numero) {
        String lista[] = new String[numero];
        for (int i = 0; i < numero; i++) {
            lista[i] = Integer.toString(i + 1);
        }
        return lista;
    }

    public  void clearConsole(){
        try{
            String operatingSystem = System.getProperty("os.name"); //Check the current operating system

            if(operatingSystem.contains("Windows")){
                ProcessBuilder pb = new ProcessBuilder("cmd", "/c", "cls");
                Process startProcess = pb.inheritIO().start();
                startProcess.waitFor();
            } else {
                ProcessBuilder pb = new ProcessBuilder("clear");
                Process startProcess = pb.inheritIO().start();

                startProcess.waitFor();
            }
        }catch(Exception e){
            System.out.println(e);
        }
    }
    private void archivoCoodenadas(Hashtable<String, Barco> listaBarcos){
        try {
            String ruta = "coordenadas.txt";
            String contenido = "Contenido de ejemplo";
            File file = new File(ruta);
            // Si el archivo no existe es creado
            if (!file.exists()) {
                file.createNewFile();
            }
            FileWriter fw = new FileWriter(file);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(listaBarcos.toString());
            bw.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
