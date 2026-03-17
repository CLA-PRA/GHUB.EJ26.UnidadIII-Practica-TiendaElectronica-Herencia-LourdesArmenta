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
        if (inventario == null || cantidad <= 0) {
            return false;
        }
        Producto producto = inventario.obtener(id);
        if (producto == null) {
            return false;
        }
        int idx = indexOf(id);
        if (idx < 0 && lineas.size() >= capacidadMaxima) {
            return false;
        }
        if (!inventario.retirar(id, cantidad)) {
            return false;
        }
        if (idx >= 0) {
            lineas.get(idx).incrementar(cantidad);
        } else {
            lineas.add(new LineaCarrito(producto, cantidad));
        }
        return true;
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
        if (inventario == null || cantidad <= 0) {
            return false;
        }
        int idx = indexOf(id);
        if (idx < 0) {
            return false;
        }
        LineaCarrito linea = lineas.get(idx);
        if (linea.getCantidad() < cantidad) {
            return false;
        }
        if (!inventario.reabastecer(id, cantidad)) {
            return false;
        }
        linea.reducir(cantidad);
        if (linea.getCantidad() == 0) {
            lineas.remove(idx);
        }
        return true;
    }

    public int getCantidad(String id) {
        int idx = indexOf(id);
        if (idx < 0) {
            return 0;
        }
        return lineas.get(idx).getCantidad();
    }

    public double total() {
        double suma = 0.0;
        for (LineaCarrito linea : lineas) {
            suma += linea.getProducto().getPrecio() * linea.getCantidad();
        }
        return suma;
    }

    public double totalConDescuento(double porcentaje) {
        double porcentajeValido = porcentaje;
        if (porcentajeValido < 0 || porcentajeValido > 100) {
            porcentajeValido = 0.0;
        }
        return total() * (1.0 - porcentajeValido / 100.0);
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
        if (id == null || id.isBlank()) {
            return -1;
        }
        String buscado = id.trim();
        for (int i = 0; i < lineas.size(); i++) {
            if (lineas.get(i).getProducto().getId().equalsIgnoreCase(buscado)) {
                return i;
            }
        }
        return -1;
    }

    private double porcentajeSegunCupon(String cupon) {
        if (cupon == null) {
            return 0.0;
        }
        switch (cupon.trim().toUpperCase(Locale.ROOT)) {
            case "REGALO10":
            case "GAMER10":
                return 10.0;
            case "TECH20":
            case "GAMER20":
                return 20.0;
            default:
                return 0.0;
        }
    }
}