package com.etf.ppis.lambda.telenash.controller;

import com.etf.ppis.lambda.telenash.controller.errors.UserRoleNotFoundException;
import com.etf.ppis.lambda.telenash.model.UserRole;
import com.etf.ppis.lambda.telenash.repository.UserRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/userRole")
@CrossOrigin(origins = "*")
public class UserRoleController
{
    private final UserRoleRepository userRoleRepository;

    @Autowired
    public UserRoleController(UserRoleRepository userRoleRepository)
    {
        this.userRoleRepository = userRoleRepository;
    }

    @GetMapping("/all")
    public List<UserRole> getAllUserRoles()
    {
        return userRoleRepository.findAll();
    }

    @GetMapping("/{id}")
    public UserRole getByIdUserRole(@PathVariable(value = "id") Integer id) throws UserRoleNotFoundException
    {
        UserRole userRole = userRoleRepository
                .findById(id)
                .orElseThrow(() -> new UserRoleNotFoundException(id.toString()));

        return userRole;
    }

    @PostMapping
    public void createUserRole(@RequestBody UserRole userRole)
    {
        userRoleRepository.save(userRole);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUserRole(@PathVariable(value = "id") Integer id) throws UserRoleNotFoundException
    {
        UserRole userRole = userRoleRepository.findById(id)
                .orElseThrow(() -> new UserRoleNotFoundException(id.toString()));

        userRoleRepository.delete(userRole);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    public UserRole updateUserRole(@PathVariable(value = "id") Integer id, @RequestBody @Valid UserRole userRoleUpdate) throws UserRoleNotFoundException
    {
        UserRole userRole = userRoleRepository
                .findById(id)
                .orElseThrow(() -> new UserRoleNotFoundException(id.toString()));

        userRole.setName(userRoleUpdate.getName());
        userRole.setUsers(userRoleUpdate.getUsers());

        UserRole o = userRoleRepository.save(userRole);
        return o;
    }
}