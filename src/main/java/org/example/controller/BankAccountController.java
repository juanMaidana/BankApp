package org.example.controller;

import org.example.controller.interfaces.IAccountOperator;
import org.example.controller.interfaces.IInfoProvider;
import org.example.model.Account;

import java.util.List;

public class BankAccountController {
    private IAccountOperator withdrawOperator;

    private IInfoProvider<Double, Integer> balanceProvider;

    public BankAccountController(IAccountOperator withdrawOperator, IInfoProvider<Double, Integer> balanceProvider) {
        this.withdrawOperator = withdrawOperator;
        this.balanceProvider = balanceProvider;
    }

    public void withdraw(int accountId, double amount) {
        if(getMyBalance(accountId) > amount){
            this.withdrawOperator.execute(accountId, amount);
        }else{
            System.out.println("Insufficient balance, the operation was not performed");
        }


    }
    public double getMyBalance(int accountId){
        return balanceProvider.get(accountId);
    }
}
