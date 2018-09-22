package com.example.vehicle.resource;

import com.example.vehicle.repository.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping(value="/client/vehicle")
@CrossOrigin(origins = "http://185.91.158.33:3000")
public class VehiclesClient {

    @Autowired
    private DiscoveryClient discoveryClient;

    @Autowired
    VehicleRepository vehicleRepository;

    @Autowired
    private ApplicationContext context;

    @GetMapping(value="/test")
    public String getAll(){
        int id=5;
        return getVehicles(id);
    }

    public String getVehicles(int id) {
        List<ServiceInstance> instances=discoveryClient.getInstances("Vehicles");
        if(instances.isEmpty()) return "Servis nedostupan";
        ServiceInstance serviceInstance=instances.get(0);

        String baseUrl=serviceInstance.getUri().toString()+ "/rest/vehicles/get/"+id;
        System.out.println(baseUrl);

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response=null;
        try{
            response=restTemplate.exchange(baseUrl,
                    HttpMethod.GET, getHeaders(),String.class);
        }catch (Exception ex)
        {	///ovdje if(contains null) return NEMA
            return ex.getMessage();
            //return ex.getCause().toString();
            //System.out.println(ex);
        }
        System.out.println(response.getBody());
        return response.getBody();
    }

    public String doesClientExist(int id) {

        List<ServiceInstance> instances=discoveryClient.getInstances("Accounts");
        if(instances.isEmpty()) return "Servis nedostupan";
        ServiceInstance serviceInstance=instances.get(0);

        String baseUrl=serviceInstance.getUri().toString()+ "/client/get/"+id;
        System.out.println(baseUrl);

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response=null;
        try{
            response=restTemplate.exchange(baseUrl,
                    HttpMethod.GET, getHeaders(),String.class);
        }catch (Exception ex)
        {	//ovdje if(contains null) return NEMA
            return ex.toString();
            //System.out.println(ex);
        }
        System.out.println(response.getBody());
        //return response.getBody();
        return "OK";
    }

    public String doesOrderExist(int id) {
        List<ServiceInstance> instances=discoveryClient.getInstances("Orders");
        //List<ServiceInstance> instances=discoveryClient.getInstances("Accounts");
        if(instances.isEmpty()) return "Servis nedostupan";
        ServiceInstance serviceInstance=instances.get(0);

        String baseUrl=serviceInstance.getUri().toString()+ "/rest/rentals/get/"+id;
        System.out.println(baseUrl);

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response=null;
        try{
            response=restTemplate.exchange(baseUrl,
                    HttpMethod.GET, getHeaders(),String.class);
        }catch (Exception ex)
        {	//ovdje if(contains null) return NEMA
            return ex.toString();
            //System.out.println(ex);
        }
        System.out.println(response.getBody());
        //return response.getBody();
        return "OK";

    }

    private static HttpEntity<?> getHeaders() throws IOException {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);
        return new HttpEntity<>(headers);
    }
}
