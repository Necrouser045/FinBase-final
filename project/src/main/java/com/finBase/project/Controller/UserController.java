package com.finBase.project.Controller;

import com.finBase.project.Model.User;
import com.finBase.project.Service.UserService;
import com.finBase.project.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("/api")
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @PostMapping("/users")
    public ResponseEntity<String> createUser(@RequestBody User user) {
        System.out.println("Received User: " + user.getUsername());
        System.out.println("Received Password: "+user.getPassword());
        System.out.println("Received Email ID: "+user.getEmail());
        userRepository.save(user);
        return ResponseEntity.ok("User created successfully!");
    }

    @GetMapping("/checkUser")
    public ResponseEntity<Map<String, Object>> checkUser(@RequestParam String email) {
        boolean exists = userService.checkIfUserExists(email);

        Map<String, Object> response = new HashMap<>();
        response.put("exists", exists);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/all")
    public List<User> getUsers() {
        return userRepository.findAll();
    }
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody User user) {
        Optional<User> userOptional = userRepository.findByEmail(user.getEmail());

        if (userOptional.isPresent()) {
            User guest = userOptional.get();

            if (guest.getPassword().equals(user.getPassword())) {
                return ResponseEntity.ok("Login successful");
            } else {
                return ResponseEntity.status(401).body("Invalid password. Please try again.");
            }
        } else {
            return ResponseEntity.status(404).body("User not found. Please try again.");
        }
    }

}
