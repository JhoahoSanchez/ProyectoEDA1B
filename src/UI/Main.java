package UI;

import CodigoAlgoritmos.*;
import java.io.BufferedReader;
import java.util.Arrays;

//En esta clase es donde se administra toda la logica del programa, 
//esta es llamada desde el menu
public class Main {

    //Declaracion de variables
    public ManejoArchivos txt = new ManejoArchivos();
    private FuerzaBruta fb;
    private KMP kmp;
    private BoyerMoore bm;
    private String[] texto;
    private String[] patrones;
    private Results rs = new Results();

    //Metodo para añadir el texto desde un archivo cargado en memoria
    public void aniadirTexto(BufferedReader bf, String nombre) {
        txt.setArchivo(bf);
        txt.setNombre(nombre);
        txt.LeerTexto();
        texto = txt.getLineasTexto();
    }

    //Metodo para añadir el texto ingresado dentro del area de texto
    public void aniadirTextoFromTA(String s) {
        texto = s.split("\n");
    }

    //Metodo para añadir el/los patrones ingresados en el area de texto
    public void aniadirPatronFromTA(String s) {
        patrones = s.split(",");
    }

    //Metodo para añador el/los patrones desde un archivo cargado en memoria
    public void aniadirPatron(BufferedReader bf, String nombre) {
        txt.setArchivo(bf);
        txt.setNombre(nombre);
        txt.LeerPatron();
        patrones = txt.getPatrones();
    }

    //Inicializa el objeto y llama al metodo de implementacion
    private void calculoFuerzaBruta() {
        fb = new FuerzaBruta(texto, patrones);
        fb.implementacion();
    }

    //Inicializa el objeto y llama al metodo de implementacion
    private void calculoKMP() {
        kmp = new KMP(texto, patrones);
        kmp.implementacion();
    }

    //Inicializa el objeto y llama al metodo de implementacion
    private void calculoBoyerMoore() {
        bm = new BoyerMoore(texto, patrones);
        bm.implementacion();
    }

    //Imprime los resultados obtenidos al utilizar fuerza bruta
    public void imprimirResultadosFB() {
        long tiempoTotal = 0;
        long tiempoInicio = System.currentTimeMillis();
        calculoFuerzaBruta();
        long tiempoFinal = System.currentTimeMillis();
        tiempoTotal = tiempoFinal - tiempoInicio;
        rs.setVisible(true);
        rs.setText(("Para el algoritmo de Fuerza Bruta se encontraron "
                + fb.getContador() + " coincidencias y la búsqueda tomó " + tiempoTotal + " milisegundos.\n"
                + fb.getResultados()), "Resultados de Fuerza Bruta:");

    }

    //Imprime los resultados obtenidos al utilizar KMP
    public void imprimirResultadosKMP() {
        long tiempoTotal = 0;
        long tiempoInicio = System.currentTimeMillis();
        calculoKMP();
        long tiempoFinal = System.currentTimeMillis();
        tiempoTotal = tiempoFinal - tiempoInicio;
        rs.setVisible(true);
        rs.setText(("Para el algoritmo de KMP se encontraron "
                + kmp.getContador() + " coincidencias y la búsqueda tomó " + tiempoTotal + " milisegundos.\n"
                + kmp.getResultados()), "Resultados de KMP: ");
    }

