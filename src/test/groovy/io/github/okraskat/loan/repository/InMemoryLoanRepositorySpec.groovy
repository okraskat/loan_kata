package io.github.okraskat.loan.repository

import io.github.okraskat.loan.domain.Loan
import spock.lang.Specification
import spock.lang.Subject

class InMemoryLoanRepositorySpec extends Specification {

    @Subject
    def repository

    void setup() {
        repository = new InMemoryLoanRepository()
    }

    def "should save loan with generated id"() {
        given:
        def loan = new Loan()

        when:
        repository.save(loan)

        then:
        loan.id > 0
        repository.findLoan(loan.id).isPresent()
        repository.findLoan(loan.id).get() == loan
    }

    def "should return empty loan for non existing id"() {
        when:
        def loan = repository.findLoan(-1)

        then:
        !loan.isPresent()
    }
}
