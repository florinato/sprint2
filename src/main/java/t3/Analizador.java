package t3;

import java.util.Arrays;


public class Analizador {
    int resultado=0;
    String abecedario="abcdefghijklmnñopqrstuvwxyz";
    public static void unicoBucle(String texto) {
        int numPalabras = 0;
        int longitudTotal = 0;
        int numOraciones = 0;
    
        String[] palabras = texto.split("\\s+");
        String[] oraciones = texto.split("[.!?]+");
    
        for (String palabra : palabras) {
            numPalabras++;
            longitudTotal += palabra.length();
        }
    
        numOraciones = oraciones.length;
        double longitudMedia = (double) longitudTotal / numPalabras;
    
        System.out.println("Total de palabras: " + numPalabras);
        System.out.println("Longitud media de las palabras: " + longitudMedia);
        System.out.println("Número de oraciones: " + numOraciones);
    }
    public static void unicoBucleLambda(String texto) {
        String[] palabras = texto.split("\\s+");
        String[] oraciones = texto.split("[.!?]+");
    
        long numPalabras = palabras.length;
        double longitudMedia = Arrays.stream(palabras).mapToInt(String::length).average().orElse(0.0);
    
        long numOraciones = oraciones.length;
    
        System.out.println("Total de palabras: " + numPalabras);
        System.out.println("Longitud media de las palabras: " + longitudMedia);
        System.out.println("Número de oraciones: " + numOraciones);
   
    
    }
    
}
