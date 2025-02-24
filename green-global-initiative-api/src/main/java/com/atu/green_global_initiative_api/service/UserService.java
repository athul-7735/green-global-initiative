package com.atu.green_global_initiative_api.service;

import com.atu.green_global_initiative_api.dto.UserDetailsDto;
import com.atu.green_global_initiative_api.model.dao.UserDetails;

import java.util.List;

public interface UserService {
    List<UserDetailsDto> getAllUserDetails();
    UserDetailsDto userSignUp(UserDetails userDetails);
    UserDetailsDto authenticateUser(String username, String password);
    UserDetailsDto getAllUserDetailsById(Integer id);
    UserDetailsDto getAllUserDetailsByEmail(String email);
}
