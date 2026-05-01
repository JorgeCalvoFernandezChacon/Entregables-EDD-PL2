package EEDD_P1.pila;


public class Pila<T> {
    /*
     * Variables
     */

    /// cabeza: que es el elemento más arriba de la pila
    private Elemento<T> cabeza;
    /// tamaño: que es el número de elementos de la lista
    private int tamaño;

    /*
     * Métodos
     */

    // Constructor
    public Pila() {
        this.cabeza = null;
        this.tamaño = 0;
    }

    // Metodo para añadir elementos a la pila
    public void push(T dato) {
        // Primero nos protegemos ante la posible entrada de un elemento null:
        if (dato == null) return;
        // Creamos el nuevo elemento de la pila
        Elemento<T> nuevoElemento = new Elemento<T>(dato);
        // Ponemos la antigua cabeza en siguiente de nuevoElemento
        nuevoElemento.setSiguiente(cabeza);
        // La nueva cabeza sera el nuevoElemento
        cabeza = nuevoElemento;
        // Aumentamos el tamaño de la lista
        tamaño++;
    }

    // Metodo para sacar elementos de la pila
    public T pop() {
        if (cabeza != null) {
            // Copiamos el elemento a elimiar con otra variable
            Elemento<T> elementoEliminado = cabeza;
            // Copiamos el siguiente de la cabeza que será la nueva cabeza
            Elemento<T> nuevaCabeza = cabeza.getSiguiente();
            // La nueva cabeza será el siguiente de la antigua
            cabeza = nuevaCabeza;
            // Devolvemos el dato del elemento eliminado
            tamaño--;
            return elementoEliminado.getDato();
        } else return null;
    }

    // Metodo para buscar la posicion de un elemento (aunque al ser una pila no tiene mucho sentido)
    public int pos(T dato) {
        // Inicializamos la posicion en 0
        int posicion = 0;
        // Copiamos la cabeza en actual
        Elemento<T> actual = cabeza;
        // Recorremos la pila
        while (actual != null) {
            // Si encuentra el dato, devuelve la posicion
            if (actual.getDato().equals(dato)) {
                return posicion;
            }
            actual = actual.getSiguiente();
            posicion++;
        }
        // Si no lo encuentra, devuelve -1
        return -1;
    }

    // Metodo get para recibir el dato de un elemento (aunque al ser una pila no tiene mucho sentido)
    public T get(int posicion) {
        // Primero verificamos que el numero sea válido
        if (verificarNumero(posicion)) {
            // Copiamos cabeza en actual
            Elemento<T> actual = cabeza;
            // Recorremos la pila hasta la posicion
            for (int i = 0; i < posicion; i++) {
                actual = actual.getSiguiente();
            }
            // Cuando llegamos, retornamos el dato del elemento
            return actual.getDato();
        }
        // Si no es válido devuelve null
        else return null;
    }

    // Metodo para obtener el primer dato
    public T top() {
        if (cabeza != null) return cabeza.getDato();
        return null;
    }

    // Metodo para limpiar la pila
    public void clear() {
        cabeza = null;
        tamaño = 0;
    }

    // Metodo para mostrar la lista
    public String toString() {
        Elemento<T> actual = cabeza;
        // Si la lista es vacía, devuelve [].
        if (cabeza == null) return "[]";
        // Si no, la recorremos añadiendo el dato y ", " en todos menos el último.
        String resultado = "[";
        while (actual != null) {
            resultado += actual.getDato();
            if (actual.getSiguiente() != null) resultado += ", ";
            actual = actual.getSiguiente();
        }
        resultado += "]";
        return resultado;
    }

    // Metodo size para mostrar el tamaño de la pila
    public int size() { return tamaño; }

    // Metodo auxiliar para verificar que el numero vale para la pila
    private boolean verificarNumero(int numero) {
        // Si está en el tamaño de la pila devuelve true
        if (numero >= 0 && numero < tamaño) return true;
        // Si no, devuelve false
        else return false;
    }
}

