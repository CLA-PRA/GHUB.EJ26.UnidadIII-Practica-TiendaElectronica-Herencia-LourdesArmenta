package logica;

public class Computadora extends Producto {

    private int ramGb;
    private int almacenamientoGb;
    private boolean portatil;

    public Computadora() {
        // TODO(autograding): Construir un objeto por defecto con datos válidos.
        // Pista: usa this(...) para reutilizar el constructor completo.
        this("", "", 0.0, "", 0, 0, false);
    }

    public Computadora(String id, String nombre, double precio, String marca,
            int ramGb, int almacenamientoGb, boolean portatil) {
        // TODO(autograding): Inicializar atributos heredados y propios.
        // Pista: valida enteros positivos para RAM y almacenamiento.
        // Pista extra: también debes conservar el valor recibido en "portatil".
        super(id, nombre, precio, marca);
        this.ramGb = 0;
        this.almacenamientoGb = 0;
        this.portatil = false;
    }

    public Computadora(Computadora otra) {
        // TODO(autograding): Implementar constructor copia completo.
        // Pista: si el argumento es null, asigna valores por defecto.
        // Pista extra: cuando no sea null, copia exactamente los 3 atributos propios.
        super(otra);
        this.ramGb = 0;
        this.almacenamientoGb = 0;
        this.portatil = false;
    }

    private static int valorPositivo(int valor, int porDefecto) {
        // TODO(autograding): Regresar valor cuando sea positivo; de lo contrario porDefecto.
        // Pista: este método evita repetir validaciones en constructores.
        return porDefecto;
    }

    public int getRamGb() {
        // TODO(autograding): Regresar la RAM del objeto.
        // Pista: este getter debe exponer el atributo, no un valor fijo.
        return 0;
    }

    public int getAlmacenamientoGb() {
        // TODO(autograding): Regresar el almacenamiento del objeto.
        // Pista: sigue el mismo patrón de getRamGb().
        return 0;
    }

    public boolean isPortatil() {
        // TODO(autograding): Regresar si la computadora es portátil.
        // Pista: método booleano tipo "is".
        return false;
    }

    @Override
    public String getCategoria() {
        return "Computadora";
    }

    @Override
    public String getDetalleTecnico() {
        // TODO(autograding): Describir RAM, almacenamiento y tipo (portátil/escritorio).
        // Pista: conserva el formato "XGB RAM, YGB SSD, tipo".
        return "Computadora sin detalle";
    }
}