package com.project05.service;

import com.project05.domain.User;
import com.project05.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User addUser(User user) {
        // Assume password encryption or hashing is handled before this point
        return userRepository.save(user);
    }

    public Optional<User> findUserById(String userId) {
        return userRepository.findById(userId);
    }

    public boolean updateUser(User user) {
        // Assuming 'user' contains the 'userid' to identify the record to be updated
        if (!userRepository.existsById(user.getUserid())) {
            return false; // Return false if user does not exist
        }
        userRepository.save(user); // Save method updates the user if it exists
        return true;
    }

    public boolean deleteUser(String userId) {
        if (!userRepository.existsById(userId)) {
            return false; // Return false if user does not exist
        }
        userRepository.deleteById(userId);
        return true;
    }

    public boolean validateUserLogin(String userid, String userpw) {
        Optional<User> user = userRepository.findById(userid);
        return user.isPresent() && user.get().getUserpw().equals(userpw); // Assumes passwords are stored in plain text (not recommended in production)
    }

    // Additional methods as per your business logic
}
