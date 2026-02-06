package gui;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import model.AgeCategory;
import model.EmploymentCategory;
import model.Gender;

public class FormPanel extends JPanel {

    private final JTextField nameField = new JTextField(10);
    private final JTextField occupationField = new JTextField(10);
    private final JList<AgeCategory> ageList = new JList<>(AgeCategory.values());
    private final JComboBox<EmploymentCategory> empCombo =
            new JComboBox<>(EmploymentCategory.values());
    private final JCheckBox citizenCheck = new JCheckBox();
    private final JTextField taxField = new JTextField(10);
    private final JRadioButton maleRadio = new JRadioButton("male");
    private final JRadioButton femaleRadio = new JRadioButton("female");
    private final ButtonGroup genderGroup = new ButtonGroup();
    private final JButton okBtn = new JButton("OK");

    private FormListener formListener;

    public FormPanel() {
        setLayout(new GridBagLayout());
        Dimension dim = getPreferredSize();
        dim.width = 330;
        setPreferredSize(dim);

        Border inner = BorderFactory.createTitledBorder("Add Person");
        Border outer = BorderFactory.createEmptyBorder(5,5,5,5);
        setBorder(BorderFactory.createCompoundBorder(outer, inner));

        ageList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        ageList.setSelectedIndex(1); // "18 to 65"
        empCombo.setSelectedItem(EmploymentCategory.EMPLOYED);

        taxField.setEnabled(false);

        citizenCheck.addActionListener(e -> taxField.setEnabled(citizenCheck.isSelected()));

        genderGroup.add(maleRadio);
        genderGroup.add(femaleRadio);
        maleRadio.setSelected(true);

        // Layout controls
        GridBagConstraints gc = new GridBagConstraints();
        gc.gridy = 0;
        gc.weightx = 1;
        gc.weighty = 0.1;
        gc.fill = GridBagConstraints.NONE;

        // Row 0
        gc.gridx = 0; gc.anchor = GridBagConstraints.LINE_END;
        add(new JLabel("Name: "), gc);
        gc.gridx = 1; gc.anchor = GridBagConstraints.LINE_START;
        add(nameField, gc);

        // Row 1
        gc.gridy++;
        gc.gridx = 0; gc.anchor = GridBagConstraints.LINE_END;
        add(new JLabel("Occupation: "), gc);
        gc.gridx = 1; gc.anchor = GridBagConstraints.LINE_START;
        add(occupationField, gc);

        // Row 2
        gc.gridy++;
        gc.gridx = 0; gc.anchor = GridBagConstraints.FIRST_LINE_END;
        add(new JLabel("Age: "), gc);
        gc.gridx = 1; gc.anchor = GridBagConstraints.FIRST_LINE_START;
        JScrollPane ageScroller = new JScrollPane(ageList);
        ageScroller.setPreferredSize(new Dimension(160, 68));
        add(ageScroller, gc);

        // Row 3
        gc.gridy++;
        gc.gridx = 0; gc.anchor = GridBagConstraints.LINE_END;
        add(new JLabel("Employment: "), gc);
        gc.gridx = 1; gc.anchor = GridBagConstraints.LINE_START;
        add(empCombo, gc);

        // Row 4
        gc.gridy++;
        gc.gridx = 0; gc.anchor = GridBagConstraints.LINE_END;
        add(new JLabel("US Citizen: "), gc);
        gc.gridx = 1; gc.anchor = GridBagConstraints.LINE_START;
        add(citizenCheck, gc);

        // Row 5
        gc.gridy++;
        gc.gridx = 0; gc.anchor = GridBagConstraints.LINE_END;
        add(new JLabel("Tax ID: "), gc);
        gc.gridx = 1; gc.anchor = GridBagConstraints.LINE_START;
        add(taxField, gc);

        // Row 6
        gc.gridy++;
        gc.gridx = 0; gc.anchor = GridBagConstraints.LINE_END;
        add(new JLabel("Gender: "), gc);
        JPanel genderPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        genderPanel.add(maleRadio);
        genderPanel.add(Box.createHorizontalStrut(10));
        genderPanel.add(femaleRadio);
        gc.gridx = 1; gc.anchor = GridBagConstraints.LINE_START;
        add(genderPanel, gc);

        // Row 7
        gc.gridy++;
        gc.weighty = 2;
        gc.gridx = 1; gc.anchor = GridBagConstraints.FIRST_LINE_START;
        add(okBtn, gc);

        okBtn.addActionListener(e -> {
            if (formListener == null) return;
            String name = nameField.getText().trim();
            String occ = occupationField.getText().trim();
            AgeCategory age = ageList.getSelectedValue();
            EmploymentCategory emp = (EmploymentCategory) empCombo.getSelectedItem();
            boolean us = citizenCheck.isSelected();
            String tax = taxField.getText().trim();
            Gender gender = maleRadio.isSelected() ? Gender.MALE : Gender.FEMALE;

            formListener.formEventOccurred(
                new FormEvent(this, name, occ, age, emp, us, tax, gender)
            );

            // Clear a bit
            nameField.setText("");
            occupationField.setText("");
            if (!us) taxField.setText("");
        });
    }

    public void setFormListener(FormListener listener) { this.formListener = listener; }
}
