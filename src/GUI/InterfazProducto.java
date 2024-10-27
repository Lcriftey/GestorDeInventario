package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InterfazProducto extends JDialog{
    private JPanel panel1;
    private JButton bttCrear;
    private JPanel PanelTitulo;
    private JPanel PanelBotones;
    private JButton cancelarButton;


    public InterfazProducto(JFrame parentInterfazProducto) {
        super(parentInterfazProducto);
        setTitle("Producto");
        setContentPane(PanelBotones);
        setMinimumSize(new Dimension(600, 490));
        setModal(true);
        setLocationRelativeTo(PanelBotones);
        /*setDefaultCloseOperation(DISPOSE_ON_CLOSE);*/

        bttCrear.addActionListener(new ActionListener( {
            @Override
              public void actionPerformed(ActionEvent e){
            CrearProductos abrirVentana = new CrearProductos(Login.this);
        }
        });
    }
}