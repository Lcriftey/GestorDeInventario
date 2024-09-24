package LogicaProyecto;

public class Usuario {
    private int edadUsuario;
    private String nombreUsuario;
    private String apellidoUsuario;
    private String tipoNifUsuario;
    private String numeroNifUsuario;
    private String direccionUsuario;
    private String telefonoUsuario;
    private boolean estadoUsuario;
    private String correoUsuario;
    private String contrasenaUsuario;

    public Usuario(String nombre, String apellido, int edad,
                   String tipoNif, String numeroNif, String correo, String direccion,
                   String telefono, String contrasena, boolean estado){
        edadUsuario = edad;
        nombreUsuario = nombre;
        apellidoUsuario = apellido;
        tipoNifUsuario = tipoNif;
        numeroNifUsuario = numeroNif;
        direccionUsuario = direccion;
        telefonoUsuario = telefono;
        estadoUsuario = estado;
        correoUsuario = correo;
        contrasenaUsuario = contrasena;
    }

    public int getEdadUsuario() {
        return edadUsuario;
    }

    public void setEdadUsuario(int edadUsuario) {
        this.edadUsuario = edadUsuario;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getApellidoUsuario() {
        return apellidoUsuario;
    }

    public void setApellidoUsuario(String apellidoUsuario) {
        this.apellidoUsuario = apellidoUsuario;
    }

    public String getTipoNifUsuario() {
        return tipoNifUsuario;
    }

    public void setTipoNifUsuario(String tipoNifUsuario) {
        this.tipoNifUsuario = tipoNifUsuario;
    }

    public String getNumeroNifUsuario() {
        return numeroNifUsuario;
    }

    public void setNumeriNifUsuario(String numeriNifUsuario) {
        this.numeroNifUsuario = numeriNifUsuario;
    }

    public String getDireccionUsuario() {
        return direccionUsuario;
    }

    public void setDireccionUsuario(String direccionUsuario) {
        this.direccionUsuario = direccionUsuario;
    }

    public String getTelefonoUsuario() {
        return telefonoUsuario;
    }

    public void setTelefonoUsuario(String telefonoUsuario) {
        this.telefonoUsuario = telefonoUsuario;
    }

    public boolean isEstadoUsuario() {
        return estadoUsuario;
    }

    public void setEstadoUsuario(boolean estadoUsuario) {
        this.estadoUsuario = estadoUsuario;
    }

    public String getCorreoUsuario() {
        return correoUsuario;
    }

    public void setCorreoUsuario(String correoUsuario) {
        this.correoUsuario = correoUsuario;
    }

    public String getContrasenaUsuario() {
        return contrasenaUsuario;
    }

    public void setContrasenaUsuario(String contrasenaUsuario) {
        this.contrasenaUsuario = contrasenaUsuario;
    }
}
