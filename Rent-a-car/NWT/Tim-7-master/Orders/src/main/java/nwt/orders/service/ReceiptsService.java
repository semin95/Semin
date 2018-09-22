package nwt.orders.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javassist.NotFoundException;
import nwt.orders.DateValidator;
import nwt.orders.model.Receipt;
import nwt.orders.repository.ReceiptsRepository;

@Service
public class ReceiptsService {

	@Autowired
	ReceiptsRepository receiptsRepository;
	
	public Receipt updateReceipt(Long receiptId, Receipt receiptUpdated) throws NotFoundException {
		 Receipt receipt = receiptsRepository
		    		.findById(receiptId)
		    		.orElseThrow(
		    				() -> new NotFoundException("Receipt with given id not found")
		    				);
		    DateValidator.validateDate(receiptUpdated.getDateCreated());
		    receipt.setDateCreated(receiptUpdated.getDateCreated());
		    receipt.setDiscount(receiptUpdated.getDiscount());
		    receipt.setPrice(receiptUpdated.getPrice());
		    receipt.setRental(receiptUpdated.getRental());
		    receipt.setTransactionNumber(receiptUpdated.getTransactionNumber());
		    
		    Receipt updatedReceipt = receiptsRepository.save(receipt);
		    return updatedReceipt;
	}
	public Receipt createReceipt(Receipt receipt) {
		DateValidator.validateDate(receipt.getDateCreated());
		 return receiptsRepository.save(receipt);
	}
	
	
}
