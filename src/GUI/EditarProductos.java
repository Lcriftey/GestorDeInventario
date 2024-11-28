package GUI;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import LogicaProyecto.*;


public class EditarProductos extends JFrame{
    private JPanel panelEditarProductos;
    private JPanel panelTablaInventario;
    private JPanel panelBotones;
    private JButton bttGuardarCambiosYSalir;
    private JButton bttCancelarOperacion;
    private JTextArea textArea1;
    private JTable tbltablaDeInventario;
    private DefaultTableModel modeloDeTabla;

    public EditarProductos() {
        setTitle("Crear Productos");
        setContentPane(panelEditarProductos);
        setMinimumSize(new Dimension(600, 490));
        setLocationRelativeTo(panelEditarProductos);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        CrearTabla();




        bttGuardarCambiosYSalir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LinkedList<Producto> nuevosDatos = ExtraerInformacionDeLasCeldas(new LinkedList<>());


                Inventario inv = new Inventario("inventario.txt");
                inv.borrarContenido();

                ProductoRepositorio pr = new ProductoRepositorio();
                pr.actualizarListaDeProductos(nuevosDatos);

                BDProductos bd = new BDProductos();
                for (Producto producto : nuevosDatos) {
                    bd.RegistrarProducto(producto);
                }
                InterfazProducto nuevaVentana = new InterfazProducto();
                nuevaVentana.setLocationRelativeTo(null);
                nuevaVentana.setVisible(true);
                dispose();
            }
        });
        bttCancelarOperacion.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                InterfazProducto nuevaVentana = new InterfazProducto();
                nuevaVentana.setLocationRelativeTo(null);
                nuevaVentana.setVisible(true);
                dispose();
            }
        });setVisible(true);
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
                    producto.getProveedorProducto().getNombreProveedor()
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
            String nombreProveedor = (String) modeloDeTabla.getValueAt(row, 6);


            Proveedor proveedorDeProducto = new Proveedor(nombreProveedor, " ",
                    " ", " ");

            // Crear un nuevo objeto Producto con los datos de la fila
            Producto productoActualizado = new Producto(idProducto, nombreProducto, categoriaProducto,
                    cantidadProducto, precioProducto, expiracionProducto, proveedorDeProducto);

            nuevosDatos.add(productoActualizado);
        }
        return nuevosDatos;
    }
}
