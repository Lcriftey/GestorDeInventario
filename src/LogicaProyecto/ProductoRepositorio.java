package LogicaProyecto;
//Metodos que permitan realizar el CRUD con los productos

import java.util.LinkedList;

public class ProductoRepositorio {
    private LinkedList <Producto> listaDeProductos= new LinkedList<>();

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

    /*Aqui debe ir el metodo de cargar productos en la lista*/
    public void CargarProductoALaLista(){
        BDProductos bdproductos = new BDProductos();
        listaDeProductos = bdproductos.ObtenerProductosDelArchivo();
    }

    public void ImprimirLista(){
        for(int i=0; i<listaDeProductos.size(); i++){
            System.out.println(listaDeProductos.get(i).getIdProducto()+ " " + listaDeProductos.get(i).getNombreProducto());
        }
    }

    public LinkedList<Producto> getListaDeProductos() {
        return listaDeProductos;
    }
}
