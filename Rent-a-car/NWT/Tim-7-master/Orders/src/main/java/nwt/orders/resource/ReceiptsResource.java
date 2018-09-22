package nwt.orders.resource;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

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
import nwt.orders.model.Receipt;
import nwt.orders.repository.ReceiptsRepository;
import nwt.orders.service.ReceiptsService;

@RestController
@RequestMapping(value="/rest/receipts")
@CrossOrigin(origins = "http://185.91.158.33:3000")
public class ReceiptsResource {
	
	@Autowired
	ReceiptsRepository receiptsRepository;
	
	@Autowired
	ReceiptsService receiptsService;
	
	@Autowired
	RabbitTemplate rabbitTemplate;
	
	@GetMapping(value="/all")
	public List<Receipt> getAll(){
		return receiptsRepository.findAll();
	}
	
	@GetMapping("/get/{id}")
	public Receipt getReceiptById(@PathVariable(value = "id") Long receiptId) throws NotFoundException {
	    return receiptsRepository.findById(receiptId).orElseThrow(() -> new NotFoundException("Receipt with given id not found"));
	}
	
//	@GetMapping("/get/{id}")
//	public String getReceiptById(@PathVariable(value = "id") Long receiptId) throws NotFoundException {
//		System.out.println("Sending message...");
//        rabbitTemplate.convertAndSend(OrdersApplication.topicExchangeName, "nwt.vehicles", receiptId.toString());
//        return "Rabbit!";
//	}
	
	
	@PutMapping("update/{id}")
	public Receipt updateReceipt(@PathVariable(value = "id") Long receiptId,
	                                        @Valid @RequestBody Receipt receiptUpdated) throws NotFoundException {

	   
	    return receiptsService.updateReceipt(receiptId, receiptUpdated);
	}
	
	@DeleteMapping("delete/{id}")
	public ResponseEntity<?> deleteNote(@PathVariable(value = "id") Long receiptId) throws NotFoundException {
	    Receipt Receipt = receiptsRepository.findById(receiptId)
	            .orElseThrow(() -> new NotFoundException("Receipt with given id not found"));

	    receiptsRepository.delete(Receipt);

	    return ResponseEntity.ok().build();
	}
	@PostMapping(value="/insert")
	public Receipt createReceipt(@Valid @RequestBody final Receipt receipt){
		return receiptsService.createReceipt(receipt);
		//return receiptsRepository.findAll();		
	}
	@GetMapping(value="/price/{price}")
	public List<Receipt> receiptsWithPriceLessThan(@PathVariable("price") Double price){
		return receiptsRepository.findByPriceLessThan(price);
	}
	@GetMapping(value="/rental/{id}")
	public Optional<Receipt>  receiptWithRentalId(@PathVariable("id") Long rental){
		return receiptsRepository.findByRental(rental);
	}
	
	
}
