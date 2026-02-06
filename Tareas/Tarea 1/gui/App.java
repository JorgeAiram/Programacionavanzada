package gui;

import javax.swing.SwingUtilities;
import controller.Controller;

public class App {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                // Crear el controlador
                Controller controller = new Controller();

                // Crear la ventana principal con el controlador
                MainFrame mainFrame = new MainFrame(controller);
                mainFrame.setVisible(true);

                // Mostrar diálogo de preferencias al iniciar
                PrefsDialog prefsDialog = new PrefsDialog(mainFrame);
                prefsDialog.setPrefsListener((user, password, port) -> {
                    System.out.println("User: " + user +
                                       ", Password: " + password +
                                       ", Port: " + port);
                });

                prefsDialog.setVisible(true);
            }
        });
    }
}
