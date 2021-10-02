package com.kaidash.mobileapplicationsservice.controller;

import com.kaidash.mobileapplicationsservice.entity.Application;
import com.kaidash.mobileapplicationsservice.entity.ApplicationComparisonEntity;
import com.kaidash.mobileapplicationsservice.entity.ApplicationProcessingResponse;
import com.kaidash.mobileapplicationsservice.entity.ContentRateEntity;
import com.kaidash.mobileapplicationsservice.service.ApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;
import java.util.List;
import java.util.NoSuchElementException;

import static com.kaidash.mobileapplicationsservice.controller.EndPoints.*;

@RestController
@RequestMapping(API)
public class ApplicationRestController {

    private final ApplicationService applicationService;

    @Autowired
    public ApplicationRestController(ApplicationService applicationService) {
        this.applicationService = applicationService;
    }

    /**
     * Finds all applications.
     *
     * @return list of applications and HttpStatus 'OK'
     */
    @GetMapping(APPLICATIONS)
    public ResponseEntity<List<Application>> showAll(){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(applicationService.findAll());
    }

    /**
     * Finds application by id.
     *
     * @return application by id and HttpStatus 'OK'
     */
    @GetMapping(APPLICATION_BY_ID)
    public ResponseEntity<Application> showApplicationById(@PathVariable("applicationId") int applicationId){
        try{
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(applicationService.showApplicationById(applicationId));
        } catch (EmptyResultDataAccessException | EntityNotFoundException | NoSuchElementException exp){
            throw new EntityNotFoundException(exp.getMessage() + " for id - " + applicationId);
        }
    }

    /**
     * Saves new application.
     *
     * @return HttpStatus 'CREATED' and saves new post to db
     */
    @PostMapping(APPLICATIONS)
    public ResponseEntity<ApplicationProcessingResponse> saveApplication(@Valid @RequestBody Application application){
        ApplicationProcessingResponse applicationProcessingResponse;
        try{
            applicationService.saveApplication(application);
            applicationProcessingResponse = new ApplicationProcessingResponse(HttpStatus.CREATED.value(),
                    "Application was successfully saved", System.currentTimeMillis());
            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(applicationProcessingResponse);

        } catch (EmptyResultDataAccessException | EntityNotFoundException | NoSuchElementException exp){
            applicationProcessingResponse = new ApplicationProcessingResponse(HttpStatus.BAD_REQUEST.value(),
                    exp.getMessage(), System.currentTimeMillis());
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(applicationProcessingResponse);
        }
    }

    /**
     * Deletes application by id.
     *
     * @return HttpStatus 'CREATED' and saves new post to db
     */
    @DeleteMapping(APPLICATION_BY_ID)
    public ResponseEntity<ApplicationProcessingResponse> deleteApplicationById(
                                                                    @PathVariable("applicationId") int applicationId){
        ApplicationProcessingResponse applicationProcessingResponse;
        try{
            applicationService.deleteApplicationById(applicationId);
            applicationProcessingResponse = new ApplicationProcessingResponse(HttpStatus.OK.value(),
                    String.format("Application with id %s deleted successfully", applicationId), System.currentTimeMillis());

        } catch (EmptyResultDataAccessException | EntityNotFoundException | NoSuchElementException exp){
            applicationProcessingResponse = new ApplicationProcessingResponse(HttpStatus.NOT_FOUND.value(),
                   exp.getMessage(), System.currentTimeMillis());
        }
        return ResponseEntity.ok().body(applicationProcessingResponse);
    }

    /**
     * Compare versions of 2 or more applications provided in request body.
     *
     * @return HttpStatus 'OK' and comparison result
     */
    @PostMapping(APPLICATIONS_COMPARISON)
    public ResponseEntity<ApplicationProcessingResponse> saveApplication(
            @RequestBody ApplicationComparisonEntity applicationComparisonEntity){
        List<Integer> applicationIds = applicationComparisonEntity.getId();

        List<Application> applications = applicationService.findApplicationsById(applicationIds);

        ApplicationProcessingResponse applicationProcessingResponse;

        // checking that amount of applications was greater than 1 or sending error message
        if(applications.size() <= 1){
            applicationProcessingResponse = new ApplicationProcessingResponse(HttpStatus.BAD_REQUEST.value(),
                    "Seems like ids either not valid or have emply values."
                    + " Should be at least 2 correct ids passed for version comparison.", System.currentTimeMillis());
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(applicationProcessingResponse);
        }

        String responseMessage = "";

        for(int i = 0; i < applications.size(); i++){
            if(i == 0){
                responseMessage = responseMessage + "Application " + applications.get(i).getName()
                                    + " " + applications.get(i).getVersion();
            } else if(i == 1){
                responseMessage = responseMessage + " is greater than " + applications.get(i).getName()
                        + " " + applications.get(i).getVersion();
            } else {
                responseMessage = responseMessage + " and greater than " + applications.get(i).getName()
                        + " " + applications.get(i).getVersion();
            }

        }

        applicationProcessingResponse = new ApplicationProcessingResponse(HttpStatus.OK.value(),
                responseMessage, System.currentTimeMillis());
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(applicationProcessingResponse);
    }

    /**
     * Stat data about amount of applications with specified content rate.
     *
     * @return stats by content rate and HttpStatus 'OK'
     */
    @GetMapping(APPLICATIONS_CONTENT_RATE_STATS)
    public ResponseEntity<ContentRateEntity> showContentRateStats(){

        ContentRateEntity contentRateEntity = new ContentRateEntity();
        contentRateEntity.setContentRateName("Count of applications with specified content rates");
        contentRateEntity.setContentRateAmountFor_3(applicationService.getContentRateCount(3));
        contentRateEntity.setContentRateAmountFor_7(applicationService.getContentRateCount(7));
        contentRateEntity.setContentRateAmountFor_12(applicationService.getContentRateCount(12));
        contentRateEntity.setContentRateAmountFor_16(applicationService.getContentRateCount(16));
        contentRateEntity.setContentRateAmountFor_18(applicationService.getContentRateCount(18));

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(contentRateEntity);
    }
}
