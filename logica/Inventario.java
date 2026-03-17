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
        if (cantidad <= 0) {
            return false;
        }
        int idx = indexOf(id);
        if (idx < 0) {
            return false;
        }
        return stock.get(idx) >= cantidad;
    }

    public boolean retirar(String id, int cantidad) {
        if (cantidad <= 0) {
            return false;
        }
        int idx = indexOf(id);
        if (idx < 0 || stock.get(idx) < cantidad) {
            return false;
        }
        stock.set(idx, stock.get(idx) - cantidad);
        return true;
    }

    public boolean reabastecer(String id, int cantidad) {
        if (cantidad <= 0) {
            return false;
        }
        int idx = indexOf(id);
        if (idx < 0) {
            return false;
        }
        stock.set(idx, stock.get(idx) + cantidad);
        return true;
    }

    public Producto obtener(String id) {
        int idx = indexOf(id);
        if (idx < 0) {
            return null;
        }
        return catalogo.get(idx);
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
        if (id == null || id.isBlank()) {
            return -1;
        }
        String buscado = id.trim();
        for (int i = 0; i < catalogo.size(); i++) {
            if (catalogo.get(i).getId().equalsIgnoreCase(buscado)) {
                return i;
            }
        }
        return -1;
    }

    private boolean validarRegistro(Producto producto, int existenciasIniciales) {
        if (producto == null || !producto.tieneDatosBasicosValidos()) {
            return false;
        }
        if (existenciasIniciales < 0) {
            return false;
        }
        if (catalogo.size() >= capacidadMaxima) {
            return false;
        }
        return indexOf(producto.getId()) < 0;
    }
}