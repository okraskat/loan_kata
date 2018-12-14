package io.github.okraskat.loan.repository;

import io.github.okraskat.loan.domain.Loan;

import java.util.Optional;

public interface LoanRepository {
    long save(Loan loan);

    Optional<Loan> findLoan(long loanId);
}
