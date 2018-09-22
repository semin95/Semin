package com.example.account_m.repository;

import com.example.account_m.entity.VehicleDisposeOf;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VehicleDisposeOfRepository extends JpaRepository<VehicleDisposeOf, Long>{
}
