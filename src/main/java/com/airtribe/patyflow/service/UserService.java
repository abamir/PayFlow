package com.airtribe.patyflow.service;

import com.airtribe.patyflow.entity.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
public interface UserService {

    //registerUser
    public User registerUser(User user);

    //getAllUsers
    public List<User> getAllUsers();

    //getUserById
    public Optional<User> getUserById(Long userId);

    //getUserByUpiId
    public Optional<User> getUserByUpiId(String upiId);

    //getUsersWithBalanceGreaterThan
    public List<User> getUsersWithBalanceGreaterThan(Double amount);
}
