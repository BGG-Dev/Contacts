package org.griddynamics.phonebook.tui;

import org.griddynamics.phonebook.PhoneBook;
import org.griddynamics.phonebook.PhoneNumber;
import org.griddynamics.phonebook.records.Person;
import org.griddynamics.phonebook.records.PhoneBookRecord;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Phone book menu implementation
 */
public final class PhoneBookMenu extends Menu {

    // PhoneBook instance
    private final PhoneBook phoneBook;

    /**
     * Default constructor
     * @param map | Command -> Method map
     * @param phoneBook | PhoneBook instance to work with
     */
    private PhoneBookMenu(Map<String, Runnable> map,
                          PhoneBook phoneBook) {
        // Calling super
        super(map);

        // Filling map with methods
        map.put("add", this::add);
        map.put("remove", this::remove);
        map.put("edit", this::edit);
        map.put("count", this::count);
        map.put("list", this::list);
        map.put("exit", this::exit);

        // Initializing phoneBook
        this.phoneBook = phoneBook;
    }

    /**
     * Default instance creator for PhoneBookMenu
     * @param phoneBook | PhoneBook to work with
     * @return
     */
    public static PhoneBookMenu getInstance(PhoneBook phoneBook) {
        // Creating map
        Map<String, Runnable> map = new LinkedHashMap<>();

        // Returning instance
        return new PhoneBookMenu(map, phoneBook);
    }

    /**
     * menu overriding from Menu class
     */
    @Override
    public void menu() {
        while (true) {
            System.out.print("Enter action (add, remove, edit, count, list, exit): ");
            event();
        }
    }

    /**
     * Add PhoneBookRecord to PhoneBook
     */
    private void add() {
        // Person's builder
        Person.Builder builder = new Person.Builder();

        // Asking to enter name
        System.out.print("Enter the name: ");

        // Obtaining
        builder.setName(getScanner().nextLine());

        // Asking to enter surname
        System.out.print("Enter the surname: ");

        // Obtaining
        builder.setSurname(getScanner().nextLine());

        // Trying to get phone number
        try {
            System.out.print("Enter the number: ");
            builder.setPhoneNumber(PhoneNumber.parsePhoneNumber(getScanner().nextLine()));
        } catch (IllegalArgumentException e) {
            System.out.println(TUIMessages.INVALID_PHONE_WARNING);
            builder.setPhoneNumber(PhoneNumber.getEmpty());
        }

        // Adding
        this.phoneBook.add(builder.build());

        // Printing
        System.out.println("The record added.");
    }

    private void remove() {
        // Checking for empty
        if (this.phoneBook.count() == 0) {
            System.out.println("No records to remove!");
            return;
        }

        // Printing
        this.list();

        // Selecting
        int indexOfSelected = this.selectIndexOfRecord();

        // Removing
        this.phoneBook.removeAt(indexOfSelected);

        // Printing
        System.out.println("The record removed!");
    }

    private void edit() {
        // Checking for empty
        if (this.phoneBook.count() == 0) {
            System.out.println("No records to edit!");
            return;
        }

        // Printing
        this.list();

        // Selecting record
        int index = this.selectIndexOfRecord();

        // Editing
        Menu editMenu = null;
        PhoneBookRecord temp = phoneBook.getAt(index);
        if (temp instanceof Person) {
            editMenu = EditPersonMenu.getInstance((Person)temp);
        }
        editMenu.menu();
    }

    private void count() {
        System.out.printf("The Phone Book has %d records.\n", this.phoneBook.count());
    }

    private void list() {
        // Checking for empty
        if (this.phoneBook.count() == 0) {
            System.out.println("The Phone Book has no records!");
            return;
        }

        // Printing
        for (int i = 0; i < phoneBook.count(); i++) {
            System.out.println((i + 1) + ". " + phoneBook.getAt(i));
        }
    }

    private void exit() {
        System.exit(0);
    }

    private int selectIndexOfRecord() {
        while (true) {
            // Asking to enter number
            System.out.print("Select a record: ");

            // Trying to parse
            int index;
            try {
                index = Integer.parseInt(getScanner().nextLine());
            } catch (NumberFormatException e) {
                System.out.println(TUIMessages.INVALID_COMMAND);
                continue;
            }

            // Checking for bound
            index--;
            if (0 > index || this.phoneBook.count() <= index) {
                System.out.println("Index out of bounds!");
                continue;
            }

            // Returning
            return index;
        }
    }
}
