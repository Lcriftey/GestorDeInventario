package GUI;

import LogicaProyecto.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;

public class RegistrarMovimientos extends JFrame{
    private JPanel panelRegistrarMovimientos;
    private JPanel panelBasico;
    private JPanel panelTablaInventario;
    private JPanel panelInformacion;
    private JPanel panelBotones;
    private JButton bttRegistrarEntrada;
    private JButton bttRegistrarSalida;
    private JTextField txtIdProducto;
    private JTextField txtNombreProducto;
    private JTextField txtCantidadAAlterar;
    private JTextArea txtJustificaciónMovimiento;
    private JLabel lblIdProducto;
    private JLabel lblNombreProducto;
    private JLabel lblCantidadAAlterar;
    private JLabel lblFechaProducto;
    private JTextField txtFechaProducto;
    private JLabel lblJustificaciónMovimiento;
    private JButton volverButton;
    private JTable tbltablaDeInventario;
    private DefaultTableModel modeloDeTabla;

    public RegistrarMovimientos(){
        setTitle("Registrar Movimientos");
        setContentPane(panelRegistrarMovimientos);
        setMinimumSize(new Dimension(600, 490));
        setLocationRelativeTo(panelRegistrarMovimientos);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        CrearTabla();


        bttRegistrarEntrada.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Movimientos nuevoMovimiento = ExtraerInformacionDeCeldas();
                MovimientoServicio movimientoServicio = new MovimientoServicio();

                BDProductos bdProductos = new BDProductos();
                LinkedList<Producto> listaProductos = bdProductos.ObtenerProductosDelArchivo();



                boolean exito = movimientoServicio.MovimientoEntrada(nuevoMovimiento, listaProductos, true);
                if (exito) {
                    System.out.println("Archivo actualizado correctamente.");
                } else {
                    System.out.println("Hubo un error al actualizar el archivo.");
                }

                recargarDatosDeTabla();
                VerificarStockEnTabla();
            }
        });
        bttRegistrarSalida.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Movimientos nuevoMovimiento = ExtraerInformacionDeCeldas();
                MovimientoServicio movimientoServicio = new MovimientoServicio();

                BDProductos bdProductos = new BDProductos();
                LinkedList<Producto> listaProductos = bdProductos.ObtenerProductosDelArchivo();



                boolean exito = movimientoServicio.MovimientoEntrada(nuevoMovimiento, listaProductos, false);
                if (exito) {
                    System.out.println("Archivo actualizado correctamente.");
                } else {
                    System.out.println("Hubo un error al actualizar el archivo.");
                }

                recargarDatosDeTabla();
                VerificarStockEnTabla();
            }
        });
        volverButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Principal principal = new Principal();
                principal.setLocationRelativeTo(null);
                principal.setVisible(true);
                dispose();
            }
        });setVisible(true);
        VerificarStockEnTabla();


    }

    public void VerificarStockEnTabla(){
        NotificacionesProducto notificacionesProducto = new NotificacionesProducto();
        ProductoRepositorio productoRepositorio = new ProductoRepositorio();
        productoRepositorio.CargarProductoALaLista();
        LinkedList<Producto> listaProducto = productoRepositorio.getListaDeProductos();
        boolean alertaUsuario = notificacionesProducto.CantidadCorrecta(listaProducto);
        if(!alertaUsuario){
            int respuesta = JOptionPane.showConfirmDialog(RegistrarMovimientos.this,
                    "Actualmente hay uno o mas productos que no cuenta con el stock minimo" +
                            "\n¿Deseas solicitar unidades al proveedor?", "Confirmación",
                    JOptionPane.YES_NO_OPTION);
            if (respuesta == JOptionPane.YES_OPTION) {
                System.out.println("Has seleccionado Sí.");
                notificacionesProducto.CantidadMaxima(listaProducto);
                recargarDatosDeTabla();
            } else if (respuesta == JOptionPane.NO_OPTION) {
                System.out.println("Has seleccionado No.");
            }
        }
    }

    public void CrearTabla(){
        ProductoRepositorio repositorio = new ProductoRepositorio();
        repositorio.CargarProductoALaLista();  // Cargar productos en la lista desde el archivo
        LinkedList<Producto> productos = repositorio.getListaDeProductos();

        modeloDeTabla = new DefaultTableModel();
        modeloDeTabla.addColumn("Id Producto");
        modeloDeTabla.addColumn("Nombre del Producto");
        modeloDeTabla.addColumn("Stock");

        modeloDeTabla.addColumn("Proveedor");

        for (Producto producto : productos) {
            Object[] fila = {
                    producto.getIdProducto(),
                    producto.getNombreProducto(),
                    producto.getCantidadProducto(),
                    producto.getProveedorProducto().getNombreProveedor()
            };
            modeloDeTabla.addRow(fila);
        }
        tbltablaDeInventario = new JTable(modeloDeTabla);
        JScrollPane scrollPane = new JScrollPane(tbltablaDeInventario);
        panelTablaInventario.add(scrollPane, BorderLayout.CENTER);
    }

    public Movimientos ExtraerInformacionDeCeldas(){
        BDProductos bdProductos = new BDProductos();
        LinkedList<Producto> listaProductos = bdProductos.ObtenerProductosDelArchivo();

        String nombreProducto, idProducto, fechaProducto;
        int cantidadProducto;

        idProducto = txtIdProducto.getText();
        //nombreProducto = txtNombreProducto.getText();
        cantidadProducto = Integer.parseInt(txtCantidadAAlterar.getText());
        fechaProducto = txtFechaProducto.getText();

        ProductoRepositorio productoRepositorio = new ProductoRepositorio();
        Producto producto = productoRepositorio.ObtenerProductoPorId(listaProductos, idProducto);

        Movimientos movimiento = new Movimientos(idProducto, producto, fechaProducto, cantidadProducto);
        return movimiento;
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
                    producto.getCantidadProducto(),
                    producto.getProveedorProducto().getNombreProveedor()
            };
            modeloDeTabla.addRow(fila);
        }

        // Refresca la tabla
        tbltablaDeInventario.revalidate();
        tbltablaDeInventario.repaint();
    }

    public static void main (String [] args){
        RegistrarMovimientos rm = new RegistrarMovimientos();
        rm.setLocationRelativeTo(null);
        rm.setVisible(true);
    }
}
