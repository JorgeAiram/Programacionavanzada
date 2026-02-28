package PA_2241330012_practica2;

import javax.swing.*;
import java.awt.*;

public class VistaInsumos extends JInternalFrame {
    public JTable tabla;
    public JList<Categoria> listaCat;
    public JLabel lblImagen;
    public JButton btnAdd, btnDel, btnCargarImg;

    public VistaInsumos() {
        super("Gestión de Insumos", true, true, true, true);
        setSize(700, 400);
        setLayout(null);

        listaCat = new JList<>();
        JScrollPane scrollList = new JScrollPane(listaCat);
        scrollList.setBounds(20, 20, 150, 150);
        scrollList.setBorder(BorderFactory.createTitledBorder("Categorías"));
        add(scrollList);

        tabla = new JTable();
        JScrollPane scrollTop = new JScrollPane(tabla);
        scrollTop.setBounds(190, 20, 320, 300);
        add(scrollTop);

        lblImagen = new JLabel("Sin Imagen", SwingConstants.CENTER);
        lblImagen.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        lblImagen.setBounds(530, 20, 130, 130);
        add(lblImagen);

        btnCargarImg = new JButton("Cargar Imagen");
        btnCargarImg.setBounds(530, 160, 130, 30);
        add(btnCargarImg);

        btnAdd = new JButton("Agregar Insumo"); 
        btnAdd.setBounds(20, 190, 150, 30); add(btnAdd);
        
        btnDel = new JButton("Eliminar Insumo"); 
        btnDel.setBounds(20, 230, 150, 30); add(btnDel);
    }
}