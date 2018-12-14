package io.github.okraskat.loan.exceptions;

public class LoanNotExistsException extends RuntimeException {
    public LoanNotExistsException(long loanId) {
        super(String.format("Could not extend not existing loan with id %d", loanId));
    }
}
