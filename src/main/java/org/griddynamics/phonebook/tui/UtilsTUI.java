package org.griddynamics.phonebook.tui;

/**
 * Common code placeholder
 */
class UtilsTUI {

    static final String INVALID_COMMAND = "Invalid command!";

    static final String INVALID_PHONE_WARNING = "Wrong number format!";

    static final String INVALID_BIRTHDAY_WARNING = "Bad birth date!";

    static final String INVALID_GENDER_WARNING = "Bad gender!";

    static final String SAVED_AFTER_EDIT_MESSAGE = "Saved";

    static int getIndexFromCommandLessThanBound(String command, int bound) {
        // Trying to parse
        int index;
        try {
            index = Integer.parseInt(command);
        } catch (NumberFormatException e) {
            System.out.println(UtilsTUI.INVALID_COMMAND);
            return -1;
        }

        // Checking for bound
        index--;
        if (0 > index || bound <= index) {
            System.out.println("Index out of bounds!");
            return -1;
        }

        // Returning
        return index;
    }
}
