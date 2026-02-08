package vista;

import javax.swing.*;

public class CalculadoraMenuView extends JFrame {

    private JMenuBar menuBar;
    private JMenu menuOpciones;

    public CalculadoraMenuView() {
        setTitle("Calculadora con JMenuBar");
        setBounds(100, 100, 300, 200);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        menuBar = new JMenuBar();
        setJMenuBar(menuBar);

        menuOpciones = new JMenu("Opciones");
        menuBar.add(menuOpciones);
    }

    public JMenuBar getMenuBarCalc() { return menuBar; }
    public JMenu getMenuOpciones() { return menuOpciones; }
}
