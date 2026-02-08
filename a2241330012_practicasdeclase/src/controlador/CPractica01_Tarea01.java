package controlador;

import Modelo.*;
import vista.*;
import javax.swing.*;
import java.awt.event.*;

public class CPractica01_Tarea01 implements ActionListener {

    private VPractica01_Tarea01 vista;
    private ListaInsumos Modelo;

    public CPractica01_Tarea01() {
        vista = new VPractica01_Tarea01();
        Modelo = new ListaInsumos();

        vista.getLista().setModel(Modelo.getModeloLista());

        vista.getBtnAgregar().addActionListener(this);
        vista.getBtnEliminar().addActionListener(this);
        vista.getBtnSalir().addActionListener(this);

        vista.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == vista.getBtnAgregar()) {

            try {
                int id = Integer.parseInt(vista.getTxtId().getText().trim());
                String nom = vista.getTxtNombre().getText().trim();
                String cat = vista.getCbCategoria().getSelectedItem().toString();

                if (nom.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Nombre vacío");
                    return;
                }

                Modelo.agregar(new Insumo(id, nom, cat));
                vista.getTxtId().setText("");
                vista.getTxtNombre().setText("");

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Datos inválidos");
            }
        }

        if (e.getSource() == vista.getBtnEliminar()) {
            Modelo.eliminar(vista.getLista().getSelectedIndex());
        }

        if (e.getSource() == vista.getBtnSalir()) {
            System.exit(0);
        }
    }
}