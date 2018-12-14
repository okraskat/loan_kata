package io.github.okraskat.loan.service

import io.github.okraskat.loan.terms.LoanTermProvider
import spock.lang.Specification
import spock.lang.Subject

class LoanCostCalculatorSpec extends Specification {
    @Subject
    def loanCostCalculator

    def loanTermProvider = Mock(LoanTermProvider)

    void setup() {
        loanCostCalculator = new LoanCostCalculator(loanTermProvider)
    }

    def "should calculate loan cost properly"() {
        given:
        def loanAmount = BigDecimal.valueOf(1000)

        when:
        def cost = loanCostCalculator.calculateCost(loanAmount)

        then:
        loanTermProvider.getLoanInterestRate() >> BigDecimal.valueOf(0.125)

        cost != null
        cost == BigDecimal.valueOf(125)
    }

}
