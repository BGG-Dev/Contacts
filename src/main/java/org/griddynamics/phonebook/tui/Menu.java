package org.griddynamics.phonebook.tui;

import java.util.Map;
import java.util.Scanner;

/**
 * Abstract class
 * to inherit tui implementations from
 */
abstract class Menu {

    // Command -> Method map
    private final Map<String, Runnable> map;

    // Stdin scanner
    private final Scanner scanner;

    /**
     * Default constructor
     * @param map | Command -> Method map
     */
    public Menu(Map<String, Runnable> map) {
        this.map = map;
        scanner = new Scanner(System.in);
    }

    /**
     * Menu runner
     */
    public abstract void menu();

    /**
     * Runs single event
     */
    void event() {
        // Reading command
        String command = scanner.nextLine();

        // Executing
        for (Map.Entry<String, Runnable> current : map.entrySet()) {
            if (command.equals(current.getKey())) {
                current.getValue().run();
                return;
            }
        }

        // No execution -> print error
        System.out.println(TUIMessages.INVALID_COMMAND);
    }

    /**
     * Scanner getter
     * @return
     */
    Scanner getScanner() {
        return this.scanner;
    }
}
