package logica;

import java.util.Locale;

public class Producto {

    private String id;
    private String nombre;
    private double precio;
    private String marca;

    protected Producto() {
        this("NA", "Producto sin nombre", 0.0, "Generica");
    }

    protected Producto(String id, String nombre, double precio, String marca) {
        this.id = limpiar(id);
        this.nombre = limpiar(nombre);
        if (precio >= 0) {
            this.precio = precio;
        } else {
            this.precio = 0.0;
        }
        this.marca = limpiarMarca(marca);
    }

    protected Producto(Producto otro) {
        if (otro == null) {
            this.id = "NA";
            this.nombre = "Producto sin nombre";
            this.precio = 0.0;
            this.marca = "Generica";
            return;
        }
        this.id = otro.id;
        this.nombre = otro.nombre;
        this.precio = otro.precio;
        this.marca = otro.marca;
    }

    private static String limpiar(String valor) {
        if (valor == null) {
            return "";
        }
        return valor.trim();
    }

    private static String limpiarMarca(String marca) {
        String marcaLimpia = limpiar(marca);
        if (marcaLimpia.isBlank()) {
            return "Generica";
        }
        return marcaLimpia;
    }

    public String getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public String getMarca() {
        return marca;
    }

    public void setNombre(String nombre) {
        String nombreLimpio = limpiar(nombre);
        if (!nombreLimpio.isBlank()) {
            this.nombre = nombreLimpio;
        }
    }

    public void setPrecio(double precio) {
        if (precio >= 0) {
            this.precio = precio;
        }
    }

    public void setMarca(String marca) {
        this.marca = limpiarMarca(marca);
    }

    public boolean tieneDatosBasicosValidos() {
        return !id.isBlank() && !nombre.isBlank() && precio >= 0;
    }

    public double precioConDescuento(double porcentaje) {
        // TODO(autograding): Validar que el porcentaje quede entre 0 y 100.
        // Pista: si es inválido, regresa el precio original sin cambios.
        double porcentajeValido = 0.0;
        return precio * (1.0 - porcentajeValido / 100.0);
    }

    public double precioConDescuento(String cupon) {
        // TODO(autograding): Traducir el cupón a porcentaje (10% o 20%).
        // Pista: normaliza el texto con trim + mayúsculas.
        double porcentaje = 0.0;
        return precioConDescuento(porcentaje);
    }

    public String getCategoria() {
        return "Producto";
    }

    public String getDetalleTecnico() {
        return "Sin detalle tecnico";
    }

    @Override
    public String toString() {
        return getCategoria() + ": " + id + " - " + nombre + " (" + marca + ", $"
                + String.format(Locale.US, "%.2f", precio) + ") | " + getDetalleTecnico();
    }
}