package com.jorder.wallet.model;

import java.util.Calendar;
// import java.util.GregorianCalendar;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "transactions")
public class Transaction {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String description;

    private float value;

    @Enumerated(EnumType.STRING)
    private Flux flux;

    private Calendar creationDate;

    private String tag;

}
