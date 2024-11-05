package GUI;

import LogicaProyecto.BDProveedores;
import LogicaProyecto.Proveedor;
import LogicaProyecto.ProveedorRepositorio;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;

public class CrearProveedores extends JFrame {
    private JPanel panel1;
    private JPanel panelCrearProveedores;
    private JButton bttGuardarProveedor;
    private JButton bttCerrarVentana;
    private JTextField txtIDProveedor;
    private JTextField txtNombreProveedor;
    private JTextField txtDireccionProveedor;
    private JTextField txtTelefonoProveedor;
    private JPanel JPanelListaProveedores;
    private JLabel IdProveedor;
    private JTable tblTablaDeProveedores;
    private DefaultTableModel modeloDeTabla;

    public CrearProveedores() {
        setTitle("Crear Proveedores");
        setContentPane(panelCrearProveedores);
        setMinimumSize(new Dimension(600, 490));
        setLocationRelativeTo(panelCrearProveedores);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        // Cargar proveedores en la tabla al iniciar
        crearTabla();

        bttGuardarProveedor.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Proveedor proveedor = extraerInformacionDeCampos();
                BDProveedores bdProveedor = new BDProveedores();

                boolean registrado = bdProveedor.registrarProveedor(proveedor);

                if (registrado) {
                    JOptionPane.showMessageDialog(null, "Proveedor registrado con éxito.");
                    recargarDatosDeTabla();
                } else {
                    JOptionPane.showMessageDialog(null, "Error al registrar el proveedor.");
                }
            }
        });

        bttCerrarVentana.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                InterfazProveedores abrirVentana = new InterfazProveedores();
                abrirVentana.setLocationRelativeTo(null);
                abrirVentana.setVisible(true);
                dispose();
            }
        });

        setVisible(true);
    }

    public Proveedor extraerInformacionDeCampos() {
        String id = txtIDProveedor.getText();
        String nombre = txtNombreProveedor.getText();
        String direccion = txtDireccionProveedor.getText();
        String telefono = txtTelefonoProveedor.getText();

        return new Proveedor(id, nombre, direccion, telefono);
    }

    public void recargarDatosDeTabla() {
        // Limpia todas las filas del modelo actual
        modeloDeTabla.setRowCount(0);

        // Cargar proveedores desde el archivo
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
        tblTablaDeProveedores.revalidate();
        tblTablaDeProveedores.repaint();
    }

    public void crearTabla() {
        ProveedorRepositorio repositorio = new ProveedorRepositorio();
        repositorio.cargarProveedorALaLista();  // Cargar proveedores en la lista desde el archivo
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
        tblTablaDeProveedores = new JTable(modeloDeTabla);
        JScrollPane scrollPane = new JScrollPane(tblTablaDeProveedores);
        JPanelListaProveedores.add(scrollPane, BorderLayout.CENTER);
    }

    public static void main(String[] args) {
        CrearProveedores ventanaProveedores = new CrearProveedores();
    }
}
