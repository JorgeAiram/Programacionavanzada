package vista;

import javax.swing.*;
import java.awt.*;

public class VistaProveedores extends JInternalFrame {
    private static final long serialVersionUID = 1L;

    public VistaProveedores() {
        super("Directorio de Proveedores", true, true, true, true);
        setSize(700, 450);
        setLayout(new BorderLayout(10, 10));

        // Un pequeño formulario visual
        JPanel panelNorte = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelNorte.setBorder(BorderFactory.createTitledBorder("Nuevo Proveedor"));
        panelNorte.add(new JLabel("Empresa:"));
        panelNorte.add(new JTextField(10));
        panelNorte.add(new JLabel("Contacto:"));
        panelNorte.add(new JTextField(10));
        panelNorte.add(new JLabel("Teléfono:"));
        panelNorte.add(new JTextField(10));
        panelNorte.add(new JButton("Guardar"));
        add(panelNorte, BorderLayout.NORTH);

        // Tabla con datos de ejemplo precargados para la presentación
        String[] columnas = {"ID", "Empresa", "Contacto", "Teléfono", "Días de Visita"};
        Object[][] datos = {
            {"PRV-01", "Bimbo", "Juan Pérez", "555-1234", "Lunes y Jueves"},
            {"PRV-02", "Coca-Cola", "Ana Gómez", "555-5678", "Martes"},
            {"PRV-03", "Sabritas", "Carlos Ruiz", "555-9012", "Miércoles"},
            {"PRV-04", "Lala / Alpura", "María López", "555-3456", "Viernes"}
        };
        JTable tabla = new JTable(datos, columnas);
        add(new JScrollPane(tabla), BorderLayout.CENTER);

        JPanel panelSur = new JPanel();
        panelSur.add(new JButton("Modificar"));
        panelSur.add(new JButton("Eliminar"));
        add(panelSur, BorderLayout.SOUTH);
    }
}