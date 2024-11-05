package GUI;

import LogicaProyecto.BDProductos;
import LogicaProyecto.Inventario;
import LogicaProyecto.Producto;
import LogicaProyecto.ProductoRepositorio;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;

public class BorrarProducto extends JDialog{
    private JPanel panelBorrarProductos;
    private JTextField txtIDProductoAEliminar;
    private JButton bttEliminarProducto;
    private JButton bttSalir;
    private JButton bttCancelar;
    private JLabel lbIDProductoAEliminar;
    private JPanel panelTablaInventario;
    private JPanel panelSolicitudDeID;
    private JPanel panelBotones;
    private JTable tbltablaDeInventario;
    private DefaultTableModel modeloDeTabla;

    public BorrarProducto(InterfazProducto parentBorrarProductos) {
        super(parentBorrarProductos);
        setTitle("Crear Productos");
        setContentPane(panelBorrarProductos);
        setMinimumSize(new Dimension(600, 490));
        setModal(true);
        setLocationRelativeTo(panelBorrarProductos);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        CrearTabla();

        bttSalir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                InterfazProducto nuevaVentana = new InterfazProducto();
                nuevaVentana.setLocationRelativeTo(null);
                nuevaVentana.setVisible(true);
                dispose();
            }
        });
        bttEliminarProducto.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String IdABorrar = txtIDProductoAEliminar.getText();
                LinkedList<Producto> nuevosDatos = ExtraerInformacionDeLasCeldas(new LinkedList<>());

                ProductoRepositorio pr = new ProductoRepositorio();
                pr.compararId(nuevosDatos, IdABorrar);
                pr.actualizarListaDeProductos(nuevosDatos);

                Inventario inv = new Inventario("inventario.txt");
                inv.borrarContenido();

                BDProductos bd = new BDProductos();
                for (Producto producto : nuevosDatos) {
                    bd.RegistrarProducto(producto);
                }

                recargarDatosDeTabla();

            }
        });
        setVisible(true);
    }

    public void CrearTabla(){
        ProductoRepositorio repositorio = new ProductoRepositorio();
        repositorio.CargarProductoALaLista();  // Cargar productos en la lista desde el archivo
        LinkedList<Producto> productos = repositorio.getListaDeProductos();

        modeloDeTabla = new DefaultTableModel();
        modeloDeTabla.addColumn("Id Producto");
        modeloDeTabla.addColumn("Nombre del Producto");
        modeloDeTabla.addColumn("Categoría Del Producto");
        modeloDeTabla.addColumn("Stock");
        modeloDeTabla.addColumn("Precio del Producto");
        modeloDeTabla.addColumn("Fecha de Expiracion");
        modeloDeTabla.addColumn("Proveedor");

        for (Producto producto : productos) {
            Object[] fila = {
                    producto.getIdProducto(),
                    producto.getNombreProducto(),
                    producto.getCategoriaProducto(),
                    producto.getCantidadProducto(),
                    producto.getPrecioProducto(),
                    producto.getExpiracionProducto(),
                    producto.getNombreProveedor()
            };
            modeloDeTabla.addRow(fila);
        }
        tbltablaDeInventario = new JTable(modeloDeTabla);
        JScrollPane scrollPane = new JScrollPane(tbltablaDeInventario);
        panelTablaInventario.add(scrollPane, BorderLayout.CENTER);
    }



    public LinkedList<Producto> ExtraerInformacionDeLasCeldas(LinkedList<Producto> nuevosDatos){
        nuevosDatos = new LinkedList<>();
        int rowCount = modeloDeTabla.getRowCount();

        // Iterar sobre cada fila para obtener los datos y actualizar el producto en la lista
        for (int row = 0; row < rowCount; row++) {
            String idProducto = (String) modeloDeTabla.getValueAt(row, 0);
            String nombreProducto = (String) modeloDeTabla.getValueAt(row, 1);
            String categoriaProducto = (String) modeloDeTabla.getValueAt(row, 2);
            int cantidadProducto = Integer.parseInt(modeloDeTabla.getValueAt(row, 3).toString());
            float precioProducto = Float.parseFloat(modeloDeTabla.getValueAt(row, 4).toString());
            String expiracionProducto = (String) modeloDeTabla.getValueAt(row, 5);
            String proveedorProducto = (String) modeloDeTabla.getValueAt(row, 6);

            // Crear un nuevo objeto Producto con los datos de la fila
            Producto productoActualizado = new Producto(idProducto, nombreProducto, categoriaProducto,
                    cantidadProducto, precioProducto, expiracionProducto, proveedorProducto);

            nuevosDatos.add(productoActualizado);
        }
        return nuevosDatos;
    }

    public void recargarDatosDeTabla() {
        // Limpia todas las filas del modelo actual
        modeloDeTabla.setRowCount(0);

        // Vuelve a cargar los productos desde el archivo
        ProductoRepositorio repositorio = new ProductoRepositorio();
        repositorio.CargarProductoALaLista();  // Cargar productos en la lista desde el archivo
        LinkedList<Producto> productos = repositorio.getListaDeProductos();

        // Agrega cada producto como una nueva fila en el modelo
        for (Producto producto : productos) {
            Object[] fila = {
                    producto.getIdProducto(),
                    producto.getNombreProducto(),
                    producto.getCategoriaProducto(),
                    producto.getCantidadProducto(),
                    producto.getPrecioProducto(),
                    producto.getExpiracionProducto(),
                    producto.getNombreProveedor()
            };
            modeloDeTabla.addRow(fila);
        }

        // Refresca la tabla
        tbltablaDeInventario.revalidate();
        tbltablaDeInventario.repaint();
    }


    public static void main (String [] args){
        BorrarProducto tabla;
        tabla = new BorrarProducto(null);
    }
}
