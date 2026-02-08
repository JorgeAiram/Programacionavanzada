package vista;

import javax.swing.*;
import java.awt.*;

public class Vejercicioclase01 extends JFrame {

    public JTextField txtNombre, txtApellido;
    public JTextArea txtArea;
    public JButton btnAgregar, btnSalir;

    public Vejercicioclase01() {
        setTitle("Ejercicio Clase 01");
        setSize(400, 350);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel panelDatos = new JPanel(new GridLayout(2, 2, 5, 5));
        panelDatos.add(new JLabel("Nombre:"));
        txtNombre = new JTextField();
        panelDatos.add(txtNombre);

        panelDatos.add(new JLabel("Apellido:"));
        txtApellido = new JTextField();
        panelDatos.add(txtApellido);

        add(panelDatos, BorderLayout.NORTH);

        txtArea = new JTextArea();
        add(new JScrollPane(txtArea), BorderLayout.CENTER);

        JPanel panelBotones = new JPanel();
        btnAgregar = new JButton("Agregar");
        btnSalir = new JButton("Salir");
        panelBotones.add(btnAgregar);
        panelBotones.add(btnSalir);

        add(panelBotones, BorderLayout.SOUTH);
    }
}