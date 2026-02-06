package gui;

import java.util.EventObject;
import model.AgeCategory;
import model.EmploymentCategory;
import model.Gender;

public class FormEvent extends EventObject {
    private final String name;
    private final String occupation;
    private final AgeCategory ageCategory;
    private final EmploymentCategory employmentCategory;
    private final boolean usCitizen;
    private final String taxId;
    private final Gender gender;

    public FormEvent(Object source, String name, String occupation,
                     AgeCategory ageCategory, EmploymentCategory employmentCategory,
                     boolean usCitizen, String taxId, Gender gender) {
        super(source);
        this.name = name;
        this.occupation = occupation;
        this.ageCategory = ageCategory;
        this.employmentCategory = employmentCategory;
        this.usCitizen = usCitizen;
        this.taxId = taxId;
        this.gender = gender;
    }

    public String getName() { return name; }
    public String getOccupation() { return occupation; }
    public AgeCategory getAgeCategory() { return ageCategory; }
    public EmploymentCategory getEmploymentCategory() { return employmentCategory; }
    public boolean isUsCitizen() { return usCitizen; }
    public String getTaxId() { return taxId; }
    public Gender getGender() { return gender; }
}
