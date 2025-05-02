package com.pluralsight;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Scanner;

public class BankAccountLedgerApp {
    //static scanner than can be used in main and in methods
    static Scanner scan = new Scanner(System.in);

    public static void main(String[] args) {
        // Asks if user wants to start app and takes answer as input
        System.out.println("Would you like to launch the Town Bank App? (Y/N)");
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

            // check if file exists if not will create and write to file
            boolean fileExists = Files.exists(Path.of("src/main/resources/transactions.csv"));

            if(!fileExists) {
                //file writer to write to file
                FileWriter writer = new FileWriter("src/main/resources/transactions.csv", true);
                BufferedWriter bufWriter = new BufferedWriter(writer);

                //writes the header for the file
                bufWriter.write("date|time|description|vendor|amount \n");
                //Dummy data used to ensure all features are working properly
                bufWriter.write("2024-08-20|14:35:20|Bought textbooks|University of Michigan|-210.80 \n");
                bufWriter.write("2024-09-10|10:10:10|Part-time Salary|Starbucks|700.00 \n");
                bufWriter.write("2024-10-22|18:20:40|Car Repair|AutoFix Center|-340.90 \n");
                bufWriter.write("2024-11-05|19:50:50|Sold Bicycle|Facebook Marketplace|180.00 \n");
                bufWriter.write("2024-12-24|08:30:30|Holiday Gift Shopping|Amazon|-215.67 \n");
                bufWriter.write("2025-01-10|13:13:13|New Year Bonus|Dunder Mifflin|500.00 \n");
                bufWriter.write("2025-02-14|20:00:00|Valentine's Dinner|Outback Steakhouse|-150.00 \n");
                bufWriter.write("2025-03-01|07:50:00|Monthly Subscription|Spotify|-9.99 \n");
                bufWriter.write("2025-03-25|12:12:12|Sold Artwork|Blue Art Gallery|350.00 \n");
                bufWriter.write("2025-04-28|15:16:35|Bought sandwich|Walmart|-10.00 \n");

                bufWriter.close();
            }


            //loops through home screen options
            while(appRunning) {
                System.out.println("─────────────────────────────────────Town Bank Accounting Home───────────────────────────────────");
                System.out.println("Welcome to the Ace Account Home Screen! Select a letter from our list of options \n" +
                        "D) Add Deposit \n" +
                        "P) Make Payment \n" +
                        "L) Ledger \n" +
                        "X) Exit");
                System.out.println("───────────────────────────────────────────────────────────────────────────────────────────────");
                System.out.println();
                System.out.print("Enter option here: ");


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
            FileWriter writer = new FileWriter("src/main/resources/transactions.csv" , true);
            BufferedWriter bufWriter = new BufferedWriter(writer);

            //saves input in variables
            System.out.print("Enter description: ");
            String description = scan.nextLine();
            System.out.print("Enter vender: ");
            String vendor = scan.nextLine();
            System.out.print("Enter amount: ");
            double amount = scan.nextDouble();
            scan.nextLine();


            bufWriter.write(date + "|" + time + "|" + description + "|" + vendor + "|" + amount + "\n");
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
            FileWriter writer = new FileWriter("src/main/resources/transactions.csv", true);
            BufferedWriter bufWriter = new BufferedWriter(writer);

            //saves input in variables
            System.out.print("Enter description: ");
            String description = scan.nextLine();
            System.out.print("Enter vender: ");
            String vendor = scan.nextLine();
            System.out.print("Enter amount: ");
            double amount = scan.nextDouble();
            scan.nextLine();

            bufWriter.write(date + "|" + time + "|" + description + "|" + vendor + "|" + "-" + amount + "\n");
            bufWriter.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    static void ledgerView() {
        try {
            //reads lines from file
            FileReader fileReader = new FileReader("src/main/resources/transactions.csv");
            BufferedReader bufReader = new BufferedReader(fileReader);
            //reads and throws away first line of file which is the header
            bufReader.readLine();

            //reads all file lines and adds them to list
            ArrayList<Transaction> entryList = new ArrayList<>();
            String input;
            while((input = bufReader.readLine()) != null) {
                String[] transacParts = input.split("\\|");
                Transaction newTransac = new Transaction(LocalDate.parse(transacParts[0]), LocalTime.parse(transacParts[1]), transacParts[2],
                        transacParts[3], Double.parseDouble(transacParts[4]));
                entryList.add(newTransac);
            }

            //loops through ledger options
            boolean onLedger = true;
            while(onLedger) {
                System.out.println("─────────────────────────────────────────Ledger View──────────────────────────────────────────");
                System.out.println("Welcome to the Ace Accounting Ledger where you can view account entries based on criteria. \n" +
                        "A) All \n" +
                        "D) Deposits \n" +
                        "P) Payments \n" +
                        "R) Reports \n" +
                        "H) Home");
                System.out.println("───────────────────────────────────────────────────────────────────────────────────────────────");
                System.out.print("Enter Option here:");

                String ledgerChoice = scan.nextLine();
                if(ledgerChoice.equalsIgnoreCase("a")) {
                    System.out.println("All ledger entries-------------------------------------------------");
                    //loops through list in reverse
                    for(int i = entryList.size() - 1; i >= 0; i--) {
                        Transaction t = entryList.get(i);
                        System.out.printf("%tF | %tT | %s | %s | %.2f \n", t.getDate(), t.getTime(), t.getDescription(), t.getVendor(), t.getAmount());
                    }
                    System.out.println("───────────────────────────────────────────────────────────────────────────────────────────────");
                    System.out.println();

                } else if(ledgerChoice.equalsIgnoreCase("d")) {
                    System.out.println("Deposit ledger entries-------------------------------------------------");
                    for(int i = entryList.size() - 1; i >= 0; i--) {
                        Transaction t = entryList.get(i);
                        // makes sure number is greater than zero
                        if(t.getAmount() > 0) {
                            System.out.printf("%tF | %tT | %s | %s | %.2f \n", t.getDate(), t.getTime(), t.getDescription(), t.getVendor(), t.getAmount());
                        }
                    }
                    System.out.println("───────────────────────────────────────────────────────────────────────────────────────────────");
                    System.out.println();

                } else if(ledgerChoice.equalsIgnoreCase("p")) {
                    System.out.println("Payment ledger entries-------------------------------------------------");
                    for(int i = entryList.size() - 1; i >= 0; i--) {
                        Transaction t = entryList.get(i);
                        // makes sure number is less than zero
                        if(t.getAmount() < 0) {
                            System.out.printf("%tF | %tT | %s | %s | %.2f \n", t.getDate(), t.getTime(), t.getDescription(), t.getVendor(), t.getAmount());
                        }
                    }
                    System.out.println("───────────────────────────────────────────────────────────────────────────────────────────────");
                    System.out.println();

                } else if(ledgerChoice.equalsIgnoreCase("r")) {
                    //get current time
                    LocalDate current = LocalDate.now();

                    //loops through report options
                    boolean onReports = true;
                    label:
                    while(onReports) {
                        System.out.println("─────────────────────────────────────────Reports Page─────────────────────────────────────────────");
                        System.out.println("Report page. Click one of the numbers to select one of our options. \n" +
                                "1) Month To Date \n" +
                                "2) Previous Month \n" +
                                "3) Year To Date \n" +
                                "4) Previous Year \n" +
                                "5) Search by Vendor \n" +
                                "0) Back");
                        System.out.println("───────────────────────────────────────────────────────────────────────────────────────────────");
                        System.out.println();

                        System.out.print("Enter Choice here: ");
                        String reportsChoice = scan.nextLine();
                        //Switch statement that'll run a reverse loop printing out lines from file that meet criteria
                        switch (reportsChoice) {
                            case "1": //month to date
                                System.out.println("Month to date--------------------------");
                                for (int i = entryList.size() - 1; i >= 0; i--) {
                                    Transaction t = entryList.get(i);
                                    //boolean that checks if entry is within the same month as the current month
                                    boolean monthToDate = t.getDate().getMonth().equals(LocalDate.now().getMonth());
                                    if (monthToDate) {
                                        System.out.printf("%tF | %tT | %s | %s | %.2f \n", t.getDate(), t.getTime(), t.getDescription(), t.getVendor(), t.getAmount());
                                    }
                                }
                                System.out.println("───────────────────────────────────────────────────────────────────────────────────────────────");
                                System.out.println();
                                break;
                            case "2": // previous month
                                System.out.println("Previous month--------------------------");
                                for (int i = entryList.size() - 1; i >= 0; i--) {
                                    Transaction t = entryList.get(i);
                                    //boolean that checks if entry's month is within the previous month
                                    boolean previousMonth = t.getDate().getMonth().equals(current.getMonth().minus(1)) && (t.getDate().getYear() == LocalDate.now().getYear());
                                    if (previousMonth) {
                                        System.out.printf("%tF | %tT | %s | %s | %.2f \n", t.getDate(), t.getTime(), t.getDescription(), t.getVendor(), t.getAmount());
                                    }
                                }
                                System.out.println("───────────────────────────────────────────────────────────────────────────────────────────────");
                                System.out.println();
                                break;
                            case "3": //year to date
                                System.out.println("Year to date--------------------------");
                                for (int i = entryList.size() - 1; i >= 0; i--) {
                                    Transaction t = entryList.get(i);
                                    // boolean checks if entry is within year of current date
                                    boolean yearToDate = t.getDate().getYear() == LocalDate.now().getYear();
                                    if (yearToDate) {
                                        System.out.printf("%tF | %tT | %s | %s | %.2f \n", t.getDate(), t.getTime(), t.getDescription(), t.getVendor(), t.getAmount());
                                    }
                                }
                                System.out.println("───────────────────────────────────────────────────────────────────────────────────────────────");
                                System.out.println();
                                break;
                            case "4": //previous year
                                System.out.println("Previous Year--------------------------");
                                for (int i = entryList.size() - 1; i >= 0; i--) {
                                    Transaction t = entryList.get(i);
                                    // boolean that checks if entry's year  is within year of previous year
                                    boolean previousYear = t.getDate().getYear() == LocalDate.now().getYear() - 1;
                                    if (previousYear) {
                                        System.out.printf("%tF | %tT | %s | %s | %.2f \n", t.getDate(), t.getTime(), t.getDescription(), t.getVendor(), t.getAmount());
                                    }
                                }
                                System.out.println("───────────────────────────────────────────────────────────────────────────────────────────────");
                                System.out.println();
                                break;
                            case "5": //search by vendor
                                System.out.println("Search by Vendor--------------------------");
                                System.out.println("Enter vendor name to search by vendor.");
                                String vendorName = scan.nextLine();
                                for (int i = entryList.size() - 1; i >= 0; i--) {
                                    Transaction t = entryList.get(i);
                                    // boolean that checks if vendor name is inside transaction.
                                    boolean vendorMatch = t.getVendor().toLowerCase().contains(vendorName.toLowerCase());
                                    if (vendorMatch) {
                                        System.out.printf("%tF | %tT | %s | %s | %.2f \n", t.getDate(), t.getTime(), t.getDescription(), t.getVendor(), t.getAmount());
                                    }
                                }
                                System.out.println("───────────────────────────────────────────────────────────────────────────────────────────────");
                                System.out.println();
                                break;
                            case "0": // go back to ledger screen
                                onReports = false;
                                break;
                        }
                    }

                } else if(ledgerChoice.equalsIgnoreCase("h")) {
                    onLedger = false;
                    break;
                }
            } bufReader.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}