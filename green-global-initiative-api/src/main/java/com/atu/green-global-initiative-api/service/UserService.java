package com.atu.devops.service;

import com.atu.devops.dto.UserDetailsDto;
import com.atu.devops.model.dao.UserDetails;

import java.util.List;

public interface UserService {
    List<UserDetailsDto> getAllUserDetails();
    UserDetailsDto userSignUp(UserDetails userDetails);
    UserDetailsDto authenticateUser(String username, String password);
}
