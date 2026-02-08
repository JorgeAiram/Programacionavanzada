package vista;

import javax.swing.*;

public class ConversorMonedaView extends JFrame {

    private JTextField txtCantidad;
    private JComboBox<String> cmbMoneda;
    private JButton btnConvertir;

    public ConversorMonedaView() {
        setTitle("Conversor de Monedas");
        setBounds(100, 100, 400, 250);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(null);

        txtCantidad = new JTextField();
        txtCantidad.setBounds(120, 40, 150, 25);
        getContentPane().add(txtCantidad);

        cmbMoneda = new JComboBox<>();
        cmbMoneda.setModel(new DefaultComboBoxModel<>(new String[]{"USD", "MXN", "EUR"}));
        cmbMoneda.setBounds(120, 80, 150, 25);
        getContentPane().add(cmbMoneda);

        btnConvertir = new JButton("Convertir");
        btnConvertir.setBounds(140, 130, 100, 30);
        getContentPane().add(btnConvertir);
    }

    public JTextField getTxtCantidad() { return txtCantidad; }
    public JComboBox<String> getCmbMoneda() { return cmbMoneda; }
    public JButton getBtnConvertir() { return btnConvertir; }
}
