package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InterfazProducto extends JFrame{
    private JPanel panel1;
    private JButton bttCrear;
    private JPanel PanelTitulo;
    private JPanel PanelBotones;
    private JButton bttvolverVentanaPrincipal;
    private JButton bttAbrirEditarProductos;
    private JButton bttAbrirConsultarProductos;
    private JButton bttAbrirBorrarProductos;


    public InterfazProducto() {

        setTitle("Producto");
        setContentPane(PanelBotones);
        setMinimumSize(new Dimension(800, 690));
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);


        bttCrear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CrearProductos abrirVentana = new CrearProductos(InterfazProducto.this);
                dispose();
            }
        });

        bttAbrirEditarProductos.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                EditarProductos abrirVentana = new EditarProductos();
                abrirVentana.setLocationRelativeTo(null);
                abrirVentana.setVisible(true);
                dispose();
            }
        });
        bttAbrirConsultarProductos.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ConsultarProductos vn = new ConsultarProductos();
                vn.setLocationRelativeTo(null);
                vn.setVisible(true);
                dispose();
            }
        });

        bttAbrirBorrarProductos.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                BorrarProducto vn = new BorrarProducto();
                vn.setLocationRelativeTo(null);
                vn.setVisible(true);
                dispose();
            }
        });
        bttvolverVentanaPrincipal.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Principal abrirVentana = new Principal();
                abrirVentana.setLocationRelativeTo(null);
                abrirVentana.setVisible(true);
                dispose();
            }
        });setVisible(true);
    }
}
