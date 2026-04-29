package com.pluralsight;

public class transactions {

    // variables
    private String date;
    private String time;
    private String description;
    private String vendor;
    private double amount;

    // Constructor
    public transactions(String date, String time, String description, String vendor, double amount) {
        this.date = date;
        this.time = time;
        this.description = description;
        this.vendor = vendor;
        this.amount = amount;

    }
    // Getters and Setters
    public String getDate() {
        return date;
    }
    public void setDate(String date) {
        this.date = date;
    }
    public String getTime() {
        return time;
    }
    public void setTime(String time) {
        this.time = time;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public String getVendor() {
        return vendor;
    }
    public void setVendor(String vendor) {
        this.vendor = vendor;
    }
    public double getAmount() {
        return amount;
    }
    public void setAmount(double amount) {
        this.amount = amount;
    }

    // Method that converts the transaction into a single line of text for the csv file
    public String toCSVLine() {
        return date + "|" + time + "|" + description + "|" + vendor + "|" + amount;
    }
    // Method that organizes the transaction in a readable format on screen
    public void printTransaction() {
        System.out.println(date + " " + time + " | " + description + " | " + vendor + " | $" + amount);
    }
}
