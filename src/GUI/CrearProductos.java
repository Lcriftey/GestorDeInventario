package GUI;

import LogicaProyecto.BDProductos;
import LogicaProyecto.Producto;
import LogicaProyecto.ProductoRepositorio;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;

public class CrearProductos extends JDialog{
    private JPanel panelCrearProductos;
    private JButton bttGuardarProducto;
    private JButton bttCerrarVentana;
    private JLabel IdProducto;
    private JTextField txtCantidadProducto;
    private JTextField txtIDProducto;
    private JTextField txtNombrePoducto;
    private JComboBox cmbTipoProducto;
    private JTextField txtPrecioProducto;
    private JTextField txtFechaExpiracionProducto;
    private JTextField txtProveedorProducto;
    private JPanel JPanelInventario;
    private JTable tbltablaDeInventario;
    private DefaultTableModel modeloDeTabla;

    public CrearProductos(InterfazProducto parentCrearProductos) {
        super(parentCrearProductos);
        setTitle("Crear Productos");
        setContentPane(panelCrearProductos);
        setMinimumSize(new Dimension(600, 490));
        setModal(true);
        setLocationRelativeTo(panelCrearProductos);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

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
        JPanelInventario.add(scrollPane, BorderLayout.CENTER);
        //add(scrollPane);
        setVisible(true);
        bttGuardarProducto.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Producto p = new Producto("200","Waffer", "No Perecedero",4,100452,"12082029","Colombina");
                BDProductos bd = new BDProductos();
                bd.RegistrarProducto(p);
            }
        });
    }
}