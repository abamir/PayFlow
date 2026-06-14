package com.airtribe.patyflow.service;

import com.airtribe.patyflow.entity.Transaction;
import org.springframework.stereotype.Service;

import java.util.List;


public interface TransactionService {

    public Transaction sendMoney(Transaction transaction);


    public List<Transaction> getAllTransactions();
}
