package br.edu.senac.exceptions;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public class CustomException extends RuntimeException {

    private final int status;
    private final String message;

    public CustomException(HttpStatus status, String message) {
        super(message);
        this.status = status.value();
        this.message = message;
    }
}