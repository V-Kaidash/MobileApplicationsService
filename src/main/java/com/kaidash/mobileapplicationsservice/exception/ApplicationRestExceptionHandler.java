package com.kaidash.mobileapplicationsservice.exception;

import com.kaidash.mobileapplicationsservice.entity.ApplicationProcessingResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.persistence.EntityNotFoundException;

@ControllerAdvice
public class ApplicationRestExceptionHandler {

    /**
     * Exception handler for catching EntityNotFoundException
     *
     * @return error response in JSON
     */
    @ExceptionHandler
    public ResponseEntity<ApplicationProcessingResponse> handleException(EntityNotFoundException exc){

        ApplicationProcessingResponse errorResponse = new ApplicationProcessingResponse(
                HttpStatus.NOT_FOUND.value(),
                exc.getMessage(),
                System.currentTimeMillis());

        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    /**
     * Exception handler for catching any Exception
     *
     * @return error response in JSON
     */
    @ExceptionHandler
    public ResponseEntity<ApplicationProcessingResponse> handleException(Exception exc){

        ApplicationProcessingResponse errorResponse = new ApplicationProcessingResponse(
                HttpStatus.BAD_REQUEST.value(),
                exc.getMessage(),
                System.currentTimeMillis());

        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }
}
