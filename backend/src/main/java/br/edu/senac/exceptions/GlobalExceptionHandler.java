package br.edu.senac.exceptions;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


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

        ErrorResponseException errorResponse = new ErrorResponseException(HttpStatus.UNPROCESSABLE_ENTITY, "Erro de validação.");

        for (ConstraintViolation<?> violation : ex.getConstraintViolations()) {
            errorResponse.addError(violation.getPropertyPath().toString(), violation.getMessage());
        }

        return buildErrorResponse(errorResponse);
    }

    //FIXME Precisamos descobrir uma forma de retornar qual tipo de valor ta duplicado
//    @ExceptionHandler(DataIntegrityViolationException.class)
//    protected  ResponseEntity<Object> handleDataIntegrityViolationException(DataIntegrityViolationException ex){
//        ErrorResponseException errorResponseException = new ErrorResponseException(HttpStatus.CONFLICT, ex.);
//
//    }
//    // COLOCAR EM PRODUÇÃO
//    @ExceptionHandler(Throwable.class)
//    protected ResponseEntity<Object> handleException(ErrorResponseException ex) {
//        return buildErrorResponse(ex);
//    }
}