    //Imprime los resultados obtenidos al utilizar las tres opciones
    public void imprimirResultados3O() {

        long tiempoTotalFB = 0;
        long tiempoInicio = System.currentTimeMillis();
        calculoFuerzaBruta();
        long tiempoFinal = System.currentTimeMillis();
        tiempoTotalFB = tiempoFinal - tiempoInicio;
        long tiempoTotalKMP = 0;
        tiempoInicio = System.currentTimeMillis();
        calculoKMP();
        tiempoFinal = System.currentTimeMillis();
        tiempoTotalKMP = tiempoFinal - tiempoInicio;
        long tiempoTotalBM = 0;
        tiempoInicio = System.currentTimeMillis();
        calculoBoyerMoore();
        tiempoFinal = System.currentTimeMillis();
        tiempoTotalBM = tiempoFinal - tiempoInicio;

        rs.setVisible(true);
        if (tiempoTotalFB < tiempoTotalKMP && tiempoTotalFB < tiempoTotalBM) {
            rs.setText(("Para el algoritmo de Fuerza Bruta se encontraron "
                    + fb.getContador() + " coincidencias y la búsqueda tomó " + tiempoTotalFB + " milisegundos.\n"
                    + fb.getResultados() + "\n\nPara el algoritmo de KMP se encontraron "
                    + kmp.getContador() + " coincidencias y la búsqueda tomó " + tiempoTotalKMP + " milisegundos.\n"
                    + kmp.getResultados() + "\n\nPara el algoritmo de Boyer Moore se encontraron "
                    + bm.getContador() + " coincidencias y la búsqueda tomó " + tiempoTotalBM + " milisegundos.\n"
                    + bm.getResultados() + "\n\nPara buscar el patrón " + Arrays.toString(patrones)
                    + " el mejor algoritmo fue Fuerza Bruta"), "Resultados de los tres algoritmos: ");
        } else if (tiempoTotalBM < tiempoTotalFB && tiempoTotalBM < tiempoTotalKMP) {
            rs.setText(("Para el algoritmo de Fuerza Bruta se encontraron "
                    + fb.getContador() + " coincidencias y la búsqueda tomó " + tiempoTotalFB + " milisegundos.\n"
                    + fb.getResultados() + "\n\nPara el algoritmo de KMP se encontraron "
                    + kmp.getContador() + " coincidencias y la búsqueda tomó " + tiempoTotalKMP + " milisegundos.\n"
                    + kmp.getResultados() + "\n\nPara el algoritmo de Boyer Moore se encontraron "
                    + bm.getContador() + " coincidencias y la búsqueda tomó " + tiempoTotalBM + " milisegundos.\n"
                    + bm.getResultados() + "\n\nPara buscar el patrón " + Arrays.toString(patrones)
                    + " el mejor algoritmo fue Boyer Moore"), "Resultados de los tres algoritmos: ");
        } else if (tiempoTotalKMP < tiempoTotalBM && tiempoTotalKMP < tiempoTotalFB) {
            rs.setText(("Para el algoritmo de Fuerza Bruta se encontraron "
                    + fb.getContador() + " coincidencias y la búsqueda tomó " + tiempoTotalFB + " milisegundos.\n"
                    + fb.getResultados() + "\n\nPara el algoritmo de KMP se encontraron "
                    + kmp.getContador() + " coincidencias y la búsqueda tomó " + tiempoTotalKMP + " milisegundos.\n"
                    + kmp.getResultados() + "\n\nPara el algoritmo de Boyer Moore se encontraron "
                    + bm.getContador() + " coincidencias y la búsqueda tomó " + tiempoTotalBM + " milisegundos.\n"
                    + bm.getResultados() + "\n\nPara buscar el patrón " + Arrays.toString(patrones)
                    + " el mejor algoritmo fue KMP"), "Resultados de los tres algoritmos: ");
        } else {

        }

    }

