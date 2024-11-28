package LogicaProyecto;

import java.util.LinkedList;

public class MovimientoServicio {
    private LinkedList<Movimientos>  listaMovimientos = new LinkedList<>();

    public LinkedList<Movimientos> ObtenerListaDeMovimientos(){
        return listaMovimientos;
    }

    public boolean MovimientoEntrada(Movimientos movimiento, LinkedList<Producto> listaproductos, boolean esSuma) {
        Producto productoMovimiento = movimiento.getProductoMovimiento();
        BDProductos bdProductos = new BDProductos();

        int cantidadAjuste = movimiento.getCantidadDeMovimientos();

        for (Producto producto : listaproductos) {
            if (producto.getIdProducto().equals(productoMovimiento.getIdProducto())) {
                // Ajustar la cantidad según el valor del parámetro esSuma
                int nuevaCantidad = esSuma
                        ? producto.getCantidadProducto() + cantidadAjuste
                        : producto.getCantidadProducto() - cantidadAjuste;

                // Asegurarse de que la cantidad no sea negativa
                if (nuevaCantidad < 0) {
                    System.out.println("Error: La cantidad no puede ser negativa.");
                    return false;
                }

                producto.setCantidadProducto(nuevaCantidad);

                // Guardar la lista actualizada en el archivo
                return bdProductos.GuardarListaDeProductos(listaproductos);
            }
        }

        // Producto no encontrado
        System.out.println("Error: Producto no encontrado.");
        return false;
    }


    public void MovimientoSalida(){

    }

    public int CalcularDisponibilidad(Producto producto){
        int cantidadDisponible = producto.getCantidadProducto();
        return cantidadDisponible;
    }

}
