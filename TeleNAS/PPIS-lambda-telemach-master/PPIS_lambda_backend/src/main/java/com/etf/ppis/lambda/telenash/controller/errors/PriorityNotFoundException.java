package com.etf.ppis.lambda.telenash.controller.errors;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class PriorityNotFoundException extends RuntimeException
{
    public PriorityNotFoundException(String id)
    {
        super("Priority with id: " + id + " not found.");
    }
}