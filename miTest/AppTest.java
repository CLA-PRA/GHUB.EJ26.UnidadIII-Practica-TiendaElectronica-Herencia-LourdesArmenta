package miTest;

import logica.*;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class AppTest {

    @Test
    void testRegistrarProductosHeterogeneosEnInventario() {
        Inventario inventario = new Inventario(10);

        inventario.registrar(new Computadora("LAP1", "Laptop gamer", 18999.0,
                "Lenovo", 16, 512, true), 3);
        inventario.registrar(new Telefono("TEL1", "Telefono 5G", 9999.0,
                "Motorola", 256, true), 5);

        assertEquals(2, inventario.getTamanio());
        Producto laptop = inventario.obtener("LAP1");
        assertNotNull(laptop);
        assertTrue(laptop instanceof Computadora);
        assertEquals("Computadora", laptop.getCategoria());
        assertEquals(5, inventario.existencias("TEL1"));
    }

    @Test
    void testAgregarProductoAlCarritoPorId() {
        Inventario inventario = new Inventario(10);
        inventario.registrar(new Videojuego("VID1", "Mario Kart 8", 1299.0,
                "Nintendo", "Switch", "Carreras"), 4);

        Carrito carrito = new Carrito(5, inventario);

        boolean resultado = carrito.agregar("VID1", 2);

        assertTrue(resultado);
        assertEquals(2, carrito.getCantidad("VID1"));
        assertEquals(2, inventario.existencias("VID1"));
        assertEquals(2598.0, carrito.total(), 0.01);
        assertEquals(2078.4, carrito.totalConDescuento("TECH20"), 0.01);
    }

    @Test
    void testAgregarProductoAlCarritoPorObjeto() {
        Inventario inventario = new Inventario(10);
        Tableta tableta = new Tableta("TAB1", "iPad Air", 13499.0,
                "Apple", 10.9, true);
        inventario.registrar(tableta, 2);

        Carrito carrito = new Carrito(5, inventario);
        boolean resultado = carrito.agregar(tableta, 1);

        assertTrue(resultado);
        assertEquals(1, carrito.getCantidad("TAB1"));
        assertEquals(1, inventario.existencias("TAB1"));
        assertTrue(carrito.productoEnPosicion(0) instanceof Tableta);
    }

    @Test
    void testCalcularPrecioConDescuentoPorCupon() {
        Videojuego juego = new Videojuego("HLO3", "Halo Infinite", 69.99);

        double precioConRegalo = juego.precioConDescuento("REGALO10");
        double precioConTech = juego.precioConDescuento("TECH20");
        double precioConGamer = juego.precioConDescuento("GAMER10");
        double precioSinDescuento = juego.precioConDescuento("INVALIDO");

        assertEquals(62.99, precioConRegalo, 0.01);
        assertEquals(55.99, precioConTech, 0.01);
        assertEquals(62.99, precioConGamer, 0.01);
        assertEquals(69.99, precioSinDescuento, 0.01);
    }

    @Test
    void testEliminarProductoDelCarritoYDevolverStock() {
        Inventario inventario = new Inventario(10);
        inventario.registrar(new ConsolaVideojuego("CON1", "PlayStation 5", 12999.0,
                "Sony", "Slim", false), 2);

        Carrito carrito = new Carrito(5, inventario);
        carrito.agregar("CON1", 2);

        boolean resultado = carrito.eliminar("CON1", 1);

        assertTrue(resultado);
        assertEquals(1, carrito.getCantidad("CON1"));
        assertEquals(1, inventario.existencias("CON1"));
    }

    @Test
    void testValidacionesDeRegistroEnInventario() {
        Inventario inventario = new Inventario(2);

        inventario.registrar(new Videojuego("   ", "Sin id", 100.0), 1);
        inventario.registrar(new Videojuego("V0", "Stock invalido", 100.0), -1);
        assertEquals(0, inventario.getTamanio());

        inventario.registrar(new Videojuego("V1", "Juego 1", 100.0), 1);
        inventario.registrar(new Videojuego("V1", "Juego repetido", 120.0), 2);
        inventario.registrar(new Telefono("T1", "Telefono", 9000.0,
                "Motorola", 128, true), 2);
        inventario.registrar(new Computadora("C1", "PC", 15000.0,
                "Lenovo", 8, 256, false), 1);

        assertEquals(2, inventario.getTamanio());
        assertNotNull(inventario.obtener("V1"));
        assertNotNull(inventario.obtener("T1"));
        assertNull(inventario.obtener("C1"));
    }

    @Test
    void testOperacionesDeStockConCasosLimite() {
        Inventario inventario = new Inventario(5);
        inventario.registrar(new Videojuego("ST1", "Stock Test", 500.0), 2);

        assertFalse(inventario.hayStock("ST1", 0));
        assertFalse(inventario.hayStock("NO", 1));
        assertTrue(inventario.hayStock("ST1", 2));

        assertFalse(inventario.retirar("ST1", 0));
        assertFalse(inventario.retirar("NO", 1));
        assertFalse(inventario.retirar("ST1", 3));
        assertTrue(inventario.retirar("ST1", 1));
        assertEquals(1, inventario.existencias("ST1"));

        assertFalse(inventario.reabastecer("ST1", 0));
        assertFalse(inventario.reabastecer("NO", 1));
        assertTrue(inventario.reabastecer("ST1", 4));
        assertEquals(5, inventario.existencias("ST1"));
    }

    @Test
    void testCarritoConCapacidadYEliminacionesInvalidas() {
        Inventario inventario = new Inventario(5);
        inventario.registrar(new Videojuego("V1", "Juego 1", 100.0), 3);
        inventario.registrar(new Videojuego("V2", "Juego 2", 200.0), 3);

        Carrito carrito = new Carrito(1, inventario);

        assertFalse(carrito.agregar("NO", 1));
        assertFalse(carrito.agregar("V1", 0));

        assertTrue(carrito.agregar("V1", 1));
        assertTrue(carrito.agregar("V1", 1));
        assertEquals(2, carrito.getCantidad("V1"));
        assertEquals(1, inventario.existencias("V1"));

        assertFalse(carrito.agregar("V2", 1));
        assertEquals(0, carrito.getCantidad("V2"));

        assertFalse(carrito.eliminar("NO", 1));
        assertFalse(carrito.eliminar("V1", 0));
        assertFalse(carrito.eliminar("V1", 5));
        assertTrue(carrito.eliminar("V1", 1));
        assertEquals(1, carrito.getCantidad("V1"));
        assertEquals(2, inventario.existencias("V1"));
    }

    @Test
    void testDescuentoPorPorcentajeFueraDeRango() {
        Videojuego juego = new Videojuego("D1", "Juego", 100.0);

        assertEquals(100.0, juego.precioConDescuento(-10), 0.01);
        assertEquals(100.0, juego.precioConDescuento(150), 0.01);

        Inventario inventario = new Inventario(5);
        inventario.registrar(new Videojuego("D2", "Juego carrito", 100.0), 2);
        Carrito carrito = new Carrito(2, inventario);
        assertTrue(carrito.agregar("D2", 2));

        assertEquals(200.0, carrito.total(), 0.01);
        assertEquals(200.0, carrito.totalConDescuento(-5), 0.01);
        assertEquals(200.0, carrito.totalConDescuento(101), 0.01);
        assertEquals(150.0, carrito.totalConDescuento(25), 0.01);
    }

    
}