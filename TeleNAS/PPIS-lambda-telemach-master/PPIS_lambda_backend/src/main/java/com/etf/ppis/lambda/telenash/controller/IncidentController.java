package com.etf.ppis.lambda.telenash.controller;

import com.etf.ppis.lambda.telenash.controller.errors.IncidentNotFoundException;
import com.etf.ppis.lambda.telenash.controller.errors.RequestNotFoundException;
import com.etf.ppis.lambda.telenash.model.Incident;
import com.etf.ppis.lambda.telenash.model.StatisticsReport;
import com.etf.ppis.lambda.telenash.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/incident")
@CrossOrigin(origins = "*")
public class IncidentController
{
    private final IncidentRepository incidentRepository;

    @Autowired
    public IncidentController(IncidentRepository incidentRepository)
    {
        this.incidentRepository = incidentRepository;
    }

    @GetMapping("/all")
    public List<Incident> getAllIncidents()
    {
        return incidentRepository.findAll();
    }

    @GetMapping("/{id}")
    public Incident getByIdIncident(@PathVariable(value = "id") Integer id) throws IncidentNotFoundException
    {
        Incident incident = incidentRepository
                .findById(id)
                .orElseThrow(() -> new IncidentNotFoundException(id.toString()));

        return incident;
    }

    @GetMapping("/status/{name}")
    public List<Incident> getByStatusNameIncident(@PathVariable(value = "name") String name) throws IncidentNotFoundException
    {
        List<Incident> allIncidents = incidentRepository.findAll();
        List<Incident> incidentsToReturn = new ArrayList<>();

        for (Incident i : allIncidents) {
            if (i.getStatus().getName() == name)
                incidentsToReturn.add(i);
        }
        return incidentsToReturn;
    }

    @GetMapping("/user/{id}")
    public List<Incident> getByUserIdIncident(@PathVariable(value = "id") Integer id) throws IncidentNotFoundException
    {
        List<Incident> allIncidents = incidentRepository.findAll();
        List<Incident> incidentsToReturn = new ArrayList<>();

        for (Incident i : allIncidents) {
            if (i.getUser().getId() == id)
                incidentsToReturn.add(i);
        }
        return incidentsToReturn;
    }

    @GetMapping ("/stats")
    public List<StatisticsReport> getIncidentStatistics(@RequestParam (required = true, value = "year") Integer year,
                                                        @RequestParam (required = false, value = "month") Integer month)
    {
        return incidentRepository.getCountByMonthAndStatus(year, month);
    }

    @PostMapping
    public Incident createIncident(@RequestBody Incident incident)
    {
        return incidentRepository.save(incident);
    }

    @DeleteMapping(value="/{id}")
    public void deleteById(@PathVariable("id") Integer id) throws RequestNotFoundException {
        incidentRepository.deleteById(id);
    }

    @PutMapping("/{id}")
    public Incident updateIncident(@PathVariable(value = "id") Integer id, @RequestBody @Valid Incident incidentUpdate) throws IncidentNotFoundException
    {
        Incident incident = incidentRepository
                .findById(id)
                .orElseThrow(() -> new IncidentNotFoundException(id.toString()));

        incident.setName(incidentUpdate.getName());
        incident.setDescription(incidentUpdate.getDescription());
        incident.setDate(incidentUpdate.getDate());
        incident.setRadniNalog(incidentUpdate.getRadniNalog());
        incident.setWayOfSubmission(incidentUpdate.getWayOfSubmission());
        incident.setWayOfResponse(incidentUpdate.getWayOfResponse());
        incident.setStatus(incidentUpdate.getStatus());
        incident.setUserPriority(incidentUpdate.getUserPriority());
        incident.setAdminPriority(incidentUpdate.getAdminPriority());
        incident.setDepartment(incidentUpdate.getDepartment());
        incident.setUser(incidentUpdate.getUser());
        incident.setProduct(incidentUpdate.getProduct());
        incident.setFeedbackIncidents(incidentUpdate.getFeedbackIncidents());

        return incidentRepository.save(incident);
    }
}