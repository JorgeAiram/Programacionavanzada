package vista;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class ClienteView extends JFrame {

    private JTextField txtId;
    private JTextField txtNombre;
    private JTextField txtTelefono;

    private JButton btnGuardar;
    private JButton btnEditar;
    private JButton btnEliminar;
    private JButton btnLimpiar;

    private JTable tblClientes;

    public ClienteView() {
        setTitle("Gestión de Clientes");
        setBounds(100, 100, 650, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setLayout(null);

        JLabel lblTitulo = new JLabel("Clientes");
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 18));
        lblTitulo.setBounds(20, 10, 200, 30);
        getContentPane().add(lblTitulo);

        JLabel lblId = new JLabel("ID:");
        lblId.setBounds(20, 60, 80, 25);
        getContentPane().add(lblId);

        txtId = new JTextField();
        txtId.setBounds(100, 60, 150, 25);
        getContentPane().add(txtId);

        JLabel lblNombre = new JLabel("Nombre:");
        lblNombre.setBounds(20, 100, 80, 25);
        getContentPane().add(lblNombre);

        txtNombre = new JTextField();
        txtNombre.setBounds(100, 100, 150, 25);
        getContentPane().add(txtNombre);

        JLabel lblTelefono = new JLabel("Teléfono:");
        lblTelefono.setBounds(20, 140, 80, 25);
        getContentPane().add(lblTelefono);

        txtTelefono = new JTextField();
        txtTelefono.setBounds(100, 140, 150, 25);
        getContentPane().add(txtTelefono);

        btnGuardar = new JButton("Guardar");
        btnGuardar.setBounds(20, 190, 100, 30);
        getContentPane().add(btnGuardar);

        btnEditar = new JButton("Editar");
        btnEditar.setBounds(130, 190, 100, 30);
        getContentPane().add(btnEditar);

        btnEliminar = new JButton("Eliminar");
        btnEliminar.setBounds(240, 190, 100, 30);
        getContentPane().add(btnEliminar);

        btnLimpiar = new JButton("Limpiar");
        btnLimpiar.setBounds(350, 190, 100, 30);
        getContentPane().add(btnLimpiar);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(280, 60, 340, 110);
        getContentPane().add(scrollPane);

        tblClientes = new JTable();
        tblClientes.setModel(new DefaultTableModel(
            new Object[][] {},
            new String[] { "ID", "Nombre", "Teléfono" }
        ));
        scrollPane.setViewportView(tblClientes);
    }

    // ===== GETTERS =====
    public JTextField getTxtId() {
        return txtId;
    }

    public JTextField getTxtNombre() {
        return txtNombre;
    }

    public JTextField getTxtTelefono() {
        return txtTelefono;
    }

    public JButton getBtnGuardar() {
        return btnGuardar;
    }

    public JButton getBtnEditar() {
        return btnEditar;
    }

    public JButton getBtnEliminar() {
        return btnEliminar;
    }

    public JButton getBtnLimpiar() {
        return btnLimpiar;
    }

    public JTable getTblClientes() {
        return tblClientes;
    }
}
