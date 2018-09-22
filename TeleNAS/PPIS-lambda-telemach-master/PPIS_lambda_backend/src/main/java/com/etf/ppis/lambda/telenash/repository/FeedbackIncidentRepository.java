package com.etf.ppis.lambda.telenash.repository;

import com.etf.ppis.lambda.telenash.model.FeedbackIncident;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FeedbackIncidentRepository extends JpaRepository<FeedbackIncident, Integer>
{

}