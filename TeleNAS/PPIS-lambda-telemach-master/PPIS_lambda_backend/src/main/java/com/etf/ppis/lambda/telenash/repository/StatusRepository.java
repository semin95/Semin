package com.etf.ppis.lambda.telenash.repository;

import com.etf.ppis.lambda.telenash.model.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StatusRepository extends JpaRepository<Status, Integer>
{
}