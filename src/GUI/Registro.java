package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import LogicaProyecto.*;

public class Registro extends JDialog{

    private JPanel panelRegistro;
    private JFormattedTextField tfCorreoUsuarioRegistrar;
    private JButton btRegistrarUsuario;
    private JLabel lbCorreoUsuarioRegistrar;
    private JLabel lbContraseñaUsuarioRegistrar;
    private JPasswordField pfContraseñaUsuarioRegistrar;
    private JPanel PanelRegistro;
    private JLabel lbRegistrarNombre;
    private JLabel lbRegistrarApellido;
    private JLabel lbRegistraredad;
    private JLabel lbRegistrarTipoNIf;
    private JLabel lbRegistrarNIf;
    private JLabel lbRegistrarDireccion;
    private JLabel lbRegistrarTelefono;
    private JFormattedTextField tfRegistrarNombre;
    private JFormattedTextField tfRegistrarApellido;
    private JFormattedTextField tfRegistrarEdad;
    private JFormattedTextField tfRegistrarTipoNif;
    private JFormattedTextField tfRegistrarNif;
    private JFormattedTextField tfRegistrarDireccion;
    private JFormattedTextField tfRegistrarTelefono;

    public Registro(Login parentRegistro){
        super(parentRegistro);
        setTitle("Registro");
        setContentPane(panelRegistro);
        setMinimumSize(new Dimension(512, 470));
        setModal(true);
        setLocationRelativeTo(parentRegistro);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        btRegistrarUsuario.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nombre = tfRegistrarNombre.getText();
                String apellido = tfRegistrarApellido.getText();
                int edad = Integer.parseInt(tfRegistrarEdad.getText());
                String tipoNif = tfRegistrarTipoNif.getText();
                String numeroNif = tfRegistrarNif.getText();
                String correo = tfCorreoUsuarioRegistrar.getText();
                String direccion = tfRegistrarDireccion.getText();
                String telefono = tfRegistrarTelefono.getText();
                String contrasena = new String(pfContraseñaUsuarioRegistrar.getPassword());

                // Crear un repositorio de usuarios y pasar los datos al método
                UsuarioRepositorio registrarUsuarios = new UsuarioRepositorio();
                registrarUsuarios.CrearUsuario(nombre, apellido, edad, tipoNif, numeroNif, correo,
                        direccion, telefono, contrasena);

                JOptionPane.showMessageDialog(Registro.this,
                        "Usuario registrado exitosamente.",
                        "Éxito",
                        JOptionPane.INFORMATION_MESSAGE);
                dispose();  // Cierra la ventana
            }
        });
        setVisible(true);
    }
}
