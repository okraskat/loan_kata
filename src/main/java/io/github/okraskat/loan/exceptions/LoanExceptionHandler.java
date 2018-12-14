package io.github.okraskat.loan.exceptions;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
class LoanExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {LoanNotExistsException.class})
    ResponseEntity<Object> handleLoanNotExistsException(RuntimeException exception, WebRequest request) {
        return handleExceptionInternal(exception, exception.getMessage(), new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler(value = {LoanAmountExceededException.class})
    ResponseEntity<Object> handleLoanAmountExceededException(RuntimeException exception, WebRequest request) {
        return handleExceptionInternal(exception, exception.getMessage(), new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler(value = {LoanTermExceededException.class})
    ResponseEntity<Object> handleLoanTermExceededException(RuntimeException exception, WebRequest request) {
        return handleExceptionInternal(exception, exception.getMessage(), new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler(value = {MaximumLoanAppliedInForbiddenHoursException.class})
    ResponseEntity<Object> MaxiumLoanAppliedInForbiddenHoursException(RuntimeException exception, WebRequest request) {
        return handleExceptionInternal(exception, exception.getMessage(), new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }
}
