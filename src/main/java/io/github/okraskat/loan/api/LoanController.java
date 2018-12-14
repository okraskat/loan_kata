package io.github.okraskat.loan.api;

import io.github.okraskat.loan.api.dto.ApplyForLoanRequest;
import io.github.okraskat.loan.api.dto.ApplyLoanResponse;
import io.github.okraskat.loan.api.dto.ExtendLoadResponse;
import io.github.okraskat.loan.domain.Loan;
import io.github.okraskat.loan.service.LoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/loans")
class LoanController {

    private final LoanRequestValidator loanRequestValidator;
    private final LoanService loanService;

    @Autowired
    public LoanController(LoanRequestValidator loanRequestValidator, LoanService loanService) {
        this.loanRequestValidator = loanRequestValidator;
        this.loanService = loanService;
    }

    @PostMapping
    public ApplyLoanResponse applyForLoan(@RequestBody ApplyForLoanRequest applyForLoanRequest) {
        loanRequestValidator.validate(applyForLoanRequest);
        Loan loanApplied = loanService.applyLoan(applyForLoanRequest.getLoanAmount(), applyForLoanRequest.getLoanEndDate());
        return new ApplyLoanResponse(loanApplied.getId(), loanApplied.getCost(), loanApplied.getTotalAmountToPay(),
                loanApplied.getDueDate());
    }

    @PutMapping("/{loanId}")
    public ExtendLoadResponse extendLoan(@PathVariable("loanId") long loanId) {
        LocalDateTime extendedDueDate = loanService.extendLoan(loanId);
        return new ExtendLoadResponse(extendedDueDate);
    }
}
