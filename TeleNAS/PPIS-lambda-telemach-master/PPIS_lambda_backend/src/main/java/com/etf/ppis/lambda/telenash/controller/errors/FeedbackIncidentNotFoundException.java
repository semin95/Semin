package com.etf.ppis.lambda.telenash.controller.errors;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class FeedbackIncidentNotFoundException extends RuntimeException
{
    public FeedbackIncidentNotFoundException(String id)
    {
        super("Feedback Incident with id: " + id + " not found.");
    }
}