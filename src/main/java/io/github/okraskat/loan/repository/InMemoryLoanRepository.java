package io.github.okraskat.loan.repository;

import io.github.okraskat.loan.domain.Loan;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Repository
class InMemoryLoanRepository implements LoanRepository {

    private static final ConcurrentHashMap<Long, Loan> LOAN_MAP = new ConcurrentHashMap<>();
    private static final AtomicLong ID_PROVIDER = new AtomicLong(1);

    @Override
    public long save(Loan loan) {
        long id = ID_PROVIDER.getAndIncrement();
        loan.setId(id);
        LOAN_MAP.put(id, loan);
        return id;
    }

    @Override
    public Optional<Loan> findLoan(long loanId) {
        return Optional.ofNullable(LOAN_MAP.get(loanId));
    }
}
