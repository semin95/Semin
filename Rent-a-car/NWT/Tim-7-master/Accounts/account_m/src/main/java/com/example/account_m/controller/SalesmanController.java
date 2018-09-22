package com.example.account_m.controller;

import com.example.account_m.entity.Salesman;
import com.example.account_m.repository.SalesmanRepository;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/salesman")
@CrossOrigin(origins = "http://185.91.158.33:3000")
public class SalesmanController {


    @Autowired
    SalesmanRepository salesmanRepository;

    @GetMapping(value="/all")
    public List<Salesman> getAll(){
        return salesmanRepository.findAll();
    }

    @GetMapping("/get/{id}")
    public Salesman getSalesmanById(@PathVariable(value = "id") Long id) throws NotFoundException {
        return salesmanRepository.findById(id).orElseThrow(() -> new NotFoundException("Salesman with given id not found"));
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<?> deleteSalesman(@PathVariable(value = "id") Long id) throws NotFoundException {
        Salesman salesman = salesmanRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Salesman with given id not found"));

        salesmanRepository.delete(salesman);

        return ResponseEntity.ok().build();
    }

    @PutMapping("update/{id}")
    public Salesman updateSalesman(@PathVariable(value = "id") Long id,
                                               @RequestBody @Valid Salesman salesmanUpdated, Errors errors) throws NotFoundException, Exception {

        if(errors.hasErrors()){
            throw new Exception(errors.getAllErrors().get(0).getDefaultMessage());
        }

        Salesman salesman = salesmanRepository
                .findById(id)
                .orElseThrow(
                        () -> new NotFoundException("Salesman with given id not found")
                );

        salesman.setJMBG(salesmanUpdated.getJMBG());
        salesman.setBirthDate(salesmanUpdated.getBirthDate());
        salesman.setPay(salesmanUpdated.getPay());
        salesman.setRentACarOffice(salesmanUpdated.getRentACarOffice());


        salesmanUpdated = salesmanRepository.save(salesman);
        return salesmanUpdated ;
    }

    @PostMapping(value="/insert")
    public Salesman createSalesman(@RequestBody @Valid final Salesman salesman, Errors errors) throws Exception{

        if(errors.hasErrors()){
            throw new Exception(errors.getAllErrors().get(0).getDefaultMessage());
        }

        return salesmanRepository.save(salesman);
    }

}
