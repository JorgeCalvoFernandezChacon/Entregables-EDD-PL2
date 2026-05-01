package org.example;

public class MainGrafo {
    public static void main(String[] args) {
        System.out.println("--- PRÁCTICA GRAFOS (VERSIÓN FINAL SIN IMPORTS) ---");
        Grafo g1 = new Grafo();
        g1.cargarSimulandoArchivo("disjunto.json");
        System.out.println("¿Es el grafo disjunto con disjunto.json?: " + g1.esDisjunto());
        Grafo g2 = new Grafo();
        g2.cargarSimulandoArchivo("nobel.json");
        g2.agregarTripleta("persona:Antonio", "nace_en", "lugar:Villarrubia");
        System.out.println("¿Es el grafo disjunto tras añadir a Antonio?: " + g2.esDisjunto());
    }
}