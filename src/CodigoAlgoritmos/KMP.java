package CodigoAlgoritmos;

import java.util.ArrayList;

public class KMP {

    //Declaracion de variables
    private ArrayList<Integer> posicionesDeCoincidencias = new ArrayList<Integer>();
    private ArrayList<String> resultadosBusqueda = new ArrayList<String>();
    private ArrayList<String> resultadosFinal = new ArrayList<String>();
    private int numCoincidencias;
    private int coincidenciaActual;
    private String[] lineas;
    private String[] patrones;

    //Metodo constructor de la clase KMP
    public KMP(String[] lineas, String... patrones) {
        this.lineas = lineas;
        this.patrones = patrones;
    }

    //Compara si existen coincidenciasPos y llama al metodo de impresion
    public void implementacion() {
        for (int j = 0; j < patrones.length; j++) {

            String r = "";

            for (int i = 0; i < lineas.length; i++) {
                if (kmp(lineas[i], patrones[j]) == true) {
                    imprimirCoincidencias((i + 1), patrones[j]);
                }
            }

            r = "\nLas coincidencias encontradas para el patron " + patrones[j] + " fueron las siguientes: \n";
            resultadosFinal.add(r);
            resultadosFinal.addAll(resultadosBusqueda);
            resultadosBusqueda.clear();
            coincidenciaActual = 0;
//            numCoincidencias = 0;

        }
    }

    //Logica de kmp
    public boolean kmp(String linea, String patron) {

        boolean aux = false;

        int longTexto = linea.length();
        int longPatron = patron.length();
        int[] tablaDeFallo = GenerarTablaDeFallo(patron);
        int indiceTexto = 0, indicePatron = 0;
        while (indiceTexto < longTexto) {
            if (linea.charAt(indiceTexto) == patron.charAt(indicePatron)) {
                if (indicePatron == longPatron - 1) {
                    posicionesDeCoincidencias.add(indiceTexto - longPatron + 1);
                    indicePatron = 0;
                    numCoincidencias++;
                    aux = true;
                }
                indiceTexto++;
                indicePatron++;
            } else if (indicePatron > 0) {
                indicePatron = tablaDeFallo[indicePatron - 1];
            } else {
                indiceTexto++;
            }
        }
        return aux;
    }

    //Se crea la tabla de fallo necesaria para kmp
    private static int[] GenerarTablaDeFallo(String patron) {
        int longPatron = patron.length();
        int[] tablaDeFallo = new int[patron.length()];
        for (int i = 0; i <= longPatron - 1; i++) {
            tablaDeFallo[i] = 0;
        }
        int posicionActual = 1;
        int posicionCandidato = 0;
        while (posicionActual < longPatron) {
            if (patron.charAt(posicionActual) == patron.charAt(posicionCandidato)) {
                tablaDeFallo[posicionActual] = posicionCandidato + 1;
                posicionActual++;
                posicionCandidato++;
            } else if (posicionCandidato > 0) {
                posicionCandidato = tablaDeFallo[posicionCandidato - 1];
            } else {
                posicionActual++;
            }
        }
        return tablaDeFallo;
    }

    //Impresion de las coincidenciasPos encontradas
    private void imprimirCoincidencias(int numlinea, String patron) {
        for (int i = 0; i < posicionesDeCoincidencias.size(); i++) {
            coincidenciaActual++;
            resultadosBusqueda.add("Coincidencia " + coincidenciaActual
                    + " en la linea " + numlinea + " en la posicion " + (posicionesDeCoincidencias.get(i) + 1));
        }
        posicionesDeCoincidencias.clear();
    }

    //Devuelve un String de resultados obtenidos
    public String getResultados() {

        String s = "";

        for (int i = 0; i < resultadosFinal.size(); i++) {

            s = s + "\n" + resultadosFinal.get(i);

        }

        return s;
    }

    //Devuelve el tamaño total del ArrayList de resultados
    public int getTamañoResultados() {
        return resultadosFinal.size();
    }

    //Devuelve el total de coincidencias encontradas
    public int getContador() {
        return numCoincidencias;
    }
}
