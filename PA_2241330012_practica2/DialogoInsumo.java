package PA_2241330012_practica2;

import javax.swing.*;
import java.awt.*;

public class DialogoInsumo extends JDialog {
    public JTextField txtId, txtInsumo;
    public JComboBox<Categoria> comboCat;
    public boolean guardado = false;
    public Insumo nuevoInsumo;

    public DialogoInsumo(JFrame parent, ListaCategorias categorias) {
        super(parent, "Agregar Insumo", true);
        setSize(300, 200);
        setLocationRelativeTo(parent);
        setLayout(new GridLayout(4, 2, 5, 5));

        add(new JLabel("ID Producto:")); 
        txtId = new JTextField(); add(txtId);
        
        add(new JLabel("Nombre:")); 
        txtInsumo = new JTextField(); add(txtInsumo);
        
        add(new JLabel("Categoría:"));
        comboCat = new JComboBox<>();
        for (Categoria c : categorias.getLista()) comboCat.addItem(c);
        add(comboCat);

        JButton btnGuardar = new JButton("Guardar");
        JButton btnCancelar = new JButton("Cancelar");
        add(btnGuardar); add(btnCancelar);

        btnGuardar.addActionListener(e -> {
            if(txtId.getText().isEmpty() || txtInsumo.getText().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Llene todos los campos");
            } else {
                Categoria cat = (Categoria) comboCat.getSelectedItem();
                nuevoInsumo = new Insumo(txtId.getText(), txtInsumo.getText(), cat.getIdCategoria());
                guardado = true;
                dispose();
            }
        });
        
        btnCancelar.addActionListener(e -> dispose());
    }
}