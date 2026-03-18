package logica;

import java.util.Locale;

public class Tableta extends Producto {

    private double pulgadas;
    private boolean incluyeLapiz;

    public Tableta() {
        // TODO(autograding): Construir una tableta por defecto con datos válidos.
        // Pista: usa this(...) para delegar al constructor completo.
        this("", "", 0.0, "", 0.0, false);
    }

    public Tableta(String id, String nombre, double precio, String marca,
            double pulgadas, boolean incluyeLapiz) {
        // TODO(autograding): Inicializar atributos heredados y propios.
        // Pista: valida pulgadas positivas antes de asignar.
        super(id, nombre, precio, marca);
        this.pulgadas = 0.0;
        this.incluyeLapiz = false;
    }

    public Tableta(Tableta otra) {
        // TODO(autograding): Implementar constructor copia completo.
        // Pista: si otra es null, usa valores por defecto.
        super(otra);
        this.pulgadas = 0.0;
        this.incluyeLapiz = false;
    }

    private static double valorPositivo(double valor, double porDefecto) {
        // TODO(autograding): Regresar valor cuando sea positivo; si no, porDefecto.
        // Pista: helper para evitar duplicar validaciones.
        return porDefecto;
    }

    public double getPulgadas() {
        // TODO(autograding): Regresar pulgadas de pantalla.
        // Pista: getter directo.
        return 0.0;
    }

    public boolean isIncluyeLapiz() {
        // TODO(autograding): Indicar si incluye lápiz.
        // Pista: método booleano tipo "is".
        return false;
    }

    @Override
    public String getCategoria() {
        return "Tableta";
    }

    @Override
    public String getDetalleTecnico() {
        // TODO(autograding): Devolver detalle con pulgadas e información de lápiz.
        // Pista: conserva formato tipo "Pantalla X.X\", ...".
        return "Tableta sin detalle";
    }
}