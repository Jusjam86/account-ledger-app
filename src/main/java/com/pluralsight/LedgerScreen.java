package com.pluralsight;

// This class handles everything shown on the Ledger screen,
// all entries, deposits, payments, and reports.

import java.util.*;

import static com.pluralsight.Main.displayHomeScreen;

public class LedgerScreen {

    public static void showLedgerScreen(Scanner userInput) {

        while (true) {

            System.out.println("\n===== LEDGER =====");
            System.out.println("A) All Entries");
            System.out.println("D) Deposits Only");
            System.out.println("P) Payments Only");
            System.out.println("R) Reports");
            System.out.println("H) Home");
            System.out.print("Choose an option: ");

            String choice = userInput.nextLine().toUpperCase();

            switch (choice) {

                case "A":
                    // shows all transactions (newest first)
                    printEntries(TransactionManager.transactions);
                    showLedgerScreen(userInput);
                    break;

                case "D":
                    showDeposits();
                    showLedgerScreen(userInput);
                    break;

                case "P":
                    showPayments();
                    showLedgerScreen(userInput);
                    break;

                case "R":
                    // goes to ReportsScreen
                    ReportsScreen.showReportsScreen(userInput);
                    break;

                case "H":
                    // back to home screen
                    displayHomeScreen();
                    break;

                default:
                    System.out.println();
                    System.out.println("Invalid option. Please try again.");
                    System.out.println();
                    showLedgerScreen(userInput);

            }
            return;
        }
    }

    // shows deposits (positive amounts)
    public static void showDeposits() {

        System.out.println("\n===== DEPOSITS =====");

        // create a new list to hold only the deposits
        ArrayList<transactions> deposits = new ArrayList<>();

        // loops through all transactions and find the positive ones
        for (transactions t : TransactionManager.transactions) {
            if (t.getAmount() > 0) {
                deposits.add(t);
            }
        }

        printEntries(deposits); // print the filtered list
    }

    // shows payments (negative amounts)
    public static void showPayments() {

        System.out.println("\n===== PAYMENTS =====");

        ArrayList<transactions> payments = new ArrayList<>();

        // loops through all transactions and find the negative ones
        for (transactions t : TransactionManager.transactions) {
            if (t.getAmount() < 0) {
                payments.add(t);
            }
        }

        printEntries(payments);
    }

    // print a list of transactions — newest entries will appear first
    public static void printEntries(ArrayList<transactions> list) {

        if (list.isEmpty()) {
            System.out.println();
            System.out.println("No entries to display.");
            System.out.println();
            return;
        }

        // print from 'End' of the list to the 'Beginning' so newest ones show up first
        // note: the last item added to the list is the most recent
        for (int i = list.size() - 1; i >= 0; i--) {
            list.get(i).printTransaction();
        }
    }
}