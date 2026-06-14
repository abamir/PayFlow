package com.airtribe.patyflow.service.serviceImpl;

import com.airtribe.patyflow.entity.Transaction;
import com.airtribe.patyflow.repository.TransactionRepository;
import com.airtribe.patyflow.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    TransactionRepository transactionRepository;

    @Override
    public Transaction sendMoney(Transaction transaction) {


        return transactionRepository.save(transaction);

    }



    @Override
    public List<Transaction> getAllTransactions() {
        return List.of();
    }
}
