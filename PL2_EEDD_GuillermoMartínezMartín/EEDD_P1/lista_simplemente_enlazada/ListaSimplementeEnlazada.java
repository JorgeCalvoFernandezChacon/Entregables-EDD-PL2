package EEDD_P1.lista_simplemente_enlazada;


public class ListaSimplementeEnlazada<T>{
    /*
     * VARIABLES:
     */

    /// cabeza: que es el primer elemento de la lista
    private Elemento<T> cabeza;
    /// tamaño: que es el tamaño de la lista
    private int tamaño;

    /*
     * METODOS:
     */

    /// Creamos el constructor de la lista
    public ListaSimplementeEnlazada() {
        this.cabeza = null;
        this.tamaño = 0;
    }

    // Metodo add añade un elemento al final de la lista
    public void addLast(T dato) {
        // Primero nos protegemos ante la posible entrada de un elemento null:
        if (dato == null) return;
        // El elemento nuevo contiene el dato que queremos añadir
        Elemento<T> nuevo = new Elemento<T>(dato);

        // Si la cabeza es nula (lista nueva) entonces el nuevo elemento será la cabeza
        if (cabeza == null) {
            cabeza = nuevo;
        }

        // Si no, el elemento será el siguiente del último elemento
        else {
            Elemento<T> actual = cabeza;
            while (actual.getSiguiente() != null) {
                actual = actual.getSiguiente();
            }
            actual.setSiguiente(nuevo);
        }
        // Hacemos crecer el tamaño de la lista
        tamaño++;
    }

    // Metodo addFirst añade un elemento al inicio de la lista
    public void addFirst(T dato) {
        // Primero nos protegemos ante la posible entrada de un elemento null
        if (dato == null) return;
        Elemento<T> nuevo = new Elemento<T>(dato);
        if (cabeza == null) {
            cabeza = nuevo;
        } else {
            nuevo.setSiguiente(cabeza);
            cabeza = nuevo;
        }
        // Aumentamos el tamaño de la lista
        tamaño++;
    }

    // Metodo get devuelve el elemento del número que pongamos
    public T get(int numero) {
        // Verificamos el numero, solo hace el get si es correcto
        if (verificarNumero(numero)) {
            Elemento<T> actual = cabeza;
            for (int i = 0; i < numero; i++) {
                actual = actual.getSiguiente();
            }
            return actual.getDato();
        }
        return null;
    }

    // Metodo pos devuelve el número de la posicion del primer elemento que tenga el dato que buscamos
    public int pos(T dato) {
        Elemento<T> actual = cabeza;
        int posicion = 0;

        // Mientras actual sea distinto de null (ultimo elemento)...
        while (actual != null) {
            // Si el dato actual es el mismo que buscamos, devuelve la posicion.
            if (actual.getDato().equals(dato)) {
                return posicion;
            }
            // Si no, pasamos al siguiente elemento.
            actual = actual.getSiguiente();
            posicion++;
        }
        // Si no lo encuentra, devuelve -1
        return -1;
    }

    // Metodo que elimina el elemento de la posicion que digamos
    public T del(int posicion) {
        if (verificarNumero(posicion)) {
            T datoEliminado;

            if (posicion == 0) {
                // Eliminar la cabeza
                datoEliminado = cabeza.getDato();
                cabeza = cabeza.getSiguiente();
            } else {
                // Eliminar en cualquier otra posición
                Elemento<T> actual = cabeza;
                for (int i = 0; i < posicion - 1; i++) {
                    actual = actual.getSiguiente();
                }
                Elemento<T> elementoAEliminar = actual.getSiguiente();
                datoEliminado = elementoAEliminar.getDato();
                // Saltamos el elemento para desenlazarlo
                actual.setSiguiente(elementoAEliminar.getSiguiente());
            }

            tamaño--;
            return datoEliminado;
        }
        else return null;
    }

    // Metodo para limpiar la lista
    public void clear() {
        cabeza = null;
        tamaño = 0;
    }

    // Metodo que devuelve el tamaño de la lista
    public int size() {
        return tamaño;
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

    // Metodo para añadir un elemento en una posicion específica
    public void insert(T dato, int posicion) {
        if (verificarNumero(posicion) || posicion == tamaño) {
            // Si quiere poner el elemento en la cabeza:
            if (posicion == 0) addFirst(dato);
            else if (posicion == tamaño) addLast(dato);
            else {
                // Creamos el elemento con el nuevo dato
                Elemento<T> nuevo = new Elemento<T>(dato);
                // Vamos hasta una posicion antes de la que buscamos
                Elemento<T> actual = cabeza;
                for (int i = 0; i < posicion - 1; i++) {
                    actual = actual.getSiguiente();
                }
                // Actual ahora es una posicion antes de donde queremos añadir el nuevo elemento
                // Ponemos el elemento de la posicion que queremos sustituir en nuevo.siguiente
                nuevo.setSiguiente(actual.getSiguiente());
                // Ponemos el nuevo elemento después del justo uno anterior
                actual.setSiguiente(nuevo);
                tamaño++;
            }
        }
    }

    // Metodo para comprobar que el número que damos es menor que el tamaño
    private boolean verificarNumero(int numero) {
        // Si está en el tamaño de la lista devuelve true
        if (numero >= 0 && numero < tamaño) return true;
        // Si no, devuelve false
        else return false;
    }
}