package io.github.okraskat.loan.domain;

import com.google.common.base.Preconditions;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Loan {
    private long id;
    private BigDecimal amount;
    private BigDecimal cost;
    private LocalDateTime dueDate;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public BigDecimal getCost() {
        return cost;
    }

    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public LocalDateTime getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDateTime dueDate) {
        this.dueDate = dueDate;
    }

    public BigDecimal getTotalAmountToPay() {
        Preconditions.checkNotNull(getAmount(), "Amount can not be null");
        Preconditions.checkNotNull(getCost(), "Cost can not be null");
        return getAmount().add(getCost());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        Loan loan = (Loan) o;

        return new EqualsBuilder()
                .append(id, loan.id)
                .append(amount, loan.amount)
                .append(cost, loan.cost)
                .append(dueDate, loan.dueDate)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(id)
                .append(amount)
                .append(cost)
                .append(dueDate)
                .toHashCode();
    }
}
