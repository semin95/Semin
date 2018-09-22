package com.example.account_m.controller;


import com.example.account_m.entity.Client;
import com.example.account_m.repository.ClientRepository;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/client")
@CrossOrigin(origins = "http://185.91.158.33:3000")
public class ClientController {


    @Autowired
    ClientRepository clientRepository;

    @GetMapping(value="/all")
    public List<Client> getAll(){
        return clientRepository.findAll();
    }

    @GetMapping("/get/{id}")
    public Client getClientById(@PathVariable(value = "id") Long id) throws NotFoundException {
        return clientRepository.findById(id).orElseThrow(() -> new NotFoundException("Client with given id not found"));
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<?> deleteClient(@PathVariable(value = "id") Long id) throws NotFoundException {
        Client client = clientRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Client with given id not found"));

        clientRepository.delete(client);

        return ResponseEntity.ok().build();
    }

    @PutMapping("update/{id}")
    public Client updateClient(@PathVariable(value = "id") Long id,
                                               @RequestBody @Valid Client clientUpdated, Errors errors) throws NotFoundException, Exception {

        if(errors.hasErrors()){
            throw new Exception(errors.getAllErrors().get(0).getDefaultMessage());
        }

        Client client = clientRepository
                .findById(id)
                .orElseThrow(
                        () -> new NotFoundException("Rent-a-car office with given id not found")
                );

        client.setName(clientUpdated.getName());
        client.setSurname(clientUpdated.getSurname());
        client.setEmailAdress(clientUpdated.getEmailAdress());

        clientUpdated = clientRepository.save(client);
        return clientUpdated;
    }

    @PostMapping(value="/insert")
    public Client createClient(@RequestBody @Valid final Client client, Errors errors) throws Exception {

        if(errors.hasErrors()){
            throw new Exception(errors.getAllErrors().get(0).getDefaultMessage());
        }

        return clientRepository.save(client);
    }

}
