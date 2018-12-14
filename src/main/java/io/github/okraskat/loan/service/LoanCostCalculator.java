package io.github.okraskat.loan.service;

import io.github.okraskat.loan.terms.LoanTermProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Service
class LoanCostCalculator {
    private final LoanTermProvider loanTermProvider;

    @Autowired
    LoanCostCalculator(LoanTermProvider loanTermProvider) {
        this.loanTermProvider = loanTermProvider;
    }

    BigDecimal calculateCost(BigDecimal loanAmount) {
        return loanAmount.multiply(loanTermProvider.getLoanInterestRate()).setScale(2, RoundingMode.HALF_UP);
    }
}
