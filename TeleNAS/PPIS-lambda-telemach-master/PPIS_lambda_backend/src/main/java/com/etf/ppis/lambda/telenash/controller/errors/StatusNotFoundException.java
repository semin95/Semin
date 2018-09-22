package com.etf.ppis.lambda.telenash.controller.errors;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class StatusNotFoundException extends RuntimeException
{
    public StatusNotFoundException(String id)
    {
        super("Status with id: " + id + " not found.");
    }
}