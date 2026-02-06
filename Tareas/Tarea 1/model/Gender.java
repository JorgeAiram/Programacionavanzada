package model;

public enum Gender {
    MALE("male"),
    FEMALE("female");

    private final String label;
    Gender(String label) { this.label = label; }
    @Override public String toString() { return label; }
}
