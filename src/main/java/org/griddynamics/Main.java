package org.griddynamics;

import org.griddynamics.phonebook.PhoneBook;
import org.griddynamics.phonebook.tui.PhoneBookMenu;

public class Main {
    public static void main(String[] args) {
        // Creating menu
        PhoneBookMenu phoneBookMenu = PhoneBookMenu.getInstance(new PhoneBook());

        // Running
        phoneBookMenu.menu();
    }
}