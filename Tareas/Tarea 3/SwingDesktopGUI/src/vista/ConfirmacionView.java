package vista;

import javax.swing.*;

public class ConfirmacionView extends JFrame {

    private JButton btnConfirmar;

    public ConfirmacionView() {
        setTitle("Confirmación");
        setBounds(100, 100, 300, 200);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(null);

        btnConfirmar = new JButton("Confirmar");
        btnConfirmar.setBounds(80, 70, 130, 30);
        getContentPane().add(btnConfirmar);
    }

    public JButton getBtnConfirmar() {
        return btnConfirmar;
    }
}
