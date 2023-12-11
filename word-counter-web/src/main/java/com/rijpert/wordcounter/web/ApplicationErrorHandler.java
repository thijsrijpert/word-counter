package com.rijpert.wordcounter.web;

import com.rijpert.wordcounter.web.dto.ErrorDTO;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class ApplicationErrorHandler {

    private static final Logger logger = LoggerFactory.getLogger(ApplicationErrorHandler.class);

    @ExceptionHandler({MissingServletRequestParameterException.class})
    public ResponseEntity<List<ErrorDTO>> toResponse(MissingServletRequestParameterException exception) {
        logger.error("Exception was thrown while executing request {}", exception.getMessage());
        return ResponseEntity.badRequest()
                .contentType(MediaType.APPLICATION_JSON)
                .body(List.of(new ErrorDTO()
                        .code("BAD_REQUEST")
                        .property(exception.getParameterName())
                        .message(exception.getBody().getDetail())
                ));
    }

    @ExceptionHandler({MethodArgumentNotValidException.class})
    public ResponseEntity<List<ErrorDTO>> toResponse(MethodArgumentNotValidException exception) {
        logger.error("Exception was thrown while executing request {}", exception.getMessage());
        List<ErrorDTO> errors = new ArrayList<>();
        for(FieldError error : exception.getBindingResult().getFieldErrors()) {
            errors.add(new ErrorDTO()
                    .code("BAD_REQUEST")
                    .property(error.getField())
                    .message(error.getDefaultMessage())
            );
        }

        for(ObjectError error : exception.getBindingResult().getGlobalErrors()) {
            errors.add(new ErrorDTO()
                    .code("BAD_REQUEST")
                    .property(error.getObjectName())
                    .message(error.getDefaultMessage())
            );
        }


        return ResponseEntity.badRequest()
                .contentType(MediaType.APPLICATION_JSON)
                .body(errors);
    }

    @ExceptionHandler({ConstraintViolationException.class})
    public ResponseEntity<List<ErrorDTO>> toResponse(ConstraintViolationException exception) {
        logger.error("Exception was thrown while executing request {}", exception.getMessage());
        List<ErrorDTO> errors = new ArrayList<>();
        for(ConstraintViolation<?> violation : exception.getConstraintViolations()) {
            errors.add(new ErrorDTO()
                    .code("BAD_REQUEST")
                    .property(violation.getPropertyPath().toString().substring(1))
                    .invalidValue(violation.getInvalidValue().toString())
                    .message(violation.getMessage()));
        }

        return ResponseEntity.badRequest()
                .contentType(MediaType.APPLICATION_JSON)
                .body(errors);
    }
}
