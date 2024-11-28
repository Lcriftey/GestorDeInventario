package LogicaProyecto;

public class Producto {
    private String idProducto;
    private String nombreProducto;
    private String categoriaProducto;
    private int cantidadProducto;
    private float precioProducto;
    private String expiracionProducto;
    private Proveedor proveedorDeProducto;

    public Producto(String id, String nombre, String categoría, int cantidad, float precio,
                    String fechaExpiracion, Proveedor Proveedor){
        idProducto = id;
        nombreProducto = nombre;
        categoriaProducto = categoría;
        cantidadProducto = cantidad;
        precioProducto = precio;
        expiracionProducto = fechaExpiracion;
        proveedorDeProducto = Proveedor;
    }

    public String getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(String idProducto) {
        this.idProducto = idProducto;
    }

    public String getNombreProducto() {
        return nombreProducto;
    }

    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }

    public String getCategoriaProducto() {
        return categoriaProducto;
    }

    public void setCategoriaProducto(String categoriaProducto) {
        this.categoriaProducto = categoriaProducto;
    }

    public int getCantidadProducto() {
        return cantidadProducto;
    }

    public void setCantidadProducto(int cantidadProducto) {
        this.cantidadProducto = cantidadProducto;
    }

    public float getPrecioProducto() {
        return precioProducto;
    }

    public void setPrecioProducto(int precioProducto) {
        this.precioProducto = precioProducto;
    }

    public String getExpiracionProducto() {
        return expiracionProducto;
    }

    public void setExpiracionProducto(String expiracionProducto) {
        this.expiracionProducto = expiracionProducto;
    }

    public Proveedor getProveedorProducto() {
        return proveedorDeProducto;
    }

    public void setNombreProveedor(Proveedor nombreProveedor) {
        this.proveedorDeProducto = nombreProveedor;
    }
}
