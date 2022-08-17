package org.griddynamics.phonebook.tui;

import org.griddynamics.phonebook.PhoneBook;
import org.griddynamics.phonebook.PhoneNumber;
import org.griddynamics.phonebook.records.Organization;
import org.griddynamics.phonebook.records.Person;
import org.griddynamics.phonebook.records.PhoneBookRecord;

import java.time.DateTimeException;
import java.time.LocalDate;
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
        map.put("info", this::info);
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
            System.out.print("Enter action (add, remove, edit, count, info, exit): ");
            event();
            System.out.print('\n');
        }
    }

    private class Adder extends Menu {

        // Edit flag
        private boolean isDone;

        /**
         * Default private constructor
         * @param map | Command -> Method map
         */
        private Adder(Map<String, Runnable> map) {
            // Calling super
            super(map);

            // Filling with methods
            map.put("person", this::addPerson);
            map.put("organization", this::addOrganization);

            // Initializing flag
            this.isDone = false;
        }

        /**
         * menu overriding from Menu class
         */
        @Override
        public void menu() {
            while (!isDone) {
                System.out.print("Enter the type (person, organization): ");
                event();
            }
            System.out.println("The record added.");
        }

        private void addPerson() {
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

            // Setting birthday
            System.out.print("Enter the birth date: ");
            try {
                builder.setBirthdate(LocalDate.parse(getScanner().nextLine()));
            } catch (DateTimeException e) {
                System.out.println(TUIMessages.INVALID_BIRTHDAY_WARNING);
                builder.setBirthdate(null);
            }

            // Setting gender
            System.out.print("Enter the gender (M, F): ");
            String command = getScanner().nextLine();
            switch (command) {
                case "M":
                    builder.setGender(Person.Gender.MALE);
                    break;
                case "F":
                    builder.setGender(Person.Gender.FEMALE);
                    break;
                default:
                    builder.setGender(null);
                    System.out.println(TUIMessages.INVALID_GENDER_WARNING);
                    break;
            }

            // Setting phone number
            try {
                System.out.print("Enter the number: ");
                builder.setPhoneNumber(PhoneNumber.parsePhoneNumber(getScanner().nextLine()));
            } catch (IllegalArgumentException e) {
                System.out.println(TUIMessages.INVALID_PHONE_WARNING);
                builder.setPhoneNumber(PhoneNumber.getEmpty());
            }

            // Adding
            phoneBook.add(builder.build());

            // Setting flag
            isDone = true;
        }

        private void addOrganization() {
            // Organization's builder
            Organization.Builder builder = new Organization.Builder();

            // Asking to enter name
            System.out.print("Enter the organization name: ");

            // Obtaining
            builder.setOrganizationName(getScanner().nextLine());

            // Asking to enter address
            System.out.print("Enter the address: ");

            // Obtaining
            builder.setAddress(getScanner().nextLine());

            // Setting phone number
            try {
                System.out.print("Enter the number: ");
                builder.setPhoneNumber(PhoneNumber.parsePhoneNumber(getScanner().nextLine()));
            } catch (IllegalArgumentException e) {
                System.out.println(TUIMessages.INVALID_PHONE_WARNING);
                builder.setPhoneNumber(PhoneNumber.getEmpty());
            }

            // Adding
            phoneBook.add(builder.build());

            // Setting flag
            isDone = true;
        }
    }

    private void add() {
        // Creating new Adder
        Adder adder = new Adder(new LinkedHashMap<>());

        // Calling menu
        adder.menu();
    }

    private void remove() {
        // Checking for empty
        if (this.phoneBook.count() == 0) {
            System.out.println("No records to remove!");
            return;
        }

        // Printing
        System.out.println(this.phoneBook);

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
        System.out.println(this.phoneBook);

        // Selecting record
        int index = this.selectIndexOfRecord();

        // Editing
        Menu editMenu = null;
        PhoneBookRecord temp = phoneBook.getAt(index);
        if (temp.getClass() == Person.class) {
            editMenu = EditPersonMenu.getInstance((Person)temp);
        } else if (temp.getClass() == Organization.class) {
            editMenu = EditOrganizationMenu.getInstance((Organization)temp);
        } else {
            System.exit(1);
        }
        editMenu.menu();
    }

    private void count() {
        System.out.printf("The Phone Book has %d records.\n", this.phoneBook.count());
    }

    private void info() {
        // Checking for empty
        if (this.phoneBook.count() == 0) {
            System.out.println("The Phone Book has no records!");
            return;
        }

        // Printing
        System.out.println(this.phoneBook);

        // Selecting
        int index = selectIndexOfRecord();

        // Displaying full info
        System.out.println(phoneBook.getAt(index));
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
