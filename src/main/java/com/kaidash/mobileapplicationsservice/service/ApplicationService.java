package com.kaidash.mobileapplicationsservice.service;

import com.kaidash.mobileapplicationsservice.entity.Application;
import com.kaidash.mobileapplicationsservice.repository.ApplicationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ApplicationService {

    private final ApplicationRepository applicationRepository;

    @Autowired
    public ApplicationService(ApplicationRepository applicationRepository) {
        this.applicationRepository = applicationRepository;
    }

    public List<Application> findAll(){
        return applicationRepository.findAll();
    }

    public List<Application> findApplicationsById(List<Integer> id){
        return applicationRepository.findApplicationsById(id);
    }

    public Application showApplicationById(int applicationId) {
        return applicationRepository.findById(applicationId).get();
    }

    public void saveApplication(Application application) {
        applicationRepository.save(application);
    }

    public String deleteApplicationById(int applicationId) {
        applicationRepository.deleteById(applicationId);
        return "Application with ID " + applicationId + " deleted successfully";
    }

    public int getContentRateCount(int contentRate) {
        return applicationRepository.getContentRateCount(contentRate);
    }
}
