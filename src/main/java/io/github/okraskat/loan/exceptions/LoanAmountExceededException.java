package io.github.okraskat.loan.exceptions;

import org.apache.commons.lang3.Range;

import java.math.BigDecimal;

public class LoanAmountExceededException extends RuntimeException {
    public LoanAmountExceededException(BigDecimal loanAmount, Range<BigDecimal> loanAmountRange) {
        super(String.format("Requested loan amount %g exceeded loan limit %g-%g", loanAmount, loanAmountRange.getMinimum(),
                loanAmountRange.getMaximum()));
    }
}
