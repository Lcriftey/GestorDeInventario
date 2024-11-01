package LogicaProyecto;

import java.util.ArrayList;
import java.util.List;

public class ProveedoresRepositorio {
    private List<Proveedores> proveedores;

    // Constructor
    public ProveedoresRepositorio() {
        proveedores = new ArrayList<>();
    }

    // Crear o guardar un proveedor
    public void guardar(Proveedores proveedor) {
        proveedores.add(proveedor);
    }

    // Leer o buscar un proveedor por su ID
    public Proveedores buscarPorId(int id) {
        for (Proveedores proveedor : proveedores) {
            if (proveedor.getId() == id) {
                return proveedor;
            }
        }
        return null; // Retorna null si no encuentra el proveedor
    }

    // Actualizar un proveedor existente
    public void actualizar(Proveedores proveedorActualizado) {
        for (int i = 0; i < proveedores.size(); i++) {
            Proveedores proveedor = proveedores.get(i);
            if (proveedor.getId() == proveedorActualizado.getId()) {
                proveedores.set(i, proveedorActualizado);
                break;
            }
        }
    }

    // Eliminar un proveedor por su ID
    public void eliminar(int id) {
        proveedores.removeIf(proveedor -> proveedor.getId() == id);
    }

    // Obtener todos los proveedores
    public List<Proveedores> obtenerTodos() {
        return proveedores;
    }
}