package org.griddynamics.phonebook.tui;

import org.griddynamics.phonebook.PhoneNumber;
import org.griddynamics.phonebook.records.Person;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Implements menu for Edit person logic
 */
final class EditPersonMenu extends Menu {

    // Person instance
    private final Person person;

    // Edit flag
    private boolean isEdited;

    /**
     * Default constructor
     * @param map | Command -> Method map
     * @param person | Person instance to work with
     */
    private EditPersonMenu(Map<String, Runnable> map,
                           Person person) {
        // Calling super
        super(map);

        // Filling map with methods
        map.put("name", this::name);
        map.put("surname", this::surname);
        map.put("birth", this::birth);
        map.put("gender", this::gender);
        map.put("number", this::number);

        // Initializing person
        this.person = person;

        // Initializing flag
        this.isEdited = false;
    }

    /**
     * Default instance creator for EditPersonMenu
     * @param person | Person to work with
     * @return
     */
    static EditPersonMenu getInstance(Person person) {
        // Creating map
        Map<String, Runnable> map = new LinkedHashMap<>();

        // Returning instance
        return new EditPersonMenu(map, person);
    }

    /**
     * menu overriding from Menu class
     */
    @Override
    public void menu() {
        while (!isEdited) {
            System.out.print("Select a field (name, surname, birth, gender, number): ");
            event();
        }
        System.out.println("The record updated!");
    }

    private void name() {
        // Asking to enter name
        System.out.print("Enter name: ");

        // Updating
        person.setName(getScanner().nextLine());
        this.isEdited = true;
    }

    private void surname() {
        // Asking to enter name
        System.out.print("Enter surname: ");

        // Updating
        person.setSurname(getScanner().nextLine());
        this.isEdited = true;
    }

    private void birth() {
        // Asking to enter birth
        System.out.print("Enter the birth date: ");

        // Updating
        try {
            person.setBirthdate(LocalDate.parse(getScanner().nextLine()));
        } catch (DateTimeException e) {
            System.out.println(TUIMessages.INVALID_BIRTHDAY_WARNING);
            person.setBirthdate(null);
        }
        this.isEdited = true;
    }

    private void gender() {
        // Asking to enter gender
        System.out.print("Enter the gender (M, F): ");

        // Updating
        String command = getScanner().nextLine();
        switch (command) {
            case "M":
                person.setGender(Person.Gender.MALE);
                break;
            case "F":
                person.setGender(Person.Gender.FEMALE);
                break;
            default:
                person.setGender(null);
                System.out.println(TUIMessages.INVALID_GENDER_WARNING);
                break;
        }
        this.isEdited = true;
    }

    private void number() {
        // Asking to enter phone number
        System.out.print("Enter number: ");

        // Updating
        try {
            person.setPhoneNumber(PhoneNumber.parsePhoneNumber(getScanner().nextLine()));
        } catch (IllegalArgumentException e) {
            System.out.println(TUIMessages.INVALID_PHONE_WARNING);
            person.setPhoneNumber(PhoneNumber.getEmpty());
        }
        this.isEdited = true;
    }
}
