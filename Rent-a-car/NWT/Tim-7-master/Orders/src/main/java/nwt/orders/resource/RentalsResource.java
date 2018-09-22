package nwt.orders.resource;

import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.omg.CosNaming.NamingContextPackage.NotFoundHelper;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javassist.NotFoundException;
import nwt.orders.OrdersApplication;
import nwt.orders.model.Rental;
import nwt.orders.repository.RentalsRepository;
import nwt.orders.service.RentalsService;

@RestController
@RequestMapping(value="/rest/rentals")
@CrossOrigin(origins = "http://185.91.158.33:3000")
public class RentalsResource {

	@Autowired 
	RentalsRepository rentalsRepository;
	
	@Autowired
	RentalsService rentalsService;
	
	@Autowired
	OrdersClient ordersClient;
	
	@Autowired
	RabbitTemplate rabbitTemplate;
	
	@GetMapping(value="/all")
	public List<Rental> getAll(){
		return rentalsRepository.findAll();
	}
	@GetMapping(value="/testAsync")
	public String getResponse(){
		rabbitTemplate.convertAndSend(OrdersApplication.topicExchangeName, "nwt.vehicles.reserve", 5);
		return "OK";

	}

	@GetMapping("/get/{id}")
	public Rental getRentalById(@PathVariable(value = "id") Long rentalId) throws NotFoundException {
	    return rentalsRepository.findById(rentalId).orElseThrow(() -> new NotFoundException("Rental with given id not found"));
	}
	
	@PutMapping("update/{id}")
	public Rental updateRental(@PathVariable(value = "id") Long rentalId,
	                                        @Valid @RequestBody Rental rentalUpdated) throws NotFoundException {
		
	   return rentalsService.updateRental(rentalId, rentalUpdated);
	}
	
	@DeleteMapping("delete/{id}")
	public ResponseEntity<?> deleteNote(@PathVariable(value = "id") Long rentalId) throws NotFoundException {
	    Rental rental = rentalsRepository.findById(rentalId)
	            .orElseThrow(() -> new NotFoundException("Rental with given id not found"));

	    rentalsRepository.delete(rental);

	    return ResponseEntity.ok().build();
	}
	
	@PostMapping(value="/insert")
	public Rental createRental(@Valid @RequestBody final Rental rental){
		
		return rentalsService.createRental(rental);
		//return rentalsRepository.findAll();		
	}
	
	@GetMapping("/before/{date}")
	public List<Rental> getRentalsBefore(@PathVariable(value = "date") String date) {
		@SuppressWarnings("deprecation")
		Date date1=new Date(date.replace('-','/'));
	    return rentalsRepository.findByDateRentedBefore(date1);
	}
	
	@GetMapping("/after/{date}")
	public List<Rental> getRentalsAfter(@PathVariable(value = "date") String date) {
		@SuppressWarnings("deprecation")
		Date date1=new Date(date.replace('-','/'));
	    return rentalsRepository.findByDateRentedAfter(date1);
	}
}
