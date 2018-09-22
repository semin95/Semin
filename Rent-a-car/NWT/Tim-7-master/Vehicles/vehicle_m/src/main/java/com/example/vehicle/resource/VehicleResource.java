package com.example.vehicle.resource;

import com.example.vehicle.Service.VehicleService;
import com.example.vehicle.model.Vehicle;
import com.example.vehicle.repository.VehicleRepository;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/rest/vehicle")
@CrossOrigin(origins = "http://185.91.158.33:3000")
public class VehicleResource {

    @Autowired
    VehicleRepository vehicleRepository;

    @Autowired
    VehicleService vehicleService;

    @GetMapping(value = "/all")
    public List<Vehicle> getAll() {
        return vehicleRepository.findAll();
    }

    @GetMapping("/get/{id}")
    public Vehicle getVehicleById(@PathVariable(value = "id") Integer id) throws NotFoundException
    {
        return vehicleRepository.findById(id).orElseThrow(()-> new NotFoundException("Vehicle with given ID not found!"));
    }

    @PutMapping("/update/{id]")
    public Vehicle updateVehicle(@PathVariable(value = "id") Integer id, @Valid @RequestBody Vehicle vehicleUpdated) throws NotFoundException
    {
        Vehicle vehicle=vehicleRepository.findById(id).orElseThrow(()-> new NotFoundException("Vehicle with given ID not found!"));

        vehicle.setBrand(vehicleUpdated.getBrand());
        vehicle.setColor(vehicleUpdated.getColor());
        vehicle.setLocationID(vehicleUpdated.getLocationID());
        vehicle.setName(vehicleUpdated.getName());
        vehicle.setProducedYear(vehicleUpdated.getProducedYear());
        vehicle.setTransmission(vehicleUpdated.getTransmission());
        vehicle.setType(vehicleUpdated.getType());
        vehicle.setAvailable(vehicleUpdated.isAvailable());

        Vehicle updatedVehicle = vehicleRepository.save(vehicle);
        return  updatedVehicle;
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<?> deleteNote(@PathVariable(value = "id") Integer id) throws NotFoundException
    {
        Vehicle vehicle=vehicleRepository.findById(id).orElseThrow(()-> new NotFoundException("Vehicle with given ID not found!"));

        vehicleRepository.delete(vehicle);
        return ResponseEntity.ok().build();
    }

    @PostMapping(value="/insert")
    public Vehicle createVehicle (@Valid @RequestBody final Vehicle vehicle)
    {
        return vehicleRepository.save(vehicle);
    }
}
