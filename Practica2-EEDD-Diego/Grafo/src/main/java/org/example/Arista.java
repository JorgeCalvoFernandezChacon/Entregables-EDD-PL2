package org.example;

public class Arista implements Comparable<Arista> {
    private Nodo destino;
    private String predicado;
    private String direccion;
    public Arista(Nodo destino, String predicado, String direccion) {
        this.destino = destino;
        this.predicado = predicado;
        this.direccion = direccion;
    }
    public Nodo getDestino() { return destino; }
    public String getPredicado() { return predicado; }
    public String getDireccion() { return direccion; }
    @Override
    public int compareTo(Arista otra) {
        return this.predicado.compareTo(otra.predicado);
    }
}