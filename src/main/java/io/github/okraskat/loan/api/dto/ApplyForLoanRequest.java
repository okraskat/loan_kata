package io.github.okraskat.loan.api.dto;

import com.google.common.base.MoreObjects;

import java.math.BigDecimal;
import java.time.LocalDate;

public class ApplyForLoanRequest {
    private BigDecimal loanAmount;
    private LocalDate loanStartDate;
    private LocalDate loanEndDate;

    public BigDecimal getLoanAmount() {
        return loanAmount;
    }

    public void setLoanAmount(BigDecimal loanAmount) {
        this.loanAmount = loanAmount;
    }

    public LocalDate getLoanStartDate() {
        return loanStartDate;
    }

    public void setLoanStartDate(LocalDate loanStartDate) {
        this.loanStartDate = loanStartDate;
    }

    public LocalDate getLoanEndDate() {
        return loanEndDate;
    }

    public void setLoanEndDate(LocalDate loanEndDate) {
        this.loanEndDate = loanEndDate;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("loanAmount", loanAmount)
                .add("loanStartDate", loanStartDate)
                .add("loanEndDate", loanEndDate)
                .toString();
    }
}
