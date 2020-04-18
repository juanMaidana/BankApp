package org.example.controller.providers;

import org.example.controller.interfaces.IInfoProvider;
import org.example.model.Account;
import org.example.model.Transaction;
import org.example.model.TransactionType;
import org.example.model.interfaces.IDataSaver;

import java.util.List;

public class AccountBalanceProvider implements IInfoProvider<Double, Integer>{
    private IDataSaver dataSaver;

    public AccountBalanceProvider(IDataSaver dataSaver) {
        this.dataSaver = dataSaver;
    }

    @Override
    public Double get(Integer accountId){
        double balance = 0.0;
        Account account = dataSaver.getAccountById(accountId);
        List<Transaction> transactions = account.getTransactions();
        for (Transaction i : transactions) {
            if(i.getType() == TransactionType.WITHDRAW){
                balance = balance - i.getAmount();
            }else if(i.getType() == TransactionType.DEPOSIT){
                balance = balance + i.getAmount();
            }
        }
        return balance;
    }
}
