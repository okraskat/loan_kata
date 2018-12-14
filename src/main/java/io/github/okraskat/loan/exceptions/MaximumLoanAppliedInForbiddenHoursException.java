package io.github.okraskat.loan.exceptions;

import java.math.BigDecimal;
import java.time.LocalTime;

public class MaximumLoanAppliedInForbiddenHoursException extends RuntimeException {
    public MaximumLoanAppliedInForbiddenHoursException(BigDecimal loanAmount, LocalTime currentTime) {
        super(String.format("Loan amount %g applied at %s was rejected.", loanAmount, currentTime.toString()));
    }
}
