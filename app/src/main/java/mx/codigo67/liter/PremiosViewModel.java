package mx.codigo67.liter;

public class PremiosViewModel {

    protected String titulo;
    protected String imagen;
    protected String puntos;

    public PremiosViewModel(String titulo, String imagen, String puntos) {
        this.titulo = titulo;
        this.imagen = imagen;
        this.puntos = puntos;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public String getPuntos() {
        return puntos;
    }

    public void setPuntos(String puntos) {
        this.puntos = puntos;
    }

}
