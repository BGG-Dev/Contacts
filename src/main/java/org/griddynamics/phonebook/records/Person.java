package org.griddynamics.phonebook.records;

import org.griddynamics.phonebook.PhoneNumber;

/**
 * Represents person phone book record
 */
public class Person extends PhoneBookRecord {

    // Surname
    private final String surname;

    public Person(String name, String surname,
                  PhoneNumber phoneNumber) {
        // Calling super
        super(name, phoneNumber);

        // Initializing surname
        this.surname = surname;
    }

    /**
     * surname getter
     * @return
     */
    public String getSurname() {
        return surname;
    }
}
