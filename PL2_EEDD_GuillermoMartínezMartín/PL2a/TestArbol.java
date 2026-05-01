package PL2a;

public class TestArbol {
    public static void main(String[] args) {
        System.out.println("--- INICIANDO PRUEBAS DEL ÁRBOL BINARIO DE BÚSQUEDA ---");

        ArbolBinarioDeBusqueda<Integer> arbol = new ArbolBinarioDeBusqueda<>();

        int[] datos = {50, 30, 70, 20, 40, 60, 80};

        for (int i = 0; i < datos.length; i++) {
            arbol.add(datos[i]);
        }

        System.out.println("\n1. Pruebas de propiedades básicas (Árbol de 7 nodos):");
        System.out.println("Altura del árbol (esperado 3): " + arbol.getAltura());
        System.out.println("Grado del árbol (esperado 2): " + arbol.getGrado());

        System.out.println("\n2. Pruebas de validación de forma:");
        System.out.println("¿Es homogéneo? (esperado true): " + arbol.isArbolHomogeneo());
        System.out.println("¿Es completo? (esperado true): " + arbol.isArbolCompleto());
        System.out.println("¿Es casi completo? (esperado true): " + arbol.isArbolCasiCompleto());

        System.out.println("\n3. Pruebas de recorridos (Aprovechamos tu método toString de la lista):");
        System.out.println("PreOrden: " + arbol.getListaPreOrden().toString());
        System.out.println("PostOrden: " + arbol.getListaPostOrden().toString());
        System.out.println("Orden Central (esperado ordenado de menor a mayor): \n" + arbol.getListaOrdenCentral().toString());

        System.out.println("\n4. Pruebas de niveles y caminos:");
        System.out.println("Datos en el nivel 0 (esperado [50]): " + arbol.getListaDatosNivel(0).toString());
        System.out.println("Datos en el nivel 2 (esperado [20, 40, 60, 80]): " + arbol.getListaDatosNivel(2).toString());
        System.out.println("Camino hasta el 60 (esperado [50, 70, 60]): " + arbol.getCamino(60).toString());

        System.out.println("\n5. Pruebas de asimetría (Añadimos el 90 y el 100):");
        arbol.add(90);
        arbol.add(100);

        System.out.println("Nueva altura del árbol (esperado 5): " + arbol.getAltura());
        System.out.println("¿Es homogéneo ahora? (esperado false): " + arbol.isArbolHomogeneo());
        System.out.println("¿Es completo ahora? (esperado false): " + arbol.isArbolCompleto());
        System.out.println("¿Es casi completo ahora? (esperado false): " + arbol.isArbolCasiCompleto());
        System.out.println("Camino hasta el 100 (esperado [50, 70, 80, 90, 100]): " + arbol.getCamino(100).toString());

        System.out.println("\n6. Pruebas de división de subárboles:");
        ArbolBinarioDeBusqueda<Integer> subIzq = arbol.getSubArbolIzquierda();
        System.out.println("Orden Central del subárbol izquierdo (esperado [20, 30, 40]): \n" + subIzq.getListaOrdenCentral().toString());
    }
}
