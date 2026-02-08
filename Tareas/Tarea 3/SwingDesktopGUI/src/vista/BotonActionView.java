package vista;

import javax.swing.*;

public class BotonActionView extends JFrame {

    private JButton btnClick;

    public BotonActionView() {
        setTitle("JButton y ActionListener");
        setBounds(100, 100, 400, 200);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(null);

        btnClick = new JButton("Haz clic");
        btnClick.setBounds(120, 70, 140, 30);
        getContentPane().add(btnClick);
    }

    public JButton getBtnClick() {
        return btnClick;
    }
}
