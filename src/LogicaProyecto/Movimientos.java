package LogicaProyecto;

public class Movimientos {
    private String fechaMovimiento;
    private Producto productoMovimiento;
    private String tipoDeMovimiento;
    private int cantidadDeMovimientos;

    public Movimientos(String fecha, Producto producto, String tipo, int movimientosEfectuados) {
        fechaMovimiento = fecha;
        productoMovimiento = producto;
        tipoDeMovimiento = tipo;
        cantidadDeMovimientos = movimientosEfectuados;

    }

    public String getFechaMovimiento() {
        return fechaMovimiento;
    }

    public void setFechaMovimiento(String fechaMovimiento) {
        this.fechaMovimiento = fechaMovimiento;
    }

    public Producto getProductoMovimiento() {
        return productoMovimiento;
    }

    public void setProductoMovimiento(Producto productoMovimiento) {
        this.productoMovimiento = productoMovimiento;
    }

    public String getTipoDeMovimiento() {
        return tipoDeMovimiento;
    }

    public void setTipoDeMovimiento(String tipoDeMovimiento) {
        this.tipoDeMovimiento = tipoDeMovimiento;
    }

    public int getCantidadDeMovimientos() {
        return cantidadDeMovimientos;
    }

    public void setCantidadDeMovimientos(int cantidadDeMovimientos) {
        this.cantidadDeMovimientos = cantidadDeMovimientos;
    }
}
