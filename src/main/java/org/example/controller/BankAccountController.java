package org.example.controller;

import org.example.controller.interfaces.IAccountOperator;
import org.example.controller.interfaces.IInfoProvider;
import org.example.model.Account;
import org.example.model.Transaction;

import java.util.List;

public class BankAccountController {
    private IAccountOperator withdrawOperator;
    private IAccountOperator depositOperator;

    private IInfoProvider<Double, Integer> balanceProvider;
    private IInfoProvider<List<Transaction>, Integer> transactionsProvider;

    public BankAccountController(IAccountOperator withdrawOperator,IAccountOperator depositOperator, IInfoProvider<Double, Integer> balanceProvider, IInfoProvider<List<Transaction>, Integer> transactionsProvider) {
        this.withdrawOperator = withdrawOperator;
        this.depositOperator = depositOperator;
        this.balanceProvider = balanceProvider;
        this.transactionsProvider = transactionsProvider;
    }

    public void withdraw(int accountId, double amount) {
        if(getMyBalance(accountId) > amount){
            this.withdrawOperator.execute(accountId, amount);
        }else{
            System.out.println("Insufficient balance, the operation was not performed");
        }
    }
    public void deposit(int accountId, double amount) {
        this.depositOperator.execute(accountId, amount);
    }
    public double getMyBalance(int accountId){
        return balanceProvider.get(accountId);
    }
    public List<Transaction> getMyTransactions(int accountId){
        return transactionsProvider.get(accountId);
    }
}
