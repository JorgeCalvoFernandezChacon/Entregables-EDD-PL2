package EEDD_P1.cola;


public class Cola<T> {
    /*
     * Variables
     */

    /// El primero de la cola será la cabeza
    private Elemento<T> cabeza;
    /// El último de la cola será "ultimo"
    private Elemento<T> ultimo;
    /// tamaño: número de elementos en la cola
    int tamaño;

    /*
     * Métodos
     */

    // Creamos el Constructor
    public Cola() {
        this.cabeza = null;
        this.ultimo = null;
        this.tamaño = 0;
    }

    // Metodo para añadir elementos a la cola
    public void encolar(T dato) {
        // Creamos el nuevo elemento con el dato introducido
        Elemento<T> nuevo = new Elemento<T>(dato);
        /// Si la lista está vacía, la cabeza y el ultimo será el nuevo elemento
        if (cabeza == null) {
            cabeza = nuevo;
            ultimo = nuevo;
        }
        // Si la lista tiene algun elemento, se añade el nuevo al final
        else {
            ultimo.setSiguiente(nuevo);
            ultimo = nuevo;
        }
        tamaño++;
    }

    // Metodo para quitar elementos de la cola
    public T desencolar() {
        // Primero verificamos que la cola tenga algun elemento
        if (tamaño >= 1) {
            Elemento<T> elementoEliminado = cabeza;
            // La nueva cabeza será el siguiente de la antigua cabeza
            cabeza = cabeza.getSiguiente();
            // Si al sacar el elemento la cola se queda vacía, ultimo también es null
            if (cabeza == null) {
                ultimo = null;
            }
            // Reducimos el tamaño
            tamaño--;
            // Devolvemos el dato del elemento eliminado
            return elementoEliminado.getDato();
        }
        else return null;
    }

    // Metodo para devolver el elemento de la posicion que digamos.
    public T get(int posicion) {
        if (verificarNumero(posicion)) {
            Elemento<T> actual = cabeza;
            // Vamos a la posicion dada
            for (int i = 0; i < posicion; i++) {
                actual = actual.getSiguiente();
            }
            // Devuelve el dato de la posicion
            return actual.getDato();
        }
        // Si el numero no es válido, devuelve null
        else return null;
    }

    // Metodo para buscar un elemento que devuelve su posicion (aunque al ser una cola no tiene mucho sentido)
    public int pos(T dato) {
        Elemento<T> actual = cabeza;
        // Iniciamos la posicion en 0
        int posicion = 0;
        // Recorremos la cola
        while (actual != null) {
            if (actual.getDato().equals(dato)) return posicion;
            actual = actual.getSiguiente();
            posicion++;
        }
        // Si el número no es válido, devuelve -1
        return -1;
    }

    // Metodo para limpiar la cola (aunque al ser una cola no tiene mucho sentido)
    public void clear() {
        cabeza = null;
        ultimo = null;
        tamaño = 0;
    }

    // Metodo que devuelve el elemento a la cabeza de la cola
    public T primero() {
        if (cabeza != null) return cabeza.getDato();
        return null;
    }

    // Metodo para mostrar la lista
    public String toString() {
        Elemento<T> actual = cabeza;
        // Si la lista está vacía, devolvemos "[]"
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

    // Metodo para devolver el tamaño de la cola
    public int size() { return tamaño; }

    // Metodo auxiliar para verificar que el numero vale para la cola
    private boolean verificarNumero(int numero) {
        // Si está en el tamaño de la cola devuelve true
        if (numero >= 0 && numero < tamaño) return true;
            // Si no, devuelve false
        else return false;
    }
}
