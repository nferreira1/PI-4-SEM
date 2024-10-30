package br.edu.senac.aspects;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validator;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Set;

@Aspect
@Component
public class ValidationAspect {

    @Autowired
    private Validator validator;

    @Pointcut("@annotation(br.edu.senac.annotations.ValidateBeforeExecutionAnnotation)")
    public void validateBeforeExecution() {

    }

    @Before("validateBeforeExecution()")
    public void validateMethodArguments(JoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();

        for (Object arg : args) {
            Set<ConstraintViolation<Object>> violations = validator.validate(arg);

            if (!violations.isEmpty()) {
                throw new ConstraintViolationException(violations);
            }
        }
    }
}
