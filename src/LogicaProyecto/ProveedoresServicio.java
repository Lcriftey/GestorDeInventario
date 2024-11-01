package LogicaProyecto;

import java.io.*;
import java.net.URL;
import java.util.LinkedList;

public class ProveedoresServicio {
    private String archivoDeProveedores = "Proveedores.txt";

    private File obtenerArchivoDeProveedores() {
        try {
            URL url = getClass().getClassLoader().getResource("Archivos/" + archivoDeProveedores);
            return new File(url.toURI());
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public LinkedList<String> obtenerTextoDelArchivo() {
        LinkedList<String> lineasDeTexto = null;
        try {
            File archivo = obtenerArchivoDeProveedores();
            if (archivo.exists()) {
                lineasDeTexto = new LinkedList<>();
                BufferedReader br = new BufferedReader(new FileReader(archivo));
                String linea;
                while ((linea = br.readLine()) != null) {
                    lineasDeTexto.add(linea);
                }
                br.close();
            } else {
                System.out.println("El archivo de proveedores no existe.");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return lineasDeTexto;
    }

    public boolean registrarProveedor(String linea) {
        File archivoProveedores = obtenerArchivoDeProveedores();
        try {
            if (archivoProveedores.exists()) {
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

    public boolean eliminarProveedor(String idProveedor) {
        File archivoProveedores = obtenerArchivoDeProveedores();
        File archivoTemporal = new File("ProveedoresTemp.txt");

        boolean eliminado = false;
        try (BufferedReader br = new BufferedReader(new FileReader(archivoProveedores));
             PrintWriter pw = new PrintWriter(new FileWriter(archivoTemporal))) {

            String linea;
            while ((linea = br.readLine()) != null) {
                if (!linea.split(";")[0].equals(idProveedor)) {
                    pw.println(linea);
                } else {
                    eliminado = true;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (eliminado) {
            archivoProveedores.delete();
            archivoTemporal.renameTo(archivoProveedores);
        } else {
            archivoTemporal.delete();
        }

        return eliminado;
    }
}