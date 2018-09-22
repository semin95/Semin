package com.etf.ppis.lambda.telenash.controller;

import com.etf.ppis.lambda.telenash.controller.errors.UserNotFoundException;
import com.etf.ppis.lambda.telenash.model.User;
import com.etf.ppis.lambda.telenash.model.UserDto;
import com.etf.ppis.lambda.telenash.repository.UserRepository;
import com.etf.ppis.lambda.telenash.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "*")
public class UserController
{
    private final UserRepository userRepository;
    private final UserService userService;

    @Autowired
    public UserController(UserRepository userRepository, UserService userService)
    {
        this.userRepository = userRepository;
        this.userService = userService;
    }

    @GetMapping("/all")
    public List<User> getAllUsers()
    {
        return userService.findAll();
    }

    @GetMapping()
    public User getByUsername(@RequestParam(value="username") String username) throws UserNotFoundException
    {
        return userService.getByUserName(username);
    }

    @GetMapping("/{id}")
    public User getByIdUser(@PathVariable(value = "id") Integer id) throws UserNotFoundException
    {
        User user = userRepository
                .findById(id)
                .orElseThrow(() -> new UserNotFoundException(id.toString()));

        return user;
    }

    @PostMapping
    public void createUser(@RequestBody User user)
    {
        userRepository.save(user);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable(value = "id") Integer id) throws UserNotFoundException
    {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id.toString()));

        userRepository.delete(user);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    public User updateUser(@PathVariable(value = "id") Integer id, @RequestBody @Valid User userUpdate) throws UserNotFoundException
    {
        User user = userRepository
                .findById(id)
                .orElseThrow(() -> new UserNotFoundException(id.toString()));

        user.setUsername(userUpdate.getUsername());
        user.setEmail(userUpdate.getEmail());
        user.setFirstName(userUpdate.getFirstName());
        user.setLastName(userUpdate.getLastName());
        user.setPhone(userUpdate.getPhone());
        user.setAddress(userUpdate.getAddress());
        user.setFeedbackIncidents(userUpdate.getFeedbackIncidents());
        user.setFeedbackRequests(userUpdate.getFeedbackRequests());
        user.setIncidents(userUpdate.getIncidents());
        user.setPassword(userUpdate.getPassword());
        user.setRequests(userUpdate.getRequests());
        user.setUserRole(userUpdate.getUserRole());

        User o = userRepository.save(user);
        return o;
    }

    @RequestMapping(value="/signup", method = RequestMethod.POST)
    public User saveUser(@RequestBody UserDto user)
    {
        return userService.save(user);
    }
}