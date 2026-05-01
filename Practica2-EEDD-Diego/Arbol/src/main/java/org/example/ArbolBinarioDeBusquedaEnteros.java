package org.example;

public class ArbolBinarioDeBusquedaEnteros extends ArbolBinarioBusqueda<Integer> {

    public ArbolBinarioDeBusquedaEnteros() { super(); }
    private ArbolBinarioDeBusquedaEnteros(Nodo<Integer> raiz) { super(raiz); }
    public int getSuma() {
        return sumarNodos(raiz);
    }
    private int sumarNodos(Nodo<Integer> nodo) {
        if (nodo == null)
            return 0;
        return nodo.getDato() + sumarNodos(nodo.getIzquierda()) + sumarNodos(nodo.getDerecha());
    }
    @Override
    public ArbolBinarioDeBusquedaEnteros getSubArbolIzquierda() {
        return raiz != null ? new ArbolBinarioDeBusquedaEnteros(raiz.getIzquierda()) : new ArbolBinarioDeBusquedaEnteros();
    }
    @Override
    public ArbolBinarioDeBusquedaEnteros getSubArbolDerecha() {
        return raiz != null ? new ArbolBinarioDeBusquedaEnteros(raiz.getDerecha()) : new ArbolBinarioDeBusquedaEnteros();
    }
    public int getDatoRaiz() {
        return raiz != null ? raiz.getDato() : 0;
    }
}