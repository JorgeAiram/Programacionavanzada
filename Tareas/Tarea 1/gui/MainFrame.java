package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import javax.swing.*;

import controller.Controller;

public class MainFrame extends JFrame {

    private final TextPanel textPanel;
    private final Toolbar toolbar;
    private final FormPanel formPanel;
    private final TablePanel tablePanel;
    private final PrefsDialog prefsDialog;

    private String prefUser = "John";
    private String prefPassword = "*****";
    private int prefPort = 3307;

    public MainFrame(Controller controller) {
        super("Hello World");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(900, 600);
        setLocationByPlatform(true);
        setLayout(new BorderLayout());

        textPanel = new TextPanel();
        toolbar = new Toolbar();
        formPanel = new FormPanel();
        tablePanel = new TablePanel(controller);
        prefsDialog = new PrefsDialog(this);

        // Wire toolbar
        toolbar.setStringListener(s -> textPanel.appendText(s + "\n"));

        // Wire form -> controller -> table
        formPanel.setFormListener(e -> {
            controller.addPerson(
                e.getName(),
                e.getOccupation(),
                e.getAgeCategory(),
                e.getEmploymentCategory(),
                e.isUsCitizen(),
                e.getTaxId(),
                e.getGender()
            );
            tablePanel.refresh();
        });

        // Prefs
        prefsDialog.setDefaults(prefUser, prefPassword, prefPort);
        prefsDialog.setPrefsListener((user, password, port) -> {
            prefUser = user;
            prefPassword = password;
            prefPort = port;
        });

        setJMenuBar(createMenuBar());

        // Left form
        add(formPanel, BorderLayout.WEST);

        // Center area with tabs (Hello/Goodbye) + table
        JTabbedPane tabs = new JTabbedPane();
        tabs.add("Hello", textPanel);
        tabs.add("Goodbye", new JScrollPane(new JTextArea("Goodbye")));
        JPanel center = new JPanel(new BorderLayout());
        center.add(tabs, BorderLayout.NORTH);
        center.add(tablePanel, BorderLayout.CENTER);
        add(center, BorderLayout.CENTER);

        add(toolbar, BorderLayout.NORTH);

        formPanel.setPreferredSize(new Dimension(330, getHeight()));
    }

    private JMenuBar createMenuBar() {
        JMenuBar menuBar = new JMenuBar();

        JMenu fileMenu = new JMenu("File");
        JMenuItem exitItem = new JMenuItem(new AbstractAction("Exit") {
            @Override public void actionPerformed(ActionEvent e) {
                int action = JOptionPane.showConfirmDialog(MainFrame.this,
                        "Do you really want to exit?", "Confirm Exit",
                        JOptionPane.OK_CANCEL_OPTION);
                if (action == JOptionPane.OK_OPTION) dispose();
            }
        });

        JMenu windowMenu = new JMenu("Window");
        JMenuItem prefsItem = new JMenuItem(new AbstractAction("Preferences") {
            @Override public void actionPerformed(ActionEvent e) {
                prefsDialog.setDefaults(prefUser, prefPassword, prefPort);
                prefsDialog.setVisible(true);
            }
        });

        fileMenu.addSeparator();
        fileMenu.add(exitItem);
        windowMenu.add(prefsItem);

        menuBar.add(fileMenu);
        menuBar.add(windowMenu);
        return menuBar;
    }
}
