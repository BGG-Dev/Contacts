package org.griddynamics.phonebook.tui;

import org.griddynamics.phonebook.records.PhoneBookRecord;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Implements search menu tui
 */
public class SearchMenu extends NumberMenu {

    // again action flag
    private boolean isAgain;

    private SearchMenu(Map<String, Runnable> map,
                       List<PhoneBookRecord> records) {
        // Calling super
        super(map, records);

        // Filling with methods
        map.put("again", this::again);

        // Initializing again flag
        this.isAgain = false;
    }

    public static SearchMenu getInstance(List<PhoneBookRecord> records) {
        // Creating map
        Map<String, Runnable> map = new LinkedHashMap<>();

        // Returning
        return new SearchMenu(map, records);
    }

    @Override
    public void menu() {
        while (!isDone()) {
            System.out.print("[search] Enter action ([number], back, again): ");
            event();
        }
    }

    private void again() {
        // Setting again flag
        this.isAgain = true;

        // Ending
        this.setDone();
    }

    boolean isAgain() {
        return this.isAgain;
    }
}
