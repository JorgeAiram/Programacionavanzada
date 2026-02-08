package controlador;
import Modelo.*;
import vista.*;
import java.awt.event.*;

public class Cejercicioclase01 implements ActionListener {

    private Vejercicioclase01 vista;
    private Lista lista;

    public Cejercicioclase01(Vejercicioclase01 v) {
        this.vista = v;
        this.lista = new Lista();

        vista.btnAgregar.addActionListener(this);
        vista.btnSalir.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == vista.btnAgregar) {

            String nom = vista.txtNombre.getText().trim();
            String ape = vista.txtApellido.getText().trim();

            if (nom.isEmpty() || ape.isEmpty()) {
                javax.swing.JOptionPane.showMessageDialog(null,
                        "No deje campos vacíos");
                return;
            }

            lista.agregar(new Cpersona(nom, ape));
            vista.txtArea.setText(lista.mostrar());

            vista.txtNombre.setText("");
            vista.txtApellido.setText("");
        }

        if (e.getSource() == vista.btnSalir) {
            System.exit(0);
        }
    }
}