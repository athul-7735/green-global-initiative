package com.atu.green_global_initiative_api.controller;

import com.atu.green_global_initiative_api.NausicaaGreenInitiativeApplication;
import com.atu.green_global_initiative_api.dto.UserDetailsDto;
import com.atu.green_global_initiative_api.model.dao.UserDetails;
import com.atu.green_global_initiative_api.model.dao.request.LoginRequest;
import com.atu.green_global_initiative_api.security.JwtUtil;
import com.atu.green_global_initiative_api.service.UserServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "*")
public class UserController {

    @Autowired
    private UserServiceImpl userServiceImpl;

    @Autowired
    private JwtUtil jwtUtil;

    static final Logger logger = LoggerFactory.getLogger(NausicaaGreenInitiativeApplication.class);

    // Get all users
    @GetMapping
    public ResponseEntity<List<UserDetailsDto>> getAllUsers() {
        logger.info("getAllUsers method Started");
        List<UserDetailsDto> users = new ArrayList<>();
        users = userServiceImpl.getAllUserDetails();
        if (users.isEmpty()) {
            logger.info("getAllUsers Sending Data");
            return new ResponseEntity<>(users, HttpStatus.NOT_FOUND);
        }
        logger.info("getAllUsers sending Data with blank response");
//        return new org.springframework.security.core.userdetails.User(users.getFirst().getEmail(),
//                users.getFirst().getFirstName(),
//                Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER")));
        return new ResponseEntity<>(users, HttpStatus.OK) ;
    }

    @PostMapping("/signup")
    public UserDetailsDto createUser(@RequestBody UserDetails userDetails) {
        logger.info("createUser method Started");
        UserDetailsDto res = new UserDetailsDto();
        try {
            res = userServiceImpl.userSignUp(userDetails);
        } catch (Exception e){
            e.printStackTrace();
        }
        return res;
    }

    @PostMapping("/login")
    @CrossOrigin(origins = "*")
    public ResponseEntity<Map<String,String>> loginUser(@RequestBody LoginRequest loginRequest) {
        try {
            logger.info("loginUser method Started");
            UserDetailsDto isAuthenticated = userServiceImpl.authenticateUser(loginRequest.getEmail(), loginRequest.getPassword());
            if (isAuthenticated != null) {
                final String jwt = jwtUtil.generateToken(isAuthenticated);
                Map<String, String> response = new HashMap<>();
                response.put("token", jwt);
                return new ResponseEntity<>(response, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
