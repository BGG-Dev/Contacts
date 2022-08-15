package org.griddynamics.phonebook.tui;

import org.griddynamics.phonebook.PhoneNumber;
import org.griddynamics.phonebook.records.Person;

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
            System.out.print("Select a field (name, surname, number): ");
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
