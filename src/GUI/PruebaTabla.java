package GUI;

import LogicaProyecto.BDProductos;
import LogicaProyecto.Producto;
import LogicaProyecto.ProductoRepositorio;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.LinkedList;

public class PruebaTabla extends JDialog{
    private JTable tabla;
    private DefaultTableModel modeloDeTabla;
    private BDProductos bd;
    private JPanel Jpanel;

    public PruebaTabla (JFrame parent){
        super(parent);
        setTitle("LogicaProyecto.Login");
        Jpanel = new JPanel(new BorderLayout());
        setContentPane(Jpanel);
        setMinimumSize(new Dimension(470, 478));
        setModal(true);
        setLocationRelativeTo(parent);
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
        tabla = new JTable(modeloDeTabla);
        JScrollPane scrollPane = new JScrollPane(tabla);
        add(scrollPane);

        setLocationRelativeTo(null); // Centra la ventana en la pantalla
        setVisible(true);
    }

    public static void main (String [] args){
        PruebaTabla tabla;
        tabla = new PruebaTabla(null);
    }
}

