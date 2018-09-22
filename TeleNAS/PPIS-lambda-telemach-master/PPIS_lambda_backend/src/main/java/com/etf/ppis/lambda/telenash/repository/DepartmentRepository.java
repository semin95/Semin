package com.etf.ppis.lambda.telenash.repository;

import com.etf.ppis.lambda.telenash.model.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Integer>
{
    Optional<Department> findByName(String name);
}