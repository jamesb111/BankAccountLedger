package com.pluralsight;

import java.io.BufferedWriter;
import java.io.FileWriter;
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

            //file writer to write to file
            FileWriter writer = new FileWriter("src/main/resources/transacpract.csv");
            BufferedWriter bufWriter = new BufferedWriter(writer);

            //writes the header for the file
            bufWriter.write("date|time|description|vendor|amount");

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

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    static void makePayment() {
        try {

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
