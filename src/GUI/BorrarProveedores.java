package GUI;

import LogicaProyecto.BDProveedores;
import LogicaProyecto.ListadoDeProveedores;
import LogicaProyecto.Proveedor;
import LogicaProyecto.ProveedorRepositorio;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;

public class BorrarProveedores extends JFrame{
    private JPanel panelBorrarProveedores;
    private JTextField txtIDProveedorAEliminar;
    private JButton bttBorrarProveedor;
    private JButton bttSalir;
    private JLabel lbIDProveedorAEliminar;
    private JPanel panelTablaProveedores;
    private JPanel panelBotones;
    private JPanel panelSolicitudDeID;
    private JTable tbltablaDeProveedores;
    private DefaultTableModel modeloDeTabla;

    public BorrarProveedores() {
        setTitle("Borrar Proveedores");
        setContentPane(panelBorrarProveedores);
        setMinimumSize(new Dimension(600, 490));
        setLocationRelativeTo(panelBorrarProveedores);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        CrearTabla();

        bttSalir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                InterfazProveedores nuevaVentana = new InterfazProveedores();
                nuevaVentana.setLocationRelativeTo(null);
                nuevaVentana.setVisible(true);
                dispose();
            }
        });

        bttBorrarProveedor.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String idABorrar = txtIDProveedorAEliminar.getText();
                LinkedList<Proveedor> nuevosDatos = ExtraerInformacionDeLasCeldas(new LinkedList<>());

                ProveedorRepositorio pr = new ProveedorRepositorio();
                pr.compararId(nuevosDatos, idABorrar);
                pr.actualizarListaDeProveedores(nuevosDatos);

                ListadoDeProveedores lp = new ListadoDeProveedores("Lista De Proveedores.txt");
                lp.borrarContenido();

                BDProveedores bd = new BDProveedores();
                for (Proveedor proveedor : nuevosDatos) {
                    bd.registrarProveedor(proveedor);
                }

                recargarDatosDeTabla();
            }
        });

        setVisible(true);
    }

    public void CrearTabla() {
        ProveedorRepositorio repositorio = new ProveedorRepositorio();
        repositorio.cargarProveedorALaLista();  // Cargar proveedores en la lista desde el archivo
        LinkedList<Proveedor> proveedores = repositorio.getListaDeProveedores();

        modeloDeTabla = new DefaultTableModel();
        modeloDeTabla.addColumn("ID Proveedor");
        modeloDeTabla.addColumn("Nombre del Proveedor");
        modeloDeTabla.addColumn("Dirección");
        modeloDeTabla.addColumn("Teléfono");

        for (Proveedor proveedor : proveedores) {
            Object[] fila = {
                    proveedor.getIdProveedor(),
                    proveedor.getNombreProveedor(),
                    proveedor.getDireccionProveedor(),
                    proveedor.getTelefonoProveedor()
            };
            modeloDeTabla.addRow(fila);
        }

        tbltablaDeProveedores = new JTable(modeloDeTabla);
        JScrollPane scrollPane = new JScrollPane(tbltablaDeProveedores);
        panelTablaProveedores.add(scrollPane, BorderLayout.CENTER);
    }

    public LinkedList<Proveedor> ExtraerInformacionDeLasCeldas(LinkedList<Proveedor> nuevosDatos) {
        nuevosDatos = new LinkedList<>();
        int rowCount = modeloDeTabla.getRowCount();

        // Iterar sobre cada fila para obtener los datos y actualizar el proveedor en la lista
        for (int row = 0; row < rowCount; row++) {
            String idProveedor = (String) modeloDeTabla.getValueAt(row, 0);
            String nombreProveedor = (String) modeloDeTabla.getValueAt(row, 1);
            String direccionProveedor = (String) modeloDeTabla.getValueAt(row, 2);
            String telefonoProveedor = (String) modeloDeTabla.getValueAt(row, 3);

            // Crear un nuevo objeto Proveedor con los datos de la fila
            Proveedor proveedorActualizado = new Proveedor(idProveedor, nombreProveedor, direccionProveedor, telefonoProveedor);

            nuevosDatos.add(proveedorActualizado);
        }
        return nuevosDatos;
    }

    public void recargarDatosDeTabla() {
        // Limpia todas las filas del modelo actual
        modeloDeTabla.setRowCount(0);

        // Vuelve a cargar los proveedores desde el archivo
        ProveedorRepositorio repositorio = new ProveedorRepositorio();
        repositorio.cargarProveedorALaLista();  // Cargar proveedores en la lista desde el archivo
        LinkedList<Proveedor> proveedores = repositorio.getListaDeProveedores();

        // Agrega cada proveedor como una nueva fila en el modelo
        for (Proveedor proveedor : proveedores) {
            Object[] fila = {
                    proveedor.getIdProveedor(),
                    proveedor.getNombreProveedor(),
                    proveedor.getDireccionProveedor(),
                    proveedor.getTelefonoProveedor()
            };
            modeloDeTabla.addRow(fila);
        }

        // Refresca la tabla
        tbltablaDeProveedores.revalidate();
        tbltablaDeProveedores.repaint();
    }

    public static void main(String[] args) {
        BorrarProveedores tabla = new BorrarProveedores();
        tabla.setLocationRelativeTo(null);
        tabla.setVisible(true);
    }
}
