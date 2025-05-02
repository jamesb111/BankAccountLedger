# 🏦 Town Bank Account Ledger App

Welcome to the **Town Bank App** - A bank account ledger written in Java. This application allows users to manage deposits and payments, view transactions in a ledger format, and generate financial reports directly from a CSV file.

## 📂 Project Structure

- **BankAccountLedgerApp.java** – The main driving force behind the application. Handles user interaction, transaction logic, date/time logic, and report generating.
- **Transaction.java** – A data class representing a single financial transaction.

## ✅ Features

### 🧾 App Home
<img width="707" alt="Image" src="https://github.com/user-attachments/assets/52bf1844-f01b-4b1a-8cab-d4ea59361d78" />

- **Add Deposit** – Record an income transaction (e.g., salary, bonus).
  <img width="553" alt="Image" src="https://github.com/user-attachments/assets/bcc57df3-81f9-4106-9f5c-e2d5d293343f" />
- **Make Payment** – Record an expense (e.g., purchases, subscriptions).
  <img width="553" alt="Image" src="https://github.com/user-attachments/assets/cfd8004c-e413-4e89-935a-1ee102ef2231" />
- **View Ledger** – Display transactons based on critera and run reports.
  <img width="600" alt="Image" src="https://github.com/user-attachments/assets/6431f747-6531-4f6d-8861-cad4a1f60c0f" />

### 📊 Ledger Menu
- **All Transactions** - Display all transactions stored in a CSV file.
  <img width="600" alt="Image" src="https://github.com/user-attachments/assets/2938178e-4257-4387-a814-770f30c0eecf" />
- **Deposits** - Display deposit entries stored in a CSV file.
  <img width="600" alt="Image" src="https://github.com/user-attachments/assets/4f551cfc-bde9-413f-9195-7ec85b46e900" />
- **Payments/Expenses** - Display payments stored in a CSV file.
  <img width="600" alt="Image" src="https://github.com/user-attachments/assets/592e3cb7-8f2e-4a24-8b49-1dba72ba5379" />
  
### 📁 Ledger Menu Reports
<img width="707" alt="Image" src="https://github.com/user-attachments/assets/090db8b7-8c2a-4ff9-a7cc-c3fa676610ec" />

- **Month-to-Date** - Displays transactions within the current month
  <img width="700" alt="Image" src="https://github.com/user-attachments/assets/3ceecb04-0d64-4fe2-a859-e6a6dac463c9" />
- **Previous Month** - Displays transactions within the previous month
  <img width="638" alt="Image" src="https://github.com/user-attachments/assets/35eec492-733a-4637-a0c9-7c861c171cec" />
- **Year-to-Date** - Displays transactions within the current year
  <img width="716" alt="Image" src="https://github.com/user-attachments/assets/382ce250-5872-49a4-aa94-e80c93bf5e90" />
- **Previous Year** - Displays transactions for the previous year
  <img width="676" alt="Image" src="https://github.com/user-attachments/assets/c1b20bdf-4b0b-4933-b8ec-990800a1fd7f" />
- **Search by Vendor** - Displays transactions belonging to a specific vendor
  <img width="641" alt="Image" src="https://github.com/user-attachments/assets/24da3f87-4a40-4bf5-a765-5e0f0c1d06dd" />


### 🕒 Timestamps
- Every transaction is automatically timestamped with the current date and time.

## 💻 Interesting piece of code

1. I find this code interessting due to it's usefulness. It reads each line from my csv file creating a new Transaction object that'll get added to a Transaction arrayList. I can then loop through this Transaction list and access the properties of the object for conditional logic that prints out specfic transactions based on criteria. 
   ```java
   //reads all file lines and adds them to list
            ArrayList<Transaction> entryList = new ArrayList<>();
            String input;
            while((input = bufReader.readLine()) != null) {
                String[] transacParts = input.split("\\|");
                Transaction newTransac = new Transaction(LocalDate.parse(transacParts[0]), LocalTime.parse(transacParts[1]), transacParts[2],
                        transacParts[3], Double.parseDouble(transacParts[4]));
                entryList.add(newTransac);
            }
