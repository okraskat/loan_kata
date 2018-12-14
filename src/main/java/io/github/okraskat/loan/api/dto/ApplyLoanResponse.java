package io.github.okraskat.loan.api.dto;

import com.google.common.base.MoreObjects;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class ApplyLoanResponse {
    private long loanId;
    private BigDecimal loanCoast;
    private BigDecimal totalAmountToPay;
    private LocalDateTime dueDate;

    public ApplyLoanResponse() {
    }

    public ApplyLoanResponse(long loanId, BigDecimal loanCoast, BigDecimal totalAmountToPay, LocalDateTime dueDate) {
        this.loanId = loanId;
        this.loanCoast = loanCoast;
        this.totalAmountToPay = totalAmountToPay;
        this.dueDate = dueDate;
    }

    public long getLoanId() {
        return loanId;
    }

    public void setLoanId(long loanId) {
        this.loanId = loanId;
    }

    public BigDecimal getLoanCoast() {
        return loanCoast;
    }

    public void setLoanCoast(BigDecimal loanCoast) {
        this.loanCoast = loanCoast;
    }

    public BigDecimal getTotalAmountToPay() {
        return totalAmountToPay;
    }

    public void setTotalAmountToPay(BigDecimal totalAmountToPay) {
        this.totalAmountToPay = totalAmountToPay;
    }

    public LocalDateTime getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDateTime dueDate) {
        this.dueDate = dueDate;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("loanId", loanId)
                .add("loanCoast", loanCoast)
                .add("totalAmountToPay", totalAmountToPay)
                .add("dueDate", dueDate)
                .toString();
    }
}
