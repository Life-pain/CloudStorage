package ru.netology.advice;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.netology.dto.ExceptionDescription;
import ru.netology.exception.AuthorizationException;

@RestControllerAdvice
public class ControllerExceptionHandler {

    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(AuthorizationException.class)
    public ExceptionDescription exceptionInLogin(AuthorizationException e) {
        return new ExceptionDescription(e.getMessage());
    }
}
