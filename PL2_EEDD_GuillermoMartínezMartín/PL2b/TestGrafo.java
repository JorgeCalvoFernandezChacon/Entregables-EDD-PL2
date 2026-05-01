package PL2b;

import EEDD_P1.lista_simplemente_enlazada.ListaSimplementeEnlazada;

public class TestGrafo {
    public static void main(String[] args) {
        System.out.println("--- INICIANDO PRUEBAS DEL GRAFO ---");

        Grafo g1 = new Grafo();
        g1.addTripleta("A", "conecta", "B");
        g1.addTripleta("B", "conecta", "C");

        g1.addTripleta("A", "conecta", "X");
        g1.addTripleta("X", "conecta", "Y");
        g1.addTripleta("Y", "conecta", "C");

        System.out.println("\n1. Pruebas de Grafo Conexo (A, B, C, X, Y):");

        System.out.println("¿Es disjunto? (esperado false): " + g1.isDisjunto());

        ListaSimplementeEnlazada<String> caminoCorto = g1.getCaminoMinimo("A", "C");
        System.out.println("Camino mínimo de A a C (esperado [A, B, C]): " +
                (caminoCorto != null ? caminoCorto.toString() : "null"));

        ListaSimplementeEnlazada<String> caminoInverso = g1.getCaminoMinimo("C", "A");
        System.out.println("Camino mínimo de C a A (esperado null): " +
                (caminoInverso != null ? caminoInverso.toString() : "null"));

        Grafo g2 = new Grafo();
        g2.addTripleta("A", "conecta", "B");
        g2.addTripleta("M", "conecta", "N");

        System.out.println("\n2. Pruebas de Grafo Disjunto (A->B y M->N):");
        System.out.println("¿Es disjunto? (esperado true): " + g2.isDisjunto());

        ListaSimplementeEnlazada<String> caminoImposible = g2.getCaminoMinimo("A", "N");
        System.out.println("Camino mínimo de A a N (esperado null): " +
                (caminoImposible != null ? caminoImposible.toString() : "null"));
        System.out.println("\n3. Pruebas de carga desde JSON:");

        Grafo g3_conexo = new Grafo();
        System.out.print("Cargando grafo conexo... ");
        g3_conexo.cargarDesdeJSON("src/PL2b/grafo_conexo.json");
        System.out.println("¿Es disjunto el JSON conexo? (esperado false): " + g3_conexo.isDisjunto());

        Grafo g4_disjunto = new Grafo();
        System.out.print("Cargando grafo disjunto... ");
        g4_disjunto.cargarDesdeJSON("src/PL2b/grafo_disjunto.json");
        System.out.println("¿Es disjunto el JSON en islas? (esperado true): " + g4_disjunto.isDisjunto());
    }
}
