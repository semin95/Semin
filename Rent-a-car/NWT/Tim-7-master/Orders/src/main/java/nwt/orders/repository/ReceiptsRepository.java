package nwt.orders.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import nwt.orders.model.Receipt;

public interface ReceiptsRepository extends JpaRepository<Receipt, Long> {
	Optional<Receipt> findById(Long id);
	List<Receipt> findByPriceLessThan(Double price);
	Optional<Receipt> findByRental(Long rental);
}
