package LogicaProyecto;

import java.util.LinkedList;
import java.util.StringTokenizer;

public class BDProductos {
    public LinkedList<Producto> ObtenerProductosDelArchivo() {
        LinkedList<Producto> productos = new LinkedList<>();
        Inventario inventario = new Inventario("inventario.txt");
        LinkedList<String> lineas = inventario.ObtenerTextoDelArchivo();

        if (lineas != null) {
            for (String linea : lineas) {
                StringTokenizer tokens = new StringTokenizer(linea, ";");

                // Verificar si la línea tiene suficientes datos
                if (tokens.countTokens() >= 7) {
                    String codigo = tokens.nextToken();
                    String nombre = tokens.nextToken();
                    String categoria = tokens.nextToken();
                    int existencias = Integer.parseInt(tokens.nextToken());
                    float precio = Float.parseFloat(tokens.nextToken());
                    String expiracion = tokens.nextToken();
                    String proveedor = tokens.nextToken();

                    productos.add(new Producto(codigo, nombre, categoria, existencias, precio, expiracion, proveedor));
                } else {
                    System.out.println("Línea con formato incorrecto: " + linea);
                }
            }
        }
        return productos;
    }

    public boolean RegistrarProducto(Producto producto) {
        Inventario archivo = new Inventario("inventario.txt");
        return archivo.registrar(producto.getIdProducto() + ";"
                + producto.getNombreProducto() + ";"
                + producto.getCategoriaProducto() + ";"
                + producto.getCantidadProducto() + ";"
                + producto.getPrecioProducto() + ";"
                + producto.getExpiracionProducto() + ";"
                + producto.getNombreProveedor());
    }
}
