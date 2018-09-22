package nwt.orders.model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.lang.Nullable;
import org.w3c.dom.ranges.RangeException;

@EntityScan
@Entity
public class Receipt {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;
    private Long rental;
    private Long transactionNumber;
    @Positive
    private double price;
    private double discount;
    private Date dateCreated;

    public Receipt(){

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getTransactionNumber() {
        return transactionNumber;
    }

    public void setTransactionNumber(Long transactionNumber) {
        this.transactionNumber = transactionNumber;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
    	if(discount<0 || discount>1) throw new ExceptionInInitializerError("Argument is out of range");
        this.discount = discount;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

	public Long getRental() {
		return rental;
	}

	public void setRental(Long rental) {
		this.rental = rental;
	}
}