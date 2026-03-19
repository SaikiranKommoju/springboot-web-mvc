package com.vsks.controller.advice;

import com.vsks.exception.StudentDatabaseException;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
@ResponseBody
public class RspEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    @Transactional
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        List<ObjectError> errors = ex.getBindingResult().getAllErrors();
        return ResponseEntity.badRequest().body(errors.stream().map(DefaultMessageSourceResolvable::getDefaultMessage).collect(Collectors.joining(", ")));
    }

    @ExceptionHandler(StudentDatabaseException.class)
    public ResponseEntity<Object> handleStudentDatabaseException(StudentDatabaseException e, HttpServletResponse response) {
        System.out.println("Handling Student database exception ...");
        return ResponseEntity.status(HttpStatus.BAD_GATEWAY).body(e.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleInternalServerError(Exception e, HttpServletResponse response) {
        System.out.println("Handling Internal server error ...");
        String errorMessage = (null != e.getMessage() && !e.getMessage().trim().isEmpty()) ? e.getMessage() : "Internal Server Error";
        return ResponseEntity.status(500).body(errorMessage);
    }
}
