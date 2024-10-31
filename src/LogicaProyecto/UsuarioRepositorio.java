package LogicaProyecto;

import java.util.Scanner;
import java.io.PrintStream;

public class UsuarioRepositorio {
    private static Usuario[] listaUsuarios = new Usuario[10];
    public void UsuarioPorDefecto(String nombre, String apellido, int edad, String tipoNif, String numeroNif,
                                  String correo, String direccion, String telefono, String contrasena){
        listaUsuarios[0] = new Usuario(nombre, apellido, edad, tipoNif, numeroNif, correo,
                direccion, telefono, contrasena, true);
    }
    Scanner ingresaData = new Scanner(System.in);
    PrintStream imprimir = new PrintStream(System.out);



    public void CrearUsuario(String nombre, String apellido, int edad, String tipoNif, String numeroNif,
                             String correo, String direccion, String telefono, String contrasena) {
        boolean nifAsignado = false;  // Variable para saber si el NIF fue asignado correctamente

        for (int indiceUsuarios = 0; indiceUsuarios < listaUsuarios.length; indiceUsuarios++) {

            if (listaUsuarios[indiceUsuarios] != null && listaUsuarios[indiceUsuarios].getNumeroNifUsuario().equals(numeroNif)) {
                imprimir.println("El NIF ya existe en la posición " + indiceUsuarios + ". Buscando otra posición...");
                continue;  // Si el NIF ya existe, se aumenta el índice y se pasa a la siguiente iteración
            }


            if (listaUsuarios[indiceUsuarios] == null) {
                listaUsuarios[indiceUsuarios] = new Usuario(nombre, apellido, edad, tipoNif, numeroNif, correo,
                        direccion, telefono, contrasena, true);
                imprimir.println("Usuario creado con éxito en la posición " + indiceUsuarios + ".");
                nifAsignado = true;  // Indicamos que el NIF fue asignado
                break;  // Salimos del ciclo, ya que el usuario fue asignado
            }
        }

        if (!nifAsignado) {
            imprimir.println("No se pudo crear el usuario: El NIF ya está registrado o no hay espacio disponible.");
        }
    }

    public void EditarUsuarios(){
        int indiceUsuarioAEditar = 0;
        int datoAEditar=0;
        imprimir.println("Ingresa el indice del usuario que desees editar");
        indiceUsuarioAEditar = ingresaData.nextInt();
        ingresaData.nextLine();

        while(indiceUsuarioAEditar != -1)
        {
            imprimir.println("Elige el dato que deseas editar del perfil de "+
                            listaUsuarios[indiceUsuarioAEditar].getNombreUsuario());
            datoAEditar = ingresaData.nextInt();
            ingresaData.nextLine();

            switch (datoAEditar){
                case 1:
                    imprimir.println("Ingrese el nuevo nombre del usuario:");
                    String nuevoNombre = ingresaData.nextLine();
                    listaUsuarios[indiceUsuarioAEditar].setNombreUsuario(nuevoNombre);
                    imprimir.println("Nombre del usuario actualizado exitosamente.");
                    break;
                case 2:
                    imprimir.println("Ingrese el nuevo Apellido del Usuairo");
                    String nuevoApellido = ingresaData.nextLine();
                    listaUsuarios[indiceUsuarioAEditar].setApellidoUsuario(nuevoApellido);
                    imprimir.println("Apellido del usuario actualizado exitosamente.");
                    break;
                case 3:
                    imprimir.println("Ingrese la nueva edad del usuario:");
                    int nuevaEdad = ingresaData.nextInt();
                    ingresaData.nextLine();
                    listaUsuarios[indiceUsuarioAEditar].setEdadUsuario(nuevaEdad);
                    break;
                case 4:
                    imprimir.println("Ingrese el nuevo Tipo de NIF");
                    String nuevoTipoNif = ingresaData.nextLine();
                    listaUsuarios[indiceUsuarioAEditar].setTipoNifUsuario(nuevoTipoNif);
                    break;
                case 5:
                    imprimir.println("Ingrese el nuevo numero de NIF");
                    String nuevoNumNif = ingresaData.nextLine();
                    listaUsuarios[indiceUsuarioAEditar].setNumeriNifUsuario(nuevoNumNif);
                    break;
                case 6:
                    imprimir.println("Ingrese el nuevo correo");
                    String nuevoCorreo = ingresaData.nextLine();
                    listaUsuarios[indiceUsuarioAEditar].setCorreoUsuario(nuevoCorreo);
                    break;
                case 7:
                    imprimir.println("Ingrese el nuevo telefono");
                    String nuevoTelefono = ingresaData.nextLine();
                    listaUsuarios[indiceUsuarioAEditar].setTelefonoUsuario(nuevoTelefono);
                    break;
                case 8:
                    imprimir.println("Ingrese la nueva contraseña");
                    String nuevaContrasena = ingresaData.nextLine();
                    listaUsuarios[indiceUsuarioAEditar].setContrasenaUsuario(nuevaContrasena);
                    break;
                default:
                    imprimir.println("Opcion Invalida");
            }

            indiceUsuarioAEditar = -1;


        }
    }

    public Usuario[] accederAlArreglo(){
        return listaUsuarios;
    }

    public void ObtenerListaDeUsuarios(){
        for(int indiceDeUsuarios=0; indiceDeUsuarios<listaUsuarios.length; indiceDeUsuarios++){
            imprimir.println(indiceDeUsuarios+") "+listaUsuarios[indiceDeUsuarios].getNombreUsuario()+" "
                                +listaUsuarios[indiceDeUsuarios].getApellidoUsuario());
        }
        imprimir.println("No se encuentran mas usuarios registrados");
    }

    public void AlterarEstadoUsuario(){
        int indiceUsuarioAEditar = 0;
        int nuevoEstadoUsuario =0;
        boolean nuevoEstado = false;

        imprimir.println("A que usuario deseas editar su estado?");
        indiceUsuarioAEditar = ingresaData.nextInt();
        ingresaData.nextLine();

        imprimir.println("Elija a que estado pasará: \n1)Activo \n2)Inactivo");
        nuevoEstadoUsuario = ingresaData.nextInt();
        ingresaData.nextLine();

        if(nuevoEstadoUsuario == 1){
            nuevoEstado = true;
            listaUsuarios[nuevoEstadoUsuario].setEstadoUsuario(nuevoEstado);}
        else {
            nuevoEstado = false;
            listaUsuarios[nuevoEstadoUsuario].setEstadoUsuario(nuevoEstado);
        }
    }

    public Usuario ObtenerCorreoUsuario(String correoABuscar){
        for(Usuario informacionUsuario : listaUsuarios){
            if(informacionUsuario.getCorreoUsuario().equals(correoABuscar)){
                imprimir.println("Este usuario se encuentra registrado");
                return informacionUsuario;
            }
        }
        imprimir.println("El usuario no se encuentra registrado");
        return null;
    }

    public void CorreoDeUsuarioABuscar(){
        String correoDeseado = "";

        imprimir.println("Ingresa el correo del usuario que desees encontrar:");
        correoDeseado = ingresaData.nextLine();

        Usuario correoABuscar = ObtenerCorreoUsuario(correoDeseado);
    }


}

