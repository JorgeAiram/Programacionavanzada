package vista;

import javax.swing.*;
import java.awt.*;

public class CalculadoraGRIDView extends JFrame {

    public CalculadoraGRIDView() {
        setTitle("Calculadora GridLayout");
        setBounds(100, 100, 300, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(new GridLayout(4, 4));

        for (int i = 1; i <= 9; i++) {
            getContentPane().add(new JButton(String.valueOf(i)));
        }
    }
}
