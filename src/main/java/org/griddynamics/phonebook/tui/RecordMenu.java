package org.griddynamics.phonebook.tui;

import org.griddynamics.phonebook.records.Organization;
import org.griddynamics.phonebook.records.Person;
import org.griddynamics.phonebook.records.PhoneBookRecord;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Implements menu
 */
public class RecordMenu extends Menu {

    // Record to work with
    private final PhoneBookRecord record;

    // is done flag
    private boolean isDone;

    /**
     * Default constructor
     * @param map Command -> Method map
     * @param record Record instance to work with
     */
    private RecordMenu(Map<String, Runnable> map,
                       PhoneBookRecord record) {
        // Calling super
        super(map);

        // Filling with methods
        map.put("edit", this::edit);
        map.put("delete", this::delete);
        map.put("menu", this::backToMenu);

        // Initializing record
        this.record = record;

        // Initializing flag
        this.isDone = false;
    }

    /**
     * Default instance creator for RecordMenu
     * @param record PhoneBookRecord instance to work with
     * @return
     */
    public static RecordMenu getInstance(PhoneBookRecord record) {
        // Creating map
        Map<String, Runnable> map = new LinkedHashMap<>();

        // Returning instance
        return new RecordMenu(map, record);
    }

    @Override
    public void menu() {
        while (!isDone) {
            // Printing record
            System.out.println(record);

            // Printing menu
            System.out.print("\n[record] Enter action (edit, delete, menu): ");

            // Event
            event();
        }
    }

    private void edit() {
        // Creating EditMenu instance
        Menu editMenu = null;
        if (record.getClass() == Person.class) {
            editMenu = EditPersonMenu.getInstance((Person)record);
        } else if (record.getClass() == Organization.class) {
            editMenu = EditOrganizationMenu.getInstance((Organization)record);
        } else {
            System.exit(1);
        }

        // Calling
        editMenu.menu();
    }

    private void delete() {
        // Deleting record
        record.getOwner().remove(record);

        // Printing
        System.out.println("The record removed!");

        // Ending
        this.isDone = true;
    }

    private void backToMenu() {
        // Ending
        this.isDone = true;
    }
}
