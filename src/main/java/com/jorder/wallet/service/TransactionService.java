package com.jorder.wallet.service;

import java.util.Calendar;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.jorder.wallet.model.Flux;
import com.jorder.wallet.model.Transaction;
import com.jorder.wallet.repository.TransactionRepository;

@Service
public class TransactionService {
    @Autowired
    private TransactionRepository TransactionRepository;

    public List<Transaction> getTransactions() {
        return TransactionRepository.findAll();
    }

    public Transaction getTransactionById(Long id) {
        Optional<Transaction> TransactionOpt = TransactionRepository.findById(id);
        if (TransactionOpt.isPresent())
            return TransactionOpt.get();
        return null;
    }

    public Transaction createTransaction(Transaction Transaction) {
        Transaction.setCreationDate(Calendar.getInstance());
        Transaction = this.setValueSign(Transaction);
        return TransactionRepository.save(Transaction);
    }

    public Transaction editTransaction(Long id, Transaction Transaction) {
        if (!TransactionRepository.existsById(id)) {
            return null;
        }
        Transaction.setId(id);
        Transaction = this.setValueSign(Transaction);
        Transaction = TransactionRepository.save(Transaction);
        return Transaction;
    }


    public ResponseEntity<Void> deleteTransaction(Long id) {
        if (!TransactionRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        TransactionRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    public Transaction setValueSign(Transaction Transaction){
        if (Transaction.getFlux().equals(Flux.GASTO)) {
            Transaction.setValue(Transaction.getValue() * -1);
        }
        return Transaction;
    }

}
