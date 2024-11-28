package GUI;

import LogicaProyecto.Usuario;
import LogicaProyecto.UsuarioRepositorio;
import LogicaProyecto.UsuarioServicio;

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
        setMinimumSize(new Dimension(500, 500));
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
                //Comprobacion rol del usuario
                UsuarioServicio usuarioServicio = new UsuarioServicio();
                Usuario usuarioActual =  UsuarioServicio.obtenerUsuarioActual();
                boolean rolUsuario = usuarioActual.isEstadoUsuario();

                if(rolUsuario) {
                    EditarProductos abrirVentana = new EditarProductos();
                    abrirVentana.setLocationRelativeTo(null);
                    abrirVentana.setVisible(true);
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(InterfazProducto.this,
                            "Rol no valido para efectuar esta acción. \n",
                            "Advertencia",
                            JOptionPane.WARNING_MESSAGE);
                }
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
                //Comprobacion rol del usuario
                UsuarioServicio usuarioServicio = new UsuarioServicio();
                Usuario usuarioActual =  UsuarioServicio.obtenerUsuarioActual();
                boolean rolUsuario = usuarioActual.isEstadoUsuario();

                if(rolUsuario) {
                    BorrarProducto vn = new BorrarProducto();
                    vn.setLocationRelativeTo(null);
                    vn.setVisible(true);
                    dispose();
                }
                else {
                    JOptionPane.showMessageDialog(InterfazProducto.this,
                            "Rol no valido para efectuar esta acción. \n",
                            "Advertencia",
                            JOptionPane.WARNING_MESSAGE);
                }
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
