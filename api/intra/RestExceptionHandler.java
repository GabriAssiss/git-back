package com.cit.backend.api.intra;

import java.util.List;
import java.util.stream.Collectors;

import com.auth0.jwt.exceptions.JWTVerificationException;
import com.cit.backend.exceptions.*;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import com.cit.backend.api.intra.message.RestErrorMessage;
import com.cit.backend.api.intra.message.RestErrorMissingVariableMessage;
import com.fasterxml.jackson.databind.JsonMappingException;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(MissingVariableException.class)
    public ResponseEntity<RestErrorMissingVariableMessage> handlerMissingVariable(MissingVariableException exception) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        RestErrorMissingVariableMessage message = new RestErrorMissingVariableMessage(status, exception.getMessage(), exception.getMissingVariables());
        return ResponseEntity.status(status).body(message);
    }

    @ExceptionHandler(UniqueColumnAlreadyExistsException.class)
    public ResponseEntity<RestErrorMessage> handlerUniqueColumnAlreadyExists(UniqueColumnAlreadyExistsException exception) {
        HttpStatus status = HttpStatus.CONFLICT;
        RestErrorMessage message = new RestErrorMessage(status, exception.getMessage());
        return ResponseEntity.status(status).body(message);
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<RestErrorMessage> handlerBadCredentials(BadCredentialsException exception) {
        HttpStatus status = HttpStatus.UNAUTHORIZED;
        RestErrorMessage message = new RestErrorMessage(status, exception.getMessage());
        return ResponseEntity.status(status).body(message);
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<RestErrorMessage> handlerAccessException(AccessDeniedException exception) {
        HttpStatus status = HttpStatus.FORBIDDEN;
        RestErrorMessage message = new RestErrorMessage(status, exception.getMessage());
        return ResponseEntity.status(status).body(message);
    }

    @ExceptionHandler(JWTVerificationException.class)
    public ResponseEntity<RestErrorMessage> handlerSignatureException(JWTVerificationException exception) {
        HttpStatus status = HttpStatus.UNAUTHORIZED;
        RestErrorMessage message = new RestErrorMessage(status, exception.getMessage());
        return ResponseEntity.status(status).body(message);
    }

    @ExceptionHandler(InternalAuthenticationServiceException.class)
    public ResponseEntity<RestErrorMessage> handlerInternalAuthenticationServiceException(InternalAuthenticationServiceException exception) {
        HttpStatus status = HttpStatus.UNAUTHORIZED;
        RestErrorMessage message = new RestErrorMessage(status, "Invalid credentials");
        return ResponseEntity.status(status).body(message);
    }

    @ExceptionHandler(UserDoesNotExistsException.class)
    public ResponseEntity<RestErrorMessage> handlerUserDoesNotExistsExceptions(UserDoesNotExistsException exception) {
        HttpStatus status = HttpStatus.UNAUTHORIZED;
        RestErrorMessage message = new RestErrorMessage(status, exception.getMessage());
        return ResponseEntity.status(status).body(message);
    }

    @ExceptionHandler(FileDoesNotExistsException.class)
    public ResponseEntity<RestErrorMessage> handlerFileDoesNotExistsException(FileDoesNotExistsException exception) {
        HttpStatus status = HttpStatus.NOT_FOUND;
        RestErrorMessage message = new RestErrorMessage(status, exception.getMessage());
        return ResponseEntity.status(status).body(message);
    }

    @ExceptionHandler(InvalidTokenException.class)
    public ResponseEntity<RestErrorMessage> handlerInvalidTokenException(InvalidTokenException exception) {
        HttpStatus status = HttpStatus.UNAUTHORIZED;
        RestErrorMessage message = new RestErrorMessage(status, exception.getMessage());
        return ResponseEntity.status(status).body(message);
    }

    @ExceptionHandler(InvalidApartmentTokenException.class)
    public ResponseEntity<RestErrorMessage> handlerInvalidRequestException(InvalidApartmentTokenException exception) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        RestErrorMessage message = new RestErrorMessage(status, exception.getMessage());
        return ResponseEntity.status(status).body(message);
    }

    @ExceptionHandler(InvalidRangeOfTimeException.class)
    public ResponseEntity<RestErrorMessage> handlerInvalidRangeOfTimeException(InvalidRangeOfTimeException exception) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        RestErrorMessage message = new RestErrorMessage(status, exception.getMessage());
        return ResponseEntity.status(status).body(message);
    }

    @ExceptionHandler(DateConflictException.class)
    public ResponseEntity<RestErrorMessage> handlerDateConflictException(DateConflictException exception) {
        HttpStatus status = HttpStatus.CONFLICT;
        RestErrorMessage message = new RestErrorMessage(status, exception.getMessage());
        return ResponseEntity.status(status).body(message);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<RestErrorMessage> handlerException(Exception exception) {
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        // TODO send exception message to response if in debug mode
        exception.printStackTrace();
        RestErrorMessage message = new RestErrorMessage(status, "Internal server error");
        return ResponseEntity.status(status).body(message);
    }

    @Override
    protected ResponseEntity<Object> handleNoResourceFoundException(NoResourceFoundException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        String errorText = "Api path not found";
        RestErrorMessage message = new RestErrorMessage(status, errorText);
        return ResponseEntity.status(status).body(message);
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException exception, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        String errorText = "Invalid request message";
        List<String> variables = null;
        Throwable cause = exception.getCause();
        RestErrorMessage message;

        message = new RestErrorMessage(status, errorText);
        if (cause instanceof com.fasterxml.jackson.databind.JsonMappingException jsonMappingException) {
            variables = jsonMappingException.getPath().stream()
                    .map(JsonMappingException.Reference::getFieldName)
                    .collect(Collectors.toList());
            message = new RestErrorMissingVariableMessage(status, errorText, variables);

        }

        return ResponseEntity.status(status).body(message);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException exception, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        String errorText = "Invalid arguments";

        List<String> errors = exception.getBindingResult().getFieldErrors().stream()
                .map(fieldError -> fieldError.getField() +": " + fieldError.getDefaultMessage())
                .collect(Collectors.toList());

        RestErrorMissingVariableMessage message = new RestErrorMissingVariableMessage(status, errorText, errors);
        return ResponseEntity.status(status).body(message);
    }

    public ResponseEntity<RestErrorMissingVariableMessage> missingVariableHandler(MissingVariableException exception) {
        RestErrorMissingVariableMessage erroMessage = new RestErrorMissingVariableMessage(HttpStatus.BAD_REQUEST, exception.getMessage(), exception.getMissingVariables());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erroMessage);
    }
}