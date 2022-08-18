package org.griddynamics.phonebook;

import java.util.regex.Pattern;

/**
 * Phone number storing class
 * with additional logic for
 * phone number processing
 */
public class PhoneNumber {

    // Phone number as String
    private final String phoneNumber;

    // Phone number regex
    //private static final Pattern PHONE_NUMBER = Pattern.compile("\\+?[a-z0-9]+[- ][a-z0-9]{2,}");
    private static final Pattern PHONE_NUMBER = Pattern.compile("^\\+?(\\(\\w+\\)|\\w+[ -]\\(\\w{2,}\\)|\\w+)([ -]\\w{2,})*");

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
    public static PhoneNumber parsePhoneNumber(String phoneNumber) throws IllegalArgumentException {
        if (PHONE_NUMBER.matcher(phoneNumber).matches()) {
            return new PhoneNumber(phoneNumber);
        } else {
            throw new IllegalArgumentException("Given string does not match phone number regex");
        }
    }

    /**
     * Returns instance of PhoneNumber with null as phoneNumber field
     * @return
     */
    public static PhoneNumber getEmpty() {
        return new PhoneNumber(null);
    }

    /**
     * Check if given string matches phone number regex
     * @param candidate | String to validate
     * @return | Match result
     */
    public static boolean isValid(String candidate) {
        return PHONE_NUMBER.matcher(candidate).matches();
    }

    /**
     * Actual phone number getter
     * @return Phone number as String
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * toString overriding
     * @return String representation of PhoneNumber instance
     */
    @Override
    public String toString() {
        if (this.phoneNumber == null) {
            return "[no number]";
        } else {
            return this.phoneNumber;
        }
    }
}
