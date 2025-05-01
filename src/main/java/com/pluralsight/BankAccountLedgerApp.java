package com.pluralsight;

import com.pluralsight.Transaction;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.YearMonth;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
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
            bufWriter.write(date + "|" + time + "|" + inputLine[0] + "|" + inputLine[1] + "|" + inputLine[2] + "\n");
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
            //reads lines from file
            FileReader fileReader = new FileReader("src/main/resources/transacpract.csv");
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
                System.out.println("Welcome to the Ace Accounting Ledger where you can view account entries based on criteria. \n" +
                        "A) All \n" +
                        "D) Deposits \n" +
                        "P) Payments \n" +
                        "R) Reports \n" +
                        "H) Home");
                String ledgerChoice = scan.nextLine();
                if(ledgerChoice.equalsIgnoreCase("a")) {
                    //loops through list in reverse
                    for(int i = entryList.size() - 1; i >= 0; i--) {
                        Transaction t = entryList.get(i);
                        System.out.printf("%tF | %tT | %s | %s | %.2f \n", t.getDate(), t.getTime(), t.getDescription(), t.getVendor(), t.getAmount());
                    }

                } else if(ledgerChoice.equalsIgnoreCase("d")) {
                    for(int i = entryList.size() - 1; i >= 0; i--) {
                        Transaction t = entryList.get(i);
                        // makes sure number is greater than zero
                        if(t.getAmount() > 0) {
                            System.out.printf("%tF | %tT | %s | %s | %.2f \n", t.getDate(), t.getTime(), t.getDescription(), t.getVendor(), t.getAmount());
                        }
                    }
                } else if(ledgerChoice.equalsIgnoreCase("p")) {
                    for(int i = entryList.size() - 1; i >= 0; i--) {
                        Transaction t = entryList.get(i);
                        // makes sure number is less than zero
                        if(t.getAmount() < 0) {
                            System.out.printf("%tF | %tT | %s | %s | %.2f \n", t.getDate(), t.getTime(), t.getDescription(), t.getVendor(), t.getAmount());
                        }
                    }
                } else if(ledgerChoice.equalsIgnoreCase("r")) {
                    //get current time
                    LocalDate current = LocalDate.now();

                    //get first of month
                    LocalDate firstOfMonth = current.withDayOfMonth(1);

                    // year month class to use atEndOfMonth method in loop later to get previous month last day
                    YearMonth monthForMethod = YearMonth.of(firstOfMonth.getYear(), firstOfMonth.getMonth());

                    //Temporal adjusters that will get first day of year and last day of year
                    LocalDate firstDayOfYear = current.with(TemporalAdjusters.firstDayOfYear());
                    LocalDate lastDayOfYear = current.with(TemporalAdjusters.lastDayOfYear());

                    //loops through report options
                    boolean onReports = true;
                    label:
                    while(onReports) {
                        System.out.println("Report page. Click one of the numbers to select one of our options. \n" +
                                "1) Month To Date \n" +
                                "2) Previous Month \n" +
                                "3) Year To Date \n" +
                                "4) Previous Year \n" +
                                "5) Search by Vendor \n" +
                                "0) Back");
                        String reportsChoice = scan.nextLine();
                        switch (reportsChoice) {
                            case "1": //month to date
                                System.out.println("Month to date--------------------------");
                                for (int i = entryList.size() - 1; i >= 0; i--) {
                                    Transaction t = entryList.get(i);
                                    //boolean that checks if entry is within first day of month till current time
                                    boolean monthToDate = (t.getDate().isAfter(firstOfMonth) || t.getDate().isEqual(firstOfMonth))  && (t.getDate().isBefore(LocalDate.now()) || t.getDate().isEqual(LocalDate.now()) );
                                    if (monthToDate) {
                                        System.out.printf("%tF | %tT | %s | %s | %.2f \n", t.getDate(), t.getTime(), t.getDescription(), t.getVendor(), t.getAmount());
                                    }
                                }
                                break;
                            case "2": // previous month
                                System.out.println("Previous month--------------------------");
                                for (int i = entryList.size() - 1; i >= 0; i--) {
                                    Transaction t = entryList.get(i);
                                    //boolean that checks if entry is within first and last day of previous month
                                    boolean previousMonth = (t.getDate().isAfter(firstOfMonth.minusMonths(1)) || t.getDate().isEqual(firstOfMonth.minusMonths(1))) && (t.getDate().isBefore(monthForMethod.atEndOfMonth().minusMonths(1) ) || t.getDate().isEqual(monthForMethod.atEndOfMonth().minusMonths(1)));
                                    if (previousMonth) {
                                        System.out.printf("%tF | %tT | %s | %s | %.2f \n", t.getDate(), t.getTime(), t.getDescription(), t.getVendor(), t.getAmount());
                                    }
                                }
                                break;
                            case "3": //year to date
                                System.out.println("Year to date--------------------------");
                                for (int i = entryList.size() - 1; i >= 0; i--) {
                                    Transaction t = entryList.get(i);
                                    // boolean checks if entry is within year to current time
                                    boolean yearToDate = (t.getDate().isAfter(firstDayOfYear) || t.getDate().isEqual(firstDayOfYear)) && (t.getDate().isBefore(LocalDate.now()) || t.getDate().isEqual(LocalDate.now()));
                                    if (yearToDate) {
                                        System.out.printf("%tF | %tT | %s | %s | %.2f \n", t.getDate(), t.getTime(), t.getDescription(), t.getVendor(), t.getAmount());
                                    }
                                }
                                break;
                            case "4": //previous year
                                System.out.println("Previous month--------------------------");
                                for (int i = entryList.size() - 1; i >= 0; i--) {
                                    Transaction t = entryList.get(i);
                                    // boolean that checks if entry is within first and last day of previous year
                                    boolean previousYear = (t.getDate().isAfter(firstDayOfYear.minusYears(1)) || t.getDate().isEqual(firstDayOfYear.minusYears(1)))&& (t.getDate().isBefore(lastDayOfYear.minusYears(1)) || t.getDate().isEqual(lastDayOfYear.minusYears(1)));
                                    if (t.getAmount() < 0) {
                                        System.out.printf("%tF | %tT | %s | %s | %.2f \n", t.getDate(), t.getTime(), t.getDescription(), t.getVendor(), t.getAmount());
                                    }
                                }
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