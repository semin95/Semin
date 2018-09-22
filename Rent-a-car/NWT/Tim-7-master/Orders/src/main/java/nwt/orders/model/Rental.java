package nwt.orders.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.springframework.lang.Nullable;

import java.util.Date;


@Entity
public class Rental {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;
    @NotNull
    private Long vehicleId;
    @NotNull
    private Long clientId;
    private Date dateRented;
    private Date dateFrom;
    private Date dateTo;
    
    public Rental() {

    }
    public Rental(Long vehicleId, Long clientId, Date dateRented, Date dateFrom, Date dateTo) {
        this.vehicleId = vehicleId;
        this.clientId = clientId;
        this.dateRented = dateRented;
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
    }

    public Long getId() {
        return id;
    }

    public Long getVehicleId() {
        return vehicleId;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setVehicleId(Long vehicleId) {
        this.vehicleId = vehicleId;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }

    public void setDateRented(Date dateRented) {
        this.dateRented = dateRented;
    }

    public void setDateFrom(Date dateFrom) {
        this.dateFrom = dateFrom;
    }

    public void setDateTo(Date dateTo) {
        this.dateTo = dateTo;
    }

    public Long getClientId() {
        return clientId;
    }

    public Date getDateRented() {
        return dateRented;
    }

    public Date getDateFrom() {
        return dateFrom;
    }

    public Date getDateTo() {
        return dateTo;
    }

    @Override
    public String toString() {
        return "Rental{" +
                "id=" + id +
                ", vehicleId=" + vehicleId +
                ", clientId=" + clientId +
                ", dateRented=" + dateRented +
                ", dateFrom=" + dateFrom +
                ", dateTo=" + dateTo +
                '}';
    }
}