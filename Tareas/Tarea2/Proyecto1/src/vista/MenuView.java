package vista;

import javax.swing.*;
import java.awt.*;

public class MenuView extends JFrame {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JButton btnClientes;
    private JButton btnProductos;
    private JButton btnSalir;

    public MenuView() {
        setTitle("Menú Principal");
        setBounds(100, 100, 400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setLayout(null);

        JLabel lblTitulo = new JLabel("Sistema Desktop");
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 18));
        lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
        lblTitulo.setBounds(80, 30, 240, 30);
        getContentPane().add(lblTitulo);

        btnClientes = new JButton("Clientes");
        btnClientes.setBounds(120, 90, 150, 30);
        getContentPane().add(btnClientes);

        btnProductos = new JButton("Productos");
        btnProductos.setBounds(120, 140, 150, 30);
        getContentPane().add(btnProductos);

        btnSalir = new JButton("Salir");
        btnSalir.setBounds(120, 190, 150, 30);
        getContentPane().add(btnSalir);
    }

    // ===== GETTERS =====
    public JButton getBtnClientes() {
        return btnClientes;
    }

    public JButton getBtnProductos() {
        return btnProductos;
    }

    public JButton getBtnSalir() {
        return btnSalir;
    }
}
