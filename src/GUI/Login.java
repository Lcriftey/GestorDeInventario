package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import LogicaProyecto.*;

public class Login extends JDialog{
    private JPanel panel1;
    private JLabel lbPresentacion;
    private JPasswordField pfContraseñaUsuario;
    private JFormattedTextField tfCorreoUsuario;
    private JLabel lbIngresarUsuario;
    private JLabel lbIngresarContraseña;
    private JButton btCrearUsuario;
    private JButton btVerificarUsuario;

    public Login(JFrame parent){
        super(parent);
        setTitle("LogicaProyecto.Login");
        setContentPane(panel1);
        setMinimumSize(new Dimension(470, 478));
        setModal(true);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        UsuarioRepositorio usuario = new UsuarioRepositorio();
        usuario.UsuarioPorDefecto("s", "s",5,"s","s","s","s",
                                    "s", "s");

        btCrearUsuario.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Registro abrirVentanaRegistro = new Registro(Login.this);
            }
        });

        btVerificarUsuario.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String correoSuministrado = tfCorreoUsuario.getText();
                String contraseñaSuministrada = new String(pfContraseñaUsuario.getPassword());
                boolean seEncuentraRegistrado = true;

                UsuarioServicio usuarioServicio = new UsuarioServicio();
                seEncuentraRegistrado = usuarioServicio.validarUsuario(correoSuministrado, contraseñaSuministrada);
                if(seEncuentraRegistrado){
                    Principal ventanaPrincipal = new Principal((JFrame) parent);
                    dispose();

                }
                else{
                    JOptionPane.showMessageDialog(Login.this,
                            "El usuario o la contraseña no coinciden, intente nuevamente o registrese. \n",
                            "Advertencia",
                            JOptionPane.WARNING_MESSAGE);
                }
            }
        });
        setVisible(true);
    }

}
