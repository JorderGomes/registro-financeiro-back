package com.jorder.wallet.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.jorder.wallet.model.Transaction;
import com.jorder.wallet.service.TransactionService;

@RestController
@RequestMapping("/transactions")
public class TransactionController {

    @Autowired
    TransactionService TransactionService;

    @GetMapping
    public List<Transaction> getTransactions() {
        return TransactionService.getTransactions();
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Transaction> getTransaction(@PathVariable Long id) {
        Transaction Transaction = TransactionService.getTransactionById(id);
        if (Transaction == null)
            ResponseEntity.notFound().build();
        return ResponseEntity.ok(Transaction);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Transaction> postTransaction(@RequestBody Transaction Transaction) {
        return ResponseEntity.ok(TransactionService.createTransaction(Transaction));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteTransaction(@PathVariable Long id) {
        return TransactionService.deleteTransaction(id);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Transaction> updateTransaction(
            @RequestBody Transaction Transaction,
            @PathVariable Long id) {
        Transaction TransactionEdited = TransactionService.editTransaction(id, Transaction);
        if (TransactionEdited == null)
            ResponseEntity.notFound().build();
        return ResponseEntity.ok(TransactionEdited);
    }

}
