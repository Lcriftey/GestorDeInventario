package LogicaProyecto;

import java.util.Scanner;
import java.io.PrintStream;

public class UsuarioServicio {
    /*private PrintStream out;
    private Scanner in;*/
    private UsuarioRepositorio repositorio;

    public UsuarioServicio(){
       /* PrintStream out = System.out;
        Scanner in = new Scanner(System.in);*/
        repositorio = new UsuarioRepositorio();
    }

    public boolean validarUsuario(String correoIngresarUsuario, String contrasenaIngresarUsuario){
        /*String contrasenaIngresarUsuario="", correoIngresarUsuario="";*/


        System.out.println("Ingrese su correo para ingresar:");
        //correoIngresarUsuario=in.nextLine();

        System.out.println("Ingrese su contraseña para ingresar:");
        //contrasenaIngresarUsuario=in.nextLine();*/

        Usuario[] listaUSuario = repositorio.accederAlArreglo();
        for(Usuario informacionUsuario : listaUSuario){
            if(informacionUsuario.getCorreoUsuario().equals(correoIngresarUsuario)
               && informacionUsuario.getContrasenaUsuario().equals(contrasenaIngresarUsuario)){
                /*NO se que hay que hacer acá, sospecho que se debe abrir la ventana principal
                 pero dejaré este comentario hasta que sepa que hacer*/
                return true;
            }
        }
        return false;
    }
}




