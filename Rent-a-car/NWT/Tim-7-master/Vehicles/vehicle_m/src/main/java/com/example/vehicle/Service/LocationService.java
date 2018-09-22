package com.example.vehicle.Service;

import com.example.vehicle.model.Location;
import com.example.vehicle.repository.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javassist.NotFoundException;

@Service
public class LocationService {

    @Autowired
    LocationRepository locationRepository;

    public Location updateLocation (Integer id, Location locationUpdated) throws NotFoundException
    {
        Location locations = locationRepository.findById(id).orElseThrow(() -> new NotFoundException("Location with given id not found"));

        locations.setName(locationUpdated.getName());
        locations.setAddress(locationUpdated.getAddress());

        Location updatedLocations = locationRepository.save(locations);
        return updatedLocations;
    }

    public Location createLocation(Location locations)
    {
        return locationRepository.save(locations);
    }
}
