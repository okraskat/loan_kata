package io.github.okraskat.loan.api

import io.github.okraskat.loan.api.dto.ApplyForLoanRequest
import io.github.okraskat.loan.exceptions.LoanAmountExceededException
import io.github.okraskat.loan.exceptions.LoanTermExceededException
import io.github.okraskat.loan.exceptions.MaximumLoanAppliedInForbiddenHoursException
import io.github.okraskat.loan.terms.LoanTermProvider
import org.apache.commons.lang3.Range
import spock.lang.Specification
import spock.lang.Subject
import spock.lang.Unroll

import java.time.LocalDate
import java.time.LocalTime

class LoanRequestValidatorSpec extends Specification {
    @Subject
    def loanRequestValidator

    def loanTermProvider = Mock(LoanTermProvider)

    def dateTimeProvider = Mock(DateTimeProvider)

    void setup() {
        loanRequestValidator = new LoanRequestValidator(loanTermProvider, dateTimeProvider)
    }

    @Unroll
    def "should throw exception when loan request exceeded loan amount #loanAmount"() {
        given:
        def request = new ApplyForLoanRequest(
                loanAmount: loanAmount,
                loanStartDate: LocalDate.of(2018, 12, 01),
                loanEndDate: LocalDate.of(2018, 12, 15)
        )

        when:
        loanRequestValidator.validate(request)

        then:
        thrown(LoanAmountExceededException)
        loanTermProvider.getLoanAmountRange() >> Range.between(BigDecimal.valueOf(10), BigDecimal.valueOf(20))

        where:
        loanAmount << [9, 21]
    }

    @Unroll
    def "should throw exception when loan request exceeded loan term startDate: #startDate endDate :#endDate"() {
        given:
        def request = new ApplyForLoanRequest(
                loanAmount: 15,
                loanStartDate: startDate,
                loanEndDate: endDate
        )

        when:
        loanRequestValidator.validate(request)

        then:
        thrown(LoanTermExceededException)
        loanTermProvider.getLoanAmountRange() >> Range.between(BigDecimal.valueOf(10), BigDecimal.valueOf(20))
        loanTermProvider.getLoanPeriodRangeInDays() >> Range.between(20L, 30L)

        where:
        startDate                   |         endDate
        LocalDate.of(2018, 12, 01)  | LocalDate.of(2018, 12, 20)
        LocalDate.of(2018, 12, 01)  | LocalDate.of(2018, 12, 10)
    }

    def "should throw exception when max loan amount requested in forbidden hours"() {
        given:
        def request = new ApplyForLoanRequest(
                loanAmount: 20,
                loanStartDate: LocalDate.of(2018, 12, 01),
                loanEndDate: LocalDate.of(2018, 12, 21)
        )

        when:
        loanRequestValidator.validate(request)

        then:
        thrown(MaximumLoanAppliedInForbiddenHoursException)
        loanTermProvider.getLoanAmountRange() >> Range.between(BigDecimal.valueOf(10), BigDecimal.valueOf(20))
        loanTermProvider.getLoanPeriodRangeInDays() >> Range.between(20L, 30L)
        loanTermProvider.getForbiddenHoursRange() >> Range.between(LocalTime.of(0,0), LocalTime.of(6, 0))
        dateTimeProvider.getLocalTime() >> LocalTime.of(4, 40)
    }

    def "should not throw exception when request is valid"() {
        given:
        def request = new ApplyForLoanRequest(
                loanAmount: 20,
                loanStartDate: LocalDate.of(2018, 12, 01),
                loanEndDate: LocalDate.of(2018, 12, 21)
        )

        when:
        loanRequestValidator.validate(request)

        then:
        noExceptionThrown()
        loanTermProvider.getLoanAmountRange() >> Range.between(BigDecimal.valueOf(10), BigDecimal.valueOf(20))
        loanTermProvider.getLoanPeriodRangeInDays() >> Range.between(20L, 30L)
        loanTermProvider.getForbiddenHoursRange() >> Range.between(LocalTime.of(0,0), LocalTime.of(6, 0))
        dateTimeProvider.getLocalTime() >> LocalTime.of(7, 40)
    }
}
