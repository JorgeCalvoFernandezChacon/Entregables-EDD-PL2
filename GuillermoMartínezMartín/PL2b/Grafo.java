package PL2b;

import com.google.gson.Gson;

import java.util.*;

public class Grafo {
    // Usamos Map para poder buscar por nombre
    private Map<String, Nodo> nodos;

    public  Grafo() {
        this.nodos = new HashMap<>();
    }

    public void addTripleta(String sujeto, String predicado, String objeto) {
        // Añadimos los elementos si estos no existen
        nodos.putIfAbsent(sujeto, new Nodo(sujeto));
        nodos.putIfAbsent(objeto, new Nodo(objeto));

        // Creamos la relación entre ellos
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
            // Leemos el JSON y lo convertimos a nuestra clase DatosJSON
            DatosJSON datos = gson.fromJson(reader, DatosJSON.class);

            // Recorremos las tripletas y usamos la función addTripleta
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
    public List<String> getCaminoMinimo(String origen, String destino) {
        if (!nodos.containsKey(origen) || !nodos.containsKey(destino)) return null;
        if (origen.equals(destino)) return Arrays.asList(origen);

        Queue<String> cola = new LinkedList<>();
        Map<String, String> padres = new HashMap<>(); // Para reconstruir la ruta
        Set<String> visitados = new HashSet<>();

        cola.add(origen);
        visitados.add(origen);
        padres.put(origen, null);

        while (!cola.isEmpty()) {
            String actual = cola.poll();

            if (actual.equals(destino)) {
                return construirRuta(padres, destino); // Si lo encontramos
            }

            for (Arista arista : nodos.get(actual).getConexiones()) {
                String vecino = arista.getDestino().getNombre();
                if (!visitados.contains(vecino)) {
                    visitados.add(vecino);
                    padres.put(vecino, actual);
                    cola.add(vecino);
                }
            }
        }
        return null; // No hay conexión
    }
    private List<String> construirRuta(Map<String, String> padres, String destino) {
        LinkedList<String> ruta = new LinkedList<>();
        String paso = destino;
        while (paso != null) {
            ruta.addFirst(paso); // Insertamos al principio para que salga en orden Origen -> Destino
            paso = padres.get(paso);
        }
        return ruta;
    }

    // Disjunto
    public boolean isDisjunto() {
        if (nodos.isEmpty()) return false;

        String inicio = nodos.keySet().iterator().next();
        Set<String> visitados = new HashSet<>();
        Queue<String> cola = new LinkedList<>();

        cola.add(inicio);
        visitados.add(inicio);

        while (!cola.isEmpty()) {
            String actual = cola.poll();

            Nodo actualNodo = nodos.get(actual);
            if (actualNodo != null) {
                for (Arista arista : actualNodo.getConexiones()) {
                    String vecino = arista.getDestino().getNombre();
                    if (visitados.add(vecino)) {
                        cola.add(vecino);
                    }
                }
            }

            for (String nombreOtro : nodos.keySet()) {
                Nodo otroNodo = nodos.get(nombreOtro);
                if (otroNodo != null) {
                    for (Arista arista : otroNodo.getConexiones()) {
                        if (arista.getDestino().getNombre().equals(actual)) {
                            if (visitados.add(nombreOtro)) {
                                cola.add(nombreOtro);
                            }
                        }
                    }
                }
            }
        }

        // Si después de navegar hacia adelante y hacia atrás no hemos tocado todos, está roto
        return visitados.size() < nodos.size();
    }
}
