package EEDD_P1.interfaces;

public interface InterfazListaCircular<T> {
    void add(T dato);
    T get(int posicion);
    T del(int posicion);
    int pos(T dato);
    void clear();
    String toString();
    void insert(T dato, int posicion);
    int size();
    void rotate(int pasos);
}
