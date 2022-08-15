package org.griddynamics;

import org.griddynamics.phonebook.PhoneNumber;
import org.griddynamics.phonebook.records.Person;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter the name of the person:");

        String name = scanner.nextLine();

        System.out.println("Enter the surname of the person:");

        String surname = scanner.nextLine();

        System.out.println("Enter the number:");

        PhoneNumber phoneNumber = PhoneNumber.getInstance(scanner.nextLine());

        Person person = new Person(name, surname, phoneNumber);

        System.out.println("\nA record created!");
        System.out.println("A Phone Book with a single record created!");
    }
}