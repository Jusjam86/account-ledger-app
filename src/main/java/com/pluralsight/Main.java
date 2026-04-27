package com.pluralsight;

import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.*;
import java.util.*;
import java.io.*;

public class Main {

    static Scanner userInput = new Scanner(System.in);

    static void main() {

        displayHomeScreen();
        displayPaymentScreen();
        displayLedgerScreen();
        displayReportScreen();

    }

    static void displayHomeScreen() {

        while(true) {

            System.out.println();
            System.out.println("Welcome to the Account Ledger Application");
            System.out.println("---------------------------------------------------------");
            System.out.println("D) Add Deposit");
            System.out.println("P) Make Payment (Debit)");
            System.out.println("L) Ledger");
            System.out.println("X) Exit");
            System.out.println();
            System.out.print("Make your choice: ");
            String choice = userInput.nextLine().strip().toUpperCase();

            System.out.println();

            switch (choice) {

                case "D":
                    displayDepositScreen();
                    break;
                case "P":
                    displayPaymentScreen();
                    break;
                case "L":
                    displayLedgerScreen();
                    break;
                case "X":
                    System.out.println("Exiting, come back anytime!");
                    // return exits the loop and the method; in this case, this ends the application
                    break;

                default:
                    System.out.println("Invalid Choice, Try again");
                    displayHomeScreen();
            }
            return;
        }

    }
    static void displayDepositScreen() {
        while(true) {

            System.out.println();
            System.out.println("Please enter your deposit information");
            System.out.println("------------------------------------------------------");
            System.out.println("Amount: ");
            double amount = Double.parseDouble(userInput.nextLine());
            System.out.println("Description: ");
            String description = userInput.nextLine().strip();
            System.out.println("Vendor: ");
            String vendor = userInput.nextLine().strip();
            System.out.println("Please enter date in MM/DD/YYYY format: ");
            String dateInput = userInput.nextLine().strip();

            // fixes formatting of the date, makes it American standard
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
            LocalDate dateOfTransaction = LocalDate.parse(dateInput, formatter);

            // add timestamp without user input
            LocalDateTime now = LocalDateTime.now();
            DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("HH:mm:ss");
            String timeStamp = now.format(formatter2);

            // calls logTransactions method
            // plugs in user input variables above
            // the user inputs are recorded onto the .csv using transactions' initial variables
            new transactions(dateInput, timeStamp, description, vendor, amount);

            System.out.println();
            System.out.println("Deposit Recorded");
            displayHomeScreen();

        }
    }

    static void displayPaymentScreen() {
        while(true) {
            System.out.println();
        }
    }

    static void displayLedgerScreen() {
        while(true) {
            System.out.println();
        }
    }

    static void displayReportScreen() {
        while(true) {
            System.out.println();
        }
    }
}
