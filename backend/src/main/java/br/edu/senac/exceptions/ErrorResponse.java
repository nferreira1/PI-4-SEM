package br.edu.senac.exceptions;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@RequiredArgsConstructor
public class ErrorResponse {

    private final int status;
    private final String message;
    private List<Error> errors;

    public void addError(String field, String message) {
        if (Objects.isNull(errors)) {
            this.errors = new ArrayList<>();
        }

        this.errors.add(new Error(field, message));
    }

    private record Error(String field, String message) {

    }

}
