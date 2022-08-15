package org.griddynamics.phonebook;

import org.junit.jupiter.api.Test;

import java.util.LinkedHashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Phone number class tests
 */
class PhoneNumberTest {

    @Test
    void stage2PhoneSetTest() {
        // Test data
        LinkedHashMap<String, Boolean> data = new LinkedHashMap<>();
        data.put("123", true);
        data.put("123 abc", true);
        data.put("123-ABC", true);
        data.put("123 456 xyz", true);
        data.put("123-456-XYZ", true);
        data.put("123 456-789", true);
        data.put("123-456 789", true);
        data.put("123 45-up-89", true);
        data.put("(123)", true);
        data.put("(123) 456", true);
        data.put("123-(456)", true);
        data.put("123 (456) 789", true);
        data.put("123-(456)-789", true);
        data.put("(123) 456-789", true);
        data.put("(123)-456 789", true);
        data.put("123 (45)-67-89", true);
        data.put("+(phone)", true);
        data.put("123+456 78912", false);
        data.put("(123)-456-(78912)", false);
        data.put("9", true);
        data.put("123 456 9", false);
        data.put("123 9 9234", false);
        data.put("123 4?5 678", false);
        data.put("+(with space)", false);
        data.put("193", true);
        data.put("129 abf", true);
        data.put("123-AFC", true);
        data.put("154 456 xyz", true);
        data.put("123-566-XYZ", true);
        data.put("123 456-349", true);
        data.put("134-456 789", true);
        data.put("123 45-down-89", true);
        data.put("(234)", true);
        data.put("(123) 566", true);
        data.put("873-(456)", true);
        data.put("123 (786) 789", true);
        data.put("163-(456)-789", true);
        data.put("(123) 496-789", true);
        data.put("(173)-456 789", true);
        data.put("123 (95)-67-89", true);
        data.put("+(another)", true);
        data.put("132+456 78912", false);
        data.put("(123)-456-(45912)", false);
        data.put("8", true);
        data.put("153 456 9", false);
        data.put("823 9 9234", false);
        data.put("123 4?5 654", false);
        data.put("+(another space)", false);
        data.put("+1 ()", false);
        data.put("+1 11", true);

        for (Map.Entry<String, Boolean> entry : data.entrySet()) {
            assertEquals(entry.getValue(), PhoneNumber.isValid(entry.getKey()));
        }
    }
}