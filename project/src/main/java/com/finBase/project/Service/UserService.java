package com.finBase.project.Service;

import com.finBase.project.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public boolean checkIfUserExists(String email) {
        return userRepository.existsByEmail(email);
    }
}


