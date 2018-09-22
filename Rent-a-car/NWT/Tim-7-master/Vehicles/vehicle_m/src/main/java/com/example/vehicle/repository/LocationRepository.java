package com.example.vehicle.repository;

import com.example.vehicle.model.Location;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LocationRepository  extends JpaRepository<Location,Integer> {
}
