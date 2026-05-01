package org.example;

public class ArbolBinarioBusqueda<T extends Comparable<T>> {
    protected Nodo<T> raiz;
    public ArbolBinarioBusqueda() {
        this.raiz = null;
    }
    protected ArbolBinarioBusqueda(Nodo<T> raiz) {
        this.raiz = raiz;
    }
    public void add(T dato) {
        raiz = addRecursivo(raiz, dato);
    }

    private Nodo<T> addRecursivo(Nodo<T> actual, T dato) {
        // Si llegamos a un hueco vacío, creamos el nodo
        if (actual == null) {
            return new Nodo<>(dato);
        }
        // Si el dato es menor, nos vamos por la rama izquierda
        if (dato.compareTo(actual.getDato()) < 0) {
            actual.setIzquierda(addRecursivo(actual.getIzquierda(), dato));
        }
        // Si el dato es mayor, nos vamos por la rama derecha
        else if (dato.compareTo(actual.getDato()) > 0) {
            actual.setDerecha(addRecursivo(actual.getDerecha(), dato));
        }
        // Devolvemos el nodo actual para que no se pierda la conexión
        return actual;
    }
    public ArbolBinarioBusqueda<T> getSubArbolIzquierda() {
        if (this.raiz != null) {
            return new ArbolBinarioBusqueda<>(this.raiz.getIzquierda());
        }
        else {
            return new ArbolBinarioBusqueda<>(); // Árbol vacío
        }
    }
    public ArbolBinarioBusqueda<T> getSubArbolDerecha() {
        if (this.raiz != null) {
            return new ArbolBinarioBusqueda<>(this.raiz.getDerecha());
        }
        else {
            return new ArbolBinarioBusqueda<>();
        }
    }
    public int getAltura() {
        return calcularAltura(raiz);
    }
    private int calcularAltura(Nodo<T> nodo) {
        // Si el nodo es nulo, la altura es -1 para que las hojas tengan altura 0
        if (nodo == null) {
            return -1;
        }
        int alturaIzq = calcularAltura(nodo.getIzquierda());
        int alturaDer = calcularAltura(nodo.getDerecha());
        return 1 + Math.max(alturaIzq, alturaDer);
    }
    public int getGrado() {
        return calcularGradoMaximo(raiz);
    }
    private int calcularGradoMaximo(Nodo<T> nodo) {
        if (nodo == null) {
            return 0;
        }
        int contadorHijos = 0;
        if (nodo.getIzquierda() != null) contadorHijos++;
        if (nodo.getDerecha() != null) contadorHijos++;
        // Si ya tiene 2 hijos, es el grado máximo posible, no hace falta mirar más
        if (contadorHijos == 2) {
            return 2;
        }
        int gradoIzq = calcularGradoMaximo(nodo.getIzquierda());
        int gradoDer = calcularGradoMaximo(nodo.getDerecha());
        return Math.max(contadorHijos, Math.max(gradoIzq, gradoDer));
    }
    public Cola<T> getCamino(T objetivo) {
        Cola<T> camino = new Cola<>();
        Nodo<T> nodoActual = raiz;
        while (nodoActual != null) {
            camino.add(nodoActual.getDato());
            int comparacion = objetivo.compareTo(nodoActual.getDato());
            if (comparacion == 0) {
                return camino;
            }
            if (comparacion < 0) {
                nodoActual = nodoActual.getIzquierda();
            }
            else {
                nodoActual = nodoActual.getDerecha();
            }
        }
        return new Cola<>(); // Si sale del bucle, es que no está, devolvemos cola vacía
    }
    public Cola<T> getListaOrdenCentral() {
        Cola<T> listaResultado = new Cola<>();
        ordenCentral(raiz, listaResultado);
        return listaResultado;
    }
    private void ordenCentral(Nodo<T> nodo, Cola<T> lista) {
        if (nodo != null) {
            ordenCentral(nodo.getIzquierda(), lista);
            lista.add(nodo.getDato()); // Primero izquierda, luego raíz, luego derecha
            ordenCentral(nodo.getDerecha(), lista);
        }
    }
    public Cola<T> getListaPreOrden() {
        Cola<T> listaResultado = new Cola<>();
        preOrden(raiz, listaResultado);
        return listaResultado;
    }
    private void preOrden(Nodo<T> nodo, Cola<T> lista) {
        if (nodo != null) {
            lista.add(nodo.getDato());
            preOrden(nodo.getIzquierda(), lista);
            preOrden(nodo.getDerecha(), lista);
        }
    }
    public Cola<T> getListaPostOrden() {
        Cola<T> listaResultado = new Cola<>();
        postOrden(raiz, listaResultado);
        return listaResultado;
    }
    private void postOrden(Nodo<T> nodo, Cola<T> lista) {
        if (nodo != null) {
            postOrden(nodo.getIzquierda(), lista);
            postOrden(nodo.getDerecha(), lista);
            lista.add(nodo.getDato());
        }
    }
}
