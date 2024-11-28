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

public class ConsultarProductos extends JFrame{
    private JPanel panelConsultarProductos;
    private JButton salirButton;
    private JPanel panelBotones;
    private JPanel panelTablaInventario;
    private JTextField txtNombreAConsultar;
    private JTextField txtCategoriaAConsultar;
    private JButton bttConsultarInventario;
    private JLabel lblConsultaNombre;
    private JLabel lblConsultaCategoría;
    private JButton restablecerButton;
    private JButton consultarArchivoButton;
    private JTable tbltablaDeInventario;
    private DefaultTableModel modeloDeTabla;

    public ConsultarProductos() {
        setTitle("Consultar Productos");
        setContentPane(panelConsultarProductos);
        setMinimumSize(new Dimension(600, 490));
        setLocationRelativeTo(panelConsultarProductos);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        LinkedList<Producto> listaDefault = null;
        CrearTabla();
        salirButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                InterfazProducto nuevaVentana = new InterfazProducto();
                nuevaVentana.setLocationRelativeTo(null);
                nuevaVentana.setVisible(true);
                dispose();
            }
        });
        bttConsultarInventario.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {


                BDProductos bdProductos = new BDProductos();
                LinkedList<Producto> lista = bdProductos.ObtenerProductosDelArchivo();
                recargarDatosDeTabla(ProductosConsultados(lista));

            }
        });
        restablecerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ProductoRepositorio repositorio = new ProductoRepositorio();
                repositorio.CargarProductoALaLista();  // Cargar productos en la lista desde el archivo
                LinkedList<Producto> productos = repositorio.getListaDeProductos();
                recargarDatosDeTabla(productos);
            }
        });
        consultarArchivoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Inventario archivo = new Inventario("inventario.txt");
                archivo.AbrirArchivo();

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


    //Se encarga de crear una nueva lista con solamente los productos que concuerdan con los atributos buscados
    public LinkedList<Producto> ProductosConsultados(LinkedList<Producto> nuevaLista){
        LinkedList<Producto> productosBuscados = new LinkedList<>();

        ProductoRepositorio productoRepositorio = new ProductoRepositorio();
        String nombreSuministrado, numeroSuministrado;
        nombreSuministrado = txtNombreAConsultar.getText();
        numeroSuministrado = txtCategoriaAConsultar.getText();

        productosBuscados = productoRepositorio.buscarProductoPorAtributo(nombreSuministrado,
                                                                            numeroSuministrado,
                                                                                nuevaLista);


        return productosBuscados;
    }


    public void recargarDatosDeTabla(LinkedList<Producto> productos) {
        // Limpia todas las filas del modelo actual
        modeloDeTabla.setRowCount(0);

        // Agrega cada producto como una nueva fila en el modelo
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

        // Refresca la tabla
        tbltablaDeInventario.revalidate();
        tbltablaDeInventario.repaint();
    }
}
