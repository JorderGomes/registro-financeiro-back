package com.jorder.wallet.controller;

import java.util.GregorianCalendar;
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

import com.jorder.wallet.model.Register;
import com.jorder.wallet.service.RegisterService;

@RestController
@RequestMapping("/registers")
public class RegisterController {

    @Autowired
    RegisterService registerService;

    @GetMapping
    public List<Register> getRegisters() {
        return registerService.getRegisters();
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Register> getRegister(@PathVariable Long id) {
        Register register = registerService.getRegisterById(id);
        if (register == null)
            ResponseEntity.notFound().build();
        return ResponseEntity.ok(register);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Register> postRegister(@RequestBody Register register) {
        return ResponseEntity.ok(registerService.createRegister(register));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteRegister(@PathVariable Long id) {
        return registerService.deleteRegister(id);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Register> updateRegister(
            @RequestBody Register register,
            @PathVariable Long id) {
        Register registerEdited = registerService.editRegister(id, register);
        if (registerEdited == null)
            ResponseEntity.notFound().build();
        return ResponseEntity.ok(registerEdited);
    }

    @PutMapping("/update-effective-date/{id}")
    public ResponseEntity<String> updateEffectiveDate(@PathVariable Long id, @RequestBody GregorianCalendar effectiveDate) {
        boolean result = registerService.updateEffectiveDate(id, effectiveDate); 
        if (result) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        } 
    }

}
