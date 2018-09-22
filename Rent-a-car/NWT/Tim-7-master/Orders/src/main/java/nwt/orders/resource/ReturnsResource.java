package nwt.orders.resource;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

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
import nwt.orders.model.Receipt;
import nwt.orders.model.Rental;
import nwt.orders.model.Returns;
import nwt.orders.model.Returns;
import nwt.orders.repository.ReturnsRepository;
import nwt.orders.service.ReturnsService;

@RestController
@RequestMapping(value="/rest/returns")
@CrossOrigin(origins = "http://185.91.158.33:3000")
public class ReturnsResource {

	@Autowired
	ReturnsRepository returnsRepository;
	
	@Autowired
	ReturnsService returnsService;
	
	@GetMapping(value="/all")
	public List<Returns> getAll(){
		return returnsRepository.findAll();
	}
	@GetMapping("/get/{id}")
	public Returns getReturnsById(@PathVariable(value = "id") Long returnsId) throws NotFoundException {
	    return returnsRepository.findById(returnsId).orElseThrow(() -> new NotFoundException("Returns with given id not found"));
	}
	@PutMapping("update/{id}")
	public Returns updateReturns(@PathVariable(value = "id") Long returnsId,
	                                        @Valid @RequestBody Returns returnsUpdated) throws NotFoundException {

	    return returnsService.updateReturn(returnsId, returnsUpdated);
	}
	
	@DeleteMapping("delete/{id}")
	public ResponseEntity<?> deleteNote(@PathVariable(value = "id") Long returnsId) throws NotFoundException {
	    Returns returns = returnsRepository.findById(returnsId)
	            .orElseThrow(() -> new NotFoundException("Returns with given id not found"));

	    returnsRepository.delete(returns);

	    return ResponseEntity.ok().build();
	}
	@PostMapping(value="/insert")
	public Returns createReturns(@Valid @RequestBody final Returns returns){
		return returnsService.createReturn(returns);
		//return returnsRepository.findAll();		
	}
	@GetMapping("/after/{date}")
	public List<Returns> getReturnsBefore(@PathVariable(value = "date") String date) {
		@SuppressWarnings("deprecation")
		Date date1=new Date(date.replace('-','/'));
	    return returnsRepository.findByDateReturnBefore(date1);
	}
	@GetMapping(value="/rental/{id}")
	public Optional<Returns>  receiptWithRentalId(@PathVariable("id") Long rental){
		return returnsRepository.findByRental(rental);
	}
}
