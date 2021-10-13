package modelo.perfil;

public class PerfilM {

    private String nombreUsuario;
    private String descripcion;
    private String hobbies;
    private String gustos;
    private String[] categorias;
    private String[] etiquetas;
    private FotoM foto;

    public PerfilM() {

    }

    public PerfilM(String descripcion, String hobbies, String gustos, FotoM foto) {
        this.descripcion = descripcion;
        this.hobbies = hobbies;
        this.gustos = gustos;
        this.foto = foto;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getHobbies() {
        return hobbies;
    }

    public void setHobbies(String hobbies) {
        this.hobbies = hobbies;
    }

    public String getGustos() {
        return gustos;
    }

    public void setGustos(String gustos) {
        this.gustos = gustos;
    }

    public String[] getCategorias() {
        return categorias;
    }

    public void setCategorias(String[] categorias) {
        this.categorias = categorias;
    }

    public String[] getEtiquetas() {
        return etiquetas;
    }

    public void setEtiquetas(String[] etiquetas) {
        this.etiquetas = etiquetas;
    }

    public FotoM getFoto() {
        return foto;
    }

    public void setFoto(FotoM foto) {
        this.foto = foto;
    }
}
