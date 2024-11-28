package LogicaProyecto;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.LinkedList;

public class Inventario extends Component {
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

    public boolean borrarContenido() {
        File archivoInventario = obtenerArchivoDeInventario();
        try {
            if (archivoInventario.exists()) {
                FileWriter fw = new FileWriter(archivoInventario, false); // Modo "false" sobreescribe el archivo
                fw.write(""); // Escribe una cadena vacía para borrar el contenido
                fw.close();
                return true;
            }
        } catch (IOException error) {
            error.printStackTrace();
        }
        return false;
    }

    public void AbrirArchivo(){
        try {
            // Obtén la ruta del archivo en tu proyecto (en la carpeta Archivos, por ejemplo)
            File archivo = new File("C:\\Users\\juanc\\Documents\\Parcial-ProgramacionlV\\GestionInventario\\out\\production\\GestionInventario\\Archivos\\inventario.txt");

            // Verifica si Desktop es soportado
            if (Desktop.isDesktopSupported()) {
                Desktop desktop = Desktop.getDesktop();

                // Verifica si se puede abrir archivos
                if (archivo.exists() && desktop.isSupported(Desktop.Action.OPEN)) {
                    desktop.open(archivo); // Abre el archivo
                } else {
                    JOptionPane.showMessageDialog(null, "El archivo no existe o no se puede abrir en este sistema.");
                }
            } else {
                JOptionPane.showMessageDialog(null, "La función de apertura no es soportada en este sistema.");
            }
        } catch (IOException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Hubo un error al intentar abrir el archivo.");
        }
    }
}


