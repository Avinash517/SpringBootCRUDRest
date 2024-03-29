package com.cloudsolv.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.cloudsolv.model.*;
import com.cloudsolv.repository.*;

import com.cloudsolv.exception.ResourceNotFoundException;

@RestController
@RequestMapping("/api/v2")
public class UserController {

	@Autowired
    private UserRepository userRepository;
	
	
    @GetMapping("/users")
    public List<User> getAllusers() {
        return userRepository.findAll();
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<User> getUserById(@PathVariable(value = "id") Long userId)
        throws ResourceNotFoundException {
        User user = userRepository.findById(userId)
          .orElseThrow(() -> new ResourceNotFoundException("user not found for this id :: " + userId));
        return ResponseEntity.ok().body(user);
    }
    
    @PostMapping("/users")
    public User createuser(@Valid @RequestBody User user) {
        return userRepository.save(user);
    }

    @PutMapping("/users/{id}")
    public ResponseEntity<User> updateUser(@PathVariable(value = "id") Long userId,
         @Valid @RequestBody User userDetails) throws ResourceNotFoundException {
        User user = userRepository.findById(userId)
        .orElseThrow(() -> new ResourceNotFoundException("user not found for this id :: " + userId));

        user.setEmailId(userDetails.getEmailId());
        user.setLastName(userDetails.getLastName());
        user.setFirstName(userDetails.getFirstName());
        final User updateduser = userRepository.save(user);
        return ResponseEntity.ok(updateduser);
    }

    @DeleteMapping("/users/{id}")
    public Map<String, Boolean> deleteuser(@PathVariable(value = "id") Long userId)
         throws ResourceNotFoundException {
        User user = userRepository.findById(userId)
       .orElseThrow(() -> new ResourceNotFoundException("user not found for this id :: " + userId));

        userRepository.delete(user);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
}
