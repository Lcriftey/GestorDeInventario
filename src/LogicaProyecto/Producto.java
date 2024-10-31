package LogicaProyecto;

public class Producto {
    private String idProducto;
    private String nombreProducto;
    private String categoriaProducto;
    private int cantidadProducto;
    private float precioProducto;
    private String expiracionProducto;
    private String nombreProveedor;
    /*private Proveedor proveedorDelProducto;  Aqui la idea es que haya una dependencia entre los productos y los proveedores*/

    public Producto(String id, String nombre, String categoría, int cantidad, float precio,
                    String fechaExpiracion, String Proveedor){
        idProducto = id;
        nombreProducto = nombre;
        categoriaProducto = categoría;
        cantidadProducto = cantidad;
        precioProducto = precio;
        expiracionProducto = fechaExpiracion;
        nombreProveedor = Proveedor;
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

    public String getNombreProveedor() {
        return nombreProveedor;
    }

    public void setNombreProveedor(String nombreProveedor) {
        this.nombreProveedor = nombreProveedor;
    }
}
