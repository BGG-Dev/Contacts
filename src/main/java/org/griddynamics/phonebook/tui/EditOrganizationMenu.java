package org.griddynamics.phonebook.tui;

import org.griddynamics.phonebook.PhoneNumber;
import org.griddynamics.phonebook.records.Organization;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Edit menu for Organization class
 */
public class EditOrganizationMenu extends Menu {

    // Organization instance
    private final Organization organization;

    // Edit flag
    private boolean isEdited;

    /**
     * Default constructor
     * @param map Command -> Method map
     * @param organization Organization instance to work with
     */
    private EditOrganizationMenu(Map<String, Runnable> map,
                                 Organization organization) {
        // Calling super
        super(map);

        // Filling with methods
        map.put("address", this::address);
        map.put("number", this::number);

        // Initializing organization
        this.organization = organization;

        // Initializing flag
        this.isEdited = false;
    }

    /**
     * Default instance creator for EditOrganizationMenu
     * @param organization Organization to work with
     * @return
     */
    static EditOrganizationMenu getInstance(Organization organization) {
        // Creating map
        Map<String, Runnable> map = new LinkedHashMap<>();

        // Returning instance
        return new EditOrganizationMenu(map, organization);
    }

    /**
     * menu overriding from Menu class
     */
    @Override
    public void menu() {
        while (!isEdited) {
            System.out.print("Select a field (address, number): ");
            event();
        }
        System.out.println("The record updated!");
    }

    private void address() {
        // Asking to enter address
        System.out.print("Enter address: ");

        // Updating
        organization.setAddress(getScanner().nextLine());
        this.isEdited = true;
    }

    private void number() {
        // Asking to enter phone number
        System.out.print("Enter number: ");

        // Updating
        try {
            organization.setPhoneNumber(PhoneNumber.parsePhoneNumber(getScanner().nextLine()));
        } catch (IllegalArgumentException e) {
            System.out.println(TUIMessages.INVALID_PHONE_WARNING);
            organization.setPhoneNumber(PhoneNumber.getEmpty());
        }
        this.isEdited = true;
    }
}
