package com.bhan.ked.service;

import com.bhan.ked.entity.MaintenanceDetailEntity;
import com.bhan.ked.entity.UserDetailEntity;
import com.bhan.ked.model.MaintenanceDetail;
import com.bhan.ked.repository.MaintenanceDetailRepository;
import com.bhan.ked.repository.UserDetailRepository;
import io.micronaut.security.utils.SecurityService;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;

import java.util.Optional;

@Singleton
public class UserDetailService {

    @Inject
    private UserDetailRepository userDetailRepository;
    @Inject
    private SecurityService securityService;

    public Optional<UserDetailEntity> getUserDetailByMobileNumber(String mobileNo){
        return userDetailRepository.getByMobileNo(mobileNo);
    }
}
