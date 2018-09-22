package nwt.orders.service;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javassist.NotFoundException;
import nwt.orders.DateValidator;
import nwt.orders.OrdersApplication;
import nwt.orders.model.Rental;
import nwt.orders.repository.RentalsRepository;
import nwt.orders.resource.OrdersClient;

@Service
public class RentalsService {
	
	@Autowired
	RentalsRepository rentalsRepository;

	@Autowired
	OrdersClient ordersClient;
	
	@Autowired
	RabbitTemplate rabbitTemplate;
	
	public Rental updateRental(Long rentalId, Rental rentalUpdated) throws NotFoundException {
		Rental rental = rentalsRepository
				.findById(rentalId)
				.orElseThrow(
						() -> new NotFoundException("Rental with given id not found")
						);
		
		try	{
			String status=ordersClient.doesClientExist(rentalUpdated.getClientId());
			if(status!="OK") throw new NotFoundException("Client with given id does not exist!");
		}
		catch(Exception ex) {
			System.out.println(ex.getMessage());
			
		}
		try	{
			String status=ordersClient.doesClientExist(rentalUpdated.getClientId());
			if(status!="OK") throw new NotFoundException("Vehicle with given id does not exist");
		}
		catch(Exception ex) {
			System.out.println(ex.getMessage());
			
		}
		
		DateValidator.validateDate(rentalUpdated.getDateRented());
		DateValidator.compareDates(rentalUpdated.getDateFrom(), rentalUpdated.getDateTo());
		rental.setClientId(rentalUpdated.getClientId());
		rental.setDateFrom(rentalUpdated.getDateFrom());
		rental.setDateRented(rentalUpdated.getDateRented());
		rental.setDateTo(rentalUpdated.getDateTo());
		rental.setVehicleId(rentalUpdated.getVehicleId());

		Rental updatedRental = rentalsRepository.save(rental);
		return updatedRental;
	}
	
	public Rental createRental(Rental rental) {
		try	{
			String status=ordersClient.doesClientExist(rental.getClientId());
			if(status!="OK") throw new NotFoundException("Client with given id does not exist!");
		}
		catch(Exception ex) {
			System.out.println(ex.getMessage());
			
		}
		try	{
			String status=ordersClient.doesClientExist(rental.getClientId());
			if(status!="OK") throw new NotFoundException("Vehicle with given id does not exist");
		}
		catch(Exception ex) {
			System.out.println(ex.getMessage());
			
		}
		//asinhrona komunikacija sa mikroservisom vehicles
		rabbitTemplate.convertAndSend(OrdersApplication.topicExchangeName, "nwt.vehicles.reserve", rental.getVehicleId());
		DateValidator.validateDate(rental.getDateRented());
		 DateValidator.compareDates(rental.getDateFrom(), rental.getDateTo());
		 return rentalsRepository.save(rental);
	}
}
