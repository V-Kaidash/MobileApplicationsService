package com.kaidash.mobileapplicationsservice.service;

import com.kaidash.mobileapplicationsservice.entity.Application;
import com.kaidash.mobileapplicationsservice.entity.ApplicationIdsComparisonRequest;
import com.kaidash.mobileapplicationsservice.entity.ApplicationProcessingResponse;
import com.kaidash.mobileapplicationsservice.entity.ContentRateEntity;
import com.kaidash.mobileapplicationsservice.repository.ApplicationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
public class ApplicationServiceImpl implements ApplicationService {

    private final ApplicationRepository applicationRepository;

    @Autowired
    public ApplicationServiceImpl(ApplicationRepository applicationRepository) {
        this.applicationRepository = applicationRepository;
    }

    @Override
    public List<Application> findAll(){
        return applicationRepository.findAll();
    }

    @Override
    public Application findApplicationById(int applicationId) {

        Optional<Application> applicationOptional = applicationRepository.findById(applicationId);
        if(!applicationOptional.isPresent()) {
            throw new EntityNotFoundException(String.format("Application id [%s] not found.", applicationId));
        }
        return applicationOptional.get();
    }

    @Override
    public ApplicationProcessingResponse saveApplication(Application application) {

            Optional<Application> duplicatedApplication = applicationRepository
                    .findApplicationByNameAndVersion(application.getName(), application.getVersion());
            if(duplicatedApplication.isPresent()) {
                throw new IllegalStateException(String.format("Application with name [%s] and version [%s] is already exists.",
                        application.getName(), application.getVersion()));
            }
            applicationRepository.save(application);

        return new ApplicationProcessingResponse(
            HttpStatus.CREATED.value(),
            String.format("Application [%s] (version: [%s]) was successfully saved",
                    application.getName(), application.getVersion()),
            System.currentTimeMillis());
    }

    @Override
    public ApplicationProcessingResponse deleteApplicationById(int applicationId) {

        Optional<Application> applicationOptional = applicationRepository.findById(applicationId);
        if(!applicationOptional.isPresent()) {
            throw new EntityNotFoundException(String.format("Application id [%s] not found.", applicationId));
        }

        applicationRepository.deleteById(applicationId);

        return new ApplicationProcessingResponse(HttpStatus.OK.value(),
                String.format("Application with id %s deleted successfully", applicationId), System.currentTimeMillis());
    }

    @Override
    public ApplicationProcessingResponse findApplicationsByIdAndCompareVersions(
                                                ApplicationIdsComparisonRequest applicationIdsComparisonRequest){

        List<Integer> applicationIdList = applicationIdsComparisonRequest.getId();

        List<Application> applications = applicationRepository.findApplicationsById(applicationIdList);

        // checking that amount of applications was greater than 1 or sending error message
        if(applications.size() <= 1){
            throw new IllegalStateException("Seems like passed less than 2 valid ids for version comparison.");
        }

        String responseMessage = "";

        for(int i = 0; i < applications.size(); i++){
            if(i == 0){
                responseMessage = responseMessage + "Application " + applications.get(i).getName()
                        + " " + applications.get(i).getVersion() + " version";
            } else if(i == 1){
                responseMessage = responseMessage + " is greater than " + applications.get(i).getName()
                        + " " + applications.get(i).getVersion() + " version";
            } else {
                responseMessage = responseMessage + " and greater than " + applications.get(i).getName()
                        + " " + applications.get(i).getVersion() + " version";
            }

        }

        return new ApplicationProcessingResponse(
                HttpStatus.OK.value(),
                responseMessage,
                System.currentTimeMillis());
    }

    @Override
    public ContentRateEntity getContentRateCount() {

        ContentRateEntity contentRateEntity = new ContentRateEntity();
        contentRateEntity.setContentRateName("Count of applications with specified content rates");
        contentRateEntity.setContentRateAmountFor_3(applicationRepository.getContentRateCount(3));
        contentRateEntity.setContentRateAmountFor_7(applicationRepository.getContentRateCount(7));
        contentRateEntity.setContentRateAmountFor_12(applicationRepository.getContentRateCount(12));
        contentRateEntity.setContentRateAmountFor_16(applicationRepository.getContentRateCount(16));
        contentRateEntity.setContentRateAmountFor_18(applicationRepository.getContentRateCount(18));

        return contentRateEntity;
    }

}
