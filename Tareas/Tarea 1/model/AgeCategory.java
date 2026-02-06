package model;

public enum AgeCategory {
    CHILD("Under 18"),
    ADULT("18 to 65"),
    SENIOR("65 or over");

    private final String label;
    AgeCategory(String label) { this.label = label; }
    @Override public String toString() { return label; }
}
