package gui;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.text.DecimalFormat;

public class PrefsDialog extends JDialog {

    private final JTextField userField = new JTextField(12);
    private final JPasswordField passField = new JPasswordField(12);
    private final JSpinner portSpinner = new JSpinner(
        new SpinnerNumberModel(3307, 0, 65535, 1)
    );

    private PrefsListener prefsListener;

    public PrefsDialog(JFrame parent) {
        super(parent, "Preferences", true);
        setLayout(new BorderLayout());
        setSize(420, 230);
        setLocationRelativeTo(parent);

        JPanel form = new JPanel(new GridBagLayout());
        form.setBorder(new EmptyBorder(10, 10, 10, 10));
        GridBagConstraints gc = new GridBagConstraints();
        gc.fill = GridBagConstraints.NONE;
        gc.weightx = 1;
        gc.weighty = 1;

        // user
        gc.gridx = 0; gc.gridy = 0; gc.anchor = GridBagConstraints.LINE_END;
        form.add(new JLabel("User:"), gc);
        gc.gridx = 1; gc.anchor = GridBagConstraints.LINE_START;
        form.add(userField, gc);

        // pass
        gc.gridy++;
        gc.gridx = 0; gc.anchor = GridBagConstraints.LINE_END;
        form.add(new JLabel("Password:"), gc);
        gc.gridx = 1; gc.anchor = GridBagConstraints.LINE_START;
        form.add(passField, gc);

        // port
        gc.gridy++;
        gc.gridx = 0; gc.anchor = GridBagConstraints.LINE_END;
        form.add(new JLabel("Port:"), gc);
        gc.gridx = 1; gc.anchor = GridBagConstraints.LINE_START;
        JSpinner.NumberEditor editor = new JSpinner.NumberEditor(portSpinner, "#,##0");
        editor.getFormat().setGroupingUsed(true);
        portSpinner.setEditor(editor);
        form.add(portSpinner, gc);

        JPanel buttons = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton ok = new JButton("OK");
        JButton cancel = new JButton("Cancel");
        buttons.add(ok);
        buttons.add(cancel);

        ok.addActionListener(e -> {
            if (prefsListener != null) {
                prefsListener.preferencesSet(
                    userField.getText().trim(),
                    new String(passField.getPassword()),
                    ((Number)portSpinner.getValue()).intValue()
                );
            }
            setVisible(false);
        });

        cancel.addActionListener(e -> setVisible(false));

        add(form, BorderLayout.CENTER);
        add(buttons, BorderLayout.SOUTH);
    }

    public void setDefaults(String user, String password, int port) {
        userField.setText(user);
        passField.setText(password);
        portSpinner.setValue(port);
    }

    public void setPrefsListener(PrefsListener listener) {
        this.prefsListener = listener;
    }
}
