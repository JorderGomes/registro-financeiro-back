package com.jorder.wallet.repository;

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
}
