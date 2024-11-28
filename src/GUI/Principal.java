package GUI;

import LogicaProyecto.Usuario;
import LogicaProyecto.UsuarioServicio;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Principal extends JFrame{
    private JPanel panel1;
    private JLabel lbBienvenida;
    private JButton btCerrarSesion;
    private JButton btProductos;
    private JButton bttAbrirVentanaProveedores;
    private JButton bttRegistrarMovimientos;

    Usuario usuarioActual =  UsuarioServicio.obtenerUsuarioActual();
    String bienvenidaUsuario = usuarioActual.getNombreUsuario();


    public Principal() {
        setTitle("Ventana Principal");
        setContentPane(panel1);
        setMinimumSize(new Dimension(590, 478));
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        lbBienvenida.setText("Bienvenido " + bienvenidaUsuario);

        btCerrarSesion.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(Principal.this,
                        "Se cerrará esta ventana.",
                        "Éxito",
                        JOptionPane.INFORMATION_MESSAGE);

                Login nuevaVentana = new Login();
                nuevaVentana.setLocationRelativeTo(null);
                nuevaVentana.setVisible(true);
                dispose();

            }
        });

        btProductos.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                InterfazProducto nuevaVentana = new InterfazProducto();
                nuevaVentana.setLocationRelativeTo(null);
                nuevaVentana.setVisible(true);
                dispose();
            }
        });
        bttAbrirVentanaProveedores.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                InterfazProveedores abrirVentana = new InterfazProveedores();
                abrirVentana.setLocationRelativeTo(null);
                abrirVentana.setVisible(true);
                dispose();
            }
        });
        bttRegistrarMovimientos.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                RegistrarMovimientos abrirVentana = new RegistrarMovimientos();
                abrirVentana.setLocationRelativeTo(null);
                abrirVentana.setVisible(true);
                dispose();
            }
        });setVisible(true);
    }

}
