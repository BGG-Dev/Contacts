package org.griddynamics.phonebook.records;

import org.griddynamics.phonebook.PhoneBook;
import org.griddynamics.phonebook.PhoneNumber;

import java.time.LocalDate;

/**
 * Represents person phone book record
 */
public class Person extends PhoneBookRecord {

    /**
     * Gender enum
     */
    public enum Gender {
        MALE, FEMALE;

        @Override
        public String toString() {
            switch (this) {
                case MALE:
                    return "M";
                case FEMALE:
                    return "F";
                default:
                    return "";
            }
        }
    }

    // Name
    private String name;

    // Surname
    private String surname;

    // Birthdate
    private LocalDate birthdate;

    // Gender
    private Gender gender;

    /**
     * Default constructor
     * @param name | Person's name as String
     * @param surname | Person's surname as String
     * @param owner
     * @param phoneNumber
     */
    private Person(String name, String surname,
                   LocalDate birthdate, Gender gender,
                   PhoneBook owner, PhoneNumber phoneNumber) {
        // Calling super
        super(owner, phoneNumber);

        // Initializing fields
        this.name = name;
        this.surname = surname;
        this.birthdate = birthdate;
        this.gender = gender;
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

        // Birthdate
        private LocalDate birthdate;

        // Gender
        private Gender gender;

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
         * birthdate setter
         * @param birthdate | birthdate as LocalDateTime
         */
        public Builder setBirthdate(LocalDate birthdate) {
            this.birthdate = birthdate;
            return this;
        }

        /**
         * Gender setter
         * @param gender
         * @return Builder self reference
         */
        public Builder setGender(Gender gender) {
            this.gender = gender;
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
        public Person build(PhoneBook owner) {
            return new Person(this.name, this.surname,
                              this.birthdate, this.gender,
                              owner, this.phoneNumber);
        }
    }

    /**
     * getCaption overriding
     * @return | Person's name as caption
     */
    @Override
    public String getCaption() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(this.name);
        stringBuilder.append(' ');
        stringBuilder.append(this.surname);
        return stringBuilder.toString();
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
     * birthdate getter
     * @return
     */
    public LocalDate getBirthdate() {
        return birthdate;
    }

    /**
     * gender getter
     * @return
     */
    public Gender getGender() {
        return gender;
    }

    /**
     * name setter
     * @param name | New name value
     */
    public void setName(String name) {
        this.name = name;
        updateLastEditTime();
    }

    /**
     * surname setter
     * @param surname | New surname value
     */
    public void setSurname(String surname) {
        this.surname = surname;
        updateLastEditTime();
    }

    /**
     * birthdate setter
     * @param birthdate
     */
    public void setBirthdate(LocalDate birthdate) {
        this.birthdate = birthdate;
        updateLastEditTime();
    }

    /**
     * gender setter
     * @param gender
     */
    public void setGender(Gender gender) {
        this.gender = gender;
        updateLastEditTime();
    }

    /**
     * getKeywordString Overriding
     * @return String - container of record's keywords
     */
    @Override
    public String getKeywordString() {
        // Creating keyword string
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(name);
        stringBuilder.append(' ');
        stringBuilder.append(surname);
        stringBuilder.append(' ');
        stringBuilder.append(getBirthdate());
        stringBuilder.append(' ');
        stringBuilder.append(getPhoneNumber());

        // Returning
        return stringBuilder.toString();
    }

    /**
     * toString overriding
     * @return String representation of Person instance
     */
    @Override
    public String toString() {
        // Creating string builder
        StringBuilder stringBuilder = new StringBuilder();

        // Appending name
        stringBuilder.append("Name: ");
        stringBuilder.append(name);
        stringBuilder.append('\n');

        // Appending surname
        stringBuilder.append("Surname: ");
        stringBuilder.append(surname);
        stringBuilder.append('\n');

        // Appending birthday
        stringBuilder.append("Birth date: ");
        if (birthdate == null) {
            stringBuilder.append("[no data]");
        } else {
            stringBuilder.append(birthdate);
        }
        stringBuilder.append('\n');

        // Appending gender
        stringBuilder.append("Gender: ");
        if (gender == null) {
            stringBuilder.append("[no data]");
        } else {
            stringBuilder.append(gender);
        }
        stringBuilder.append('\n');

        // Appending number
        stringBuilder.append("Number: ");
        stringBuilder.append(getPhoneNumber());
        stringBuilder.append('\n');

        // Appending times
        appendTimeToStringBuilder(stringBuilder);

        // Returning
        return stringBuilder.toString();
    }
}
