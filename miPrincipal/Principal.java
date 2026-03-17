package miPrincipal;

import logica.Computadora;
import logica.Inventario;
import logica.Carrito;
import logica.ConsolaVideojuego;
import logica.Tableta;
import logica.Telefono;
import logica.Videojuego;
import interfaz.MenuTienda;

public class Principal {

    public static void main(String[] args) {
        Inventario inventario = new Inventario(100);
        Carrito carrito = new Carrito(50, inventario);
    cargarCatalogoDemo(inventario);
        MenuTienda menu = new MenuTienda(inventario, carrito);
        menu.correr();
    }

    private static void cargarCatalogoDemo(Inventario inventario) {
    inventario.registrar(new Computadora("LAP-01", "Laptop IdeaPad", 15999.0,
        "Lenovo", 16, 512, true), 4);
    inventario.registrar(new Tableta("TAB-01", "Galaxy Tab", 8999.0,
        "Samsung", 11.0, true), 6);
    inventario.registrar(new ConsolaVideojuego("CON-01", "Nintendo Switch OLED", 7299.0,
        "Nintendo", "OLED", true), 5);
    inventario.registrar(new Videojuego("VID-01", "Zelda: Tears of the Kingdom", 1499.0,
        "Nintendo", "Switch", "Aventura"), 10);
    inventario.registrar(new Telefono("TEL-01", "Moto Edge", 10999.0,
        "Motorola", 256, true), 8);
    }
}