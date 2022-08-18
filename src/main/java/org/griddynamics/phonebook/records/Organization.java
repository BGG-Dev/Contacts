package org.griddynamics.phonebook.records;

import org.griddynamics.phonebook.PhoneBook;
import org.griddynamics.phonebook.PhoneNumber;

/**
 * Represents organization type record
 */
public class Organization extends PhoneBookRecord {

    // Organization name
    private String organizationName;

    // Address
    private String address;

    /**
     * Default constructor
     * @param organizationName | Organization's name as String
     * @param address | Address as String
     * @param phoneNumber
     */
    private Organization(String organizationName, String address,
                         PhoneBook owner, PhoneNumber phoneNumber) {
        // Calling super
        super(owner, phoneNumber);

        // Initializing fields
        this.organizationName = organizationName;
        this.address = address;
    }

    /**
     * Builder pattern implementation
     * for Organization class
     */
    public static class Builder {

        // Organization name
        private String organizationName;

        // Address
        private String address;

        // Phone number
        private PhoneNumber phoneNumber;

        /**
         * Organization name setter
         * @param organizationName | organization name as String
         * @return Builder self reference
         */
        public Builder setOrganizationName(String organizationName) {
            this.organizationName = organizationName;
            return this;
        }

        /**
         * Address setter
         * @param address | address as String
         * @return Builder self reference
         */
        public Builder setAddress(String address) {
            this.address = address;
            return this;
        }

        /**
         * Phone number setter
         * @param phoneNumber
         * @return Builder self reference
         */
        public Builder setPhoneNumber(PhoneNumber phoneNumber) {
            this.phoneNumber = phoneNumber;
            return this;
        }

        /**
         * Build method
         * @return Person instance with fields from Builder
         */
        public Organization build(PhoneBook owner) {
            return new Organization(this.organizationName, this.address,
                                    owner, this.phoneNumber);
        }
    }

    /**
     * getCaption overriding from PhoneBookRecord
     * @return | Organization's caption as String
     */
    @Override
    public String getCaption() {
        return this.organizationName;
    }

    /**
     * Organization name getter
     * @return
     */
    public String getOrganizationName() {
        return organizationName;
    }

    /**
     * Address getter
     * @return
     */
    public String getAddress() {
        return address;
    }

    /**
     * organization name setter
     * @param organizationName
     */
    public void setOrganizationName(String organizationName) {
        this.organizationName = organizationName;
        updateLastEditTime();
    }

    /**
     * Address setter
     * @param address
     */
    public void setAddress(String address) {
        this.address = address;
        updateLastEditTime();
    }

    /**
     * getKeywordString overriding
     * @return String - container of Organization keywords
     */
    @Override
    public String getKeywordString() {
        // Creating keyword string
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(organizationName);
        stringBuilder.append(' ');
        stringBuilder.append(address);
        stringBuilder.append(' ');
        stringBuilder.append(getPhoneNumber());

        // Returning
        return stringBuilder.toString();
    }

    /**
     * toString overriding
     * @return String representation of Organization instance
     */
    @Override
    public String toString() {
        // Creating string builder
        StringBuilder stringBuilder = new StringBuilder();

        // Appending organization name
        stringBuilder.append("Organization name: ");
        stringBuilder.append(organizationName);
        stringBuilder.append('\n');

        // Appending surname
        stringBuilder.append("Address: ");
        stringBuilder.append(address);
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
