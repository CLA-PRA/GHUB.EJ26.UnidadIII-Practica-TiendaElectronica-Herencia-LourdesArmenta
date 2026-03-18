package logica;

import java.util.ArrayList;

public class Inventario {

    private final int capacidadMaxima;
    private final ArrayList<Producto> catalogo;
    private final ArrayList<Integer> stock;

    public Inventario(int capacidad) {
        if (capacidad > 0) {
            this.capacidadMaxima = capacidad;
        } else {
            this.capacidadMaxima = 1;
        }
        this.catalogo = new ArrayList<>(this.capacidadMaxima);
        this.stock = new ArrayList<>(this.capacidadMaxima);
    }

    public void registrar(Producto producto, int existenciasIniciales) {
        if (!validarRegistro(producto, existenciasIniciales)) {
            System.out.println("No se pudo registrar el producto.");
            return;
        }
        catalogo.add(producto);
        stock.add(existenciasIniciales);
    }

    public void registrar(Videojuego juego, int existenciasIniciales) {
        registrar((Producto) juego, existenciasIniciales);
    }

    public void registrar(String id, String titulo, double precio, int existenciasIniciales) {
        registrar(new Videojuego(id, titulo, precio), existenciasIniciales);
    }

    public int existencias(String id) {
        int idx = indexOf(id);
        if (idx < 0) {
            return -1;
        }
        return stock.get(idx);
    }

    public boolean hayStock(String id, int cantidad) {
        // TODO(autograding): Verificar cantidad válida, producto existente y stock suficiente.
        // Pista: usa indexOf(id) para encontrar la posición del producto.
        return false;
    }

    public boolean retirar(String id, int cantidad) {
        // TODO(autograding): Restar stock solo cuando exista producto y alcance inventario.
        // Pista: si la operación procede, actualiza stock con set(posicion, nuevoValor).
        return false;
    }

    public boolean reabastecer(String id, int cantidad) {
        // TODO(autograding): Sumar stock cuando la cantidad sea positiva y el id exista.
        // Pista: este método es el inverso conceptual de retirar(...).
        return false;
    }

    public Producto obtener(String id) {
        // TODO(autograding): Regresar el producto asociado al id o null si no existe.
        // Pista: primero localiza índice y luego consulta catalogo.
        return null;
    }

    public int getTamanio() {
        return catalogo.size();
    }

    public Producto enPosicion(int i) {
        if (i < 0 || i >= catalogo.size()) {
            return null;
        }
        return catalogo.get(i);
    }

    public int stockEnPosicion(int i) {
        if (i < 0 || i >= stock.size()) {
            return -1;
        }
        return stock.get(i);
    }

    private int indexOf(String id) {
        // TODO(autograding): Buscar linealmente por id ignorando mayúsculas/minúsculas.
        // Pista: recuerda validar null/blanco antes de iterar.
        return -1;
    }

    private boolean validarRegistro(Producto producto, int existenciasIniciales) {
        // TODO(autograding): Validar datos del producto, stock inicial y capacidad.
        // Pista: también debes rechazar ids duplicados en el catálogo.
        return false;
    }
}