package com.etf.ppis.lambda.telenash.repository;

import com.etf.ppis.lambda.telenash.model.Incident;
import com.etf.ppis.lambda.telenash.model.StatisticsReport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IncidentRepository extends JpaRepository<Incident, Integer>
{
    Optional<Incident> findByName(String name);

    @Query("SELECT new com.etf.ppis.lambda.telenash.model.StatisticsReport(" +
            "   MONTH(incident.date), COUNT(incident.id), incident.status.name) " +
            "FROM Incident incident " +
            "WHERE YEAR(incident.date) = :year " +
            "   AND (:month IS NULL OR MONTH(incident.date) = :month) " +
            "GROUP BY MONTH(incident.date), incident.status.name " +
            "ORDER BY MONTH(incident.date)")
    List<StatisticsReport> getCountByMonthAndStatus(@Param ("year") Integer year,
                                                    @Param ("month") Integer month);
}