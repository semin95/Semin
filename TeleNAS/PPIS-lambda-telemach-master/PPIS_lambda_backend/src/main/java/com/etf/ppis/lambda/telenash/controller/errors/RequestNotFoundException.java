package com.etf.ppis.lambda.telenash.controller.errors;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class RequestNotFoundException extends RuntimeException
{
    public RequestNotFoundException(String id)
    {
        super("Request with id: " + id + " not found.");
    }
}