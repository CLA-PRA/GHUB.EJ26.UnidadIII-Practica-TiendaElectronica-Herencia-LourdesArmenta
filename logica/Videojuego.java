package logica;

import java.util.Locale;

public class Videojuego extends Producto {

    private String plataforma;
    private String genero;

    public Videojuego() {
        // TODO(autograding): Construir un videojuego por defecto con datos válidos.
        // Pista: usa this(...) para evitar repetir lógica.
        this("", "", 0.0, "", "", "");
    }

    public Videojuego(String id, String titulo, double precio) {
        // TODO(autograding): Constructor corto que delega al constructor completo.
        // Pista: define marca/plataforma/género por defecto.
        this(id, titulo, precio, "Generica", "Multiplataforma", "Aventura");
    }

    public Videojuego(String id, String titulo, double precio, String marca,
            String plataforma, String genero) {
        // TODO(autograding): Inicializar atributos heredados y específicos.
        // Pista: valida texto no vacío para plataforma y género.
        super(id, titulo, precio, marca);
        this.plataforma = "";
        this.genero = "";
    }

    public Videojuego(Videojuego otro) {
        // TODO(autograding): Implementar constructor copia completo.
        // Pista: si otro es null, usa valores por defecto.
        super(otro);
        this.plataforma = "";
        this.genero = "";
    }

    private static String textoOValor(String valor, String porDefecto) {
        // TODO(autograding): Regresar valor limpio o porDefecto cuando venga vacío.
        // Pista: trim() + isBlank() simplifican esta validación.
        return porDefecto;
    }

    public String getTitulo() {
        // TODO(autograding): Vincular título con el nombre heredado.
        // Pista: delega en getNombre().
        return "";
    }

    public void setTitulo(String titulo) {
        // TODO(autograding): Actualizar título usando el setter heredado.
        // Pista: evita duplicar validaciones de nombre.
    }

    public String getPlataforma() {
        // TODO(autograding): Regresar plataforma del juego.
        // Pista: getter directo del atributo.
        return "";
    }

    public String getGenero() {
        // TODO(autograding): Regresar género del juego.
        // Pista: getter directo del atributo.
        return "";
    }

    public void setPlataforma(String plataforma) {
        // TODO(autograding): Asignar plataforma validando texto no vacío.
        // Pista: puedes reutilizar textoOValor(...).
    }

    public void setGenero(String genero) {
        // TODO(autograding): Asignar género validando texto no vacío.
        // Pista: comportamiento simétrico a setPlataforma(...).
    }

    @Override
    public String getCategoria() {
        return "Videojuego";
    }

    @Override
    public String getDetalleTecnico() {
        // TODO(autograding): Devolver detalle con plataforma y género.
        // Pista: conserva formato "Plataforma: X, genero: Y".
        return "Videojuego sin detalle";
    }

    @Override
    public String toString() {
        // TODO(autograding): Mostrar id, título y precio con 2 decimales.
        // Pista: usa Locale.US para formatear el punto decimal.
        return "Videojuego";
    }
}