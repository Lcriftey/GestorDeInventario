package LogicaProyecto;

import javax.swing.*;
import java.io.*;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.LinkedList;

public class Inventario {
    private String archivoDeInventario;

    public Inventario(String archivoDeInventario) {
        this.archivoDeInventario = archivoDeInventario;
    }

    private File obtenerArchivoDeInventario(){
        try {
            URL url = getClass().getClassLoader().getResource("Archivos/" + archivoDeInventario);
            return new File(url.toURI());
        } catch (URISyntaxException ex){
            ex.printStackTrace();
            return null;
        }
    }

    public LinkedList<String> ObtenerTextoDelArchivo() {
        LinkedList<String> lineasDeTexto=null;
        try {
            File archivo = obtenerArchivoDeInventario();
            if (archivo.exists()) {
                lineasDeTexto = new LinkedList();
                BufferedReader br = new BufferedReader(new FileReader(archivo));
                String linea;
                while ((linea = br.readLine()) != null) {
                    System.out.println(linea);
                    lineasDeTexto.add(linea);
                }
                br.close();
            } else {
                JOptionPane.showMessageDialog(null, "El archivo de texto no existe");}
        } catch (Exception ex){
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Se produjo un error ");
        }
        return lineasDeTexto;
    }

    public boolean registrar(String linea) {
        File archivoInventario = obtenerArchivoDeInventario();
        try {
            if (archivoInventario.exists()) {
                FileWriter fw = new FileWriter(archivoInventario, true);
                BufferedWriter bw = new BufferedWriter(fw);
                PrintWriter pw = new PrintWriter(bw);
                pw.println(linea);
                pw.flush();
                pw.close();
                return true;
            }
        } catch (Exception error) {
            error.printStackTrace();
        }
        return false;
    }
}


