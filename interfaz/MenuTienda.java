package interfaz;

import logica.Carrito;
import logica.Computadora;
import logica.ConsolaVideojuego;
import logica.Inventario;
import logica.Producto;
import logica.Tableta;
import logica.Telefono;
import logica.Videojuego;

import java.util.Locale;

public class MenuTienda {

    private final Lector in = new Lector();
    private final Inventario inventario;
    private final Carrito carrito;

    public MenuTienda(Inventario inventario, Carrito carrito) {
        this.inventario = inventario;
        this.carrito = carrito;
    }

    public void correr() {
        int opcion;
        do {
            mostrarMenu();
            opcion = in.leerEntero("Elige una opción: ");
            
                switch (opcion) {
                    case 1 : registrarProducto();
                        break;
                    case 2 : listarCatalogo();
                        break;
                    case 3 : reabastecer();
                        break;
                    case 4 : retirar();
                        break;
                    case 5 : agregarAlCarritoPorId();
                        break;
                    case 6 : agregarAlCarritoPorObjeto();
                        break;
                    case 7 : eliminarDelCarrito();
                        break;
                    case 8 : verCarrito();
                        break;
                    case 9 : totalSinDescuento();
                         break;
                    case 10 : totalConPorcentaje();
                        break;
                    case 11 : totalConCupon();
                         break;
                    case 0 : System.out.println("Saliendo... ¡Gracias!");
                        break;
                    default : System.out.println("Opción no válida.");
                }
            
            System.out.println();
        } while (opcion != 0);
    }

    private void mostrarMenu() {
        System.out.println("===== TIENDA DE REGALOS ELECTRONICOS =====");
        System.out.println("1) Registrar producto electronico");
        System.out.println("2) Listar catálogo");
        System.out.println("3) Reabastecer stock");
        System.out.println("4) Retirar stock");
        System.out.println("5) Agregar al carrito (por ID)");
        System.out.println("6) Agregar al carrito (por objeto)");
        System.out.println("7) Eliminar del carrito");
        System.out.println("8) Ver carrito");
        System.out.println("9) Total sin descuento");
        System.out.println("10) Total con % de descuento");
        System.out.println("11) Total con cupón (REGALO10/TECH20)");
        System.out.println("0) Salir");
    }

    private void registrarProducto() {
        System.out.println("--- Registrar producto electrónico ---");
        Producto producto = crearProductoDesdeEntrada();
        if (producto == null) {
            System.out.println("No se creó el producto.");
            return;
        }
        int existencias = in.leerEntero("Existencias iniciales: ");
        int totalAntes = inventario.getTamanio();
        inventario.registrar(producto, existencias);
        if (inventario.getTamanio() > totalAntes) {
            System.out.println("✔ Registrado: " + producto.getCategoria());
        } else {
            System.out.println("No se registró el producto.");
        }
    }

    private Producto crearProductoDesdeEntrada() {
        int tipo = leerTipoProducto();
        String id = in.leerLinea("ID: ");
        String nombre = in.leerLinea("Nombre: ");
        String marca = in.leerLinea("Marca: ");
        double precio = in.leerDouble("Precio: ");

        switch (tipo) {
            case 1:
                int ram = in.leerEntero("RAM en GB: ");
                int almacenamientoPc = in.leerEntero("Almacenamiento en GB: ");
                boolean portatilPc = leerSiNo("¿Es portátil? (s/n): ");
                return new Computadora(id, nombre, precio, marca, ram, almacenamientoPc, portatilPc);
            case 2:
                double pulgadas = in.leerDouble("Tamaño de pantalla en pulgadas: ");
                boolean lapiz = leerSiNo("¿Incluye lápiz? (s/n): ");
                return new Tableta(id, nombre, precio, marca, pulgadas, lapiz);
            case 3:
                String modelo = in.leerLinea("Modelo o edición: ");
                boolean portatilConsola = leerSiNo("¿Es portátil? (s/n): ");
                return new ConsolaVideojuego(id, nombre, precio, marca, modelo, portatilConsola);
            case 4:
                String plataforma = in.leerLinea("Plataforma: ");
                String genero = in.leerLinea("Género: ");
                return new Videojuego(id, nombre, precio, marca, plataforma, genero);
            case 5:
                int almacenamientoTelefono = in.leerEntero("Almacenamiento en GB: ");
                boolean cincoG = leerSiNo("¿Tiene conectividad 5G? (s/n): ");
                return new Telefono(id, nombre, precio, marca, almacenamientoTelefono, cincoG);
            default:
                System.out.println("Tipo de producto no válido.");
                return null;
        }
    }

