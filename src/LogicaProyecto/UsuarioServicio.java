package LogicaProyecto;

public class UsuarioServicio {
    private UsuarioRepositorio repositorio;
    private Usuario informacionUsuario;
    public UsuarioServicio(){
        repositorio = new UsuarioRepositorio();
    }

    public boolean validarUsuario(String correoIngresarUsuario, String contrasenaIngresarUsuario){
        System.out.println("Ingrese su correo para ingresar:");
        System.out.println("Ingrese su contraseña para ingresar:");

        Usuario[] arreglo = repositorio.accederAlArreglo();
        for(Usuario informacionUsuario : arreglo){
            if(informacionUsuario.getCorreoUsuario().equals(correoIngresarUsuario)
               && informacionUsuario.getContrasenaUsuario().equals(contrasenaIngresarUsuario)){

                return true;
            }
        }
        return false;
    }
}




