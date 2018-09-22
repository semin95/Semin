package com.etf.ppis.lambda.telenash.controller;

import com.etf.ppis.lambda.telenash.controller.errors.FeedbackRequestNotFoundException;
import com.etf.ppis.lambda.telenash.model.FeedbackRequest;
import com.etf.ppis.lambda.telenash.repository.FeedbackRequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/feedbackRequest")
@CrossOrigin(origins = "*")
public class FeedbackRequestController
{
    private final FeedbackRequestRepository feedbackRequestRepository;

    @Autowired
    public FeedbackRequestController(FeedbackRequestRepository feedbackRequestRepository)
    {
        this.feedbackRequestRepository = feedbackRequestRepository;
    }

    @GetMapping("/all")
    public List<FeedbackRequest> getAllFeedbackRequests()
    {
        return feedbackRequestRepository.findAll();
    }

    @GetMapping("/{id}")
    public FeedbackRequest getByIdFeedbackRequest(@PathVariable(value = "id") Integer id) throws FeedbackRequestNotFoundException
    {
        FeedbackRequest feedbackRequest = feedbackRequestRepository
                .findById(id)
                .orElseThrow(() -> new FeedbackRequestNotFoundException(id.toString()));

        return feedbackRequest;
    }

    @GetMapping("/request/{id}")
    public List<FeedbackRequest> getByRequestIdFeedbackRequest(@PathVariable(value = "id") Integer id) throws FeedbackRequestNotFoundException
    {
        List<FeedbackRequest> allFeedbacks = feedbackRequestRepository.findAll();
        List<FeedbackRequest> feedbacksToReturn = new ArrayList<>();

        for (FeedbackRequest fi : allFeedbacks) {
            if (fi.getRequest().getId() == id)
                feedbacksToReturn.add(fi);
        }

        return feedbacksToReturn;
    }

    @PostMapping
    public void createFeedbackRequest(@RequestBody FeedbackRequest feedbackRequest)
    {
        feedbackRequestRepository.save(feedbackRequest);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteFeedbackRequest(@PathVariable(value = "id") Integer id) throws FeedbackRequestNotFoundException
    {
        FeedbackRequest feedbackRequest = feedbackRequestRepository.findById(id)
                .orElseThrow(() -> new FeedbackRequestNotFoundException(id.toString()));

        feedbackRequestRepository.delete(feedbackRequest);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    public FeedbackRequest updateFeedbackRequest(@PathVariable(value = "id") Integer id, @RequestBody @Valid FeedbackRequest feedbackRequestUpdate) throws FeedbackRequestNotFoundException
    {
        FeedbackRequest feedbackRequest = feedbackRequestRepository
                .findById(id)
                .orElseThrow(() -> new FeedbackRequestNotFoundException(id.toString()));

        feedbackRequest.setComment(feedbackRequestUpdate.getComment());
        feedbackRequest.setDate(feedbackRequestUpdate.getDate());
        feedbackRequest.setUser(feedbackRequestUpdate.getUser());
        feedbackRequest.setRequest(feedbackRequestUpdate.getRequest());

        FeedbackRequest o = feedbackRequestRepository.save(feedbackRequest);
        return o;
    }
}