package org.example;

public class MainOrdenado {
    public static void main(String[] args) {
        System.out.println("=== PRUEBA 1: ORDENADO (0 a 128) ===");
        ArbolBinarioDeBusquedaEnteros arbol = new ArbolBinarioDeBusquedaEnteros();
        for (int i = 0; i <= 128; i++) {
            arbol.add(i);
        }
        System.out.println("Suma total (getSuma): " + arbol.getSuma());
        System.out.println("Suma PreOrden: " + sumarCola(arbol.getListaPreOrden()));
        System.out.println("Suma PostOrden: " + sumarCola(arbol.getListaPostOrden()));
        System.out.println("Suma OrdenCentral: " + sumarCola(arbol.getListaOrdenCentral()));
        int sumaIzq = arbol.getSubArbolIzquierda().getSuma();
        int sumaDer = arbol.getSubArbolDerecha().getSuma();
        System.out.println("Suma (Izq + Der + Raiz): " + (sumaIzq + sumaDer + arbol.getDatoRaiz()));
        System.out.println(" -> Al insertarse en orden, la raíz es 0, todo va a la derecha. Las sumas coinciden por ley matemática.");
        System.out.println("Altura del árbol: " + arbol.getAltura());
        System.out.println("  Altura 128 porque degenera en una especie de lista (solo hijos derechos).");
        Cola<Integer> camino = arbol.getCamino(110);
        System.out.println("Longitud del camino a 110 (aristas): " + (calcularTamano(camino) - 1));
    }
    public static int sumarCola(Cola<Integer> cola) {
        int suma = 0, i = 0;
        Integer num = cola.get(i);
        while (num != null) {
            suma += num;
            i++;
            num = cola.get(i);
        }
        return suma;
    }
    public static int calcularTamano(Cola<Integer> cola) {
        int tamano = 0;
        while (cola.get(tamano) != null) tamano++;
        return tamano;
    }
}