    private int leerTipoProducto() {
        System.out.println("Tipos disponibles:");
        System.out.println("1) Computadora");
        System.out.println("2) Tableta");
        System.out.println("3) Consola de videojuegos");
        System.out.println("4) Videojuego");
        System.out.println("5) Teléfono");
        return in.leerEntero("Tipo: ");
    }

    private boolean leerSiNo(String prompt) {
        String respuesta = in.leerLinea(prompt).toLowerCase(Locale.ROOT);
        return respuesta.equals("s") || respuesta.equals("si") || respuesta.equals("sí")
                || respuesta.equals("true") || respuesta.equals("1");
    }

    private void listarCatalogo() {
        System.out.println("--- Catálogo ---");
        
        if (inventario.getTamanio() == 0) {
            System.out.println("(Vacío)");
            return;
        }
        for (int i = 0; i < inventario.getTamanio(); i++) {
            Producto v = inventario.enPosicion(i);
            int st = inventario.stockEnPosicion(i);
            System.out.println("• " + v + " | stock: " + st);
        }
    }

private void reabastecer() {
        System.out.println("--- Reabastecer stock ---");
        String id = in.leerLinea("ID: ");
        int cantidad = in.leerEntero("Cantidad a reabastecer: ");
        if (inventario.reabastecer(id, cantidad)) {
            System.out.println("✔ Stock actual: " + inventario.existencias(id));
        } else {
            System.out.println("No fue posible reabastecer ese producto.");
        }
    }

    private void retirar() {
        System.out.println("--- Retirar stock ---");
        String id = in.leerLinea("ID: ");
        int cantidad = in.leerEntero("Cantidad a retirar: ");
        if (inventario.retirar(id, cantidad)) {
            System.out.println("✔ Stock actual: " + inventario.existencias(id));
        } else {
            System.out.println("No fue posible retirar ese stock.");
        }
    }

    private void agregarAlCarritoPorId() {
        System.out.println("--- Agregar al carrito (por ID) ---");
        String id = in.leerLinea("ID: ");
        int cantidad = in.leerEntero("Cantidad: ");
        if (carrito.agregar(id, cantidad)) {
            System.out.println("✔ En carrito (" + id + "): " + carrito.getCantidad(id));
            System.out.println("Stock restante: " + inventario.existencias(id));
        } else {
            System.out.println("No se pudo agregar al carrito.");
        }
    }

    private void agregarAlCarritoPorObjeto() {
        System.out.println("--- Agregar al carrito (por objeto) ---");
        String id = in.leerLinea("ID: ");
        int cantidad = in.leerEntero("Cantidad: ");
        Producto producto = inventario.obtener(id);
        if (carrito.agregar(producto, cantidad)) {
            System.out.println("✔ En carrito (" + id + "): " + carrito.getCantidad(id));
            System.out.println("Stock restante: " + inventario.existencias(id));
        } else {
            System.out.println("No se pudo agregar al carrito.");
        }
    }

    private void eliminarDelCarrito() {
        System.out.println("--- Eliminar del carrito ---");
        String id = in.leerLinea("ID: ");
        int cantidad = in.leerEntero("Cantidad: ");
        if (carrito.eliminar(id, cantidad)) {
            System.out.println("✔ En carrito (" + id + "): " + carrito.getCantidad(id));
            System.out.println("Stock actual: " + inventario.existencias(id));
        } else {
            System.out.println("No se pudo eliminar esa cantidad del carrito.");
        }
    }

    private void verCarrito() {
        System.out.println("--- Carrito ---");
        if (carrito.getTamanio() == 0) {
            System.out.println("(Vacío)");
            return;
        }
        for (int i = 0; i < carrito.getTamanio(); i++) {
            Producto producto = carrito.productoEnPosicion(i);
            int cant = carrito.cantidadEnPosicion(i);
            double precio = producto.getPrecio();
            System.out.println("• " + producto.getCategoria() + " - " + producto.getNombre() + " x " + cant
                    + " @ $" + String.format(Locale.US, "%.2f", precio));
        }
        System.out.println("Total: $" + String.format(Locale.US, "%.2f", carrito.total()));
    }

    private void totalSinDescuento() {
        System.out.println("--- Total sin descuento ---");
        System.out.println("Total: $" + String.format(Locale.US, "%.2f", carrito.total()));
    }

    private void totalConPorcentaje() {
        System.out.println("--- Total con % de descuento ---");
        double pct = in.leerDouble("Porcentaje [0-100]: ");
        System.out.println("Total: $" + String.format(Locale.US, "%.2f", carrito.totalConDescuento(pct)));
    }

    private void totalConCupon() {
        System.out.println("--- Total con cupón ---");
        String cupon = in.leerLinea("Cupón (REGALO10 / TECH20 / otro): ");
        System.out.println("Total: $" + String.format(Locale.US, "%.2f", carrito.totalConDescuento(cupon)));
    }
}

