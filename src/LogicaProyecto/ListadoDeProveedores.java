package LogicaProyecto;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.LinkedList;

public class ListadoDeProveedores extends Component {
    private String archivoDeProveedores;

    public ListadoDeProveedores(String archivoDeProveedores) {
        this.archivoDeProveedores = archivoDeProveedores;
    }

    private File obtenerArchivoDeProveedores() {
        try {
            URL url = getClass().getClassLoader().getResource("Archivos/" + archivoDeProveedores);
            return new File(url.toURI());
        } catch (URISyntaxException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public LinkedList<String> obtenerTextoDelArchivo() {
        LinkedList<String> lineasDeTexto = null;
        try {
            File archivo = obtenerArchivoDeProveedores();
            if (archivo != null && archivo.exists()) {
                lineasDeTexto = new LinkedList<>();
                BufferedReader br = new BufferedReader(new FileReader(archivo));
                String linea;
                while ((linea = br.readLine()) != null) {
                    System.out.println(linea);
                    lineasDeTexto.add(linea);
                }
                br.close();
            } else {
                JOptionPane.showMessageDialog(null, "El archivo de proveedores no existe");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Se produjo un error al leer el archivo de proveedores");
        }
        return lineasDeTexto;
    }

    public boolean registrarProveedor(String linea) {
        File archivoProveedores = obtenerArchivoDeProveedores();
        try {
            if (archivoProveedores != null && archivoProveedores.exists()) {
                FileWriter fw = new FileWriter(archivoProveedores, true);
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
        File archivoProveedores = obtenerArchivoDeProveedores();
        try {
            if (archivoProveedores != null && archivoProveedores.exists()) {
                FileWriter fw = new FileWriter(archivoProveedores, false); // Modo "false" sobreescribe el archivo
                fw.write(""); // Escribe una cadena vacía para borrar el contenido
                fw.close();
                return true;
            }
        } catch (IOException error) {
            error.printStackTrace();
        }
        return false;
    }
}

