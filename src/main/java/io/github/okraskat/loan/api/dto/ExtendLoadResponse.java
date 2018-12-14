package io.github.okraskat.loan.api.dto;

import com.google.common.base.MoreObjects;

import java.time.LocalDateTime;

public class ExtendLoadResponse {
    private LocalDateTime extendedDueDate;

    public ExtendLoadResponse(LocalDateTime extendedDueDate) {
        this.extendedDueDate = extendedDueDate;
    }

    public LocalDateTime getExtendedDueDate() {
        return extendedDueDate;
    }

    public void setExtendedDueDate(LocalDateTime extendedDueDate) {
        this.extendedDueDate = extendedDueDate;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("extendedDueDate", extendedDueDate)
                .toString();
    }
}
