package LogicaProyecto;

import java.util.LinkedList;
import java.util.StringTokenizer;

public class BDProductos {
    public LinkedList<Producto> ObtenerProductosDelArchivo (){
        LinkedList<Producto> productos = null;
        Inventario inventario = new Inventario("inventario.txt");
        LinkedList<String> lineas = inventario.ObtenerTextoDelArchivo();
        if(lineas != null){
            productos = new LinkedList<>();
            for(int indiceDeLinea=0; indiceDeLinea<lineas.size(); indiceDeLinea++){
                String linea = lineas.get(indiceDeLinea);
                StringTokenizer tokens = new StringTokenizer(linea, ";");
                String codigo = tokens.nextToken();
                String nombre = tokens.nextToken();
                String categoria = tokens.nextToken();
                int existencias = Integer.parseInt(tokens.nextToken());
                float precio = Float.parseFloat(tokens.nextToken());
                String expiracion = tokens.nextToken();
                String proveedor = tokens.nextToken();
                productos.add(new Producto(codigo, nombre, categoria, existencias, precio, expiracion, proveedor));
            }
        }
        return productos;
    }

    public boolean RegistrarProducto (Producto producto){
        Inventario archivo = new Inventario("inventario.txt");
        return archivo.registrar(producto.getIdProducto() + ";" + producto.getNombreProducto() + ";" +
                                  producto.getCategoriaProducto() + ";" + producto.getCantidadProducto() + ";" +
                                producto.getPrecioProducto() + ";" + producto.getExpiracionProducto() + ";" +
                                producto.getNombreProveedor());
    }
}
