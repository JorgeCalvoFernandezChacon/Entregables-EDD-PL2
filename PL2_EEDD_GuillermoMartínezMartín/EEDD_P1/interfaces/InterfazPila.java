package EEDD_P1.interfaces;

public interface InterfazPila<T> {
    void push(T dato);
    T pop();
    T top();
    int pos(T dato);
    T get(int posicion);
    void clear();
    String toString();
    int size();
}
