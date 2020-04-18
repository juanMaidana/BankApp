package org.example.controller.providers;

import org.example.controller.interfaces.IInfoProvider;
import org.example.model.Account;
import org.example.model.Transaction;
import org.example.model.interfaces.IDataSaver;

import java.util.List;

public class TransactionsProvider implements IInfoProvider<List<Transaction>, Integer> {
    private IDataSaver dataSaver;

    public TransactionsProvider(IDataSaver dataSaver) {
        this.dataSaver = dataSaver;
    }

    @Override
    public List<Transaction> get(Integer accountId) {
        Account account = dataSaver.getAccountById(accountId);
        return account.getTransactions();
    }
}
