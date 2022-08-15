package org.griddynamics.phonebook.records;

import org.griddynamics.phonebook.PhoneNumber;

/**
 * Represents person phone book record
 */
public class Person extends PhoneBookRecord {

    // Name
    private String name;

    // Surname
    private String surname;

    /**
     * Default constructor
     * @param name | Person's name as String
     * @param surname | Person's surname as String
     * @param phoneNumber
     */
    private Person(String name, String surname,
                  PhoneNumber phoneNumber) {
        // Calling super
        super(phoneNumber);

        // Initializing names
        this.name = name;
        this.surname = surname;
    }

    /**
     * Builder patter implementation
     * for Person class
     */
    public static class Builder {

        // Name
        private String name;

        // Surname
        private String surname;

        // Phone
        private PhoneNumber phoneNumber;

        /**
         * name setter
         * @param name | name as String
         * @return Builder self reference
         */
        public Builder setName(String name) {
            this.name = name;
            return this;
        }

        /**
         * surname setter
         * @param surname | surname as String
         * @return Builder self reference
         */
        public Builder setSurname(String surname) {
            this.surname = surname;
            return this;
        }

        /**
         * phone number setter
         * @param phoneNumber | Phone number
         * @return Builder self reference
         */
        public Builder setPhoneNumber(PhoneNumber phoneNumber) {
            this.phoneNumber = phoneNumber;
            return this;
        }

        /**
         * Build method
         * @return | Person instance with values from Builder
         */
        public Person build() {
            return new Person(this.name, this.surname, this.phoneNumber);
        }
    }

    /**
     * name getter
     * @return
     */
    public String getName() {
        return name;
    }

    /**
     * surname getter
     * @return
     */
    public String getSurname() {
        return surname;
    }

    /**
     * name setter
     * @param name | New name value
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * surname setter
     * @param surname | New surname value
     */
    public void setSurname(String surname) {
        this.surname = surname;
    }

    /**
     * toString overriding
     * @return String representation of Person instance
     */
    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(name);
        stringBuilder.append(" ");
        stringBuilder.append(surname);
        stringBuilder.append(", ");
        stringBuilder.append(getPhoneNumber().toString());
        return stringBuilder.toString();
    }
}
