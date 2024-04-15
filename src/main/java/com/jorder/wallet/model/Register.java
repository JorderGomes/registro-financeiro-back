package com.jorder.wallet.model;

import java.util.Calendar;
import java.util.GregorianCalendar;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Register {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String description;

    private float value;

    @Enumerated(EnumType.STRING)
    private Flux flux;

    private Calendar creationDate;
    
    private Calendar effectiveDate;

    private boolean effective;

    private String tag;

    private boolean recurrent;

    public void updateRecurrent(){
        this.setRecurrent(!this.isRecurrent());
    }

    public void updateEffectiveDate(GregorianCalendar effectiveDate){
        this.setEffectiveDate(effectiveDate);
        this.setEffective(true);
    }

}
