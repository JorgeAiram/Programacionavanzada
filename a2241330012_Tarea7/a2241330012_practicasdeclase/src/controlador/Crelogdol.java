package controlador;

import java.awt.BorderLayout;
import java.awt.Font;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.Timer;

public class Crelogdol {
    private JFrame frame;
    private JLabel lblReloj;

    public Crelogdol() {
        
        frame = new JFrame("Reloj Dual");
        frame.setSize(300, 150);
        frame.setLocation(100, 100); 
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        lblReloj = new JLabel("", SwingConstants.CENTER);
        lblReloj.setFont(new Font("Arial", Font.BOLD, 30));
        frame.add(lblReloj, BorderLayout.CENTER);

        iniciarReloj();
     
    }

    private void iniciarReloj() {
        Timer timer = new Timer(1000, e -> {
            SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
            lblReloj.setText(sdf.format(new Date()));
        });
        timer.start();
    }
}