package com.pluralsight;

// This class will everything related to reading and writing transactions
// I'm storing the deposits and payments here as well until I deem another class is required for it

import java.io.*;
import java.util.*;
import java.time.*;
import java.util.Scanner;

public class TransactionManager {

    static Scanner userInput = new Scanner(System.in);

    // the file where all transactions will be stored
    static String FILE_NAME = "transactions.csv";

    // arraylist to hold many
    static ArrayList<transactions> transactions = new ArrayList<>();

    // loads all transactions from csv file
    public void loadTransactions() {

        // create file object pointing to csv file
        File file = new File(FILE_NAME);

        // if the file doesn't exist yet, there's nothing to load
        if (!file.exists()) {
            System.out.println("No existing transaction file found.");
            return;
        }

        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));

            // skips header
            String lines = bufferedReader.readLine();

            String line;

            while ((line = bufferedReader.readLine()) != null) {

                if (line.trim().isEmpty()) ;

                String[] columns = line.split("\\|");

                if (columns.length == 5) {
                    String date = columns[0];
                    String time = columns[1];
                    String description = columns[2];
                    String vendor = columns[3];
                    double amount = Double.parseDouble(columns[4]);

                    // created object
                    transactions.add(new transactions(date, time, description, vendor, amount));
                }
            }
            bufferedReader.close();
            System.out.println("Transactions successfully loaded");

        } catch (IOException ex) {
            System.out.println("Error reading file: " + ex.getMessage());
        }
    }

    // saves/ writes a single line transaction to the csv file (append it)
    public static void saveTransactions(transactions t) {

        try {
            FileWriter writer = new FileWriter(FILE_NAME, true);
            writer.write(t.toCSVLine() + "\n");
            writer.close();
        } catch (IOException ex) {
            System.out.println("Error saving transaction: " + ex.getMessage());
        }

    }

    // Adding deposit section here
    // ask user for deposit info and save it
    public static void depositScreen (Scanner userInput) {

        System.out.println("\n===== ADD DEPOSIT =====");

        // Get today's date and current time automatically
        String date = LocalDate.now().toString();                    // Example: "2024-01-15"
        String time = LocalTime.now().toString().substring(0, 8);    // Example: "10:13:25"

        // Ask the user to type in the description and vendor
        System.out.print("Enter description: ");
        String description = userInput.nextLine();

        System.out.print("Enter name of vendor: ");
        String vendor = userInput.nextLine();

        System.out.print("Enter deposit amount: ");
        double amount = Double.parseDouble(userInput.nextLine()); // Convert text to a number

        // Deposits are always positive, so make sure it's positive
        if (amount < 0) {
            amount = amount * -1; // Flip negative to positive
        }

        // Create the transaction and add it to the list and the file
        transactions t = new transactions(date, time, description, vendor, amount);
        transactions.add(t);
        saveTransactions(t);

        System.out.println("Deposit successfully added!");

    }

    // ask user for payment info and save it
    public static void paymentScreen (Scanner userInput) {
        System.out.println("\n===== MAKE PAYMENT =====");

        String date = LocalDate.now().toString();
        String time = LocalTime.now().toString().substring(0, 8);

        System.out.print("Enter description: ");
        String description = userInput.nextLine();

        System.out.print("Enter name of vendor: ");
        String vendor = userInput.nextLine();

        System.out.print("Enter deposit amount: ");
        double amount = Double.parseDouble(userInput.nextLine());

        // payments are negative
        if (amount < 0) {
            amount = amount * -1; // flips positive to a negative
        }

        transactions t = new transactions(date, time, description, vendor, amount);
        transactions.add(t);
        saveTransactions(t);

        System.out.println("Payment successfully added!");
    }

}
