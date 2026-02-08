package vista;

import javax.swing.*;

public class DialogosView extends JFrame {

    private JButton btnAlerta;

    public DialogosView() {
        setTitle("Diálogos");
        setBounds(100, 100, 300, 200);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(null);

        btnAlerta = new JButton("Mostrar Alerta");
        btnAlerta.setBounds(70, 70, 150, 30);
        getContentPane().add(btnAlerta);
    }

    public JButton getBtnAlerta() {
        return btnAlerta;
    }
}
