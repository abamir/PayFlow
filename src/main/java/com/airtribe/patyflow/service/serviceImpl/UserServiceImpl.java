package com.airtribe.patyflow.service.serviceImpl;

import com.airtribe.patyflow.entity.User;
import com.airtribe.patyflow.repository.UserRepository;
import com.airtribe.patyflow.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User registerUser(User user) {

        Optional<User> existingUser = userRepository.findByUpiId(user.getUpiId());
        if (existingUser.isPresent()) {
            throw new RuntimeException("User already exists");
        }
        user.setBalance(0.0);
        return userRepository.save(user);

    }

    @Override
    public List<User> getAllUsers() {

        List<User> users = userRepository.findAll();

        return users;
    }

    @Override
    public Optional<User> getUserById(Long userId) {

        return userRepository.findById(userId);


    }

    @Override
    public Optional<User> getUserByUpiId(String upiId) {

        return userRepository.findByUpiId(upiId);
    }

    @Override
    public List<User> getUsersWithBalanceGreaterThan(Double amount) {

        return userRepository.findUsersWithBalanceGreaterThan(amount);
    }
}
