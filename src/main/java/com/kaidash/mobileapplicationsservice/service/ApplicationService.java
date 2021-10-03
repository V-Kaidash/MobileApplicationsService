package com.kaidash.mobileapplicationsservice.service;

import com.kaidash.mobileapplicationsservice.entity.Application;
import com.kaidash.mobileapplicationsservice.entity.ApplicationIdsComparisonRequest;
import com.kaidash.mobileapplicationsservice.entity.ApplicationProcessingResponse;
import com.kaidash.mobileapplicationsservice.entity.ContentRateEntity;
import java.util.List;

public interface ApplicationService {

    public List<Application> findAll();

    public ApplicationProcessingResponse findApplicationsByIdAndCompareVersions(ApplicationIdsComparisonRequest applicationIdsComparisonRequest);

    public Application findApplicationById(int applicationId);

    public ApplicationProcessingResponse saveApplication(Application application);

    public ApplicationProcessingResponse deleteApplicationById(int applicationId);

    public ContentRateEntity getContentRateCount();

}
