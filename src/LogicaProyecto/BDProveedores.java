package LogicaProyecto;

import java.util.LinkedList;
import java.util.StringTokenizer;

public class BDProveedor {
    public LinkedList<Proveedor> obtenerProveedoresDelArchivo() {
        LinkedList<Proveedor> proveedores = new LinkedList<>();
        Inventario inventario = new Inventario("proveedores.txt");
        LinkedList<String> lineas = inventario.ObtenerTextoDelArchivo();

        if (lineas != null) {
            for (String linea : lineas) {
                StringTokenizer tokens = new StringTokenizer(linea, ";");

                // Verificar si la línea tiene suficientes datos
                if (tokens.countTokens() >= 4) {
                    String id = tokens.nextToken();
                    String nombre = tokens.nextToken();
                    String direccion = tokens.nextToken();
                    String telefono = tokens.nextToken();

                    proveedores.add(new Proveedor(id, nombre, direccion, telefono));
                } else {
                    System.out.println("Línea con formato incorrecto: " + linea);
                }
            }
        }
        return proveedores;
    }

    public boolean registrarProveedor(Proveedor proveedor) {
        Inventario archivo = new Inventario("proveedores.txt");
        return archivo.registrar(proveedor.getId() + ";"
                + proveedor.getNombre() + ";"
                + proveedor.getDireccion() + ";"
                + proveedor.getTelefono());
    }
}

