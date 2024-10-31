package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Principal extends JDialog{
    private JPanel panel1;
    private JLabel lbBienvenida;
    private JButton btCerrarSesion;
    private JButton btProductos;

    public Principal(JFrame parentPrinicipal) {
        super(parentPrinicipal);
        setTitle("LogicaProyecto.Login");
        setContentPane(panel1);
        setMinimumSize(new Dimension(450, 478));

        setLocationRelativeTo(parentPrinicipal);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);


        btCerrarSesion.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(Principal.this,
                        "Se cerrará esta ventana.",
                        "Éxito",
                        JOptionPane.INFORMATION_MESSAGE);
                setModal(false);
                dispose();
                new Login((JFrame) parentPrinicipal);

            }
        });

        btProductos.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                InterfazProducto abrirVentana = new InterfazProducto(Principal.this);
                dispose();
            }
        });
        setVisible(true);
    }
}
