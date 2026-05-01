package PL2b;

public class Arista {
    private String predicado;
    private Nodo destino;

    public Arista(String predicado, Nodo destino) {
        this.predicado = predicado;
        this.destino = destino;
    }

    // Getters
    public String getPredicado() {
        return predicado;
    }
    public Nodo getDestino() {
        return destino;
    }
    public void setPredicado(String predicado) {}
}
