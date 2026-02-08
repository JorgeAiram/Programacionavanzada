package vista;

import javax.swing.*;

public class CopiarTextoView extends JFrame {

    private JTextField txtOrigen;
    private JTextField txtDestino;
    private JButton btnCopiar;

    public CopiarTextoView() {
        setTitle("Copiar Texto");
        setBounds(100, 100, 450, 250);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(null);

        txtOrigen = new JTextField();
        txtOrigen.setBounds(50, 50, 150, 25);
        getContentPane().add(txtOrigen);

        txtDestino = new JTextField();
        txtDestino.setBounds(240, 50, 150, 25);
        getContentPane().add(txtDestino);

        btnCopiar = new JButton("Copiar");
        btnCopiar.setBounds(160, 110, 100, 30);
        getContentPane().add(btnCopiar);
    }

    public JTextField getTxtOrigen() { return txtOrigen; }
    public JTextField getTxtDestino() { return txtDestino; }
    public JButton getBtnCopiar() { return btnCopiar; }
}
