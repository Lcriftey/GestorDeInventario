package LogicaProyecto;

import java.util.LinkedList;

public class NotificacionesProducto {

    public boolean CantidadCorrecta(LinkedList<Producto> listaProductos) {
        int cantidadMinima = 5;
        for (Producto producto : listaProductos) {
            if (producto.getCantidadProducto() <= cantidadMinima) {
                return false;
            }
        }
        return true;
    }

    public void CantidadMaxima(LinkedList<Producto> listaProductos) {
        for (Producto producto : listaProductos) {
            if (producto.getCantidadProducto() < 6) {
                int cantidadMaxima = 100 - producto.getCantidadProducto();
                producto.setCantidadProducto(cantidadMaxima + producto.getCantidadProducto());
            }
        }

        BDProductos bd = new BDProductos();
        boolean cambioEfectuado = bd.GuardarListaDeProductos(listaProductos);
    }
}
