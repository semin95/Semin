package com.example.vehicle.resource;

import com.example.vehicle.model.Location;
import com.example.vehicle.repository.LocationRepository;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/rest/location")
@CrossOrigin(origins = "http://185.91.158.33:3000")
public class LocationResource {

    @Autowired
    LocationRepository locationRepository;

    @GetMapping(value = "/all")
    public List<Location> getAll(){
        return locationRepository.findAll();
    }

    @GetMapping("/get/{id}")
    public Location getLocationById(@PathVariable(value = "id") Integer id) throws NotFoundException{
        return locationRepository.findById(id).orElseThrow(() -> new NotFoundException("Location with given ID not found!"));
    }

    @PutMapping("update/{id}")
    public Location updateLocation (@PathVariable(value = "id")Integer id, @Valid @RequestBody Location locationUpdated) throws NotFoundException {
        Location location = locationRepository.findById(id).orElseThrow(()->new NotFoundException("Location with given ID not found!"));

        location.setAddress(locationUpdated.getAddress());
        location.setName(locationUpdated.getName());

        Location updatedLocation=locationRepository.save(location);
        return updatedLocation;
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<?> deleteNote(@PathVariable(value = "id") Integer id) throws NotFoundException{
        Location location = locationRepository.findById(id).orElseThrow(()-> new NotFoundException("Location with given ID not found!"));

        locationRepository.delete(location);
        return  ResponseEntity.ok().build();
    }

    @PostMapping(value = "/insert")
    public Location createLocation (@Valid @RequestBody final Location location)
    {
        return locationRepository.save(location);
    }

}
