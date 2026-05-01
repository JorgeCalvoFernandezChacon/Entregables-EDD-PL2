package PL2a;

import EEDD_P1.lista_simplemente_enlazada.ListaSimplementeEnlazada;

import java.util.Collections;

public class Main {
    public static void main(String[] args) {

        /*
         * PRUEBA 1
         */

        ArbolBinarioDeBusqueda<Integer> arbol1 = new ArbolBinarioDeBusqueda<>();

        for (int i = 0; i <= 128; i++) {
            arbol1.add(i);
        }

        ListaSimplementeEnlazada<Integer> listaOrdenada = arbol1.getListaOrdenCentral();
        long suma_1 = 0;
        for (int i = 0; i < listaOrdenada.size(); i++) {
            suma_1 += listaOrdenada.get(i);
        }

        System.out.println("--- RESULTADOS PRUEBA 1 (Inserción Ordenada) ---");
        System.out.println("Suma total de los elementos: " + suma_1);
        System.out.println("Altura del árbol: " + arbol1.getAltura());
        System.out.println("¿Es árbol completo?: " + arbol1.isArbolCompleto());
        System.out.println("¿Es árbol casi completo?: " + arbol1.isArbolCasiCompleto());

        ListaSimplementeEnlazada<Integer> camino110_1 = arbol1.getCamino(110);
        System.out.println("Camino hasta el 110: " + camino110_1);
        System.out.println("Longitud del camino: " + (camino110_1.size() - 1));

        /*
         * PRUEBA 2
         */

        ArbolBinarioDeBusqueda<Integer> arbol2 = new ArbolBinarioDeBusqueda<>();

        java.util.ArrayList<Integer> temporal = new java.util.ArrayList<>();
        for (int i = 0; i <= 128; i++) {
            temporal.add(i);
        }
        Collections.shuffle(temporal);

        ListaSimplementeEnlazada<Integer> numeros = new ListaSimplementeEnlazada<>();
        for (Integer n : temporal) {
            numeros.addLast(n);
        }

        for (int i = 0; i < numeros.size(); i++) {
            arbol2.add(numeros.get(i));
        }

        ListaSimplementeEnlazada<Integer> listaOrdenada_1 = arbol2.getListaOrdenCentral();
        long suma_2 = 0;
        for (int i = 0; i < listaOrdenada_1.size(); i++) {
            Integer num = listaOrdenada_1.get(i);
            suma_2 += num;
        }

        System.out.println("--- RESULTADOS PRUEBA 2 (Inserción Aleatoria) ---");
        System.out.println("Suma total: " + suma_2); // Debe seguir siendo 8256
        System.out.println("Altura del árbol: " + arbol2.getAltura()); // ¡Será mucho menor!

        ListaSimplementeEnlazada<Integer> camino110_2 = arbol2.getCamino(110);
        System.out.println("Camino hasta el 110: " + camino110_2);
        System.out.println("Longitud del camino: " + (camino110_2.size() - 1));

        System.out.println("¿Es completo?: " + arbol2.isArbolCompleto());
        System.out.println("¿Es casi completo?: " + arbol2.isArbolCasiCompleto());
    }
}