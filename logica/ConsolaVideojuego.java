package logica;

public class ConsolaVideojuego extends Producto {

    private String modelo;
    private boolean portatil;

    public ConsolaVideojuego() {
        // TODO(autograding): Construir una consola por defecto con datos válidos.
        // Pista: usa this(...) para delegar en el constructor completo.
        this("", "", 0.0, "", "", false);
    }

    public ConsolaVideojuego(String id, String nombre, double precio, String marca,
            String modelo, boolean portatil) {
        // TODO(autograding): Inicializar atributos heredados y propios.
        // Pista: valida texto no vacío para modelo antes de asignar.
        super(id, nombre, precio, marca);
        this.modelo = "";
        this.portatil = false;
    }

    public ConsolaVideojuego(ConsolaVideojuego otra) {
        // TODO(autograding): Implementar constructor copia.
        // Pista: si otra es null, asigna valores por defecto.
        super(otra);
        this.modelo = "";
        this.portatil = false;
    }

    private static String textoOValor(String valor, String porDefecto) {
        // TODO(autograding): Retornar valor limpio o porDefecto cuando venga vacío.
        // Pista: trim() + isBlank() resuelven espacios.
        return porDefecto;
    }

    public String getModelo() {
        // TODO(autograding): Regresar el modelo de la consola.
        // Pista: getter directo del atributo.
        return "";
    }

    public boolean isPortatil() {
        // TODO(autograding): Indicar si la consola es portátil.
        // Pista: getter booleano tipo "is".
        return false;
    }

    @Override
    public String getCategoria() {
        return "Consola";
    }

    @Override
    public String getDetalleTecnico() {
        // TODO(autograding): Devolver detalle con modelo y tipo (portátil/sobremesa).
        // Pista: conserva el formato "modelo, tipo".
        return "Consola sin detalle";
    }
}