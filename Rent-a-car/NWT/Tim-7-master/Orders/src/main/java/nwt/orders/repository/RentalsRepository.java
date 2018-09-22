package nwt.orders.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import nwt.orders.model.Rental;


public interface RentalsRepository extends JpaRepository<Rental, Long> {
	List<Rental> findByDateRentedBefore(Date date);
	List<Rental> findByDateRentedAfter(Date date);
}