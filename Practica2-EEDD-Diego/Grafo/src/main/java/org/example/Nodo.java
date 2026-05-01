package org.example;

public class Nodo implements Comparable<Nodo> {
    private String id;
    private Lista_simple_enla<Arista> aristas;
    public Nodo(String id) {
        this.id = id;
        this.aristas = new Lista_simple_enla<>();
    }
    public String getId() { return id; }
    public Lista_simple_enla<Arista> getAristas() { return aristas; }
    public void agregarArista(Arista arista) {
        this.aristas.add(arista);
    }
    @Override
    public int compareTo(Nodo otro) {
        return this.id.compareTo(otro.id);
    }
}