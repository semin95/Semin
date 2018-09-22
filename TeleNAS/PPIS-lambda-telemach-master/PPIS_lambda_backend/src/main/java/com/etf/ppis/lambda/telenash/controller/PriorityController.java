package com.etf.ppis.lambda.telenash.controller;

import com.etf.ppis.lambda.telenash.controller.errors.PriorityNotFoundException;
import com.etf.ppis.lambda.telenash.model.Priority;
import com.etf.ppis.lambda.telenash.repository.PriorityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/priority")
@CrossOrigin(origins = "*")
public class PriorityController
{
    private final PriorityRepository priorityRepository;

    @Autowired
    public PriorityController(PriorityRepository priorityRepository)
    {
        this.priorityRepository = priorityRepository;
    }

    @GetMapping("/all")
    public List<Priority> getAllPriorities()
    {
        return priorityRepository.findAll();
    }

    @GetMapping("/{id}")
    public Priority getByIdPriority(@PathVariable(value = "id") Integer id) throws PriorityNotFoundException
    {
        Priority priority = priorityRepository
                .findById(id)
                .orElseThrow(() -> new PriorityNotFoundException(id.toString()));

        return priority;
    }

    @PostMapping
    public void createPriority(@RequestBody Priority priority)
    {
        priorityRepository.save(priority);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePriority(@PathVariable(value = "id") Integer id) throws PriorityNotFoundException
    {
        Priority priority = priorityRepository.findById(id)
                .orElseThrow(() -> new PriorityNotFoundException(id.toString()));

        priorityRepository.delete(priority);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    public Priority updatePriority(@PathVariable(value = "id") Integer id, @RequestBody @Valid Priority priorityUpdate) throws PriorityNotFoundException
    {
        Priority priority = priorityRepository
                .findById(id)
                .orElseThrow(() -> new PriorityNotFoundException(id.toString()));

        priority.setName(priorityUpdate.getName());
        return priorityRepository.save(priority);
    }
}