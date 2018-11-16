/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tallerserver;

import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author Edison
 */
public class Game {

    public  char ame(char letra) {
        final int INTENTOS_TOTALES = 7; // Constante con el limite de fallos
        int intentos = 0;
        int aciertos = 0;
        // ingresa para leer por teclado
        Scanner teclado = new Scanner(System.in);
        teclado.useDelimiter("\n");
        
        char resp;
        // Random para pillar una palabra al azar
        Random rnd = new Random();
        // Creamos unas palabras y le asignamos una aleatoria a una varibale
        String arrayPalabras[] = new String[3];
        arrayPalabras[0] = "hola";
        arrayPalabras[1] = "adios";
        arrayPalabras[2] = "saludos";

        do {

            // separamos la palabra y la guardamos en un array de caracteres
            int alea = rnd.nextInt(3);
            char[] separar = desmenusar(arrayPalabras[alea]);
            char[] copia = desmenusar(arrayPalabras[alea]); // Algo auxiliar para mas tarde
            // Array para pintar guiones en pantalla(Guiones o letras vamos)
            char[] tusRespuestas = new char[separar.length];

            // Rellenamos palabras ocn guiones
            for (int i = 0; i < tusRespuestas.length; i++) {
                tusRespuestas[i] = '_';
            }

            // Empezamos a pintar guiones en pantalla
            System.out.println("Adivina la palabra!");

            // Mientras que no nos pasemos con los intentos y no la acertemos...
            while (intentos < INTENTOS_TOTALES && aciertos != tusRespuestas.length) {
                imprimeOculta(tusRespuestas);
                // Preguntamos letras por teclado
                System.out.println("\nIntroduce una letra: ");
                resp = letra;//teclado.next().toLowerCase().charAt(0);
                // Recorremos el array y comprobamos si se ha producido un acierto
                for (int i = 0; i < separar.length; i++) {
                    if (separar[i] == resp) {
                        tusRespuestas[i] = separar[i];
                        separar[i] = ' ';
                        aciertos++;
                    }
                }
                intentos++;
            }
            // Si hemos acertado todas imprimimos un mensahe
            if (aciertos == tusRespuestas.length) {
                System.out.print("\nFalocidades!! has acertado la palabra: ");
                imprimeOculta(tusRespuestas);
            } // Si no otro
            else {
                System.out.print("\nHas perdido! la palabra era: ");
                for (int i = 0; i < copia.length; i++) {
                    System.out.print(copia[i] + " ");
                }
            }
            // Reseteamos contadores
            intentos = 0;
            aciertos = 0;
            // Volvemos a preguntarle al usuario si quiere volver a jugar
            resp = pregunta("\n\nQuieres volver a jugar?", teclado);
        } while (resp != 'n');
        
        return resp;

    }

    /**
     * Esto Separa el String en un array de caracteres
     *
     * @return array de letras.
     */
    private static char[] desmenusar(String palAzar) {
        char[] letras;
        letras = new char[palAzar.length()];
        for (int i = 0; i < palAzar.length(); i++) {
            letras[i] = palAzar.charAt(i);
        }
        return letras;
    }

    /**
     * Esto imprime la palabra con espacios
     *
     * @param tusRespuestas el array de caracteres
     */
    private static void imprimeOculta(char[] tusRespuestas) {

        for (int i = 0; i < tusRespuestas.length; i++) {
            System.out.print(tusRespuestas[i] + " ");
        }
    }

    /**
     * Esto nos pregunta si queremos volver a jugar y comprueba los caracteres
     * introducidos
     *
     * @param men texto para mostrar al usuario
     * @param teclado
     * @return caracter de respuesta (s/n)
     */
    public static char pregunta(String men, Scanner teclado) {
        char resp;
        System.out.println(men + " (s/n)");
        resp = teclado.next().toLowerCase().charAt(0);
        while (resp != 's' && resp != 'n') {
            System.out.println("Error! solo se admite S o N");
            resp = teclado.next().toLowerCase().charAt(0);
        }
        return resp;
    }

}
