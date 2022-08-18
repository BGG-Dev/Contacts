package org.griddynamics.phonebook.tui;

import org.griddynamics.phonebook.PhoneBook;
import org.griddynamics.phonebook.PhoneNumber;
import org.griddynamics.phonebook.records.Organization;
import org.griddynamics.phonebook.records.Person;
import org.griddynamics.phonebook.records.PhoneBookRecord;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.LinkedHashMap;
import java.util.List;
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
        map.put("list", this::list);
        map.put("search", this::search);
        map.put("count", this::count);
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
            System.out.print("[menu] Enter action (add, list, search, count, exit): ");
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
                System.out.println(UtilsTUI.INVALID_BIRTHDAY_WARNING);
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
                    System.out.println(UtilsTUI.INVALID_GENDER_WARNING);
                    break;
            }

            // Setting phone number
            try {
                System.out.print("Enter the number: ");
                builder.setPhoneNumber(PhoneNumber.parsePhoneNumber(getScanner().nextLine()));
            } catch (IllegalArgumentException e) {
                System.out.println(UtilsTUI.INVALID_PHONE_WARNING);
                builder.setPhoneNumber(PhoneNumber.getEmpty());
            }

            // Adding
            phoneBook.add(builder.build(phoneBook));

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
                System.out.println(UtilsTUI.INVALID_PHONE_WARNING);
                builder.setPhoneNumber(PhoneNumber.getEmpty());
            }

            // Adding
            phoneBook.add(builder.build(phoneBook));

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

    private void list() {
        // Checking for empty
        if (phoneBook.count() == 0) {
            System.out.println("No records in phone book!");
            return;
        }

        // Printing
        System.out.println(phoneBook);
        System.out.print('\n');

        // Creating new ListMenu instance
        ListMenu.getInstance(phoneBook.asList()).menu();
    }

    private void search() {
        SearchMenu searchMenu;
        do {
            // Asking to enter query
            System.out.print("Enter search query: ");

            // Searching
            List<PhoneBookRecord> result = phoneBook.search(getScanner().nextLine());

            // Checking for 0
            if (result.isEmpty()) {
                System.out.println("No records found!");
                return;
            }

            // Printing
            for (int i = 0; i < result.size(); i++) {
                System.out.printf("%d. %s\n", i + 1, result.get(i).getCaption());
            }

            // Creating searchMenu
            searchMenu = SearchMenu.getInstance(result);

            // Running
            searchMenu.menu();
        } while (searchMenu.isAgain());
    }

    private void count() {
        System.out.printf("The Phone Book has %d records.\n", this.phoneBook.count());
    }

    private void exit() {
        System.exit(0);
    }
}
