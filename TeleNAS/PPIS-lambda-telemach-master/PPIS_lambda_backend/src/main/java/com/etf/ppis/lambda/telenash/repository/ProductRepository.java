package com.etf.ppis.lambda.telenash.repository;

import com.etf.ppis.lambda.telenash.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer>
{
    Optional<Product> findByName(String name);
}