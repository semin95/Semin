package com.etf.ppis.lambda.telenash.controller;

import com.etf.ppis.lambda.telenash.controller.errors.FeedbackIncidentNotFoundException;
import com.etf.ppis.lambda.telenash.model.FeedbackIncident;
import com.etf.ppis.lambda.telenash.repository.FeedbackIncidentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/feedbackIncident")
@CrossOrigin(origins = "*")
public class FeedbackIncidentController
{
    private final FeedbackIncidentRepository feedbackIncidentRepository;

    @Autowired
    public FeedbackIncidentController(FeedbackIncidentRepository feedbackIncidentRepository)
    {
        this.feedbackIncidentRepository = feedbackIncidentRepository;
    }

    @GetMapping("/all")
    public List<FeedbackIncident> getAllFeedbackIncidents()
    {
        return feedbackIncidentRepository.findAll();
    }

    @GetMapping("/{id}")
    public FeedbackIncident getByIdFeedbackIncident(@PathVariable(value = "id") Integer id) throws FeedbackIncidentNotFoundException
    {
        FeedbackIncident feedbackIncident = feedbackIncidentRepository
                .findById(id)
                .orElseThrow(() -> new FeedbackIncidentNotFoundException(id.toString()));

        return feedbackIncident;
    }

    @GetMapping("/incident/{id}")
    public List<FeedbackIncident> getByIncidentIdFeedbackIncident(@PathVariable(value = "id") Integer id) throws FeedbackIncidentNotFoundException
    {
        List<FeedbackIncident> allFeedbacks = feedbackIncidentRepository.findAll();
        List<FeedbackIncident> feedbacksToReturn = new ArrayList<>();

        for (FeedbackIncident fi : allFeedbacks) {
            if (fi.getIncident().getId() == id)
                feedbacksToReturn.add(fi);
        }

        return feedbacksToReturn;
    }

    @PostMapping
    public void createFeedbackIncident(@RequestBody FeedbackIncident feedbackIncident)
    {
        feedbackIncidentRepository.save(feedbackIncident);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteFeedbackIncident(@PathVariable(value = "id") Integer id) throws FeedbackIncidentNotFoundException
    {
        FeedbackIncident feedbackIncident = feedbackIncidentRepository.findById(id)
                .orElseThrow(() -> new FeedbackIncidentNotFoundException(id.toString()));

        feedbackIncidentRepository.delete(feedbackIncident);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    public FeedbackIncident updateFeedbackIncident(@PathVariable(value = "id") Integer id, @RequestBody @Valid FeedbackIncident feedbackIncidentUpdate) throws FeedbackIncidentNotFoundException
    {
        FeedbackIncident feedbackIncident = feedbackIncidentRepository
                .findById(id)
                .orElseThrow(() -> new FeedbackIncidentNotFoundException(id.toString()));

        feedbackIncident.setComment(feedbackIncidentUpdate.getComment());
        feedbackIncident.setDate(feedbackIncidentUpdate.getDate());
        feedbackIncident.setUser(feedbackIncidentUpdate.getUser());
        feedbackIncident.setIncident(feedbackIncidentUpdate.getIncident());

        FeedbackIncident o = feedbackIncidentRepository.save(feedbackIncident);
        return o;
    }
}