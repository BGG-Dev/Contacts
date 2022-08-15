package org.griddynamics.phonebook;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Phone number class tests
 */
class PhoneNumberTest {

    @Test
    void successCreationTest() {
        // Creating phone instance
        PhoneNumber phoneNumber = PhoneNumber.getInstance("1-234-567-890");

        // Asserting
        assertEquals("1-234-567-890", phoneNumber.getPhoneNumber());
    }
}