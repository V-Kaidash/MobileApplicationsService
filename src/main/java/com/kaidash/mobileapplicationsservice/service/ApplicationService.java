package com.kaidash.mobileapplicationsservice.service;

import com.kaidash.mobileapplicationsservice.entity.Application;
import com.kaidash.mobileapplicationsservice.entity.ApplicationComparisonEntity;
import com.kaidash.mobileapplicationsservice.entity.ApplicationProcessingResponse;
import com.kaidash.mobileapplicationsservice.entity.ContentRateEntity;
import com.kaidash.mobileapplicationsservice.repository.ApplicationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface ApplicationService {

    public List<Application> findAll();

    public ApplicationProcessingResponse findApplicationsByIdAndCompareVersions(ApplicationComparisonEntity applicationComparisonEntity);

    public Application findApplicationById(int applicationId);

    public ApplicationProcessingResponse saveApplication(Application application);

    public ApplicationProcessingResponse deleteApplicationById(int applicationId);

    public ContentRateEntity getContentRateCount();

}
