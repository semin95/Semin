package com.example.account_m.controller;


import com.example.account_m.entity.RentACarOffice;
import com.example.account_m.repository.RentACarOfficeRepository;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/rentacaroffice")
@CrossOrigin(origins = "http://185.91.158.33:3000")
public class RentACarOfficeController {


    @Autowired
    RentACarOfficeRepository rentACarOfficeRepository;

    @GetMapping(value="/all")
    public List<RentACarOffice> getAll(){
        return rentACarOfficeRepository.findAll();
    }

    @GetMapping("/get/{id}")
    public RentACarOffice getRentACarOfficeById(@PathVariable(value = "id") Long id) throws NotFoundException {
        return rentACarOfficeRepository.findById(id).orElseThrow(() -> new NotFoundException("Rent-a-car office with given id not found"));
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<?> deleteRentACarOffice(@PathVariable(value = "id") Long id) throws NotFoundException {
        RentACarOffice rentACarOffice = rentACarOfficeRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Rent-a-car office with given id not found"));

        rentACarOfficeRepository.delete(rentACarOffice);

        return ResponseEntity.ok().build();
    }


    @PutMapping("update/{id}")
    public RentACarOffice updateRentACarOffice(@PathVariable(value = "id") Long id,
                                 @RequestBody @Valid RentACarOffice rentACarOfficeUpdated, Errors errors) throws NotFoundException, Exception {

        if(errors.hasErrors()){
            throw new Exception(errors.getAllErrors().get(0).getDefaultMessage());
        }

        RentACarOffice rentACarOffice = rentACarOfficeRepository
                .findById(id)
                .orElseThrow(
                        () -> new NotFoundException("Rent-a-car office with given id not found")
                );

        rentACarOffice.setOfficeName(rentACarOfficeUpdated.getOfficeName());
        rentACarOffice.setCarsNumber(rentACarOfficeUpdated.getCarsNumber());
        rentACarOffice.setLocationID(rentACarOfficeUpdated.getLocationID());


        RentACarOffice rentACarOfficeUpdate = rentACarOfficeRepository.save(rentACarOffice);
        return rentACarOfficeUpdated;
    }

    @PostMapping(value="/insert")
    public RentACarOffice createRentACarOffice(@RequestBody @Valid final RentACarOffice client, Errors errors) throws Exception{

        if(errors.hasErrors()){
            throw new Exception(errors.getAllErrors().get(0).getDefaultMessage());
        }

        return rentACarOfficeRepository.save(client);
    }


}
