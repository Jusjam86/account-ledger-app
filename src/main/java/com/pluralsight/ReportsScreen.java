package com.pluralsight;

// This class handles the Reports screen,
// it lets the user run pre-built reports to filter transactions by date or vendor

import java.time.*;
import java.util.*;

import static com.pluralsight.LedgerScreen.showLedgerScreen;

public class ReportsScreen {

    public static void showReportsScreen(Scanner userInput) {

        while (true) {

            System.out.println("\n===== REPORTS =====");
            System.out.println("1) Month To Date");
            System.out.println("2) Previous Month");
            System.out.println("3) Year To Date");
            System.out.println("4) Previous Year");
            System.out.println("5) Search by Vendor");
            System.out.println("0) Back");
            System.out.print("Choose an option: ");

            String choice = userInput.nextLine();

            switch (choice) {

                case "1":
                    monthToDate();
                    showReportsScreen(userInput);
                    break;

                case "2":
                    previousMonth();
                    showReportsScreen(userInput);
                    break;

                case "3":
                    yearToDate();
                    showReportsScreen(userInput);
                    break;

                case "4":
                    previousYear();
                    showReportsScreen(userInput);
                    break;

                case "5":
                    searchByVendor(userInput);
                    showReportsScreen(userInput);
                    break;

                case "0":
                    showLedgerScreen(userInput);
                    break;

                default:
                    System.out.println("Invalid option. Please try again.");
                    showReportsScreen(userInput);
            }
            return;
        }
    }

    // show all transactions from the first of this month until today
    public static void monthToDate() {

        System.out.println("\n===== MONTH TO DATE =====");

        LocalDate today = LocalDate.now(); // get today's date
        int currentMonth = today.getMonthValue(); // get the month number (1-12)
        int currentYear = today.getYear();        // get the year (example: 2024)

        ArrayList<transactions> results = new ArrayList<>();

        for (transactions t : TransactionManager.transactions) {
            // parse the transaction's date string into a LocalDate object to compare
            LocalDate transDate = LocalDate.parse(t.getDate());

            // checks to see if it's in the same month and same year
            if (transDate.getMonthValue() == currentMonth && transDate.getYear() == currentYear) {
                results.add(t);
            }
        }

        LedgerScreen.printEntries(results);
    }

    // show all transactions from last month
    public static void previousMonth() {

        System.out.println("\n===== PREVIOUS MONTH =====");

        LocalDate today = LocalDate.now();

        // minusMonths(1) goes back one month
        LocalDate lastMonth = today.minusMonths(1);
        int prevMonth = lastMonth.getMonthValue();
        int prevYear = lastMonth.getYear();

        ArrayList<transactions> results = new ArrayList<>();

        for (transactions t : TransactionManager.transactions) {
            LocalDate transDate = LocalDate.parse(t.getDate());

            if (transDate.getMonthValue() == prevMonth && transDate.getYear() == prevYear) {
                results.add(t);
            }
        }

        LedgerScreen.printEntries(results);
    }

    // show all transactions from January 1st of this year until today
    public static void yearToDate() {

        System.out.println("\n===== YEAR TO DATE =====");

        int currentYear = LocalDate.now().getYear();

        ArrayList<transactions> results = new ArrayList<>();

        for (transactions t : TransactionManager.transactions) {
            LocalDate transDate = LocalDate.parse(t.getDate());

            // Only check if the year matches — this covers all months up to today
            if (transDate.getYear() == currentYear) {
                results.add(t);
            }
        }

        LedgerScreen.printEntries(results);
    }

    // show all transactions from last year
    public static void previousYear() {

        System.out.println("\n===== PREVIOUS YEAR =====");

        int lastYear = LocalDate.now().getYear() - 1; // Subtract 1 from current year

        ArrayList<transactions> results = new ArrayList<>();

        for (transactions t : TransactionManager.transactions) {
            LocalDate transDate = LocalDate.parse(t.getDate());

            if (transDate.getYear() == lastYear) {
                results.add(t);
            }
        }

        LedgerScreen.printEntries(results);
    }

    // show all transactions matching the vendor name the user types
    public static void searchByVendor(Scanner userInput) {

        System.out.println("\n===== SEARCH BY VENDOR =====");
        System.out.print("Enter vendor name: ");
        String vendorName = userInput.nextLine();

        ArrayList<transactions> results = new ArrayList<>();

        for (transactions t : TransactionManager.transactions) {
            // equalsIgnoreCase means "Amazon" and "amazon" are treated the same
            if (t.getVendor().toLowerCase().contains(vendorName)) {
                results.add(t);
            }
        }

        if (results.isEmpty()) {
            System.out.println("No transactions found for vendor: " + vendorName);
        } else {
            LedgerScreen.printEntries(results);
        }
    }
}