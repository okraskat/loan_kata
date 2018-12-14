package io.github.okraskat.loan.service;

import io.github.okraskat.loan.domain.Loan;
import io.github.okraskat.loan.exceptions.LoanNotExistsException;
import io.github.okraskat.loan.repository.LoanRepository;
import io.github.okraskat.loan.terms.LoanTermProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Optional;

@Service
public class LoanService {
    private final LoanRepository loanRepository;
    private final LoanCostCalculator loanCostCalculator;
    private final LoanTermProvider loanTermProvider;

    @Autowired
    public LoanService(LoanRepository loanRepository, LoanCostCalculator loanCostCalculator,
                       LoanTermProvider loanTermProvider) {
        this.loanRepository = loanRepository;
        this.loanCostCalculator = loanCostCalculator;
        this.loanTermProvider = loanTermProvider;
    }

    public Loan applyLoan(BigDecimal loanAmount, LocalDate loanEndDate) {
        BigDecimal cost = loanCostCalculator.calculateCost(loanAmount);
        Loan loan = new Loan();
        loan.setAmount(loanAmount);
        loan.setCost(cost);
        loan.setDueDate(loanEndDate.atTime(LocalTime.MAX));
        loanRepository.save(loan);
        return loan;
    }

    public LocalDateTime extendLoan(long loanId) {
        Optional<Loan> loan = loanRepository.findLoan(loanId);
        return loan.map(Loan::getDueDate)
                .map(this::getNewDueDate)
                .orElseThrow(() -> new LoanNotExistsException(loanId));
    }

    private LocalDateTime getNewDueDate(LocalDateTime dueDate) {
        return dueDate.plusDays(loanTermProvider.getExtensionPeriodInDays());
    }
}
