package model;

public enum EmploymentCategory {
    EMPLOYED("employed"),
    SELF_EMPLOYED("self-employed"),
    UNEMPLOYED("unemployed"),
    OTHER("other");

    private final String label;
    EmploymentCategory(String label) { this.label = label; }
    @Override public String toString() { return label; }
}
