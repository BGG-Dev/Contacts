package org.griddynamics.phonebook;

import org.griddynamics.phonebook.records.PhoneBookRecord;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Represents phone book
 */
public class PhoneBook {

    // Private record list
    private final List<PhoneBookRecord> records;

    /**
     * Default constructor
     */
    public PhoneBook() {
        records = new ArrayList<>();
    }

    /**
     * Adds given record to list
     * @param record | Record to add
     * @return Self reference
     */
    public PhoneBook add(PhoneBookRecord record) {
        this.records.add(record);
        return this;
    }

    /**
     * Returns record at given pos
     * @param pos | pos to take from
     * @return | Record instance on pos
     */
    public PhoneBookRecord getAt(int pos) {
        return this.records.get(pos);
    }

    /**
     * Removes record with given index from list
     * @param pos | Index of record to remove
     * @return Self reference
     */
    public PhoneBook removeAt(int pos) {
        this.records.remove(pos);
        return this;
    }

    /**
     * @return Book's records as list
     */
    public List<PhoneBookRecord> asList() {
        return Collections.unmodifiableList(this.records);
    }

    /**
     * @return count of records in book
     */
    public int count() {
        return this.records.size();
    }

    /**
     * toString overriding
     * @return String representation of PhoneBook
     */
    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < this.count(); i++) {
            stringBuilder.append(i + 1);
            stringBuilder.append(". ");
            stringBuilder.append(this.getAt(i).getCaption());
            stringBuilder.append('\n');
        }
        stringBuilder.deleteCharAt(stringBuilder.length() - 1);
        return stringBuilder.toString();
    }
}
