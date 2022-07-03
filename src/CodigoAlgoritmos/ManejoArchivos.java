package CodigoAlgoritmos;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;

public class ManejoArchivos {

    //Declaracion de variables
    private BufferedReader archivo;
    private String nombre;
    private ArrayList<String> lineasTexto = new ArrayList<String>();
    private ArrayList<String> patrones = new ArrayList<String>();

    //Metodo constructor de la clase ManejoArchivos
    public void setArchivo(BufferedReader archivo) {
        this.archivo = archivo;
    }

    //Setter para el atributo de nombre
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    //Comprueba la extension del nombre del archivo para poder decidir que logica se va a utilizar
    public int comprobarExtensionArchivo(String nombre) {

        String nombreLC = nombre.toLowerCase();

        if (nombreLC.endsWith(".txt")) {
            return 0;
        } else if (nombreLC.endsWith(".csv")) {
            return 1;
        } else {
            return -1;
        }
    }

    //Lee el patron dependiendo de la extension que tenga el archivo
    public void LeerPatron() {
        int aux = comprobarExtensionArchivo(this.nombre);
        String lineaTexto = "";
        try {
            if (aux == 0 || aux == 1) {

                //archivo txt, los patrones deben estar separados por comas y nuevas lineas
                while ((lineaTexto = archivo.readLine()) != null) {

                    String[] pat = lineaTexto.split(",");

                    for (int i = 0; i < pat.length; i++) {
                        patrones.add(pat[i]);
                    }

                }

            } else {
                //TODO: mostrar mensaje de que el tipo de archivo es incorrecto
            }
            archivo.close();
        } catch (IOException ex) {

        }

    }

    //Lee el texto en el cual se desea buscar y lo almacena en un ArrayList
    public void LeerTexto() {

        String lineaTexto = "";
        try {
            while ((lineaTexto = archivo.readLine()) != null) {

                lineasTexto.add(lineaTexto);
            }
            archivo.close();
        } catch (IOException ex) {

        }
    }

    //Devuelve un arreglo de string llenado con el texto procesado
    public String[] getLineasTexto() {

        LeerTexto();
        String[] lineas = lineasTexto.toArray(new String[0]);

        return lineas;
    }

    //Devuelve un arreglo de strings lleno con los patrones procesados
    public String[] getPatrones() {

        LeerPatron();
        String[] patron = patrones.toArray(new String[0]);

        return patron;
    }

}
