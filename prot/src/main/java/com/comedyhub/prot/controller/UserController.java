package com.comedyhub.prot.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import com.comedyhub.prot.dto.UserDtoCreate;
import com.comedyhub.prot.dto.UserDtoResponse;
import com.comedyhub.prot.service.UserService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<UserDtoResponse> createUser(@RequestBody UserDtoCreate userDTO) {
        return ResponseEntity.ok(userService.createUser(userDTO));
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping
    public ResponseEntity<List<UserDtoResponse>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }
    
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/{id}")
    public ResponseEntity<?> getUserById(@PathVariable Long id) {
    	try {
    		return ResponseEntity.ok(userService.getUserById(id));
    	} catch (Exception e){
    		Map<String, String> message = new HashMap<>();
    		message.put("message", "User with id " + id + "not found");
    		return ResponseEntity.status(404).body(message);
    	}
    }

    @GetMapping("/username/{username}")
    public ResponseEntity<Map<String,String>> getUserByUsername(@PathVariable String username) {
    	Map<String, String> message = new HashMap<>();
        try {
        	message.put("message", "User with username " + username + " found");
        	UserDtoResponse user = userService.getUserByUsername(username);	
        	return ResponseEntity.status(200).body(message);
        } catch(Exception e) {
        	message.put("message", "User with username " + username + " not found");
	        return ResponseEntity.status(404).body(message);
        }
    }
    
}