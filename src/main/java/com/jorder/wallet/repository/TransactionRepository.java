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

    @Query(value = "select description as key, COALESCE(sum(value), 0) as value from transactions WHERE flux = 'GASTO' group by tag order by value DESC;", nativeQuery = true)
    public List<Map<String, Float>> getCostsByTag(); // key, value

    @Query(value = "select strftime('%Y-%m', datetime(creation_date / 1000, 'unixepoch')) AS key, SUM(CASE WHEN flux = 'GASTO' THEN value ELSE 0 END) AS value from transactions GROUP BY strftime('%Y-%m', datetime(creation_date / 1000, 'unixepoch'));", nativeQuery = true)
    public List<Map<String, Float>> getCostsByMonths(); // key, value

    @Query(value = "SELECT * from  transactions where flux = 'GASTO' and strftime('%Y-%m', datetime(creation_date / 1000, 'unixepoch')) = :year_month ORDER BY value;", nativeQuery = true)
    public List<Transaction> getCostsExpensives(String year_month);

    @Query(value = "SELECT SUM(CASE WHEN value < 0 THEN -value ELSE value END) AS total, SUM(CASE WHEN value > 0 THEN value ELSE 0 END) AS percentIncome, SUM(CASE WHEN value < 0 THEN value ELSE 0 END) AS percentCosts FROM  transactions where strftime('%Y-%m', datetime(creation_date / 1000, 'unixepoch')) = :year_month ;", nativeQuery = true)
    public Object[][] getPercentualIncomeCosts(String year_month);
}
