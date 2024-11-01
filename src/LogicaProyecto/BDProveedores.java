package LogicaProyecto;

import java.util.LinkedList;
import java.util.StringTokenizer;

public class BDProveedores {
    public LinkedList<Proveedores> obtenerProveedoresDelArchivo() {
        LinkedList<Proveedores> proveedores = null;
        ProveedoresServicio proveedoresServicio = new ProveedoresServicio();
        LinkedList<String> lineas = proveedoresServicio.obtenerTextoDelArchivo();

        if (lineas != null) {
            proveedores = new LinkedList<>();
            for (String linea : lineas) {
                StringTokenizer tokens = new StringTokenizer(linea, ";");
                int id = Integer.parseInt(tokens.nextToken());
                String nombre = tokens.nextToken();
                String direccion = tokens.nextToken();
                String telefono = tokens.nextToken();
                proveedores.add(new Proveedores(id, nombre, direccion, telefono));
            }
        }
        return proveedores;
    }

    public boolean registrarProveedor(Proveedores proveedor) {
        ProveedoresServicio proveedoresServicio = new ProveedoresServicio();
        String linea = proveedor.getId() + ";" + proveedor.getNombre() + ";" +
                proveedor.getDireccion() + ";" + proveedor.getTelefono();
        return proveedoresServicio.registrarProveedor(linea);
    }
}