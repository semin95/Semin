package com.example.vehicle.Service;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import javassist.NotFoundException;
import com.example.vehicle.model.VehicleReceipt;
import com.example.vehicle.repository.VehicleReceiptRepository;


@Service
public class VehicleReceiptService {

    @Autowired
    VehicleReceiptRepository vehicleReceiptRepository;

    public VehicleReceipt updateReceipt(Integer receiptId, VehicleReceipt receiptUpdated) throws NotFoundException {
        VehicleReceipt receipt = vehicleReceiptRepository.findById(receiptId).orElseThrow(() -> new NotFoundException("Receipt with given id not found"));

        receipt.setDeposit(receiptUpdated.getDeposit());
        receipt.setCostPerDay(receiptUpdated.getCostPerDay());
        receipt.setVehicleID(receiptUpdated.getVehicleID());

        VehicleReceipt updatedReceipt = vehicleReceiptRepository.save(receipt);
        return updatedReceipt;
    }

    public VehicleReceipt createReceipt (VehicleReceipt vehicleReceipt)
    {
        return vehicleReceiptRepository.save(vehicleReceipt);
    }

}
