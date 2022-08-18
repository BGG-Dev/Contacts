package org.griddynamics.phonebook.tui;

import org.griddynamics.phonebook.records.PhoneBookRecord;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Implementation of list menu
 */
public class ListMenu extends NumberMenu {

    /**
     * Default constructor
     * @param map Command -> Method map
     * @param records List of records to work with
     */
    private ListMenu(Map<String, Runnable> map,
                     List<PhoneBookRecord> records) {
        // Calling super
        super(map, records);
    }

    /**
     * Default instance creator for ListMenu
     * @param records List of records to work with
     * @return
     */
    public static ListMenu getInstance(List<PhoneBookRecord> records) {
        // Creating map
        Map<String, Runnable> map = new LinkedHashMap<>();

        // Returning instance
        return new ListMenu(map, records);
    }

    @Override
    public void menu() {
        while (!isDone()) {
            System.out.print("[list] Enter action ([number], back): ");
            event();
        }
    }
}
