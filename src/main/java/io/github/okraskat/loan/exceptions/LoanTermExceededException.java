package io.github.okraskat.loan.exceptions;


import org.apache.commons.lang3.Range;

public class LoanTermExceededException extends RuntimeException {
    public LoanTermExceededException(long loanTermInDays, Range<Long> loanTermRange) {
        super(String.format("Requested loan %d term in days exceeded loan term limit %d-%d", loanTermInDays, loanTermRange.getMinimum(),
                loanTermRange.getMaximum()));
    }
}
