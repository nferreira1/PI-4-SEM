package br.edu.senac.exceptions;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.nio.file.attribute.UserPrincipalNotFoundException;


@Slf4j(topic = "GLOBAL_EXCEPTION_HANDLER")
@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    private ResponseEntity<Object> buildErrorResponse(ErrorResponseException exception) {
        return ResponseEntity.status(exception.getStatus()).body(new ErrorResponseException(exception.getStatus(), exception.getMessage(), exception.getErrors()));
    }

    @ExceptionHandler(ErrorResponseException.class)
    protected ResponseEntity<Object> handleErrorResponseException(ErrorResponseException ex) {
        return buildErrorResponse(ex);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    protected ResponseEntity<Object> handleConstraintViolationException(ConstraintViolationException ex) {

        ErrorResponseException errorResponseException = new ErrorResponseException(HttpStatus.UNPROCESSABLE_ENTITY, "Erro de validação.");

        for (ConstraintViolation<?> violation : ex.getConstraintViolations()) {
            errorResponseException.addError(violation.getPropertyPath().toString(), violation.getMessage());
        }

        return buildErrorResponse(errorResponseException);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    protected ResponseEntity<Object> handleDataIntegrityViolationException(DataIntegrityViolationException ex) {
        ErrorResponseException errorResponseException = new ErrorResponseException(
                HttpStatus.CONFLICT,
                "Erro de integridade de dados."
        );

        for (Throwable cause = ex.getCause(); cause != null; cause = cause.getCause()) {
            String message = cause.getMessage();

            if (message != null && message.contains("Key (")) {
                String fieldName = "campo desconhecido";
                String customMessage = "Registro duplicado.";

                int startIndex = message.indexOf("Key (") + 5;
                int endIndex = message.indexOf(")=", startIndex);

                if (startIndex > 0 && endIndex > startIndex) {
                    fieldName = message.substring(startIndex, endIndex);

                    int valueStartIndex = message.indexOf("=(", endIndex) + 2;
                    int valueEndIndex = message.indexOf(")", valueStartIndex);
                    String duplicateValue = message.substring(valueStartIndex, valueEndIndex);

                    customMessage = String.format("O %s '%s' já está cadastrado no sistema.", fieldName, duplicateValue);
                }

                errorResponseException.addError(fieldName, customMessage);

                break;
            }
        }

        return buildErrorResponse(errorResponseException);
    }

    @ExceptionHandler(UserPrincipalNotFoundException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ResponseEntity<Object> handleUserPrincipalNotFoundException(UserPrincipalNotFoundException userPrincipalNotFoundException) {
        return buildErrorResponse(new ErrorResponseException(HttpStatus.UNAUTHORIZED, "E-mail ou senha incorretos."));
    }

    @ExceptionHandler(BadCredentialsException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ResponseEntity<Object> handleUserPrincipalNotFoundException(BadCredentialsException badCredentialsException) {
        return buildErrorResponse(new ErrorResponseException(HttpStatus.UNAUTHORIZED, "E-mail ou senha incorretos."));
    }

    @ExceptionHandler(AccessDeniedException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ResponseEntity<Object> handleUserPrincipalNotFoundException(AccessDeniedException accessDeniedException) {
        return buildErrorResponse(new ErrorResponseException(HttpStatus.FORBIDDEN, "Você não tem permissão para acessar este recurso."));
    }

    // COLOCAR EM PRODUÇÃO
//    @ExceptionHandler(Throwable.class)
//    protected ResponseEntity<Object> handleException(Throwable ex) {
//        return buildErrorResponse(new ErrorResponseException(HttpStatus.INTERNAL_SERVER_ERROR, "Ocorreu um erro inesperado."));
//    }

}
