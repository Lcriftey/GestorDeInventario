package LogicaProyecto;

import GUI.Login;

import java.io.PrintStream;
import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        UsuarioRepositorio repositorio = new UsuarioRepositorio();

        Scanner scanner = new Scanner(System.in);
        PrintStream out = new PrintStream(System.out);

        Login ventanaLogin = new Login(null);


    }
}
