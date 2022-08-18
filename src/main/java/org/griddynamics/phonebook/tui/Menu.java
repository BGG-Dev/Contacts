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

    // User's event command
    private String eventCommand;

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
        eventCommand = scanner.nextLine();

        // Executing
        for (Map.Entry<String, Runnable> current : map.entrySet()) {
            //if (command.equals(current.getKey())) {
            if (eventCommand.matches(current.getKey())) {
                current.getValue().run();
                return;
            }
        }

        // No execution -> print error
        System.out.println(UtilsTUI.INVALID_COMMAND);
    }

    /**
     * Scanner getter
     * @return
     */
    Scanner getScanner() {
        return this.scanner;
    }

    /**
     * User's command getter
     * @return Last user's command as String
     */
    String getEventCommand() {
        return this.eventCommand;
    }
}
