package gui;

import javax.swing.*;
import java.awt.*;
import controller.Controller;

public class TablePanel extends JPanel {

    private final JTable table;
    private final PersonTableModel tableModel;
    private final Controller controller;

    public TablePanel(Controller controller) {
        this.controller = controller;
        setLayout(new BorderLayout());

        tableModel = new PersonTableModel(controller.getPeople());
        table = new JTable(tableModel);

        add(new JScrollPane(table), BorderLayout.CENTER);
    }

    public void refresh() {
        tableModel.fireTableDataChanged();
    }
}
