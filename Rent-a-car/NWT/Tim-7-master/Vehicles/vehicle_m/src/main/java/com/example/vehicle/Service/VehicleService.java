package com.example.vehicle.Service;

import com.example.vehicle.VehicleApplication;
import com.example.vehicle.model.Vehicle;
import com.example.vehicle.repository.VehicleRepository;
import com.example.vehicle.resource.VehiclesClient;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
public class VehicleService {

    @Autowired
    VehicleRepository vehicleRepository;

    @Autowired
    VehiclesClient vehiclesClient;

    @Autowired
    RabbitTemplate rabbitTemplate;

    public Vehicle updatVehicle (int id, Vehicle vehicleUpdated) throws NotFoundException {
        Vehicle vehicle = vehicleRepository.findById(id).orElseThrow(() -> new NotFoundException("Rental with given id not found"));
        try	{
            String status=vehiclesClient.doesClientExist(vehicleUpdated.getId());
            if(status!="OK") throw new NotFoundException("Client with given id does not exist!");
        }
        catch(Exception ex) {
            System.out.println(ex.getMessage());

        }
        try	{
            String status=vehiclesClient.doesClientExist(vehicleUpdated.getId());
            if(status!="OK") throw new NotFoundException("Vehicle with given id does not exist");
        }
        catch(Exception ex) {
            System.out.println(ex.getMessage());

        }

        vehicle.setAvailable(vehicleUpdated.isAvailable());
        vehicle.setBrand(vehicleUpdated.getBrand());
        vehicle.setColor(vehicleUpdated.getColor());
        vehicle.setLocationID(vehicleUpdated.getLocationID());
        vehicle.setName(vehicleUpdated.getName());
        vehicle.setProducedYear(vehicle.getProducedYear());
        vehicle.setType(vehicle.getType());
        vehicle.setTransmission(vehicle.getTransmission());

        Vehicle updatedVehicle=vehicleRepository.save(vehicle);
        return updatedVehicle;
    }

    public Vehicle createVehicle(Vehicle vehicle)
    {
        try	{
            String status=vehiclesClient.doesClientExist(vehicle.getId());
            if(status!="OK") throw new NotFoundException("Client with given id does not exist!");
        }
        catch(Exception ex) {
            System.out.println(ex.getMessage());

        }
        try	{
            String status=vehiclesClient.doesClientExist(vehicle.getId());
            if(status!="OK") throw new NotFoundException("Vehicle with given id does not exist");
        }
        catch(Exception ex) {
            System.out.println(ex.getMessage());

        }
        //asinhrona komunikacija sa mikroservisom orders
        rabbitTemplate.convertAndSend(VehicleApplication.topicExchangeName, "com.example.vehicles.reserve", vehicle.getId());
        return vehicleRepository.save(vehicle);
    }

}
