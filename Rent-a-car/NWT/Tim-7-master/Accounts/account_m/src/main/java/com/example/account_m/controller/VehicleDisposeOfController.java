package com.example.account_m.controller;


import com.example.account_m.entity.VehicleDisposeOf;
import com.example.account_m.repository.VehicleDisposeOfRepository;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/vehicledisposeof")
@CrossOrigin(origins = "http://185.91.158.33:3000")
public class VehicleDisposeOfController {


    @Autowired
    VehicleDisposeOfRepository vehicleDisposeOfRepository;

    @GetMapping(value="/all")
    public List<VehicleDisposeOf> getAll(){
        return vehicleDisposeOfRepository.findAll();
    }

    @GetMapping("/get/{id}")
    public VehicleDisposeOf getVehicleDisposeOfById(@PathVariable(value = "id") Long id) throws NotFoundException {
        return vehicleDisposeOfRepository.findById(id).orElseThrow(() -> new NotFoundException("VehicleDisposeOf with given id not found"));
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<?> deleteVehicleDisposeOf(@PathVariable(value = "id") Long id) throws NotFoundException {
        VehicleDisposeOf vehicleDisposeOf = vehicleDisposeOfRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("VehicleDisposeOf with given id not found"));

        vehicleDisposeOfRepository.delete(vehicleDisposeOf);

        return ResponseEntity.ok().build();
    }

    @PutMapping("update/{id}")
    public VehicleDisposeOf updateVehicleDisposeOf(@PathVariable(value = "id") Long id,
                                               @RequestBody @Valid VehicleDisposeOf vehicleDisposeOfUpdated, Errors errors) throws NotFoundException, Exception {

        if(errors.hasErrors()){
            throw new Exception(errors.getAllErrors().get(0).getDefaultMessage());
        }

        VehicleDisposeOf vehicleDisposeOf = vehicleDisposeOfRepository
                .findById(id)
                .orElseThrow(
                        () -> new NotFoundException("VehicleDisposeOf with given id not found")
                );

        vehicleDisposeOf.setVehicleID(vehicleDisposeOfUpdated.getVehicleID());
        vehicleDisposeOf.setReason(vehicleDisposeOfUpdated.getReason());
        vehicleDisposeOf.setSalesman(vehicleDisposeOfUpdated.getSalesman());
        vehicleDisposeOf.setRentACarOffice(vehicleDisposeOfUpdated.getRentACarOffice());


        vehicleDisposeOfUpdated = vehicleDisposeOfRepository.save(vehicleDisposeOf);
        return vehicleDisposeOfUpdated;
    }

    @PostMapping(value="/insert")
    public VehicleDisposeOf createVehicleDisposeOf(@RequestBody @Valid final VehicleDisposeOf vehicleDisposeOf, Errors errors) throws Exception{

        if(errors.hasErrors()){
            throw new Exception(errors.getAllErrors().get(0).getDefaultMessage());
        }

        return vehicleDisposeOfRepository.save(vehicleDisposeOf);
    }

}
