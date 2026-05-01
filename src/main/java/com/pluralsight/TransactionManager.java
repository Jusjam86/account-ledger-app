package com.pluralsight;

// This class will everything related to reading and writing transactions
// I'm storing the deposits and payments here as well until I deem another class is required for it

import java.io.*;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.time.*;
import java.util.Scanner;

// suggested by IntelliJ to add this and it works
import static com.pluralsight.Main.displayHomeScreen;

public class TransactionManager {

    // the file where all transactions will be stored
    static String FILE_NAME = "transactions.csv";

    // arraylist to hold many
    static ArrayList<transactions> transactions = new ArrayList<>();

    // loads all transactions from csv file
    static void loadTransactions() {

        // create file object pointing to csv file
        File file = new File(FILE_NAME);

        // if the file doesn't exist yet, there's nothing to load
        if (!file.exists()) {
            System.out.println();
            System.out.println("No existing transaction file found.");
            System.out.println();
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

    // method for date
    public static String getDateInput(Scanner userInput) {

        String date;

        while (true) {

            System.out.print("Enter date (yyyy-MM-dd): ");

            try {
                String input = userInput.nextLine();
                LocalDate.parse(input);

                date = input;  // when input is valid, save it
                break;

            } catch (Exception e) {

                System.out.println();
                System.out.println("Invalid date format. Please use yyyy-MM-dd");
                System.out.println();
            }
        }

        return date;
    }

    // note: adding deposit section here
    // ---------------------------------- ask user for deposit info and save it ----------------------------------------
    public static void depositScreen (Scanner userInput) {

        System.out.println("\n===== ADD DEPOSIT =====");

        String date = getDateInput(userInput);
        String time = LocalTime.now().toString().substring(0, 8);

        System.out.print("Enter description: ");
        String description = userInput.nextLine();

        System.out.print("Enter vendor: ");
        String vendor = userInput.nextLine();

        while (true) {

            System.out.print("Enter amount: ");

            try {
                double amount = Double.parseDouble(userInput.nextLine());

                if (amount < 0) {
                    amount = amount * -1; // flips negative to positive
                }

                transactions t = new transactions(date, time, description, vendor, amount);
                transactions.add(t);
                saveTransactions(t);

                System.out.println();
                System.out.println("Deposit successfully added");
                displayHomeScreen();
                break;

            } catch (NumberFormatException e) {
                System.out.println("Invalid amount. Please enter a number like 24 or 17.38");
            }
        }
    }

    // note: add payment section here
    // ---------------------------------- ask user for payment info and save it ----------------------------------------
    public static void paymentScreen (Scanner userInput) {

        System.out.println("\n===== MAKE PAYMENT =====");

        String date = getDateInput(userInput);
        String time = LocalTime.now().toString().substring(0, 8);

        System.out.print("Enter description: ");
        String description = userInput.nextLine();

        System.out.print("Enter vendor: ");
        String vendor = userInput.nextLine();

        while (true) {

            System.out.print("Enter amount: ");

            try {
                double amount = Double.parseDouble(userInput.nextLine());

                if (amount > 0) {
                    amount = amount * -1; // flips positive to negative
                }

                transactions t = new transactions(date, time, description, vendor, amount);
                transactions.add(t);
                saveTransactions(t);

                System.out.println();
                System.out.println("Payment successfully recorded");
                displayHomeScreen();
                break;

            } catch (NumberFormatException e) {
                System.out.println("Invalid amount. Please enter a number like 24 or 17.38");

            }
        }
    }

}
