package com.etf.ppis.lambda.telenash.repository;

import com.etf.ppis.lambda.telenash.model.Request;
import com.etf.ppis.lambda.telenash.model.StatisticsReport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RequestRepository extends JpaRepository<Request, Integer>
{
    Optional<Request> findByName(String name);

    @Query ("SELECT new com.etf.ppis.lambda.telenash.model.StatisticsReport(" +
            "   MONTH(request.date), COUNT(request.id), request.status.name) " +
            "FROM Request request " +
            "WHERE YEAR(request.date) = :year " +
            "   AND (:month IS NULL OR MONTH(request.date) = :month) " +
            "GROUP BY MONTH(request.date), request.status.name " +
            "ORDER BY MONTH(request.date)")
    List<StatisticsReport> getCountByMonthAndStatus(@Param ("year") Integer year,
                                                    @Param ("month") Integer month);
}