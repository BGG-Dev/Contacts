package org.griddynamics.phonebook.tui;

import org.griddynamics.phonebook.records.PhoneBookRecord;

import java.util.List;
import java.util.Map;

/**
 * Class to inherit all record selection menus
 */
public abstract class NumberMenu extends Menu {

    // Record list
    private List<PhoneBookRecord> records;

    // is done flag
    private boolean isDone;

    /**
     * Default constructor
     * @param map Command -> Method map
     * @param records List of records to work with
     */
    NumberMenu(Map<String, Runnable> map,
               List<PhoneBookRecord> records) {
        // Calling super
        super(map);

        // Filling map with methods
        map.put("\\d", this::number);
        map.put("back", this::setDone);

        // Initializing record list
        this.records = records;

        // Initializing flag
        isDone = false;
    }

    List<PhoneBookRecord> getRecords() {
        return records;
    }

    boolean isDone() {
        return isDone;
    }

    void setDone() {
        isDone = true;
    }

    void number() {
        // Obtaining index
        int index = UtilsTUI.getIndexFromCommandLessThanBound(getEventCommand(),
                records.size());
        // Checking for fail
        if (index == -1) {
            return;
        }

        // Obtaining chosen record
        PhoneBookRecord chosen = records.get(index);

        // Entering record menu with chosen
        RecordMenu.getInstance(chosen).menu();

        // Ending
        setDone();
    }
}
