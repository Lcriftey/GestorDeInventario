package GUI;

import LogicaProyecto.Producto;
import LogicaProyecto.ProductoRepositorio;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;

public class ConsultarProductos extends JFrame{
    private JPanel panelConsultarProductos;
    private JButton salirButton;
    private JPanel panelBotones;
    private JPanel panelTablaInventario;
    private JTable tbltablaDeInventario;
    private DefaultTableModel modeloDeTabla;


    public ConsultarProductos() {
        setTitle("Editar Productos");
        setContentPane(panelConsultarProductos);
        setMinimumSize(new Dimension(600, 490));
        setLocationRelativeTo(panelConsultarProductos);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        CrearTabla();
        salirButton.addActionListener(new ActionListener() {
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
                    producto.getNombreProveedor()
            };
            modeloDeTabla.addRow(fila);
        }
        tbltablaDeInventario = new JTable(modeloDeTabla);
        JScrollPane scrollPane = new JScrollPane(tbltablaDeInventario);
        panelTablaInventario.add(scrollPane, BorderLayout.CENTER);
    }
}
