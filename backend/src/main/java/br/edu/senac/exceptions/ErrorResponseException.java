package br.edu.senac.exceptions;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonIncludeProperties;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Getter
@JsonIncludeProperties({"status", "message", "errors"})
@JsonIgnoreProperties({"cause", "stackTrace", "localizedMessage", "suppressed"})
public class ErrorResponseException extends RuntimeException {

    private final int status;
    private final String message;
    private List<Error> errors;

    public ErrorResponseException(HttpStatus status, String message) {
        super(message);
        this.status = status.value();
        this.message = message;
    }

    public ErrorResponseException(int status, String message, List<Error> errors) {
        super(message);
        this.status = status;
        this.message = message;
        this.errors = errors;
    }

    public void addError(String field, String message) {
        if (Objects.isNull(errors)) {
            this.errors = new ArrayList<>();
        }
        this.errors.add(new Error(field, message));
    }

    private record Error(String field, String message) {

    }

}

