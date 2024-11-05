package GUI;

import LogicaProyecto.BDProveedores;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InterfazProveedores extends JFrame{
    private JPanel panel1;
    private JButton bttCrear;
    private JButton bttEditar;
    private JButton bttConsultar;
    private JButton bttBorrar;
    private JButton bttVolver;
    private JLabel lbProveedores;

    public InterfazProveedores(){
        setTitle("Ventana Proveedores");
        setContentPane(panel1);
        setMinimumSize(new Dimension(450, 478));
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);


        bttCrear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CrearProveedores abrirVentana = new CrearProveedores();
                abrirVentana.setLocationRelativeTo(null);
                abrirVentana.setVisible(true);
                dispose();
            }
        });
        bttEditar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                EditarProveedores nv = new EditarProveedores();
                nv.setLocationRelativeTo(null);
                nv.setVisible(true);
                dispose();
            }
        });
        bttConsultar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ConsultarProveedores vn = new ConsultarProveedores();
                vn.setLocationRelativeTo(null);
                vn.setVisible(true);
                dispose();
            }
        });setVisible(true);
        bttBorrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                BorrarProveedores vn = new BorrarProveedores();
                vn.setLocationRelativeTo(null);
                vn.setVisible(true);
                dispose();
            }
        });
        bttVolver.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Principal vn = new Principal();
                vn.setLocationRelativeTo(null);
                vn.setVisible(true);
                dispose();
            }
        });
    }
}
