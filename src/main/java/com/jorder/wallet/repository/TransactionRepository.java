package com.jorder.wallet.repository;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.jorder.wallet.model.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    
    @Query(value = "select COALESCE(sum(value), 0) FROM transactions WHERE strftime('%m', datetime(creation_date / 1000, 'unixepoch')) = :month ;", nativeQuery = true)
    public float getMonthlyGeneralBalance(String month);

    @Query(value = "select COALESCE(sum(value), 0) FROM transactions WHERE strftime('%m', datetime(creation_date / 1000, 'unixepoch')) = :month and flux = 'GASTO';", nativeQuery = true)
    public float getMonthlySpentBalance(String month);

    @Query(value = "select COALESCE(sum(value), 0) FROM transactions WHERE strftime('%m', datetime(creation_date / 1000, 'unixepoch')) = :month and flux = 'RENDA';", nativeQuery = true)
    public float getMonthlyIncomeBalance(String month);

    @Query(value = "select description, COALESCE(sum(value), 0) as spent from transactions WHERE flux = 'GASTO' group by tag order by spent DESC;", nativeQuery = true)
    public List<Map<String, Float>> getCostsByTag();
}
