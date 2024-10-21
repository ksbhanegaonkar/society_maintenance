package com.bhan.ked.service;

import com.bhan.ked.entity.MaintenanceDetailEntity;
import com.bhan.ked.model.MaintenanceDetail;
import com.bhan.ked.repository.MaintenanceDetailRepository;
import io.micronaut.security.utils.SecurityService;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;

@Singleton
public class MaintenanceService {

    @Inject
    private MaintenanceDetailRepository maintenanceDetailRepository;
    @Inject
    private SecurityService securityService;

    public void saveMaintenanceDetail(MaintenanceDetail maintenanceDetail){
        MaintenanceDetailEntity entity = new MaintenanceDetailEntity();
        entity.setPaidYear(maintenanceDetail.getPaidYear());
        entity.setPaidMonth(maintenanceDetail.getPaidMonth());
        entity.setAmount(maintenanceDetail.getAmount());
        entity.setUserId(maintenanceDetail.getUserId());
        maintenanceDetailRepository.save(entity);
    }
}
