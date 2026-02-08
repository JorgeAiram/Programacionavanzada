package vista;

import javax.swing.*;

public class HolaMundoView extends JFrame {

    private JLabel lblMensaje;

    public HolaMundoView() {
        setTitle("Hola Mundo Swing");
        setBounds(100, 100, 400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setLayout(null);

        lblMensaje = new JLabel("Hola Mundo con Swing");
        lblMensaje.setBounds(100, 70, 200, 30);
        getContentPane().add(lblMensaje);
    }

    public JLabel getLblMensaje() {
        return lblMensaje;
    }
}
