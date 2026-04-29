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

    public static void displayHomeScreen() {

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
                    TransactionManager.depositScreen(userInput);
                    break;
                case "P":
                    TransactionManager.paymentScreen(userInput);
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
