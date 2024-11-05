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

public class CrearProductos extends JFrame{
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
    private JButton bttCloseWindow;
    private JTable tbltablaDeInventario;
    private DefaultTableModel modeloDeTabla;

    public CrearProductos(InterfazProducto parentCrearProductos) {
        setTitle("Crear Productos");
        setContentPane(panelCrearProductos);
        setMinimumSize(new Dimension(600, 490));
        setLocationRelativeTo(panelCrearProductos);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        //Se obtienen los datos del archivo y se suben a la tabla de inventario
        CrearTabla();

        bttGuardarProducto.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Producto p = (ExtraerInformacionDeCampos(txtIDProducto,txtNombrePoducto,
                        cmbTipoProducto, txtCantidadProducto, txtPrecioProducto,
                        txtFechaExpiracionProducto, txtProveedorProducto));
                BDProductos bd = new BDProductos();

                boolean registrado = bd.RegistrarProducto(p);

                if (registrado) {
                    JOptionPane.showMessageDialog(null, "Producto registrado con éxito.");
                    recargarDatosDeTabla();
                } else {
                    JOptionPane.showMessageDialog(null, "Error al registrar el producto.");
                }
            }
        });
        bttCerrarVentana.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                InterfazProducto abrirventana = new InterfazProducto();
                abrirventana.setVisible(true);
            }

        });
        setVisible(true);
    }



    public Producto ExtraerInformacionDeCampos(JTextField txtIDProducto,
                                             JTextField txtNombreProducto,
                                             JComboBox cmbTipoProducto,
                                             JTextField txtCantidadProducto,
                                             JTextField txtPrecioProducto,
                                             JTextField txtFechaExpiracionProducto,
                                             JTextField txtProveedorProducto){

        String id = txtIDProducto.getText();
        String nombre = txtNombreProducto.getText();
        String categoria = (String) cmbTipoProducto.getSelectedItem();
        int cantidad = Integer.parseInt(txtCantidadProducto.getText());
        float precio = Float.parseFloat(txtPrecioProducto.getText());
        String expiracion = txtFechaExpiracionProducto.getText();
        String proveedor = txtProveedorProducto.getText();


        return new Producto(id, nombre, categoria, cantidad, precio, expiracion, proveedor);
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
        JPanelInventario.add(scrollPane, BorderLayout.CENTER);
    }

    public static void main (String [] args){
        CrearProductos tabla;
        tabla = new CrearProductos(null);
    }
}

