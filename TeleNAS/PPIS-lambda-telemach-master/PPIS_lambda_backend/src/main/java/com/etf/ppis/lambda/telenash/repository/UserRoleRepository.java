package com.etf.ppis.lambda.telenash.repository;

import com.etf.ppis.lambda.telenash.model.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRoleRepository extends JpaRepository<UserRole, Integer>
{
    Optional<UserRole> findByName(String name);
}