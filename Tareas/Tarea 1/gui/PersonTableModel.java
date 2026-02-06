package gui;

import javax.swing.table.AbstractTableModel;
import java.util.List;
import model.Person;

public class PersonTableModel extends AbstractTableModel {

    private final List<Person> people;
    private final String[] colNames = {"ID","Name","Occup.","Age Cat.","Employment","US Citizen","Tax ID"};

    public PersonTableModel(List<Person> people) {
        this.people = people;
    }

    @Override public String getColumnName(int column) { return colNames[column]; }
    @Override public int getRowCount() { return people.size(); }
    @Override public int getColumnCount() { return colNames.length; }

    @Override
    public Object getValueAt(int row, int col) {
        Person p = people.get(row);
        return switch (col) {
            case 0 -> p.getId();
            case 1 -> p.getName();
            case 2 -> p.getOccupation();
            case 3 -> p.getAgeCategory();
            case 4 -> p.getEmpCategory();
            case 5 -> p.isUsCitizen();
            case 6 -> p.getTaxId();
            default -> null;
        };
    }

    @Override public Class<?> getColumnClass(int columnIndex) {
        return switch (columnIndex) {
            case 0 -> Integer.class;
            case 5 -> Boolean.class;
            default -> String.class;
        };
    }
}
