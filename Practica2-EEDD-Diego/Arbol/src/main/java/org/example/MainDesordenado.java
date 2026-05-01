package org.example;

public class MainDesordenado {
    public static void main(String[] args) {
        System.out.println("=== PRUEBA 2: ALEATORIO (0 a 128) ===");
        ArbolBinarioDeBusquedaEnteros arbol = new ArbolBinarioDeBusquedaEnteros();
        int[] arrayNumeros = new int[129];
        for (int i = 0; i <= 128; i++) {
            arrayNumeros[i] = i;
        }
        for (int i = arrayNumeros.length - 1; i > 0; i--) {
            int j = (int) (Math.random() * (i + 1));
            int temp = arrayNumeros[i];
            arrayNumeros[i] = arrayNumeros[j];
            arrayNumeros[j] = temp;
        }
        Cola<Integer> colaAleatoria = new Cola<>();
        for (int i = 0; i < arrayNumeros.length; i++) {
            colaAleatoria.add(arrayNumeros[i]);
        }
        Integer num = colaAleatoria.get(0);
        while (num != null) {
            arbol.add(num);
            colaAleatoria.del(0);
            num = colaAleatoria.get(0);
        }
        System.out.println("\nSuma total (getSuma): " + arbol.getSuma());
        System.out.println("Suma PreOrden: " + MainOrdenado.sumarCola(arbol.getListaPreOrden()));
        System.out.println("Suma PostOrden: " + MainOrdenado.sumarCola(arbol.getListaPostOrden()));
        System.out.println("Suma OrdenCentral: " + MainOrdenado.sumarCola(arbol.getListaOrdenCentral()));
        int sumaIzq = arbol.getSubArbolIzquierda().getSuma();
        int sumaDer = arbol.getSubArbolDerecha().getSuma();
        System.out.println("Suma (Izq + Der + Raiz): " + (sumaIzq + sumaDer + arbol.getDatoRaiz()));
        System.out.println(" -> Al insertarse al azar, los datos se distribuyen de forma variada entre ambos subárboles.");
        System.out.println("\nAltura del árbol: " + arbol.getAltura());
        System.out.println(" -> Altura mucho menor que en la prueba 1 porque los datos se distribuyen balanceando el árbol.");
        Cola<Integer> camino = arbol.getCamino(110);
        System.out.println("\nCamino a 110:");
        camino.mostrar();
        System.out.println("Longitud del camino a 110 (aristas): " + (MainOrdenado.calcularTamano(camino) - 1));
    }
}