    //Imprime los resultados obtenidos al utilizar los metodos de fuerza bruta y kmp
    public void imprimirFBvsKMP() {
        long tiempoTotalFB = 0;
        long tiempoInicio = System.currentTimeMillis();
        calculoFuerzaBruta();
        long tiempoFinal = System.currentTimeMillis();
        tiempoTotalFB = tiempoFinal - tiempoInicio;
        long tiempoTotalKMP = 0;
        tiempoInicio = System.currentTimeMillis();
        calculoKMP();
        tiempoFinal = System.currentTimeMillis();
        tiempoTotalKMP = tiempoFinal - tiempoInicio;

        rs.setVisible(true);

        if (tiempoTotalFB < tiempoTotalKMP) {
            rs.setText(("Para el algoritmo de Fuerza Bruta se encontraron "
                    + fb.getContador() + " coincidencias y la búsqueda tomó " + tiempoTotalFB + " milisegundos.\n"
                    + fb.getResultados() + "\n\nPara el algoritmo de KMP se encontraron "
                    + kmp.getContador() + " coincidencias y la búsqueda tomó " + tiempoTotalKMP + " milisegundos.\n"
                    + kmp.getResultados() + "\n\nPara buscar el patrón " + Arrays.toString(patrones)
                    + " el mejor algoritmo fue Fuerza Bruta"), "Resultados de Fuerza bruta vs KMP: ");
        } else if (tiempoTotalFB == tiempoTotalKMP) {
            rs.setText(("Para el algoritmo de Fuerza Bruta se encontraron "
                    + fb.getContador() + " coincidencias y la búsqueda tomó " + tiempoTotalFB + " milisegundos.\n"
                    + fb.getResultados() + "\n\nPara el algoritmo de KMP se encontraron "
                    + kmp.getContador() + " coincidencias y la búsqueda tomó " + tiempoTotalKMP + " milisegundos.\n"
                    + kmp.getResultados() + "\n\nPara buscar el patrón " + Arrays.toString(patrones)
                    + " ambos algoritmos rindieron igual."), "Resultados de Fuerza bruta vs KMP: ");
        } else {
            rs.setText(("Para el algoritmo de Fuerza Bruta se encontraron "
                    + fb.getContador() + " coincidencias y la búsqueda tomó " + tiempoTotalFB + " milisegundos.\n"
                    + fb.getResultados() + "\n\nPara el algoritmo de KMP se encontraron "
                    + kmp.getContador() + " coincidencias y la búsqueda tomó " + tiempoTotalKMP + " milisegundos.\n"
                    + kmp.getResultados() + "\n\nPara buscar el patrón " + Arrays.toString(patrones)
                    + " el mejor algoritmo fue KMP"), "Resultados de Fuerza bruta vs KMP: ");
        }

    }

    //Imprime los resultados obtenidos al utilizar boyer moore
    public void imprimirResultadosBM() {

        long tiempoTotal = 0;
        long tiempoInicio = System.currentTimeMillis();
        calculoBoyerMoore();
        long tiempoFinal = System.currentTimeMillis();
        tiempoTotal = tiempoFinal - tiempoInicio;
        rs.setVisible(true);
        rs.setText(("Para el algoritmo de Boyer Moore se encontraron "
                + bm.getContador() + " coincidencias y la búsqueda tomó " + tiempoTotal + " milisegundos.\n"
                + bm.getResultados()), "Resultados de Boyer Moore:");

    }

    //Imprime los resultados obtenidos al utilizar los metodos de fuerza bruta y boyer moore
    public void imprimirFBvsBM() {

        long tiempoTotalFB = 0;
        long tiempoInicio = System.currentTimeMillis();
        calculoFuerzaBruta();
        long tiempoFinal = System.currentTimeMillis();
        tiempoTotalFB = tiempoFinal - tiempoInicio;
        long tiempoTotalBM = 0;
        tiempoInicio = System.currentTimeMillis();
        calculoBoyerMoore();
        tiempoFinal = System.currentTimeMillis();
        tiempoTotalBM = tiempoFinal - tiempoInicio;
        rs.setVisible(true);

        if (tiempoTotalFB < tiempoTotalBM) {
            rs.setText(("Para el algoritmo de Fuerza Bruta se encontraron "
                    + fb.getContador() + " coincidencias y la búsqueda tomó " + tiempoTotalFB + " milisegundos.\n"
                    + fb.getResultados() + "\n\nPara el algoritmo de Boyer Moore se encontraron "
                    + bm.getContador() + " coincidencias y la búsqueda tomó " + tiempoTotalBM + " milisegundos.\n"
                    + bm.getResultados() + "\n\nPara buscar el patrón " + Arrays.toString(patrones)
                    + " el mejor algoritmo fue Fuerza Bruta"), "Resultados de Fuerza bruta vs Boyer Moore: ");
        } else if (tiempoTotalFB == tiempoTotalBM) {
            rs.setText(("Para el algoritmo de Fuerza Bruta se encontraron "
                    + fb.getContador() + " coincidencias y la búsqueda tomó " + tiempoTotalFB + " milisegundos.\n"
                    + fb.getResultados() + "\n\nPara el algoritmo de Boyer Moore se encontraron "
                    + bm.getContador() + " coincidencias y la búsqueda tomó " + tiempoTotalBM + " milisegundos.\n"
                    + bm.getResultados() + "\n\nPara buscar el patrón " + Arrays.toString(patrones)
                    + " ambos algoritmos rindieron igual."), "Resultados de Fuerza bruta vs Boyer Moore: ");
        } else {
            rs.setText(("Para el algoritmo de Fuerza Bruta se encontraron "
                    + fb.getContador() + " coincidencias y la búsqueda tomó " + tiempoTotalFB + " milisegundos.\n"
                    + fb.getResultados() + "\n\nPara el algoritmo de Boyer Moore se encontraron "
                    + bm.getContador() + " coincidencias y la búsqueda tomó " + tiempoTotalBM + " milisegundos.\n"
                    + bm.getResultados() + "\n\nPara buscar el patrón " + Arrays.toString(patrones)
                    + " el mejor algoritmo fue Boyer Moore"), "Resultados de Fuerza bruta vs Boyer Moore: ");
        }

    }

