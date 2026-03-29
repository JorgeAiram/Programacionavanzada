import controlador.ControladorEvaluacion;
import persistencia.EvaluacionPersistencia;
import vista.VistaEvaluacion;

import javax.swing.*;
import java.awt.*;

public class Main {

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {

            // Ventana principal
            JFrame frame = new JFrame("Sistema de Evaluacion de Atributos de Egreso");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(1100, 720);
            frame.setLocationRelativeTo(null);

            // Desktop pane (necesario para JInternalFrame)
            JDesktopPane desktop = new JDesktopPane();
            desktop.setBackground(new Color(60, 90, 130));
            frame.setContentPane(desktop);

            // Crear vista y controlador
            VistaEvaluacion vista = new VistaEvaluacion();
            EvaluacionPersistencia persistencia = new EvaluacionPersistencia();
            new ControladorEvaluacion(vista, persistencia);

            // Agregar y mostrar la ventana interna
            desktop.add(vista);
            vista.setVisible(true);

            frame.setVisible(true);
        });
    }
}
