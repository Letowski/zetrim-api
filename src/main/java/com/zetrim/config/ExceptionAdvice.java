package com.zetrim.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.NoSuchElementException;

@Slf4j
@RestControllerAdvice(basePackages = "com.letowski.secure.messaging.controller")
@Order(1)
public class ExceptionAdvice {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({IllegalArgumentException.class})
    public ApiError handleIllegalArgumentException(IllegalArgumentException exception) {
        log.info("handleIllegalArgumentException", exception);
        return new ApiError(exception.getMessage());
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler({IllegalStateException.class})
    public ApiError handleIllegalStateException(IllegalStateException exception) {
        log.info("handleIllegalStateException", exception);
        return new ApiError(exception.getMessage());
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NoSuchElementException.class)
    public ApiError handleNoSuchElementException(NoSuchElementException exception) {
        log.info("handleNoSuchElementException: {}", exception.getMessage());
        return new ApiError(exception.getMessage());
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public ApiError handleException(Exception exception) {
        log.info("handleException", exception);
        return new ApiError(exception.getMessage());
    }
}
