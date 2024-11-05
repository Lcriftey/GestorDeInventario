package GUI;

import LogicaProyecto.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;

public class EditarProveedores extends JFrame{
    private JPanel panelEditarProveedores;
    private JPanel panelTablaProveedores;
    private JPanel panelBotones;
    private JButton bttGuardarCambiosYSalir;
    private JButton bttCancelarOperacion;
    private JTable tbltablaDeProveedores;
    private DefaultTableModel modeloDeTabla;

    public EditarProveedores(){
        setTitle("Editar Proveedores");
        setContentPane(panelEditarProveedores);
        setMinimumSize(new Dimension(600, 490));
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        CrearTabla();

        bttGuardarCambiosYSalir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LinkedList<Proveedor> nuevosDatos = ExtraerInformacionDeLasCeldas(new LinkedList<>());


                ListadoDeProveedores inv = new ListadoDeProveedores("Lista De Proveedores.txt");
                inv.borrarContenido();

                ProveedorRepositorio pr = new ProveedorRepositorio();
                pr.actualizarListaDeProveedores(nuevosDatos);

                BDProveedores bd = new BDProveedores();
                for (Proveedor proveedor : nuevosDatos) {
                    bd.registrarProveedor(proveedor);
                }
                InterfazProveedores nuevaVentana = new InterfazProveedores();
                nuevaVentana.setLocationRelativeTo(null);
                nuevaVentana.setVisible(true);
                dispose();
            }
        });
        bttCancelarOperacion.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                InterfazProveedores nuevaVentana = new InterfazProveedores();
                nuevaVentana.setLocationRelativeTo(null);
                nuevaVentana.setVisible(true);
                dispose();
            }
        });setVisible(true);
    }


    public void CrearTabla(){
        ProveedorRepositorio repositorio = new ProveedorRepositorio();
        repositorio.cargarProveedorALaLista();  // Cargar productos en la lista desde el archivo
        LinkedList<Proveedor> proveedores = repositorio.getListaDeProveedores();

        modeloDeTabla = new DefaultTableModel();
        modeloDeTabla.addColumn("ID");
        modeloDeTabla.addColumn("Nombre");
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

    public LinkedList<Proveedor> ExtraerInformacionDeLasCeldas(LinkedList<Proveedor> nuevosDatos){
        nuevosDatos = new LinkedList<>();
        int rowCount = modeloDeTabla.getRowCount();

        // Iterar sobre cada fila para obtener los datos y actualizar el producto en la lista
        for (int row = 0; row < rowCount; row++) {
            String idProveedor = (String) modeloDeTabla.getValueAt(row, 0);
            String nombreProveedor = (String) modeloDeTabla.getValueAt(row, 1);
            String direccionProveedor = (String) modeloDeTabla.getValueAt(row, 2);
            String telefonoProveedor = (String) modeloDeTabla.getValueAt(row, 3);

            // Crear un nuevo objeto Producto con los datos de la fila
            Proveedor proveedorActualizado = new Proveedor(idProveedor,nombreProveedor, direccionProveedor, telefonoProveedor);

            nuevosDatos.add(proveedorActualizado);
        }
        return nuevosDatos;
    }
    public static void main(String[] args) {
            EditarProveedores ventanNueva = new EditarProveedores();
            ventanNueva.setLocationRelativeTo(null);
            ventanNueva.setVisible(true);
    }
}

