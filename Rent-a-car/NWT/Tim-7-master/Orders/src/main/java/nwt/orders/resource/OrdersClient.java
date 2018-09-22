package nwt.orders.resource;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import nwt.orders.repository.RentalsRepository;

@RestController
@RequestMapping(value="/client/rentals")
public class OrdersClient {
	@Autowired
	private DiscoveryClient discoveryClient;
	
	@Autowired 
	RentalsRepository rentalsRepository;
	
	@Autowired
	private ApplicationContext context;
	
	
	
	
	@GetMapping(value="/test")
	public String getAll(){
		Long id=5L;
		return getRentals(id);
	}
	
	
	
	
	
	
	public String getRentals(Long id) {
		List<ServiceInstance> instances=discoveryClient.getInstances("Orders");
		//List<ServiceInstance> instances=discoveryClient.getInstances("Accounts");
		if(instances.isEmpty()) return "Servis nedostupan";
		ServiceInstance serviceInstance=instances.get(0);
		
		String baseUrl=serviceInstance.getUri().toString()+ "/rest/rentals/get/"+id.toString();
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
	public String doesClientExist(Long id) {
		
		List<ServiceInstance> instances=discoveryClient.getInstances("Accounts");
		if(instances.isEmpty()) return "Servis nedostupan";
		ServiceInstance serviceInstance=instances.get(0);
		
		String baseUrl=serviceInstance.getUri().toString()+ "/client/get/"+id.toString();
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
	public String doesVehicleExist(Long id) {
		List<ServiceInstance> instances=discoveryClient.getInstances("Vehicles");
		//List<ServiceInstance> instances=discoveryClient.getInstances("Accounts");
		if(instances.isEmpty()) return "Servis nedostupan";
		ServiceInstance serviceInstance=instances.get(0);
		
		String baseUrl=serviceInstance.getUri().toString()+ "/rest/vehicle/get/"+id.toString();
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

