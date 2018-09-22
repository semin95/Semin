package nwt.orders.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javassist.NotFoundException;
import nwt.orders.DateValidator;
import nwt.orders.model.Returns;
import nwt.orders.repository.ReturnsRepository;

@Service
public class ReturnsService {
	
	@Autowired
	ReturnsRepository returnsRepository;
	
	public Returns updateReturn(Long returnsId, Returns returnsUpdated) throws NotFoundException {
		Returns returns = returnsRepository
	    		.findById(returnsId)
	    		.orElseThrow(
	    				() -> new NotFoundException("Returns with given id not found")
	    				);
	    DateValidator.validateDate(returnsUpdated.getDateReturn());
	    returns.setDateReturn(returnsUpdated.getDateReturn());
	    returns.setReason(returnsUpdated.getReason());
	    returns.setRental(returnsUpdated.getRental());

	    Returns updatedReturns = returnsRepository.save(returns);
	    return updatedReturns;
	}
	public Returns createReturn(Returns returns) {
		DateValidator.validateDate(returns.getDateReturn());
		 return returnsRepository.save(returns);
	}
}
