package io.github.okraskat.loan.api;

import io.github.okraskat.loan.api.dto.ApplyForLoanRequest;
import io.github.okraskat.loan.exceptions.LoanAmountExceededException;
import io.github.okraskat.loan.exceptions.LoanTermExceededException;
import io.github.okraskat.loan.exceptions.MaximumLoanAppliedInForbiddenHoursException;
import io.github.okraskat.loan.terms.LoanTermProvider;
import org.apache.commons.lang3.Range;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

@Component
class LoanRequestValidator {

    private final LoanTermProvider loanTermProvider;
    private final DateTimeProvider dateTimeProvider;

    @Autowired
    public LoanRequestValidator(LoanTermProvider loanTermProvider, DateTimeProvider dateTimeProvider) {
        this.loanTermProvider = loanTermProvider;
        this.dateTimeProvider = dateTimeProvider;
    }

    void validate(ApplyForLoanRequest applyForLoanRequest) {
        BigDecimal loanAmount = applyForLoanRequest.getLoanAmount();
        Range<BigDecimal> loanAmountRange = loanTermProvider.getLoanAmountRange();

        validateLoanAmount(loanAmount, loanAmountRange);

        validateLoanTerm(applyForLoanRequest);

        validateMaxLoanAmountInForbiddenHours(loanAmount, loanAmountRange);
    }

    private void validateLoanAmount(BigDecimal loanAmount, Range<BigDecimal> loanAmountRange) {
        if (!loanAmountRange.contains(loanAmount)) {
            throw new LoanAmountExceededException(loanAmount, loanAmountRange);
        }
    }

    private void validateLoanTerm(ApplyForLoanRequest applyForLoanRequest) {
        long loanTermInDays = ChronoUnit.DAYS.between(applyForLoanRequest.getLoanStartDate(), applyForLoanRequest.getLoanEndDate());
        Range<Long> loanTermRange = loanTermProvider.getLoanPeriodRangeInDays();
        if (!loanTermRange.contains(loanTermInDays)) {
            throw new LoanTermExceededException(loanTermInDays, loanTermRange);
        }
    }

    private void validateMaxLoanAmountInForbiddenHours(BigDecimal loanAmount, Range<BigDecimal> loanAmountRange) {
        LocalTime currentTime = dateTimeProvider.getLocalTime();
        if (loanAmountRange.getMaximum().compareTo(loanAmount) == 0 && loanAppliedInForbiddenHours(currentTime)) {
            throw new MaximumLoanAppliedInForbiddenHoursException(loanAmount, currentTime);
        }
    }

    private boolean loanAppliedInForbiddenHours(LocalTime currentTime) {
        return loanTermProvider.getForbiddenHoursRange().contains(currentTime);
    }
}
