package io.github.okraskat.loan

import io.github.okraskat.loan.api.dto.ApplyForLoanRequest
import io.github.okraskat.loan.api.dto.ApplyLoanResponse
import io.github.okraskat.loan.markers.IntegrationTest
import org.junit.experimental.categories.Category
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.http.HttpStatus
import spock.lang.Specification

import java.time.LocalDate
import java.time.LocalTime

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Category(IntegrationTest.class)
class IntegrationSpec extends Specification {

    @Autowired
    TestRestTemplate testRestTemplate

    def "should apply loan"() {
        given:
        def loanAmount = BigDecimal.valueOf(150L)
        def startDate = LocalDate.now()
        def endDate = LocalDate.now().plusDays(25L)
        def request = buildRequest(loanAmount, startDate, endDate)

        when:
        def res = testRestTemplate.postForEntity('/loans', request, ApplyLoanResponse)

        then:
        res.statusCode == HttpStatus.OK
        res.body.loanId > 0
        res.body.loanCoast == 15L
        res.body.totalAmountToPay == 165L
        res.body.dueDate == LocalDate.now().plusDays(25L).atTime(LocalTime.MAX)
    }

    def buildRequest(loanAmount, startDate, endDate) {
        ApplyForLoanRequest request = new ApplyForLoanRequest()
        request.loanAmount = loanAmount
        request.loanStartDate = startDate
        request.loanEndDate = endDate
        request
    }

}
