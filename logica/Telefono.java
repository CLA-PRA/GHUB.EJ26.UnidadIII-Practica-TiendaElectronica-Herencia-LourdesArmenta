package logica;

public class Telefono extends Producto {

    private int almacenamientoGb;
    private boolean cincoG;

    public Telefono() {
        // TODO(autograding): Construir un teléfono por defecto con datos válidos.
        // Pista: usa this(...) para reutilizar el constructor completo.
        this("", "", 0.0, "", 0, false);
    }

    public Telefono(String id, String nombre, double precio, String marca,
            int almacenamientoGb, boolean cincoG) {
        // TODO(autograding): Inicializar atributos heredados y propios.
        // Pista: valida almacenamiento positivo antes de asignar.
        super(id, nombre, precio, marca);
        this.almacenamientoGb = 0;
        this.cincoG = false;
    }

    public Telefono(Telefono otro) {
        // TODO(autograding): Implementar constructor copia completo.
        // Pista: si otro es null, asigna valores por defecto.
        super(otro);
        this.almacenamientoGb = 0;
        this.cincoG = false;
    }

    private static int valorPositivo(int valor, int porDefecto) {
        // TODO(autograding): Regresar valor cuando sea positivo; si no, porDefecto.
        // Pista: úsalo para evitar repetir validación en constructores.
        return porDefecto;
    }

    public int getAlmacenamientoGb() {
        // TODO(autograding): Regresar almacenamiento en GB.
        // Pista: getter directo del atributo.
        return 0;
    }

    public boolean isCincoG() {
        // TODO(autograding): Regresar si el teléfono soporta 5G.
        // Pista: método booleano tipo "is".
        return false;
    }

    @Override
    public String getCategoria() {
        return "Telefono";
    }

    @Override
    public String getDetalleTecnico() {
        // TODO(autograding): Devolver almacenamiento y conectividad (5G o 4G).
        // Pista: conserva el formato "XGB, descripción".
        return "Telefono sin detalle";
    }
}