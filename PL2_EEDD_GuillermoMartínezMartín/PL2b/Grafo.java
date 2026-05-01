package PL2b;

import com.google.gson.Gson;
import EEDD_P1.cola.Cola;
import EEDD_P1.lista_simplemente_enlazada.ListaSimplementeEnlazada;

import java.util.*;

public class Grafo {
    // Usamos Map para poder buscar por nombre
    private Map<String, Nodo> nodos;

    public  Grafo() {
        this.nodos = new HashMap<>();
    }

    public void addTripleta(String sujeto, String predicado, String objeto) {
        nodos.putIfAbsent(sujeto, new Nodo(sujeto));
        nodos.putIfAbsent(objeto, new Nodo(objeto));

        Nodo nodoSujeto = nodos.get(sujeto);
        Nodo nodoObjeto = nodos.get(objeto);

        nodoSujeto.agregarConexion(predicado, nodoObjeto);
    }

    // Getters
    public Nodo getNodo(String nombre) {
        return nodos.get(nombre);
    }

    public Map<String, Nodo> getNodos() {
        return nodos;
    }

    public void cargarDesdeJSON(String ruta) {
        Gson gson = new Gson();

        try (java.io.FileReader reader = new java.io.FileReader(ruta)) {
            DatosJSON datos = gson.fromJson(reader, DatosJSON.class);

            if (datos != null && datos.tripletas != null) {
                for (TripletaDTO t : datos.tripletas) {
                    this.addTripleta(t.s, t.p, t.o);
                }
            }
            System.out.println("Grafo cargado correctamente.");
        } catch (java.io.IOException e) {
            System.out.println("Error al leer el archivo: " + e.getMessage());
        }
    }

    // Camino mínimo
    public ListaSimplementeEnlazada<String> getCaminoMinimo(String origen, String destino) {
        if (!nodos.containsKey(origen) || !nodos.containsKey(destino)) return null;

        if (origen.equals(destino)) {
            ListaSimplementeEnlazada<String> rutaUnica = new ListaSimplementeEnlazada<>();
            rutaUnica.addLast(origen);
            return rutaUnica;
        }

        Cola<String> cola = new Cola<>();
        Map<String, String> padres = new HashMap<>();
        Set<String> visitados = new HashSet<>();

        cola.encolar(origen);
        visitados.add(origen);
        padres.put(origen, null);

        while (cola.size() > 0) {
            String actual = cola.desencolar();

            if (actual.equals(destino)) {
                return construirRuta(padres, destino);
            }

            for (Arista arista : nodos.get(actual).getConexiones()) {
                String vecino = arista.getDestino().getNombre();
                if (!visitados.contains(vecino)) {
                    visitados.add(vecino);
                    padres.put(vecino, actual);
                    cola.encolar(vecino);
                }
            }
        }
        return null; // No hay conexión
    }

    private ListaSimplementeEnlazada<String> construirRuta(Map<String, String> padres, String destino) {
        ListaSimplementeEnlazada<String> ruta = new ListaSimplementeEnlazada<>();
        String paso = destino;
        while (paso != null) {
            ruta.addFirst(paso);
            paso = padres.get(paso);
        }
        return ruta;
    }

    // Disjunto
    public boolean isDisjunto() {
        if (nodos.isEmpty()) return false;

        String inicio = nodos.keySet().iterator().next();
        Set<String> visitados = new HashSet<>();
        Cola<String> cola = new Cola<>();

        cola.encolar(inicio);
        visitados.add(inicio);

        while (cola.size() > 0) {
            String actual = cola.desencolar();

            Nodo actualNodo = nodos.get(actual);
            if (actualNodo != null) {
                for (Arista arista : actualNodo.getConexiones()) {
                    String vecino = arista.getDestino().getNombre();
                    if (visitados.add(vecino)) {
                        cola.encolar(vecino);
                    }
                }
            }

            for (String nombreOtro : nodos.keySet()) {
                Nodo otroNodo = nodos.get(nombreOtro);
                if (otroNodo != null) {
                    for (Arista arista : otroNodo.getConexiones()) {
                        if (arista.getDestino().getNombre().equals(actual)) {
                            if (visitados.add(nombreOtro)) {
                                cola.encolar(nombreOtro);
                            }
                        }
                    }
                }
            }
        }

        return visitados.size() < nodos.size();
    }
}