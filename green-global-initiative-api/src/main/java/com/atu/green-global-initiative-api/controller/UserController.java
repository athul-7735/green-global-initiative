package com.atu.devops.controller;

import com.atu.devops.dto.UserDetailsDto;
import com.atu.devops.model.dao.UserDetails;
import com.atu.devops.model.dao.request.LoginRequest;
import com.atu.devops.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "http://localhost:4200")
public class UserController {

    @Autowired
    private UserServiceImpl userServiceImpl;

    // Get all users
    @GetMapping
    public ResponseEntity<List<UserDetailsDto>> getAllUsers() {
        List<UserDetailsDto> users = new ArrayList<>();
        users = userServiceImpl.getAllUserDetails();
        if (users.isEmpty()) {
            return new ResponseEntity<>(users, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(users, HttpStatus.OK) ;
    }

    @PostMapping("/signup")
    public UserDetailsDto createUser(@RequestBody UserDetails userDetails) {
        UserDetailsDto res = new UserDetailsDto();
        try {
            res = userServiceImpl.userSignUp(userDetails);
        } catch (Exception e){
            e.printStackTrace();
        }
        return res;
    }

    @PostMapping("/login")
    public ResponseEntity<UserDetailsDto> loginUser(@RequestBody LoginRequest loginRequest) {
        try {
            UserDetailsDto isAuthenticated = userServiceImpl.authenticateUser(loginRequest.getEmail(), loginRequest.getPassword());
            if (isAuthenticated != null) {
                return new ResponseEntity<>(isAuthenticated, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
