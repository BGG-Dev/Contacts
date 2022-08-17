package org.griddynamics.phonebook.records;

import org.griddynamics.phonebook.PhoneNumber;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Abstract class to inherit phone book records from
 */
public abstract class PhoneBookRecord {

    // Phone number
    private PhoneNumber phoneNumber;

    // Creation time
    private final LocalDateTime creationTime;

    // Last edit time
    private LocalDateTime lastEditTime;

    /**
     * Package-private default constructor
     * @param phoneNumber
     */
    PhoneBookRecord(PhoneNumber phoneNumber) {
        // Initializing phone number
        this.phoneNumber = phoneNumber;

        // Initializing times
        this.creationTime = LocalDateTime.now();
        this.lastEditTime = LocalDateTime.now();
    }

    /**
     * Abstract method to get record's caption
     * @return | Record's caption as String
     */
    public abstract String getCaption();

    /**
     * phone number getter
     * @return
     */
    public PhoneNumber getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * creation time getter
     * @return | LocalDateTime instance
     */
    public LocalDateTime getCreationTime() {
        return creationTime;
    }

    /**
     * last edit time getter
     * @return
     */
    public LocalDateTime getLastEditTime() {
        return lastEditTime;
    }

    /**
     * Phone number setter
     * @param phoneNumber | new PhoneNumber instance
     */
    public void setPhoneNumber(PhoneNumber phoneNumber) {
        this.phoneNumber = phoneNumber;
        updateLastEditTime();
    }

    /**
     * Updates lastEditTime to now
     */
    void updateLastEditTime() {
        this.lastEditTime = LocalDateTime.now();
    }

    /**
     * Appends time info to given stringBuilder
     * @param stringBuilder | StringBuilder instance to append to
     */
    void appendTimeToStringBuilder(StringBuilder stringBuilder) {
        // Creating date-time format
        DateTimeFormatter formatter =
                DateTimeFormatter.ofPattern("uuuu-MM-dd'T'HH:mm");

        // Appending
        stringBuilder.append("Time created: ");
        stringBuilder.append(creationTime.format(formatter));
        stringBuilder.append("\nTime last edit: ");
        stringBuilder.append(lastEditTime.format(formatter));
    }
}
