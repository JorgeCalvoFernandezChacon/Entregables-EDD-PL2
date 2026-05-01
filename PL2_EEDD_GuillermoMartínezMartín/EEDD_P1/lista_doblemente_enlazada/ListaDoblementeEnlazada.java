package EEDD_P1.lista_doblemente_enlazada;

public class ListaDoblementeEnlazada<T> {

    /*
     * Variables
     */

    /// cabeza: que es el primer elemento de la lista
    private Elemento<T> cabeza;
    /// ultimo: que es el ultimo elemento de la cola; se usará para añadir al final más fácilmente
    private Elemento<T> ultimo;
    /// tamaño: que es el tamaño de la lista
    private int tamaño;

    /*
     * Métodos
     */

    // Creamos el Constructor
    public ListaDoblementeEnlazada() {
        // Por defecto inicializamos la lista vacía (cabeza = null = ultimo)
        this.cabeza = null;
        this.ultimo = null;
        this.tamaño = 0;
    }

    // Metodo para añadir un elemento al final de la lista
    public void addLast(T dato) {
        // Primero nos protegemos ante la posible entrada de un elemento null:
        if (dato == null) return;
        Elemento<T> nuevo = new Elemento<T>(dato);
        if (cabeza == null) {
            cabeza = nuevo;
            ultimo = nuevo;
        } else {
            ultimo.setSiguiente(nuevo);
            nuevo.setAnterior(ultimo);
            ultimo = nuevo;
        }
        // Aumentamos el tamaño de la lista
        tamaño++;
    }

    // Metodo para añadir un elemento al inicio de la lista
    public void addFirst(T dato) {
        // Primero nos protegemos ante la posible entrada de un elemento null
        if (dato == null) return;
        Elemento<T> nuevo = new Elemento<T>(dato);
        // Si está vacía, la cabeza y ultimo es nulo
        if (cabeza == null) {
            cabeza = nuevo;
            ultimo = nuevo;
        // Si no, nuevo será la nueva cabeza
        } else {
            cabeza.setAnterior(nuevo);
            nuevo.setSiguiente(cabeza);
            cabeza = nuevo;
        }
        // Aumentamos el tamaño de la lista
        tamaño++;
    }

    // Metodo para recibir el dato en la posicion que buscamos
    public T get(int posicion) {
        if (verificarNumero(posicion)) {
            Elemento<T> actual = cabeza;
            for (int i = 0; i < posicion; i++) {
                actual = actual.getSiguiente();
            }
            return actual.getDato();
        }
        else return null;
    }

    // Metodo para eliminar un elemento de la lista
    public T del(int posicion) {
        // Verificamos que la posicion sea válida
        if (verificarNumero(posicion)) {
            if (posicion == 0) {
                // Borramos la cabeza
                Elemento<T> ElementoAEliminar = cabeza;
                // Asignamos la nueva cabeza
                cabeza = ElementoAEliminar.getSiguiente();
                // Si la nueva cabeza no es nula, su anterior será null
                if (cabeza != null) cabeza.setAnterior(null);
                // Si la cabeza es nula, el último también será null
                else ultimo = null;
                // Devolvemos el elemento eliminado y restamos tamaño
                tamaño--;
                return ElementoAEliminar.getDato();
            }
            else {
                // Buscamos el elemento
                Elemento<T> actual = cabeza;
                for (int i = 0; i < posicion; i++) {
                    actual = actual.getSiguiente();
                }
                // Aquí es donde se borra el elemento
                // Establecemos los dos enlaces como variables
                Elemento<T> elementoEliminado = actual;
                Elemento<T> elementoAnterior = actual.getAnterior();
                Elemento<T> elementoSiguiente = actual.getSiguiente();
                // Unimos el anterior y el siguiente
                elementoAnterior.setSiguiente(elementoSiguiente);
                if (elementoSiguiente != null) elementoSiguiente.setAnterior(elementoAnterior);
                // Si no hay siguiente, es que se ha borrado al ultimo
                else ultimo = elementoAnterior;
                tamaño--;
                return elementoEliminado.getDato();
            }
        }
        else return null;
    }

    // Metodo para buscar en que posicion está un dato
    public int pos(T dato) {
        // Establecemos actual como la cabeza de la lista
        Elemento<T> actual = cabeza;
        int posicion = 0;
        // Recorremos la lista hasta que sea igual el dato que buscamos con el dato del elemento actual.
        while (actual != null) {
            if (actual.getDato().equals(dato)) return posicion;
            actual = actual.getSiguiente();
            posicion++;
        }
        // Si no lo encuentra devolverá -1
        return -1;
    }

    // Metodo para limpiar la lista
    public void clear() {
        cabeza = null;
        ultimo = null;
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

    // Metodo para añadir un elemento en una posicion específica
    public void insert(T dato, int posicion) {
        if (verificarNumero(posicion) || posicion == tamaño) {
            // Si quiere poner el elemento en la cabeza:
            if (posicion == 0) addFirst(dato);
            else if (posicion == tamaño) addLast(dato);
            else {
                // Creamos el elemento con el nuevo dato
                Elemento<T> nuevo = new Elemento<T>(dato);
                // Vamos hasta la posicion que buscamos
                Elemento<T> actual = cabeza;
                for (int i = 0; i < posicion; i++) {
                    actual = actual.getSiguiente();
                }
                // Actual ahora es la posicion donde queremos añadir el nuevo elemento
                // Cambiamos los enlaces con siguiente y anterior
                nuevo.setSiguiente(actual);
                nuevo.setAnterior(actual.getAnterior());
                // Ponemos el nuevo elemento en la lista
                actual.getAnterior().setSiguiente(nuevo);
                actual.setAnterior(nuevo);
                tamaño++;
            }
        }
    }

    // Metodo para verificar si un número es menor que el tamaño de la lista y mayor que 0
    private boolean verificarNumero(int numero) {
        // Si está en el tamaño de la lista devuelve true
        if (numero >= 0 && numero < tamaño) return true;
            // Si no, devuelve false
        else return false;
    }

    // Metodo para devolver el tamaño de la lista
    public int size() {
        return tamaño;
    }
}
