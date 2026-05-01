package EEDD_P1.lista_circular;


public class ListaCircular<T> {

    /*
     * Variables
     */

    private Elemento<T> cabeza;
    private Elemento<T> ultimo;
    private int tamaño;

    /*
     * Métodos
     */

    // Creamos el constructor
    public ListaCircular() {
        this.cabeza = null;
        this.ultimo = null;
        this.tamaño = 0;
    }

    // Metodo para añadir al final
    public void add(T dato) {
        // Primero nos protegemos ante la posible entrada de un elemento null:
        if (dato == null) return;
        // Creamos un nuevo elemento con el dato
        Elemento<T> nuevo = new Elemento<T>(dato);
        // Si la lista no tiene ningún elemento, cabeza y último es el nuevo elemento
        if (cabeza == null) {
            cabeza = nuevo;
            ultimo = nuevo;
        }
        // Si no, el nuevo elemento ahora es el último
        else {
            ultimo.setSiguiente(nuevo);
            ultimo = nuevo;

        }
        // El último elemento siempre lo actualizamos para que el siguiente sea la cabeza
        ultimo.setSiguiente(cabeza);
        tamaño++;
    }

    // Metodo para eliminar el elemento de una posicion
    public T del(int posicion) {
        if (verificarNumero(posicion)) {
            // Si quieremos borrar la cabeza
            if (posicion == 0) {
                Elemento<T> elementoEliminar = cabeza;
                if (tamaño == 1) {
                    // Si hay un único elemento, este será nulo ahora
                    cabeza = null;
                    ultimo = null;
                    // Restamos el tamaño
                    tamaño--;
                    // Devolvemos el dato del elemento eliminado
                    return elementoEliminar.getDato();
                } else {
                    // Si no, el último apuntará a la nueva cabeza.
                    cabeza = cabeza.getSiguiente();
                    ultimo.setSiguiente(cabeza);
                    // Restamos al tamaño
                    tamaño--;
                    // Devolvemos el dato del elemento eliminado
                    return elementoEliminar.getDato();
                }
            }
            // Si no queremos borrar la cabeza
            else {
                Elemento<T> actual = cabeza;
                // Me paro un elemento antes del que quiero eliminar
                for (int i = 0; i < posicion - 1; i++) {
                    actual = actual.getSiguiente();
                }
                // El siguiente del actual será el siguiente del que quiero eliminar.
                Elemento<T> elementoEliminar = actual.getSiguiente();
                // Desenlazamos, el actual salta al elemento que le sigue al que borramos
                actual.setSiguiente(elementoEliminar.getSiguiente());
                // Restamos al tamaño
                tamaño--;
                // Si el que queremos eliminar es el último, deberemos llamar último al actual
                if (elementoEliminar == ultimo) ultimo = actual;
                // Devolvemos el dato del elemento eliminado
                return elementoEliminar.getDato();
            }
        }
        // Si la posicion no es válida, devuelve null
        else return null;
    }

    // Metodo para recibir el dato de una posicion
    public T get(int posicion) {
        // Primero verificamos que el numero sea valido
        if (verificarNumero(posicion)) {
            Elemento<T> actual = cabeza;
            // Nos paramos en la posicion
            for (int i = 0; i < posicion; i++) {
                actual = actual.getSiguiente();
            }
            return actual.getDato();
        } else return null;
    }

    // Metodo para saber la posicion del primer dato
    public int pos(T dato) {
        Elemento<T> actual = cabeza;
        // Vamos hasta el final de la lista circular
        for (int i = 0; i < tamaño; i++) {
            if (actual.getDato().equals(dato)) return i;
            actual = actual.getSiguiente();
        }
        // Si no lo encuentra, devuelve -1
        return -1;
    }

    // Metodo para limpiar la lista
    public void clear() {
        cabeza = null;
        ultimo = null;
        tamaño = 0;
    }

    // Metodo rotar "n" posiciones
    public void rotate(int pasos) {
        if (tamaño > 1 && pasos > 0) {
            // Simplificamos los pasos con el módulo sobre el tamaño
            pasos = pasos % tamaño;
            // Damos los pasos
            for (int i = 0; i < pasos; i++) {
                cabeza = cabeza.getSiguiente();
                ultimo = ultimo.getSiguiente();
            }
        }
    }

    // Metodo para ver el tamaño de la lista
    public int size() { return tamaño; }

    // Metodo para añadir un elemento en una posicion específica
    public void insert(T dato, int posicion) {
        // No permitimos valores mayores al tamaño porque dará error.
        if (verificarNumero(posicion) || posicion == tamaño) {
            // Si lo queremos añadir al principio, añadimos al final y giramos uno hacia atrás.
            if (posicion == 0) {
                add(dato);
                rotate(tamaño - 1);
            } else {
                // Si no, creamos el elemento con el nuevo dato
                Elemento<T> nuevo = new Elemento<T>(dato);
                Elemento<T> actual = cabeza;
                // Recorremos la lista hasta donde queremos añadirlo
                for (int i = 0; i < posicion - 1; i++) {
                    actual = actual.getSiguiente();
                }
                // Actual ahora es una posicion antes de donde queremos añadir el nuevo elemento
                // Ponemos el elemento de la posicion que queremos sustituir en nuevo.siguiente
                nuevo.setSiguiente(actual.getSiguiente());
                // Ponemos el nuevo elemento después del justo uno anterior
                actual.setSiguiente(nuevo);
                // Si lo que hemos actualizado es el último elemento, actualizamos la variable "ultimo"
                if (posicion == tamaño) ultimo = nuevo;
                // Aumentamos el tamaño
                tamaño++;
            }
        }
    }

    // Metodo para mostrar la lista
    public String toString() {
        Elemento<T> actual = cabeza;
        // Si la lista es vacía, devuelve [].
        if (cabeza == null) return "[]";
        // Si no, la recorremos añadiendo el dato y ", " en todos menos el último.
        String resultado = "[";
        for (int i = 0; i < tamaño ; i++) {
            resultado += actual.getDato();
            if (i < tamaño - 1) resultado += ", ";
            actual = actual.getSiguiente();
        }
        resultado += "]";
        return resultado;
    }

    // Metodo para comprobar que el némero que damos es menor que el tamaño
    private boolean verificarNumero(int numero) {
        // Si está en el tamaño de la lista devuelve true
        if (numero >= 0 && numero < tamaño) return true;
            // Si no, devuelve false
        else return false;
    }
}
