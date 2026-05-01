package PL2b;
import java.util.ArrayList;
import java.util.List;

public class Nodo {
    private String nombre;
    private List<Arista> conexiones;

    // COnstructor
    public Nodo(String nombre) {
        this.nombre = nombre;
        this.conexiones = new ArrayList<>();
    }

    // Getter y setter nombre
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    // Getter y agregar conexiones
    public List<Arista> getConexiones() {
        return conexiones;
    }
    public void agregarConexion(String predicado, Nodo destino) {
        this.conexiones.add(new Arista(predicado, destino));
    }
}
