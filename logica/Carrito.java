package logica;

import java.util.ArrayList;
import java.util.Locale;

public class Carrito {

    private final int capacidadMaxima;
    private final ArrayList<LineaCarrito> lineas;
    private final Inventario inventario;

    public Carrito(int capacidad, Inventario inventario) {
        if (capacidad > 0) {
            this.capacidadMaxima = capacidad;
        } else {
            this.capacidadMaxima = 1;
        }
        this.lineas = new ArrayList<>(this.capacidadMaxima);
        this.inventario = inventario;
    }

    public boolean agregar(String id, int cantidad) {
        // TODO(autograding): Validar entrada, retirar del inventario y agregar/incrementar línea.
        // Pista: si el id no está en carrito y ya se alcanzó la capacidad, debe fallar.
        return false;
    }

    public boolean agregar(Producto producto, int cantidad) {
        if (producto == null) {
            return false;
        }
        return agregar(producto.getId(), cantidad);
    }

    public boolean agregar(Videojuego juego, int cantidad) {
        return agregar((Producto) juego, cantidad);
    }

    public boolean eliminar(String id, int cantidad) {
        // TODO(autograding): Devolver stock al inventario y reducir cantidad de la línea.
        // Pista: cuando la cantidad quede en cero, elimina la línea del ArrayList.
        return false;
    }

    public int getCantidad(String id) {
        int idx = indexOf(id);
        if (idx < 0) {
            return 0;
        }
        return lineas.get(idx).getCantidad();
    }

    public double total() {
        // TODO(autograding): Acumular subtotal por línea (precio * cantidad).
        // Pista: recorre lineas y suma en una variable acumuladora.
        return 0.0;
    }

    public double totalConDescuento(double porcentaje) {
        // TODO(autograding): Aplicar descuento porcentual solo en rango [0,100].
        // Pista: reutiliza total() para evitar duplicar cálculo.
        return total();
    }

    public double totalConDescuento(String cupon) {
        return totalConDescuento(porcentajeSegunCupon(cupon));
    }

    public void mostrarCarrito() {
        mostrarCarrito(null);
    }

    public void mostrarCarrito(String cupon) {
        double porcentajeDescuento = porcentajeSegunCupon(cupon);
        double totalSinDescuento = total();

        System.out.printf(Locale.US, "%-10s %-30s %5s %12s %12s%n",
                "id", "descripcion", "cant", "p.unitario", "Subtotal");
        System.out.println("--------------------------------------------------------------------------");

        for (LineaCarrito linea : lineas) {
            Producto producto = linea.getProducto();
            double subtotalLinea = producto.getPrecio() * linea.getCantidad();
            System.out.printf(Locale.US, "%-10s %-30s %5d %12.2f %12.2f%n",
                    producto.getId(), producto.getNombre(), linea.getCantidad(),
                    producto.getPrecio(), subtotalLinea);
        }

        System.out.println("--------------------------------------------------------------------------");
        System.out.printf(Locale.US, "%-10s %-30s %5s %12s %12.2f%n",
                "", "TOTAL", "", "", totalSinDescuento);

        if (porcentajeDescuento > 0.0) {
            double totalConDescuento = totalSinDescuento * (1.0 - porcentajeDescuento / 100.0);
            double montoDescuento = totalSinDescuento - totalConDescuento;
            String etiquetaDescuento;
            if (cupon == null || cupon.isBlank()) {
                etiquetaDescuento = String.format(Locale.US, "DESCUENTO %.0f%%", porcentajeDescuento);
            } else {
                etiquetaDescuento = String.format(Locale.US, "DESCUENTO %s (%.0f%%)",
                        cupon.trim().toUpperCase(Locale.ROOT), porcentajeDescuento);
            }

            System.out.printf(Locale.US, "%-10s %-30s %5s %12s %12.2f%n",
                    "", etiquetaDescuento, "", "", -montoDescuento);
            System.out.printf(Locale.US, "%-10s %-30s %5s %12s %12.2f%n",
                    "", "TOTAL CON DESCUENTO", "", "", totalConDescuento);
        }
    }

    public int getTamanio() {
        return lineas.size();
    }

    public String idEnPosicion(int i) {
        if (i < 0 || i >= lineas.size()) {
            return null;
        }
        return lineas.get(i).getProducto().getId();
    }

    public int cantidadEnPosicion(int i) {
        if (i < 0 || i >= lineas.size()) {
            return -1;
        }
        return lineas.get(i).getCantidad();
    }

    public Producto productoEnPosicion(int i) {
        if (i < 0 || i >= lineas.size()) {
            return null;
        }
        return lineas.get(i).getProducto();
    }

    private int indexOf(String id) {
        // TODO(autograding): Buscar posición de la línea por id ignorando mayúsculas/minúsculas.
        // Pista: retorna -1 cuando no exista coincidencia.
        return -1;
    }

    private double porcentajeSegunCupon(String cupon) {
        // TODO(autograding): Mapear cupones válidos a porcentaje de descuento.
        // Pista: REGALO10 y GAMER10 => 10; TECH20 y GAMER20 => 20.
        return 0.0;
    }
}