    //Imprime los resultados obtenidos al utilizar los metodos de kmp y boyer moore
    public void imprimirKMPvsBM() {

        long tiempoTotalKMP = 0;
        long tiempoInicio = System.currentTimeMillis();
        calculoKMP();
        long tiempoFinal = System.currentTimeMillis();
        tiempoTotalKMP = tiempoFinal - tiempoInicio;
        long tiempoTotalBM = 0;
        tiempoInicio = System.currentTimeMillis();
        calculoBoyerMoore();
        tiempoFinal = System.currentTimeMillis();
        tiempoTotalBM = tiempoFinal - tiempoInicio;
        rs.setVisible(true);

        if (tiempoTotalKMP < tiempoTotalBM) {
            rs.setText(("Para el algoritmo de KMP se encontraron "
                    + kmp.getContador() + " coincidencias y la búsqueda tomó " + tiempoTotalKMP + " milisegundos.\n"
                    + kmp.getResultados() + "\n\nPara el algoritmo de Boyer Moore se encontraron "
                    + bm.getContador() + " coincidencias y la búsqueda tomó " + tiempoTotalBM + " milisegundos.\n"
                    + bm.getResultados() + "\n\nPara buscar el patrón " + Arrays.toString(patrones)
                    + " el mejor algoritmo fue KMP"), "Resultados de KMP vs Boyer Moore: ");
        } else if (tiempoTotalKMP == tiempoTotalBM) {
            rs.setText(("Para el algoritmo de KMP se encontraron "
                    + kmp.getContador() + " coincidencias y la búsqueda tomó " + tiempoTotalKMP + " milisegundos.\n"
                    + kmp.getResultados() + "\n\nPara el algoritmo de Boyer Moore se encontraron "
                    + bm.getContador() + " coincidencias y la búsqueda tomó " + tiempoTotalBM + " milisegundos.\n"
                    + bm.getResultados() + "\n\nPara buscar el patrón " + Arrays.toString(patrones)
                    + " ambos algoritmos rindieron igual."), "Resultados de KMP vs Boyer Moore: ");
        } else {
            rs.setText(("Para el algoritmo de KMP se encontraron "
                    + kmp.getContador() + " coincidencias y la búsqueda tomó " + tiempoTotalKMP + " milisegundos.\n"
                    + kmp.getResultados() + "\n\nPara el algoritmo de Boyer Moore se encontraron "
                    + bm.getContador() + " coincidencias y la búsqueda tomó " + tiempoTotalBM + " milisegundos.\n"
                    + bm.getResultados() + "\n\nPara buscar el patrón " + Arrays.toString(patrones)
                    + " el mejor algoritmo fue Boyer Moore"), "Resultados de KMP vs Boyer Moore: ");
        }

    }

}
