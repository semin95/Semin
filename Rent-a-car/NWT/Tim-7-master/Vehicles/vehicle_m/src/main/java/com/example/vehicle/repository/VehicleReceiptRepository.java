package com.example.vehicle.repository;

import com.example.vehicle.model.VehicleReceipt;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VehicleReceiptRepository extends JpaRepository<VehicleReceipt,Integer> {
}
