package com.jorder.wallet.repository;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.jorder.wallet.model.Register;

public interface RegisterRepository extends JpaRepository<Register, Long> {
    
    @Query(value = "select COALESCE(sum(value), 0) FROM register WHERE strftime('%m', datetime(effective_date / 1000, 'unixepoch')) = :month and effective = 1;", nativeQuery = true)
    public float getMonthlyGeneralBalance(String month);

    @Query(value = "select COALESCE(sum(value), 0) FROM register WHERE strftime('%m', datetime(effective_date / 1000, 'unixepoch')) = :month and flux = 'GASTO' and effective = 1;", nativeQuery = true)
    public float getMonthlySpentBalance(String month);

    @Query(value = "select COALESCE(sum(value), 0) FROM register WHERE strftime('%m', datetime(effective_date / 1000, 'unixepoch')) = :month and flux = 'RENDA' and effective = 1;", nativeQuery = true)
    public float getMonthlyIncomeBalance(String month);

    @Query(value = "select description, COALESCE(sum(value), 0) as spent from register WHERE flux = 'GASTO' group by tag order by spent ASC;", nativeQuery = true)
    public List<Map<String, Float>> getCostsByTag();
}
