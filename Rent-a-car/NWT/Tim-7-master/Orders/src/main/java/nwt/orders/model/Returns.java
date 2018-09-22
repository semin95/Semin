package nwt.orders.model;

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
import javax.validation.constraints.Size;

import org.springframework.lang.Nullable;

import java.util.Date;

@Entity
public class Returns {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Long rental;
    private Date dateReturn;
    @Valid
    @NotBlank
    @Size(min=1, max=300)
    private String reason;

    public Long getId() {
        return id;
    }

    public Returns() {
    }


    public void setId(Long id) {

        this.id = id;
    }

    public Long getRental() {
		return rental;
	}

	public void setRental(Long rental) {
		this.rental = rental;
	}

	public Date getDateReturn() {
        return dateReturn;
    }

    public void setDateReturn(Date dateReturn) {
        this.dateReturn = dateReturn;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}