package com.airtribe.patyflow.controller;

import com.airtribe.patyflow.entity.User;
import com.airtribe.patyflow.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {


    @Autowired
    private UserService userService;

    //register
    @PostMapping
    public User registerUser(@RequestBody User user) {

        System.out.println("User registered successfully" + user);
        return userService.registerUser(user);
    }

    //getAllUsers
    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    //getUserById
    @GetMapping("/{id}")
    public User getUserById(@PathVariable Long id) {
        return userService.getUserById(id).orElseThrow();
    }


    //getUserByUpiId
    @GetMapping("/upi/{upiId}")
    public User getUserByUpiId(@PathVariable String upiId) {
        return userService.getUserByUpiId(upiId).orElseThrow();
    }

    //getUsersWithBalanceGreaterThan
    @GetMapping("/balance/{amount}")
    public List<User> getUsersWithBalanceGreaterThan(@PathVariable Double amount) {
        return userService.getUsersWithBalanceGreaterThan(amount);
    }
}
