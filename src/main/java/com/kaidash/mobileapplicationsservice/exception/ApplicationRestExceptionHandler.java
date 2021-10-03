package com.kaidash.mobileapplicationsservice.exception;

import com.kaidash.mobileapplicationsservice.entity.ApplicationValidationErrorEntity;
import com.kaidash.mobileapplicationsservice.entity.ApplicationProcessingResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.persistence.EntityNotFoundException;

@ControllerAdvice
public class ApplicationRestExceptionHandler {

    /**
     * Exception handler for validation MethodArgumentNotValidException
     *
     * @return error response in JSON
     */
    @ExceptionHandler
    public ResponseEntity<ApplicationValidationErrorEntity> handleException(MethodArgumentNotValidException exc){

        ApplicationValidationErrorEntity applicationValidationErrorEntity = new ApplicationValidationErrorEntity(
                HttpStatus.BAD_REQUEST.value(),
                (exc.getFieldError("name") == null) ? "OK" : "[" + exc.getFieldError("name").getRejectedValue() + "] - " +
                        exc.getFieldError("name").getDefaultMessage(),
                (exc.getFieldError("version") == null) ? "OK" : "[" + exc.getFieldError("version").getRejectedValue() + "] - " +
                        exc.getFieldError("version").getDefaultMessage(),
                (exc.getFieldError("contentRate") == null) ? "OK" : "[" + exc.getFieldError("contentRate").getRejectedValue() + "] - " +
                        exc.getFieldError("contentRate").getDefaultMessage(),
                System.currentTimeMillis()
        );

        return new ResponseEntity<>(applicationValidationErrorEntity, HttpStatus.BAD_REQUEST);
    }

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
