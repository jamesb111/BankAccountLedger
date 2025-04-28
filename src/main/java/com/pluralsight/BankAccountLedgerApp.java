package com.pluralsight;

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
        //boolean that will run loop
        boolean appRunning = true;

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

            } else if(userChoice.equalsIgnoreCase("p")) {

            } else if(userChoice.equalsIgnoreCase("l")) {

            } else if(userChoice.equalsIgnoreCase("x")) {
                appRunning = false;
            }
        }
    }
    static addDeposit(boolean value) {
        if(value) {

        } else {

        }
    }
    static makePayment(boolean value) {
        if(value) {

        } else {

        }
    }
    static LedgerView(boolean value) {
        if(value) {

        } else {

        }
    }
}
