package LogicaProyecto;

// Métodos que permiten realizar el CRUD con los proveedores
import java.util.LinkedList;

public class ProveedorRepositorio {
    private LinkedList<Proveedor> listaDeProveedores = new LinkedList<>();
    private LinkedList<Proveedor> nuevosDatos = new LinkedList<>();

    public void agregarProveedorALista(Proveedor proveedor) {
        listaDeProveedores.add(proveedor);
    }

    public void eliminarProveedorDeLista(Proveedor proveedor) {
        listaDeProveedores.remove(proveedor);
    }

    public int totalProveedoresEnLista() {
        return listaDeProveedores.size();
    }

    public Proveedor obtenerProveedorDeLista(int indiceDelProveedor) {
        return listaDeProveedores.get(indiceDelProveedor);
    }

    public void cargarProveedorALaLista() {
        BDProveedores bdProveedores = new BDProveedores();
        listaDeProveedores = bdProveedores.obtenerProveedoresDelArchivo();
    }

    public LinkedList<Proveedor> getListaDeProveedores() {
        return listaDeProveedores;
    }

    public void actualizarListaDeProveedores(LinkedList<Proveedor> nuevosDatos) {
        listaDeProveedores = new LinkedList<>(nuevosDatos); // Asignar la nueva lista
    }

    public void compararId(LinkedList<Proveedor> nuevosDatos, String idAComparar) {
        nuevosDatos.removeIf(proveedor -> proveedor.getIdProveedor().equals(idAComparar));
    }
}

