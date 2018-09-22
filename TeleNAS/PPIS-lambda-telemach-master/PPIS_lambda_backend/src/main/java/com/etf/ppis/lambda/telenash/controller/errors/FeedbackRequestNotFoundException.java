package com.etf.ppis.lambda.telenash.controller.errors;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class FeedbackRequestNotFoundException extends RuntimeException
{
    public FeedbackRequestNotFoundException(String id)
    {
        super("Feedback Request with id: " + id + " not found.");
    }
}