package org.griddynamics.phonebook.records;

import org.griddynamics.phonebook.PhoneNumber;

/**
 * Abstract class to inherit phone book records from
 */
public abstract class PhoneBookRecord {

    // Name
    private final String name;

    // Phone number
    private final PhoneNumber phoneNumber;

    /**
     * Package-private default constructor
     * @param name
     * @param phoneNumber
     */
    PhoneBookRecord(String name, PhoneNumber phoneNumber) {
        this.name = name;
        this.phoneNumber = phoneNumber;
    }

    /**
     * name getter
     * @return
     */
    public String getName() {
        return name;
    }

    /**
     * phone number getter
     * @return
     */
    public PhoneNumber getPhoneNumber() {
        return phoneNumber;
    }
}
