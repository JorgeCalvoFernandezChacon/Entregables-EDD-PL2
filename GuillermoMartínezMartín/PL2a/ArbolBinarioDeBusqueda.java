package PL2a;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class ArbolBinarioDeBusqueda<T extends Comparable<T>> {
    private Nodo<T> raiz;

    // Creamos el constructor.
    public ArbolBinarioDeBusqueda() {
        this.raiz = null;
    }

    // Getter y setter
    public Nodo<T> getRaiz() {
        return raiz;
    }
    public void setRaiz(Nodo<T> raiz) {
        this.raiz = raiz;
    }

    // add
    public void add(T dato) {
        if (raiz == null) {
            raiz = new Nodo<>(dato);
        } else {
            insertarRecursivo(raiz, dato);
        }
    }
    private void insertarRecursivo(Nodo<T> actual, T nuevoDato) {
        // "actual" es el nodo en el que estamos y "nuevoDato" es el nuevo dato que queremos añadir
        // "comparación" será si el dato es menor, igual o mayor
        int comparacion = nuevoDato.compareTo(actual.getDato());

        // Si es igual, no añadimos nada
        if (comparacion == 0) return;
        // Si es menor
        if (comparacion < 0) {
            // Si su izquierda está vacía añadimos
            if (actual.getIzquierda() == null) {
                actual.setIzquierda(new Nodo<>(nuevoDato));
            // Si no está vacía, llamamos al metodo de nuevo
            } else { insertarRecursivo(actual.getIzquierda(), nuevoDato); }
        }
        if (comparacion > 0) {
            // Si su derecha está vacía añadimos
            if (actual.getDerecha() == null) {
                actual.setDerecha(new Nodo<>(nuevoDato));
            // Si no está vacía, llamamos al metodo de nuevo
            } else { insertarRecursivo(actual.getDerecha(), nuevoDato); }
        }
    }

    // creemos el metodo de la altura
    public int getAltura() {
        // Llamamos a una función recursiva:
        return calcularAltura(raiz);
    }
    private int calcularAltura(Nodo<T> actual) {
        // SI la raiz es nula, la altura es 0
        if (actual == null) return 0;

        // Si no, calculamos por la izquierda y por la derecha
        int alturaIzquierda = calcularAltura(actual.getIzquierda());
        int alturaDerecha = calcularAltura(actual.getDerecha());

        // Y la altura es el máximo de las 2 + 1
        return 1 + Math.max(alturaIzquierda, alturaDerecha);
    }

    // grado del árbol
    public int getGrado() {
        // Al ser un árbol binario el grado del arbol es 2
        return 2;
    }

    // listaDatosNivel
    public ArrayList<T> getListaDatosNivel(int nivel) {
        ArrayList<T> listaDatosNivel = new ArrayList<>();
        obtenerNivelRecursivo(nivel, raiz, listaDatosNivel);
        return listaDatosNivel;
    }
    private void obtenerNivelRecursivo(int nivel, Nodo<T> actual, ArrayList<T> listaDatosNivel) {
        // Caso base 1, raiz nula
        if (actual == null) return;

        // Caso base 2, pedimos la raíz
        if (nivel == 0) listaDatosNivel.add(actual.getDato());
        else {
            obtenerNivelRecursivo(nivel - 1, actual.getIzquierda(), listaDatosNivel);
            obtenerNivelRecursivo(nivel - 1, actual.getDerecha(), listaDatosNivel);
        }
    }

    // isÁrbolHomogéneo
    public boolean isArbolHomogeneo() {
        return verificarHomogeneo(raiz);
    }
    private boolean verificarHomogeneo(Nodo<T> actual) {
        // Si el nodo es vacío, devuelve true
        if (actual == null) return true;
        // Si sus "hijos" son vacíos también devuelve true
        else if (actual.getIzquierda() == null && actual.getDerecha() == null) return true;
        // Si sus dos hijos no son vacíos, sigue la recursividad
        else if (actual.getIzquierda() != null && actual.getDerecha() != null) {
            if (verificarHomogeneo(actual.getIzquierda()) && verificarHomogeneo(actual.getDerecha())) return true;
        }
        // Si no cumple lo anterior, no es homogéneo.
        return false;
    }

    // isÁrbolCompleto
    public boolean isArbolCompleto() {
        // En cada nivel debe haber 2^i elementos para que sea completo, es decir:
        // Recorremos cada nivel
        for (int i = 0; i < getAltura(); i++) {
            // Si cada nivel no tiene el mismo número de elementos que 2^i, entonces no es completo
            if (getListaDatosNivel(i).size() != (int) Math.pow(2,i)) return false;
        }
        return true;
    }

    // isÁrbolCasiCompleto
    public boolean isArbolCasiCompleto() {
        if (raiz == null) return true;

        // Creamos una cola para recorrer el árbol nivel por nivel
        Queue<Nodo<T>> cola = new LinkedList<>();
        cola.add(raiz);

        boolean huecoEncontrado = false;

        while (!cola.isEmpty()) {
            // Sacamos el elemento y lo guardamos en actual para estudiarlo
            Nodo<T> actual = cola.poll();

            if (actual == null) {
                // A partir de este momento, cualquier nodo que saquemos de la cola debe ser nulo para que el árbol sea casi completo.
                huecoEncontrado = true;
            } else {
                // Si ya habíamos encontrado un hueco anteriormente y ahora aparece un nodo real, el árbol NO es casi completo.
                if (huecoEncontrado) {
                    return false;
                }

                // Añadimos los hijos a la cola para seguir el recorrido.
                cola.add(actual.getIzquierda());
                cola.add(actual.getDerecha());
            }
        }
        return true;
    }

    // getCamino
    public ArrayList<T> getCamino(T dato) {
        ArrayList<T> listaCamino = new ArrayList<>();
        getCaminoRecursivo(dato, raiz, listaCamino);
        return listaCamino;
    }
    private void getCaminoRecursivo(T dato, Nodo<T> actual,  ArrayList<T> listaCamino) {
        // En esta función llegaremos al elemento mediante compararlo con los datos que tenemos en el árbol
        if (actual == null) return;
        int comparacion = dato.compareTo(actual.getDato());
        if (comparacion == 0) {
            listaCamino.add(actual.getDato());
            return;
        } else if (comparacion < 0) {
            listaCamino.add(actual.getDato());
            getCaminoRecursivo(dato, actual.getIzquierda(), listaCamino);
        } else if (comparacion > 0) {
            listaCamino.add(actual.getDato());
            getCaminoRecursivo(dato, actual.getDerecha(), listaCamino);
        }
    }

    // getSubArboles
    public ArbolBinarioDeBusqueda<T> getSubArbolIzquierda() {
        ArbolBinarioDeBusqueda<T> arbolIzquierda = new ArbolBinarioDeBusqueda<>();
        if (raiz == null) return arbolIzquierda;
        arbolIzquierda.setRaiz(raiz.getIzquierda());
        return arbolIzquierda;
    }
    public ArbolBinarioDeBusqueda<T> getSubArbolDerecha() {
        ArbolBinarioDeBusqueda<T> arbolDerecha = new ArbolBinarioDeBusqueda<>();
        if (raiz == null) return arbolDerecha;
        arbolDerecha.setRaiz(raiz.getDerecha());
        return arbolDerecha;
    }

    /*
     * getListas
     */

    // preOrden
    public ArrayList<T> getListaPreOrden() {
        ArrayList<T> listaPreOrden = new ArrayList<>();
        preOrdenRecursivo(raiz, listaPreOrden);
        return listaPreOrden;
    }
    private void preOrdenRecursivo(Nodo<T> actual, ArrayList<T> listaPreOrden) {
        if (actual == null) return;
        listaPreOrden.add(actual.getDato());
        preOrdenRecursivo(actual.getIzquierda(), listaPreOrden);
        preOrdenRecursivo(actual.getDerecha(), listaPreOrden);
    }
    // posOrden
    public ArrayList<T> getListaPostOrden() {
        ArrayList<T> listaPosOrden = new ArrayList<>();
        postOrdenRecursivo(raiz, listaPosOrden);
        return listaPosOrden;
    }
    private void postOrdenRecursivo(Nodo<T> actual, ArrayList<T> listaPosOrden) {
        if (actual == null) return;
        postOrdenRecursivo(actual.getIzquierda(), listaPosOrden);
        postOrdenRecursivo(actual.getDerecha(), listaPosOrden);
        listaPosOrden.add(actual.getDato());
    }
    // ordenCentral
    public ArrayList<T> getListaOrdenCentral() {
        ArrayList<T> listaOrdenCentral = new ArrayList<>();
        ordenCentralRecursivo(raiz, listaOrdenCentral);
        return listaOrdenCentral;
    }
    private void ordenCentralRecursivo(Nodo<T> actual, ArrayList<T> listaOrdenCentral) {
        if (actual == null) return;
        ordenCentralRecursivo(actual.getIzquierda(), listaOrdenCentral);
        listaOrdenCentral.add(actual.getDato());
        ordenCentralRecursivo(actual.getDerecha(), listaOrdenCentral);
    }
}

