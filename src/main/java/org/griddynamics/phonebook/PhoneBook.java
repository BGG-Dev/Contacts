package org.griddynamics.phonebook;

import org.griddynamics.phonebook.records.PhoneBookRecord;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Pattern;

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
     * Removes given record
     * @param record Record instance to remove
     */
    public void remove(PhoneBookRecord record) {
        this.records.remove(record);
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
     * Searches record by query
     * @param query String - query to search for
     * @return List of phone book records,
     * all corresponds to given record
     */
    public List<PhoneBookRecord> search(String query) {
        // Making case-insensitive
        String caseInsensitiveQuery = query.toLowerCase();

        // Returning
        return records.stream()
                      .filter(e -> {
                          String temp = e.getKeywordString().toLowerCase();
                          return temp.matches(caseInsensitiveQuery) ||
                                 temp.contains(caseInsensitiveQuery);
                      })
                      .toList();
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
