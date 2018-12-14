package io.github.okraskat.loan.terms;

import org.apache.commons.lang3.Range;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalTime;

@Component
class PropertiesBasedLoanTermProvider implements LoanTermProvider {

    @Value("${extension.period.in.days}")
    private Integer extensionPeriodInDays;

    @Value("${loan.interest.rate}")
    private BigDecimal loanInterestRate;

    @Value("${minimum.loan.amount}")
    private BigDecimal minimumLoanAmount;

    @Value("${maximum.loan.amount}")
    private BigDecimal maximumLoanAmount;

    @Value("${minimum.loan.period.in.days}")
    private Long minimumLoanPeriodInDays;

    @Value("${maximum.loan.period.in.days}")
    private Long maximumLoanPeriodInDays;

    @Value("#{ T(java.time.LocalTime).parse('${forbidden.time.start}')}")
    private LocalTime startForbiddenTime;

    @Value("#{ T(java.time.LocalTime).parse('${forbidden.time.end}')}")
    private LocalTime endForbiddenTime;

    @Override
    public int getExtensionPeriodInDays() {
        return extensionPeriodInDays;
    }

    @Override
    public BigDecimal getLoanInterestRate() {
        return loanInterestRate;
    }

    @Override
    public Range<BigDecimal> getLoanAmountRange() {
        return Range.between(minimumLoanAmount, maximumLoanAmount);
    }

    @Override
    public Range<Long> getLoanPeriodRangeInDays() {
        return Range.between(minimumLoanPeriodInDays, maximumLoanPeriodInDays);
    }

    @Override
    public Range<LocalTime> getForbiddenHoursRange() {
        return Range.between(startForbiddenTime, endForbiddenTime);
    }
}
