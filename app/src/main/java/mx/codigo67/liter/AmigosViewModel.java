package mx.codigo67.liter;

import android.graphics.Bitmap;

public class AmigosViewModel {

    protected String titulo;
    protected Bitmap imagen;
    protected String nombre;
    protected String puntos;

    public AmigosViewModel(String titulo, Bitmap imagen, String nombre, String puntos) {
        this.titulo = titulo;
        this.imagen = imagen;
        this.nombre = nombre;
        this.puntos = puntos;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Bitmap getImagen() {
        return imagen;
    }

    public void setImagen(Bitmap imagen) {
        this.imagen = imagen;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPuntos() {
        return puntos;
    }

    public void setPuntos(String puntos) {
        this.puntos = puntos;
    }

}
