package com.etf.ppis.lambda.telenash.controller;

import com.etf.ppis.lambda.telenash.controller.errors.StatusNotFoundException;
import com.etf.ppis.lambda.telenash.model.Status;
import com.etf.ppis.lambda.telenash.repository.StatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/status")
@CrossOrigin(origins = "*")
public class StatusController
{
    private final StatusRepository statusRepository;

    @Autowired
    public StatusController(StatusRepository statusRepository)
    {
        this.statusRepository = statusRepository;
    }

    @GetMapping("/all")
    public List<Status> getAllStatuses()
    {
        return statusRepository.findAll();
    }

    @GetMapping("/{id}")
    public Status getByIdStatus(@PathVariable(value = "id") Integer id) throws StatusNotFoundException
    {
        Status status = statusRepository
                .findById(id)
                .orElseThrow(() -> new StatusNotFoundException(id.toString()));

        return status;
    }

    @PostMapping
    public void createStatus(@RequestBody Status status)
    {
        statusRepository.save(status);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteStatus(@PathVariable(value = "id") Integer id) throws StatusNotFoundException
    {
        Status status = statusRepository.findById(id)
                .orElseThrow(() -> new StatusNotFoundException(id.toString()));

        statusRepository.delete(status);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    public Status updateStatus(@PathVariable(value = "id") Integer id, @RequestBody @Valid Status statusUpdate) throws StatusNotFoundException
    {
        Status status = statusRepository
                .findById(id)
                .orElseThrow(() -> new StatusNotFoundException(id.toString()));

        status.setName(statusUpdate.getName());
        return statusRepository.save(status);
    }
}