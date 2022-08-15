package org.griddynamics.phonebook.records;

import org.griddynamics.phonebook.PhoneNumber;

/**
 * Abstract class to inherit phone book records from
 */
public abstract class PhoneBookRecord {

    // Phone number
    private PhoneNumber phoneNumber;

    /**
     * Package-private default constructor
     * @param phoneNumber
     */
    PhoneBookRecord(PhoneNumber phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    /**
     * phone number getter
     * @return
     */
    public PhoneNumber getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * Phone number setter
     * @param phoneNumber | new PhoneNumber instance
     */
    public void setPhoneNumber(PhoneNumber phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
