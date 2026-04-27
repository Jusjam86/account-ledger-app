package com.pluralsight;

import java.time.*;
import java.util.*;
import java.io.*;

public class Main {

    static Scanner userInput = new Scanner(System.in);

    static void main() {

        displayHomeScreen();

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

//            switch (choice) {
//
//                case "D":
//                    ();
//                    break;
//                case "P":
//                    ();
//                    break;
//                case "L";
//
//                    break;
//                case "X":
//                    System.out.println("Exiting, come back anytime!");
//                    // return exits the loop and the method; in this case, this ends the application
//                    return;
//                default:
//                    System.out.println("Invalid Choice, Try again");
//            }
        }


    }
}
