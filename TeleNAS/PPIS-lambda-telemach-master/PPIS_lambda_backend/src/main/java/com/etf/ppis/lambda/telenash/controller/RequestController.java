package com.etf.ppis.lambda.telenash.controller;

import com.etf.ppis.lambda.telenash.controller.errors.RequestNotFoundException;
import com.etf.ppis.lambda.telenash.model.Request;
import com.etf.ppis.lambda.telenash.model.StatisticsReport;
import com.etf.ppis.lambda.telenash.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/request")
@CrossOrigin(origins = "*")
public class RequestController
{
    private final RequestRepository requestRepository;
    private final StatusRepository statusRepository;
    private final UserRepository userRepository;
    private final PriorityRepository priorityRepository;
    private final DepartmentRepository departmentRepository;
    private final ProductRepository productRepository;

    @Autowired
    public RequestController(RequestRepository requestRepository, StatusRepository statusRepository, PriorityRepository priorityRepository,
                             DepartmentRepository departmentRepository, ProductRepository productRepository, UserRepository userRepository)
    {
        this.requestRepository = requestRepository;
        this.statusRepository = statusRepository;
        this.priorityRepository = priorityRepository;
        this.userRepository = userRepository;
        this.departmentRepository = departmentRepository;
        this.productRepository = productRepository;
    }

    @GetMapping("/all")
    public List<Request> getAllRequests()
    {
        return requestRepository.findAll();
    }

    @GetMapping ("/stats")
    public List<StatisticsReport> getIncidentStatistics(@RequestParam (required = true, value = "year") Integer year,
                                                        @RequestParam (required = false, value = "month") Integer month)
    {
        return requestRepository.getCountByMonthAndStatus(year, month);
    }

    @GetMapping("/{id}")
    public Request getByIdRequest(@PathVariable(value = "id") Integer id) throws RequestNotFoundException
    {
        Request request = requestRepository
                .findById(id)
                .orElseThrow(() -> new RequestNotFoundException(id.toString()));

        return request;
    }

    @GetMapping("/status/{name}")
    public List<Request> getByStatusNameRequest(@PathVariable(value = "name") String name) throws RequestNotFoundException
    {
        List<Request> allRequests = requestRepository.findAll();
        List<Request> requestsToReturn = new ArrayList<>();

        for (Request r : allRequests) {
            if (r.getStatus().getName() == name)
                requestsToReturn.add(r);
        }
        return requestsToReturn;
    }

    @GetMapping("/user/{id}")
    public List<Request> getByUserIdRequest(@PathVariable(value = "id") Integer id) throws RequestNotFoundException
    {
        List<Request> allRequests = requestRepository.findAll();
        List<Request> requestsToReturn = new ArrayList<>();

        for (Request r : allRequests) {
            if (r.getUser().getId() == id)
                requestsToReturn.add(r);
        }
        return requestsToReturn;
    }

    @PostMapping
    public Request createRequest(@RequestBody Request request)
    {
        return requestRepository.save(request);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable("id") Integer id) throws RequestNotFoundException
    {
        requestRepository.findById(id)
                .orElseThrow(() -> new RequestNotFoundException(id.toString()));

        requestRepository.deleteById(id);
    }

    @PutMapping("/{id}")
    public Request updateRequest(@PathVariable(value = "id") Integer id, @RequestBody @Valid Request requestUpdate) throws RequestNotFoundException
    {
        Request request = requestRepository
                .findById(id)
                .orElseThrow(() -> new RequestNotFoundException(id.toString()));

        request.setName(requestUpdate.getName());
        request.setDescription(requestUpdate.getDescription());
        request.setDate(requestUpdate.getDate());
        request.setRadniNalog(requestUpdate.getRadniNalog());
        request.setWayOfSubmission(requestUpdate.getWayOfSubmission());
        request.setWayOfResponse(requestUpdate.getWayOfResponse());
        request.setStatus(requestUpdate.getStatus());
        request.setUserPriority(requestUpdate.getUserPriority());
        request.setAdminPriority(requestUpdate.getAdminPriority());
        request.setDepartment(requestUpdate.getDepartment());
        request.setUser(requestUpdate.getUser());
        request.setProduct(requestUpdate.getProduct());
        request.setFeedbackRequests(requestUpdate.getFeedbackRequests());

        Request o = requestRepository.save(request);
        return o;
    }
}