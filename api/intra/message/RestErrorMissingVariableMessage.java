package com.cit.backend.api.intra.message;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RestErrorMissingVariableMessage extends RestErrorMessage  {
    private List<String> variables;

    public RestErrorMissingVariableMessage(HttpStatus status, String message, List<String> missingVariables) {
        super(status, message);
        this.variables = missingVariables;
    }

    public RestErrorMissingVariableMessage(HttpStatusCode status, String message, List<String> missingVariables) {
        super(status, message);
        this.variables = missingVariables;
    }
}
