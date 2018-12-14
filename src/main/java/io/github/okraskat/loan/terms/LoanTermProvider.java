package io.github.okraskat.loan.terms;


import org.apache.commons.lang3.Range;

import java.math.BigDecimal;
import java.time.LocalTime;

public interface LoanTermProvider {
    int getExtensionPeriodInDays();

    BigDecimal getLoanInterestRate();

    Range<BigDecimal> getLoanAmountRange();

    Range<Long> getLoanPeriodRangeInDays();

    Range<LocalTime> getForbiddenHoursRange();
}
