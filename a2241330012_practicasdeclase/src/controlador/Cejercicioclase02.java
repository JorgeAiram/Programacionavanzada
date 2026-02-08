package controlador;
import Modelo.*;
import vista.*;
import java.awt.event.*;
import javax.swing.*;

public class Cejercicioclase02 implements ActionListener {

    Vejercicioclase02 vista;
    Mejercicio02 Modelo;
    DefaultListModel<String> modelList;

    public Cejercicioclase02(Vejercicioclase02 v) {
        vista = v;
        Modelo = new Mejercicio02();
        modelList = new DefaultListModel<>();

        vista.lista.setModel(modelList);
        vista.btnAgregar.addActionListener(this);
        vista.btnEliminar.addActionListener(this);
        vista.btnSalir.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == vista.btnAgregar) {
            String dato = vista.txtDato.getText().trim();
            if (!dato.isEmpty()) {
                Modelo.agregar(dato);
                modelList.addElement(dato);
                vista.txtDato.setText("");
            }
        }

        if (e.getSource() == vista.btnEliminar) {
            int i = vista.lista.getSelectedIndex();
            if (i >= 0) {
                Modelo.eliminar(i);
                modelList.remove(i);
            }
        }

        if (e.getSource() == vista.btnSalir) {
            System.exit(0);
        }
    }
}
