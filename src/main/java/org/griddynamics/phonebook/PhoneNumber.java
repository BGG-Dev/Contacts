package org.griddynamics.phonebook;

/**
 * Phone number storing class
 * with additional logic for
 * phone number processing
 */
public class PhoneNumber {

    // Phone number as String
    private final String phoneNumber;

    // Phone number regex
    //private static final String PHONE_NUMBER = "\\d{10}|(?:\\d{3}-){2}\\d{4}|\\(\\d{3}\\)\\d{3}-?\\d{4}";
    private static final String PHONE_NUMBER = ".+";

    /**
     * Default private constructor
     * @param phoneNumber | valid String instance, represents phone number
     */
    private PhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    /**
     * Checks if given string is a valid phone number
     * if yes, returns PhoneNumber instance, based on given string
     * otherwise throws exception
     * @param phoneNumber | String to validate and create with
     * @return PhoneNumber instance
     * @throws IllegalArgumentException | If given string does not match phone number regex
     */
    public static PhoneNumber getInstance(String phoneNumber) throws IllegalArgumentException {
        if (phoneNumber.matches(PHONE_NUMBER)) {
            return new PhoneNumber(phoneNumber);
        } else {
            throw new IllegalArgumentException("Given string does not match phone number regex");
        }
    }

    /**
     * Actual phone number getter
     * @return Phone number as String
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }
}
