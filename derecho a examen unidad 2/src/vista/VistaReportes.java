package vista;

import javax.swing.*;
import java.awt.*;

public class VistaReportes extends JInternalFrame {
    private static final long serialVersionUID = 1L;
    
    public JButton btnGenerar;
    public JTextArea txtReporte;

    public VistaReportes() {
        super("Reportes de Inventario y Valor", true, true, true, true);
        setSize(600, 450);
        setLayout(new BorderLayout(10, 10));

        JPanel panelSup = new JPanel();
        btnGenerar = new JButton("Generar Reporte de Inventario Total");
        btnGenerar.setFont(new Font("Arial", Font.BOLD, 14));
        panelSup.add(btnGenerar);
        add(panelSup, BorderLayout.NORTH);

        txtReporte = new JTextArea();
        txtReporte.setEditable(false);
        txtReporte.setFont(new Font("Monospaced", Font.PLAIN, 14));
        txtReporte.setBackground(new Color(240, 248, 255)); // Color azul muy clarito
        
        add(new JScrollPane(txtReporte), BorderLayout.CENTER);
    }
}