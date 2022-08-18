package org.griddynamics.phonebook;

import org.griddynamics.phonebook.records.Organization;
import org.griddynamics.phonebook.records.PhoneBookRecord;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * PhoneBook search method test
 */
public class SearchTest {

    PhoneBook phoneBook;

    void createPhoneBook() {
        phoneBook = new PhoneBook();

        Organization.Builder builder = new Organization.Builder();

        builder.setOrganizationName("Central Bank");
        builder.setAddress("Wall Street, 123");

        phoneBook.add(builder.build(phoneBook));

        builder.setOrganizationName("Centurion Adams");
        builder.setAddress("CAdams@gmail.com");

        phoneBook.add(builder.build(phoneBook));

        builder.setOrganizationName("Decent Pizza shop");
        builder.setAddress("Time Square Avenu, 4");

        phoneBook.add(builder.build(phoneBook));

        builder.setOrganizationName("Car Shop");
        builder.setAddress("Takayava highway, 24");

        phoneBook.add(builder.build(phoneBook));
    }

    @Test
    void centKeywordTest() {
        createPhoneBook();

        List<PhoneBookRecord> result = phoneBook.search("cent");

        String[] expected = { "Central Bank", "Centurion Adams", "Decent Pizza shop" };

        for (int i = 0; i < expected.length; i++) {
            assertEquals(expected[i], result.get(i).getCaption());
        }
    }

    @Test
    void shopKeywordTest() {
        createPhoneBook();

        List<PhoneBookRecord> result = phoneBook.search("shop");

        String[] expected = { "Decent Pizza shop", "Car Shop" };

        for (int i = 0; i < expected.length; i++) {
            assertEquals(expected[i], result.get(i).getCaption());
        }
    }

    @Test
    void noMatchTest() {
        createPhoneBook();

        List<PhoneBookRecord> result = phoneBook.search("There is no match for me");

        assertEquals(0, result.size());
    }
}
