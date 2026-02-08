package vista;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class TablaView extends JFrame {

    private JTable table;

    public TablaView() {
        setTitle("Tabla de Datos");
        setBounds(100, 100, 400, 250);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(null);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(30, 30, 320, 150);
        getContentPane().add(scrollPane);

        table = new JTable();
        table.setModel(new DefaultTableModel(
            new Object[][] {},
            new String[] { "ID", "Nombre", "Valor" }
        ));
        scrollPane.setViewportView(table);
    }

    public JTable getTable() {
        return table;
    }
}
