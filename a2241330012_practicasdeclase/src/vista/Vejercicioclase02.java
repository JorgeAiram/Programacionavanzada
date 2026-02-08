package vista;
import javax.swing.*;
import java.awt.*;

public class Vejercicioclase02 extends JFrame {

    public JTextField txtDato;
    public JButton btnAgregar, btnEliminar, btnSalir;
    public JComboBox<String> combo;
    public JList<String> lista;

    public Vejercicioclase02() {

        setTitle("Ejercicio Clase 02");
        setSize(450, 350);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel arriba = new JPanel();
        txtDato = new JTextField(15);
        btnAgregar = new JButton("Agregar");

        arriba.add(txtDato);
        arriba.add(btnAgregar);

        add(arriba, BorderLayout.NORTH);

        lista = new JList<>();
        add(new JScrollPane(lista), BorderLayout.CENTER);

        JPanel abajo = new JPanel();
        btnEliminar = new JButton("Eliminar");
        btnSalir = new JButton("Salir");

        abajo.add(btnEliminar);
        abajo.add(btnSalir);

        add(abajo, BorderLayout.SOUTH);
    }
}
