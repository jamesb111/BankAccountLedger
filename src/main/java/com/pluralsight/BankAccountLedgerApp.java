package com.pluralsight;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.Scanner;

public class BankAccountLedgerApp {
    //static scanner than can be used in main and in methods
    static Scanner scan = new Scanner(System.in);

    public static void main(String[] args) {
        //scans user inputs

        System.out.println("Would you like to launch the Ace Accounting App? (Y/N)");
        String userStartApp = scan.nextLine();

        //Checks if user wants to start app or not
        if(userStartApp.equalsIgnoreCase("y")) {
            appHome();
        }
    }
    static void appHome() {
        try {
            //boolean that will run loop
            boolean appRunning = true;

            boolean fileExists = Files.exists(Path.of("src/main/resources/transacpract.csv"));

            //writes the header for the file
            if(!fileExists) {
                //file writer to write to file
                FileWriter writer = new FileWriter("src/main/resources/transacpract.csv", true);
                BufferedWriter bufWriter = new BufferedWriter(writer);

                //Dummy data used to ensure all features are working properly
                bufWriter.write("date|time|description|vendor|amount \n");
                bufWriter.write("2024-08-20|14:35:20|Bought textbooks|University Store|-210.80 \n");
                bufWriter.write("2024-09-10|10:10:10|Part-time Salary|Coffee Shop|700.00 \n");
                bufWriter.write("2024-10-22|18:20:40|Car Repair|AutoFix Center|-340.90 \n");
                bufWriter.write("2024-11-05|19:50:50|Sold Bicycle|Facebook Marketplace|180.00 \n");
                bufWriter.write("2024-12-24|08:30:30|Holiday Gift Shopping|Amazon|-215.67 \n");
                bufWriter.write("2025-01-10|13:13:13|New Year Bonus|Company XYZ|500.00 \n");
                bufWriter.write("2025-02-14|20:00:00|Valentine's Dinner|Fancy Steakhouse|-150.00 \n");
                bufWriter.write("2025-03-01|07:50:00|Monthly Subscription|Spotify|-9.99 \n");
                bufWriter.write("2025-03-25|12:12:12|Sold Artwork|Local Gallery|350.00 \n");
                bufWriter.write("2025-04-28|15:16:35|Bought sandwich|Walmart|-10.00 \n");

                bufWriter.close();
            }

            //loops through home screen options
            while(appRunning) {
                System.out.println("Welcome to the Ace Account Home Screen! Select a letter from our list of options \n" +
                        "D) Add Deposit \n" +
                        "P) Make Payment (Debit) \n" +
                        "L) Ledger \n" +
                        "X) Exit");
                String userChoice = scan.nextLine();
                //checks which options chosen and calls a method or ends loop based on it
                if(userChoice.equalsIgnoreCase("d")) {
                    addDeposit();
                    continue;
                } else if(userChoice.equalsIgnoreCase("p")) {
                    makePayment();
                    continue;
                } else if(userChoice.equalsIgnoreCase("l")) {
                    ledgerView();
                    continue;
                } else if(userChoice.equalsIgnoreCase("x")) {
                    appRunning = false;
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    static void addDeposit() {
        try {
            //gets current date and time. Time is shortened to not have nanoseconds using truncated to method
            LocalDate date = LocalDate.now();
            LocalTime time = LocalTime.now().truncatedTo(ChronoUnit.SECONDS);
            //file writer to write to file
            FileWriter writer = new FileWriter("src/main/resources/transacpract.csv" , true);
            BufferedWriter bufWriter = new BufferedWriter(writer);

            //gets user input and splits it to add it to the file
            System.out.println("To make deposit please enter the description, the vendor, and the amount separated by commas. ex:(pay bill,Amazon,20) ");
            String input = scan.nextLine();
            String[] inputLine = input.split(",");
            bufWriter.write(date + "|" + time + "|" + inputLine[0] + "|" + inputLine[1] + "|" + String.format("%.2f", Double.parseDouble(inputLine[2])) + "\n");
            bufWriter.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    static void makePayment() {
        try {
            //gets current date and time. Time is shortened to not have nanoseconds using truncated to method
            LocalDate date = LocalDate.now();
            LocalTime time = LocalTime.now().truncatedTo(ChronoUnit.SECONDS);
            //file writer to write to file
            FileWriter writer = new FileWriter("src/main/resources/transacpract.csv", true);
            BufferedWriter bufWriter = new BufferedWriter(writer);

            //gets user input and splits it to add it to the file
            System.out.println("To make payment please enter the description, the vendor, and the amount separated by commas. ex:(pay bill,Amazon,20.00) ");
            String input = scan.nextLine();
            String[] inputLine = input.split(",");
            bufWriter.write(date + "|" + time + "|" + inputLine[0] + "|" + inputLine[1] + "|" + "-" + inputLine[2] + "\n");
            bufWriter.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    static void ledgerView() {
        try {
            boolean onLedger = true;
            //loops through ledger options
            while(onLedger) {
                System.out.println("Welcome to the Ace Accounting Ledger where you can view accounts entries based on criteria. \n" +
                        "A) All \n" +
                        "D) Deposits \n" +
                        "P) Payments \n" +
                        "R) Reports \n" +
                        "H) Home");
                String ledgerChoice = scan.nextLine();
                if(ledgerChoice.equalsIgnoreCase("a")) {

                } else if(ledgerChoice.equalsIgnoreCase("d")) {

                } else if(ledgerChoice.equalsIgnoreCase("p")) {

                } else if(ledgerChoice.equalsIgnoreCase("r")) {
                    //loops through report options
                    boolean onReports = true;
                    while(onReports) {
                        System.out.println("Report page. Click one of the numbers to select one of our options. \n" +
                                "1) Month To Date \n" +
                                "2) Previous Month \n" +
                                "3) Year To Date \n" +
                                "4) Previous Year \n" +
                                "5) Search by Vendor \n" +
                                "0) Back");
                        String reportsChoice = scan.nextLine();
                        if(reportsChoice.equals("1")) {

                        } else if(reportsChoice.equals("2")) {

                        } else if(reportsChoice.equals("3")) {

                        } else if(reportsChoice.equals("4")) {

                        } else if(reportsChoice.equals("5")) {

                        } else if(reportsChoice.equals("0")) {
                            break;
                        }
                    }

                } else if(ledgerChoice.equalsIgnoreCase("h")) {
                    onLedger = false;
                    break;
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
