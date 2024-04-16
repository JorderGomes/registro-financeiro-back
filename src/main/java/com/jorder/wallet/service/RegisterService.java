package com.jorder.wallet.service;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.jorder.wallet.model.Flux;
import com.jorder.wallet.model.Register;
import com.jorder.wallet.repository.RegisterRepository;

@Service
public class RegisterService {
    @Autowired
    private RegisterRepository registerRepository;

    public List<Register> getRegisters() {
        return registerRepository.findAll();
    }

    public Register getRegisterById(Long id) {
        Optional<Register> registerOpt = registerRepository.findById(id);
        if (registerOpt.isPresent())
            return registerOpt.get();
        return null;
    }

    public Register createRegister(Register register) {
        register.setCreationDate(Calendar.getInstance());
        register.setEffective(false);
        register = this.setValueSign(register);
        return registerRepository.save(register);
    }

    public Register editRegister(Long id, Register register) {
        if (!registerRepository.existsById(id)) {
            return null;
        }
        register.setId(id);
        register = this.setValueSign(register);
        register = registerRepository.save(register);
        return register;
    }


    public ResponseEntity<Void> deleteRegister(Long id) {
        if (!registerRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        registerRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    public boolean updateEffectiveDate(Long id, GregorianCalendar effectiveDate) {
        if (!registerRepository.existsById(id)) {
            return false;
        }
        Register register = registerRepository.findById(id).get();
        register.updateEffectiveDate(effectiveDate);
        registerRepository.save(register);
        return true;
    }

    public Register setValueSign(Register register){
        if (register.getFlux().equals(Flux.GASTO)) {
            register.setValue(register.getValue() * -1);
        }
        return register;
    }

}
