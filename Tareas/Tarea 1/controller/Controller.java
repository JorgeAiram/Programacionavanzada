package controller;

import java.util.ArrayList;
import java.util.List;

import model.Person;
import model.AgeCategory;
import model.EmploymentCategory;
import model.Gender;

public class Controller {

    private final List<Person> people = new ArrayList<>();

    public void addPerson(String name, String occupation, AgeCategory ageCat,
                          EmploymentCategory empCat, boolean usCitizen, String taxId, Gender gender) {

        people.add(new Person(name, occupation, ageCat, empCat, usCitizen, taxId, gender));
    }

    public List<Person> getPeople() {
        return people;
    }
}
