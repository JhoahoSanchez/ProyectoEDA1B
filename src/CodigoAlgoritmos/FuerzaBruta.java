package CodigoAlgoritmos;

import java.util.ArrayList;

public class FuerzaBruta {

    //Declaracion de variables
    private ArrayList<String> coincidenciasPos = new ArrayList<String>();
    private ArrayList<String> resultadosBusqueda = new ArrayList<String>();
    private ArrayList<String> resultadosFinal = new ArrayList<String>();
    private String[] lineas;
    private String[] patrones;
    private int actualCoincidencias = 0;
    private int totalCoincidencias = 0;

    //Metodo construtor
    public FuerzaBruta(String[] lineas, String... patrones) {

        this.lineas = lineas;
        this.patrones = patrones;

    }

    //Compara si existen coincidenciasPos y llama al metodo de impresion
    public void implementacion() {

        for (int j = 0; j < patrones.length; j++) {

            String r = "";

            for (int i = 0; i < lineas.length; i++) {

                if (FuerzaBruta(lineas[i].toCharArray(), patrones[j].toCharArray()) == true) {
                    imprimirCoincidencias((i + 1), patrones[j]);
                }
            }

            r = "\nLas coincidencias encontradas para el patron " + patrones[j] + " fueron las siguientes: \n";
            resultadosFinal.add(r);
            resultadosFinal.addAll(resultadosBusqueda);
            resultadosBusqueda.clear();
            actualCoincidencias = 0;
//            totalCoincidencias = 0;

        }
    }

    //Logica de fuerza bruta
    public boolean FuerzaBruta(char[] linea, char[] patron) {

        boolean aux = false;

        for (int i = 0; i < (linea.length - patron.length + 1); i++) {

            int j = 0;

            while (j < patron.length && patron[j] == linea[i + j]) {

                j = j + 1;

                if (j == patron.length) {
                    coincidenciasPos.add((i + 1) + "");

                    totalCoincidencias++;
                    aux = true;
                }
            }
        }
        return aux;
    }

    //Devuelve los resultados con un String
    public String getResultados() {

        String s = "";

        for (int i = 0; i < resultadosFinal.size(); i++) {

            s = s + "\n" + resultadosFinal.get(i);

        }

        return s;
    }

    //Devuelve el tamaño total de resultadosFinal
    public int getTamanioResultados() {
        return resultadosFinal.size();
    }

    //Devuelve el total de coincidencias encontradas
    public int getContador() {
        return totalCoincidencias;
    }

    //Añade las coincidencias a un ArrayList para hacer uso de este luego
    private void imprimirCoincidencias(int numlinea, String patron) {

        for (int i = 0; i < coincidenciasPos.size(); i++) {
            actualCoincidencias++;
            resultadosBusqueda.add("Coincidencia " + actualCoincidencias
                    + " en la linea " + numlinea + " en la posicion " + coincidenciasPos.get(i));
        }
        coincidenciasPos.clear();
    }
}
