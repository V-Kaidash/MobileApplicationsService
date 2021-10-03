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
import java.util.Optional;

import static com.kaidash.mobileapplicationsservice.controller.EndPoints.*;

/**
 * The Application controller responsible for handling requests for apps.
 */
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
    public ResponseEntity<Application> findApplicationById(@PathVariable("applicationId") int applicationId){
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(applicationService.findApplicationById(applicationId));
    }

    /**
     * Saves new application.
     *
     * @return HttpStatus 'CREATED' and saves new post to db
     */
    @PostMapping(APPLICATIONS)
    public ResponseEntity<ApplicationProcessingResponse> saveApplication(@Valid @RequestBody Application application){
            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(applicationService.saveApplication(application));

    }

    /**
     * Deletes application by id.
     *
     * @return HttpStatus 'CREATED' and saves new post to db
     */
    @DeleteMapping(APPLICATION_BY_ID)
    public ResponseEntity<ApplicationProcessingResponse> deleteApplicationById(
                                                                    @PathVariable("applicationId") int applicationId){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(applicationService.deleteApplicationById(applicationId));
    }

    /**
     * Compare versions of 2 or more applications provided in request body.
     *
     * @return HttpStatus 'OK' and comparison result
     */
    @PostMapping(APPLICATIONS_COMPARISON)
    public ResponseEntity<ApplicationProcessingResponse> compareApplicationsByVersion(
            @RequestBody ApplicationComparisonEntity applicationComparisonEntity){

        ApplicationProcessingResponse applicationProcessingResponse = applicationService
                                            .findApplicationsByIdAndCompareVersions(applicationComparisonEntity);

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
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(applicationService.getContentRateCount());
    }
}
