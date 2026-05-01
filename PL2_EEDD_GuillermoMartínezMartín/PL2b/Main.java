package PL2b;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        Grafo g = new Grafo();
        // Asegúrate de que el nombre del archivo coincida con el de tu carpeta
        g.cargarDesdeJSON("src/PL2b/archivo.json");

        // Verificamos si Einstein se cargó bien
        if (g.getNodo("persona:Albert Einstein") != null) {
            System.out.println("¡Einstein encontrado en el grafo!");
        }

        // Prueba 2
        System.out.println("\n--- INFERENCIA ---");
        resolverRetoEinstein(g);

        System.out.println("\n--- DISJUNTOS? ---");
        sonDisjuntos();
    }

    public static void resolverRetoEinstein(Grafo g) {
        String objetivo = "persona:Albert Einstein";
        Nodo nodoEinstein = g.getNodo(objetivo);

        if (nodoEinstein == null) return;

        // PASO 1: Encontrar la ciudad de Einstein
        String ciudadEinstein = "";
        for (Arista a : nodoEinstein.getConexiones()) {
            if (a.getPredicado().equals("nace_en")) {
                ciudadEinstein = a.getDestino().getNombre();
                break;
            }
        }

        System.out.println("Einstein nació en: " + ciudadEinstein);
        for (String nombreCandidato : g.getNodos().keySet()) {
            // 1. Protección contra nulos
            if (nombreCandidato == null) continue;
            if (nombreCandidato.equals(objetivo)) continue;

            Nodo candidato = g.getNodo(nombreCandidato);
            if (candidato == null) continue; // Por si acaso

            boolean mismoLugar = false;
            boolean esFisico = false;

            for (Arista a : candidato.getConexiones()) {
                // ¿Nació en la misma ciudad?
                if (a.getPredicado().equals("nace_en") && a.getDestino().getNombre().equals(ciudadEinstein)) {
                    mismoLugar = true;
                }
                // ¿Es físico?
                if (a.getPredicado().equals("profesion") && a.getDestino().getNombre().equals("concepto:fisico")) {
                    esFisico = true;
                }
            }

            if (mismoLugar && esFisico) {
                System.out.println("¡ENCONTRADO!: " + nombreCandidato + " también es físico y nació en " + ciudadEinstein);
            }
        }
    }

    public static void sonDisjuntos() {
        Grafo g1 = new Grafo();
        // Asegúrate de que el nombre del archivo coincida con el de tu carpeta
        g1.cargarDesdeJSON("src/PL2b/grafo_disjunto.json");

        Grafo g2 = new Grafo();
        // Asegúrate de que el nombre del archivo coincida con el de tu carpeta
        g2.cargarDesdeJSON("src/PL2b/grafo_noDisjunto.json");
        System.out.println("¿Es el grafo de archivo.json disjunto?: " + g1.isDisjunto());
        System.out.println("¿Es el grafo de archivo2_Disjunto.json disjunto?: " + g2.isDisjunto());
    }
}
