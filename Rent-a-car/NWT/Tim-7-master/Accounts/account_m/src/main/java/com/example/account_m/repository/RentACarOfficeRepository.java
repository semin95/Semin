package com.example.account_m.repository;


import com.example.account_m.entity.RentACarOffice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RentACarOfficeRepository extends JpaRepository<RentACarOffice, Long>{
}
