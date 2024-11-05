package GUI;

import LogicaProyecto.Proveedor;
import LogicaProyecto.ProveedorRepositorio;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;

public class ConsultarProveedores extends JFrame{
    private JPanel panelConsultarProveedores;
    private JButton salirButton;
    private JPanel panelBotones;
    private JPanel panelTablaProveedores;
    private JTable tbltablaDeProveedores;
    private DefaultTableModel modeloDeTabla;

    public ConsultarProveedores() {
        setTitle("Consultar Proveedores");
        setContentPane(panelConsultarProveedores);
        setMinimumSize(new Dimension(600, 490));
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        CrearTabla();
        salirButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                InterfazProveedores nuevaVentana = new InterfazProveedores();
                nuevaVentana.setLocationRelativeTo(null);
                nuevaVentana.setVisible(true);
                dispose();
            }
        });
        setVisible(true);
    }

    public void CrearTabla() {
        ProveedorRepositorio repositorio = new ProveedorRepositorio();
        repositorio.cargarProveedorALaLista(); // Cargar proveedores en la lista desde el archivo
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

    public static void main(String[] args) {
        ConsultarProveedores tabla = new ConsultarProveedores();
        tabla.setLocationRelativeTo(null);
        tabla.setVisible(true);
    }
}

