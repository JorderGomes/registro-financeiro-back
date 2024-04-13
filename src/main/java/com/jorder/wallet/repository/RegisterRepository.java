package com.jorder.wallet.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jorder.wallet.model.Register;

public interface RegisterRepository extends JpaRepository<Register, Long> {
    
}
