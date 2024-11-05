package LogicaProyecto;
//Metodos que permitan realizar el CRUD con los productos

import java.util.LinkedList;

public class ProductoRepositorio {
    private LinkedList <Producto> listaDeProductos= new LinkedList<>();
    private LinkedList <Producto> nuevosdatos = new LinkedList<>();

    public void AgregarProductosALista (Producto producto){
        listaDeProductos.add(producto);
    }

    public void EliminarProductoDeLista (Producto producto){
        listaDeProductos.remove(producto);
    }

    public int TotalProductosEnLista (Producto producto){
        return listaDeProductos.size();
    }

    public Producto ObtenerProductoDeLista (int indiceDelProducto){
        return listaDeProductos.get(indiceDelProducto);
    }

    public void CargarProductoALaLista(){
        BDProductos bdproductos = new BDProductos();
        listaDeProductos = bdproductos.ObtenerProductosDelArchivo();
    }


    public LinkedList<Producto> getListaDeProductos() {
        return listaDeProductos;
    }

    public void actualizarListaDeProductos(LinkedList<Producto> nuevosDatos) {
        listaDeProductos = new LinkedList<>(nuevosDatos); // Asignar la nueva lista
    }

    public void compararId(LinkedList<Producto> nuevosdatos, String IdAComparar){
        nuevosdatos.removeIf(producto -> producto.getIdProducto().equals(IdAComparar));
    }

}
