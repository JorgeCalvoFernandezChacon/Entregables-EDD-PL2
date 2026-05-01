package org.example;

public class Grafo {
    // Lista donde guardamos todos los nodos que vamos creando
    private Lista_simple_enla<Nodo> todosLosNodos;
    public Grafo() {
        this.todosLosNodos = new Lista_simple_enla<>();
    }
    public void cargarSimulandoArchivo(String nombreArchivo) {
        String contenido = "";
        if (nombreArchivo.equals("junto.json")) {
            contenido = "{\"sujeto\": \"persona:Einstein\", \"predicado\": \"nace_en\", \"objeto\": \"lugar:Ulm\"}," +
                    "{\"sujeto\": \"persona:Einstein\", \"predicado\": \"profesion\", \"objeto\": \"profesion:Fisico\"}";
        }
        else if (nombreArchivo.equals("disjunto.json")) {
            contenido = "{\"sujeto\": \"persona:Einstein\", \"predicado\": \"nace_en\", \"objeto\": \"lugar:Ulm\"}," +
                    "{\"sujeto\": \"persona:Marie_Curie\", \"predicado\": \"nace_en\", \"objeto\": \"lugar:Varsovia\"}";
        }
        else if (nombreArchivo.equals("nobel.json")) {
            contenido = "{\"sujeto\": \"persona:Einstein\", \"predicado\": \"nace_en\", \"objeto\": \"lugar:Ulm\"}," +
                    "{\"sujeto\": \"persona:Einstein\", \"predicado\": \"premio\", \"objeto\": \"premio:Nobel\"}," +
                    "{\"sujeto\": \"persona:Hans_K\", \"predicado\": \"nace_en\", \"objeto\": \"lugar:Ulm\"}," +
                    "{\"sujeto\": \"persona:Hans_K\", \"predicado\": \"profesion\", \"objeto\": \"profesion:Fisico\"}";
        }

        // Procesamos ese texto para rellenar el grafo
        String[] objetos = contenido.split("\\}");
        for (int i = 0; i < objetos.length; i++) {
            if (objetos[i].contains("sujeto")) {
                String s = extraerCampo(objetos[i], "sujeto");
                String p = extraerCampo(objetos[i], "predicado");
                String o = extraerCampo(objetos[i], "objeto");
                this.agregarTripleta(s, p, o);
            }
        }
    }
    // Función auxiliar para sacar el texto entre comillas
    private String extraerCampo(String texto, String clave) {
        int pos = texto.indexOf(clave);
        int inicio = texto.indexOf(":", pos) + 3;
        int fin = texto.indexOf("\"", inicio);
        return texto.substring(inicio, fin);
    }
    public void agregarTripleta(String sId, String p, String oId) {
        Nodo s = obtenerOcrear(sId);
        Nodo o = obtenerOcrear(oId);
        // Creamos la conexión de ida y la de vuelta para poder navegar por el grafo
        s.agregarArista(new Arista(o, p, "out"));
        o.agregarArista(new Arista(s, p, "in"));
    }
    private Nodo obtenerOcrear(String id) {
        // Buscamos si el nodo ya existe en nuestra lista[cite: 8]
        int i = 0;
        Nodo temp;
        while ((temp = todosLosNodos.get(i)) != null) {
            if (temp.getId().equals(id)) return temp;
            i++;
        }
        // Si no existe, lo creamos y lo añadimos[cite: 8]
        Nodo nuevo = new Nodo(id);
        todosLosNodos.add(nuevo);
        return nuevo;
    }

    // Algoritmo para ver si el grafo está partido (disjunto)
    public boolean esDisjunto() {
        if (todosLosNodos.get(0) == null)
            return true;
        Lista_simple_enla<String> visitados = new Lista_simple_enla<>();
        Cola<Nodo> c = new Cola<>();
        c.add(todosLosNodos.get(0));
        int cont = 0;
        while (c.get(0) != null) {
            Nodo actual = c.get(0);
            c.del(0); // Sacamos el primero de la cola[cite: 1]
            if (!yaVisitado(visitados, actual.getId())) {
                visitados.add(actual.getId());
                cont++;
                // Metemos a los vecinos en la cola para seguir el camino
                int j = 0;
                Arista a;
                while ((a = actual.getAristas().get(j)) != null) {
                    c.add(a.getDestino());
                    j++;
                }
            }
        }
        // Comparamos los visitados con el total de la lista[cite: 8]
        int total = 0;
        while (todosLosNodos.get(total) != null) total++;
        return cont < total;
    }
    private boolean yaVisitado(Lista_simple_enla<String> lista, String id) {
        int i = 0;
        String s;
        while ((s = lista.get(i)) != null) {
            if (s.equals(id)) return true;
            i++;
        }
        return false;
    }
}