package com.example.vehicle.resource;

import com.example.vehicle.model.VehicleReceipt;
import com.example.vehicle.repository.VehicleReceiptRepository;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/rest/vehiclereceipt")
@CrossOrigin(origins = "http://185.91.158.33:3000")
public class VehicleReceiptResource {

    @Autowired
    VehicleReceiptRepository vehicleReceiptRepository;

    @GetMapping(value = "/all")
    public List<VehicleReceipt> getAll()
    {
        return vehicleReceiptRepository.findAll();
    }

    @GetMapping("/get/{id}")
    public VehicleReceipt getVehicleReceiptById(@PathVariable(value = "id") Integer id) throws NotFoundException
    {
        return vehicleReceiptRepository.findById(id).orElseThrow(()->new NotFoundException("Receipt with given ID not found!"));
    }

    @PutMapping("update/{id}")
    public VehicleReceipt updateVehicleReceipt(@PathVariable(value = "id") Integer id, @Valid @RequestBody VehicleReceipt receiptUpdated) throws NotFoundException {
        VehicleReceipt receipt = vehicleReceiptRepository.findById(id).orElseThrow(()->new NotFoundException("Receipt with given ID not found!"));

        receipt.setCostPerDay(receiptUpdated.getCostPerDay());
        receipt.setDeposit(receiptUpdated.getDeposit());
        receipt.setVehicleID(receiptUpdated.getVehicleID());

        VehicleReceipt updatedReceipt = vehicleReceiptRepository.save(receipt);
        return updatedReceipt;
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<?> deleteNote (@PathVariable(value = "id") Integer id) throws NotFoundException
    {
        VehicleReceipt receipt = vehicleReceiptRepository.findById(id).orElseThrow(()->new NotFoundException("Receipt with given ID not found!"));

        vehicleReceiptRepository.delete(receipt);
        return ResponseEntity.ok().build();
    }


    @PostMapping(value = "/insert")
    public VehicleReceipt createReceipt (@Valid @RequestBody final VehicleReceipt vehicleReceipt)
    {
        return vehicleReceiptRepository.save(vehicleReceipt);
    }
}
