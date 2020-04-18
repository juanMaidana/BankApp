package org.example.view.console;

import org.example.controller.BankAccountController;
import org.example.controller.ControllerManager;
import org.example.controller.OwnerController;
import org.example.model.Account;
import org.example.model.Transaction;
import org.example.model.TransactionType;

import java.util.List;
import java.util.Scanner;

public class ConsoleApp {
    private BankAccountController bankAccountController;
    private OwnerController ownerController;

    public static void main(String[] args) {
        new ConsoleApp().run();
    }

    public ConsoleApp() {
        ControllerManager controllerManager = new ControllerManager();
        bankAccountController = controllerManager.getAccountController();
        ownerController = controllerManager.getOwnerController();
    }

    public void run() {
        String ownerName = "jperez";

        // TODO Change this to use DTOs
        List<Account> accounts = ownerController.getAccountsByOwner(ownerName);

        // TODO Add InfoProvider to replace this logic
        Account account = accounts.get(0);

        printWelcomeMessage(ownerName, account.getId());
        showMainMenu();
        selectOption(account.getId());
    }

    private static void print(String message) {
        System.out.println(message);
    }

    private void printWelcomeMessage(String name, int accountId) {
        // Welcome to the BankApp
        print("Welcome " + name);
        print("Your current account is " + accountId);
    }

    private void showMainMenu() {
        // Main Menu
        print(System.lineSeparator());
        print("********************");
        print("(W) Withdraw");
        print("(D) Deposit");
        print("(B) My Balance");
        print("(T) My Transactions");
        print("(E) Exit");
        print("********************");
    }

    private void selectOption(int accountId) {
        // Press a key to select an option
        try (Scanner scanner = new Scanner(System.in)){
            boolean closeApp = false;
            char option;
            while (true) {
                System.out.print("Select an option: ");
                option = getSelectedOption(scanner);
                switch (option) {
                    case 'A':
                        showMainMenu();
                        break;
                    case 'E':
                        print("Exit...");
                        closeApp = true;
                        break;
                    case 'B':
                        print("Your Balance is: " + (myBalance(accountId)));
                        break;
                    case 'W':
                        withdraw(scanner, accountId);
                        break;
                    case 'D':
                        deposit(scanner, accountId);
                        break;
                    case 'T':
                        myTransactions(accountId);
                        break;
                    default:
                        print(option + " is an invalid option");
                        break;
                }

                // Is running
                if (closeApp) {
                    break;
                }

                print("(A) Show All options");
            }
        }
    }

    private char getSelectedOption(Scanner scanner) {
        return scanner.next().trim().toUpperCase().charAt(0);
    }

    private void withdraw(Scanner scanner, int accountId) {
        System.out.print("Enter amount you need to withdraw: ");
        double amount = scanner.nextDouble();
        bankAccountController.withdraw(accountId, amount);
        System.out.println("successful withdraw operation");
    }

    private double myBalance(int accountId){
        return bankAccountController.getMyBalance(accountId);
    }

    private void deposit(Scanner scanner, int accountId){
        System.out.print("Enter amount you want to deposit: ");
        double amount = scanner.nextDouble();
        bankAccountController.deposit(accountId, amount);
        System.out.println("successful deposit operation");
    }

    private void myTransactions(int accountId){
        List<Transaction> transactions = bankAccountController.getMyTransactions(accountId);
        print("***********************************************");
        for (Transaction i : transactions) {
            print("-------------------------------------");
            print("Transaction id: "+ String.valueOf(i.getId()));
            print("Account id: "+ String.valueOf(i.getAccountId()));
            print("Amount: "+ String.valueOf(i.getAmount()));
            print("Transaction date: "+ String.valueOf(i.getDateTime()));
            print("Transaction type: "+ String.valueOf(i.getType()));
            print("-------------------------------------");
        }
        print("***********************************************");
    }
}
