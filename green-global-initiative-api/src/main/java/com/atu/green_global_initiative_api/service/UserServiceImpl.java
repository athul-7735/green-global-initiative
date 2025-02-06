package com.atu.green_global_initiative_api.service;

import com.atu.green_global_initiative_api.dto.UserDetailsDto;
import com.atu.green_global_initiative_api.model.dao.ApplicationDetails;
import com.atu.green_global_initiative_api.model.dao.Grants;
import com.atu.green_global_initiative_api.model.dao.UserDetails;
import com.atu.green_global_initiative_api.repository.UserDetailsRepo;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDetailsRepo userDetailsRepo;

//    @Autowired
//    private PasswordEncoder passwordEncoder;

    @Override
    public List<UserDetailsDto> getAllUserDetails() {
        List<UserDetails> userDetailsList = userDetailsRepo.findAll();
        List<UserDetailsDto> userDetailsDtoList = new ArrayList<>();
        for (UserDetails userDetails : userDetailsList) {
             userDetailsDtoList.add(mapToUserDto(userDetails));
        }
        return userDetailsDtoList;
    }

    @Override
    public UserDetailsDto userSignUp(UserDetails userDetails) {
        UserDetails res = userDetailsRepo.save(userDetails);
        UserDetailsDto userDetailsDto = mapToUserDto(res);
        return userDetailsDto;
    }
    @Override
    public UserDetailsDto authenticateUser(String username, String password) {
        UserDetailsDto userDetails = null;
        List<UserDetails> users = userDetailsRepo.findAllByEmail(username);
        if(!users.isEmpty()){
            for (UserDetails user : users) {
                if (user != null
                        && user.getPassword().equals(password)
                ) {
                    userDetails = mapToUserDto(user);
                    return userDetails;
                }
                return userDetails;
            }
        }
        return userDetails;
    }

    private UserDetailsDto mapToUserDto(UserDetails userDetails){
        UserDetailsDto userDetailsDto = new UserDetailsDto();
        userDetailsDto.setUserId(userDetails.getUserId());
        userDetailsDto.setFirstName(userDetails.getFirstName());
        userDetailsDto.setLastName(userDetails.getLastName());
        userDetailsDto.setEmail(userDetails.getEmail());
        userDetailsDto.setAdmin(userDetails.isAdmin());
        List<ApplicationDetails> applicationDetailsList  = new ArrayList<>();
        if(userDetails.getApplicationDetails() != null && !userDetails.getApplicationDetails().isEmpty()){
            for(ApplicationDetails applicationDetails : userDetails.getApplicationDetails()){
                ApplicationDetails applicationDetail = new ApplicationDetails();
                applicationDetail.setApplicationId(applicationDetails.getApplicationId());
                applicationDetail.setApplicationStatus(applicationDetails.getApplicationStatus());
                applicationDetail.setOrganizationName(applicationDetails.getOrganizationName());
                Grants grant = new Grants();
                grant.setGrantId(applicationDetails.getGrants().getGrantId());
                grant.setGrantName(applicationDetails.getGrants().getGrantName());
                grant.setDescription(applicationDetails.getGrants().getDescription());
                grant.setAmount(applicationDetails.getGrants().getAmount());
                grant.setEligibility(applicationDetails.getGrants().getEligibility());
                applicationDetail.setGrants(grant);
                applicationDetailsList.add(applicationDetail);
            }
        }
        userDetailsDto.setApplicationDetails(applicationDetailsList);
        return userDetailsDto;
    }
}
