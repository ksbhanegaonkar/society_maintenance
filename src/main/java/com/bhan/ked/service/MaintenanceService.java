package com.bhan.ked.service;

import com.bhan.ked.entity.MaintenanceDetailEntity;
import com.bhan.ked.entity.UserDetailEntity;
import com.bhan.ked.model.MaintenanceDetail;
import com.bhan.ked.repository.MaintenanceDetailRepository;
import com.bhan.ked.repository.UserDetailRepository;
import io.micronaut.security.utils.SecurityService;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;

import java.time.LocalDate;
import java.util.Optional;

@Singleton
public class MaintenanceService {

    @Inject
    private MaintenanceDetailRepository maintenanceDetailRepository;
    @Inject
    private UserDetailRepository userDetailRepository;
    @Inject
    private SecurityService securityService;

    public void saveMaintenanceDetail(MaintenanceDetail maintenanceDetail, String mobileNo){
        Optional<UserDetailEntity> userDetail = userDetailRepository.getByMobileNo(mobileNo);
        if(userDetail.isPresent()) {
            MaintenanceDetailEntity entity = new MaintenanceDetailEntity();
            entity.setPaidYear(LocalDate.now().getYear());
            //entity.setPaidMonth(maintenanceDetail.getPaidMonth());
            entity.setAmount(maintenanceDetail.getAmount());
            entity.setUserId(userDetail.get().getId());
            maintenanceDetailRepository.save(entity);
        }
    }
}
