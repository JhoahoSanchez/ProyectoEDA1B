package CodigoAlgoritmos;

import java.util.ArrayList;
import java.util.HashMap;

public class BoyerMoore {

    //Declaracion de variables
    private HashMap<Character, Integer> CarIncorrecto = new HashMap<Character, Integer>();
    private HashMap<Integer, Integer> CarBueno = new HashMap<Integer, Integer>();
    private ArrayList<Integer> posicionesCoincidencias = new ArrayList<Integer>();
    private ArrayList<String> resultadosBusqueda = new ArrayList<String>();
    private ArrayList<String> resultadosFinal = new ArrayList<String>();
    private int numCoincidencias = 0;
    private int coincidenciaActual;
    private String[] lineas;
    private String[] patrones;

    //Metodo constructor de la clase Boyer Moore
    public BoyerMoore(String[] lineas, String... patrones) {
        this.lineas = lineas;
        this.patrones = patrones;
    }

    //Compara si existen coincidenciasPos y llama al metodo de impresion
    public void implementacion() {
        for (int j = 0; j < patrones.length; j++) {

            String r = "";

            for (int i = 0; i < lineas.length; i++) {
                if (algoritmoBoyerMoore(lineas[i], patrones[j]) == true) {
                    imprimirCoincidencias((i + 1), patrones[j]);
                }
            }

            r = "\nLas coincidencias encontradas para el patron " + patrones[j] + " fueron las siguientes: \n";
            resultadosFinal.add(r);
            resultadosFinal.addAll(resultadosBusqueda);
            resultadosBusqueda.clear();
            coincidenciaActual = 0;
            //numCoincidencias = 0;

        }
    }

    //Logica de Boyer Moore
    private boolean algoritmoBoyerMoore(String linea, String patron) {

        boolean aux = false;
        char[] linea_arre = linea.toCharArray();
        char[] patron_arre = patron.toCharArray();
        crearTablaD1(patron_arre);
        crearTablaD2(patron_arre);

        for (int i = 0; i < (linea_arre.length - patron_arre.length + 1); i++) {
            int aux1 = patron_arre.length;
            int primer_caracter = i + patron_arre.length - 1;
            for (int j = patron_arre.length - 1; j >= 0; j--) {
                if (linea_arre[j + i] == patron_arre[j]) {
                    aux1--;
                } else {
                    Integer sum = Math.max(CarBueno.getOrDefault(j, 0), CarIncorrecto.getOrDefault(linea_arre[primer_caracter], patron_arre.length) - 1);
                    i += sum;
                    break;
                }
            }
            if (aux1 == 0) {
                posicionesCoincidencias.add(i);
                numCoincidencias++;
                aux = true;
            }
        }
        return aux;
    }

    //Se crea la tabla D1
    public void crearTablaD1(char[] patron) {
        for (int i = 0; i < patron.length; i++) {
            CarIncorrecto.put(patron[i], patron.length - i - 1);
        }
    }

    //Se crea la tabla D2
    public void crearTablaD2(char[] patron) {
        String p = new String(patron);
        String parteI, parteD;
        int index;
        for (int i = patron.length - 2; i > 0; i--) {
            for (int j = i + 1; j < p.length(); j++) {
                parteI = p.substring(0, i);
                parteD = p.substring(j, p.length());
                index = parteI.lastIndexOf(parteD.substring(0, 1));
                if (index != -1) {
                    if (p.substring(index, index + parteD.length()).equals(parteD)) {
                        CarBueno.putIfAbsent(i, j - index);
                    }
                    break;
                }
            }
            CarBueno.putIfAbsent(i, p.length());
        }
    }

    //Impresion de las coincidenciasPos encontradas
    private void imprimirCoincidencias(int numlinea, String patron) {
        for (int i = 0; i < posicionesCoincidencias.size(); i++) {
            coincidenciaActual++;
            resultadosBusqueda.add("Coincidencia " + coincidenciaActual
                    + " en la linea " + numlinea + " en la posicion " + (posicionesCoincidencias.get(i) + 1));
        }
        posicionesCoincidencias.clear();
    }

    //Devuelve un string con los resultados obtenidos
    public String getResultados() {

        String s = "";

        for (int i = 0; i < resultadosFinal.size(); i++) {

            s = s + "\n" + resultadosFinal.get(i);

        }

        return s;
    }

    //Devuelve el tamaño del ArrayList de resultados
    public int getTamañoResultados() {
        return resultadosFinal.size();
    }

    //Devuelve el total de coincidencias encontradas
    public int getContador() {
        return numCoincidencias;
    }

}
