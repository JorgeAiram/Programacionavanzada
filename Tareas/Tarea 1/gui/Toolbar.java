package gui;

import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JToolBar;

public class Toolbar extends JToolBar {

    private final JButton helloBtn = new JButton("Hello");
    private final JButton goodbyeBtn = new JButton("Goodbye");
    private StringListener textListener;

    public Toolbar() {
        setFloatable(false);
        setLayout(new FlowLayout(FlowLayout.LEFT));
        add(helloBtn);
        add(goodbyeBtn);

        helloBtn.addActionListener(e -> {
            if (textListener != null) textListener.textEmitted("Hello\n");
        });
        goodbyeBtn.addActionListener(e -> {
            if (textListener != null) textListener.textEmitted("Goodbye\n");
        });
    }

    public void setStringListener(StringListener l) { this.textListener = l; }
}
