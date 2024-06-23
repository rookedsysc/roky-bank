package org.rookedsysc.loan.infrastructure.dto

import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.constraints.Pattern
import org.rookedsysc.loan.domain.validation.NullOrCheck
import org.rookedsysc.loan.domain.validation.ValidationType

data class LoanUpdateRequest(
        @field:Schema(description = "휴대폰 번호", example = "4365327698", nullable = true)
        @field:NullOrCheck(type = ValidationType.MOBILE, message = "Mobile number must be 10 digits")
        val mobileNumber: String?,

        @field:Schema(
                description = "Loan 고객 번호", example = "548732457654", nullable = false,
                minLength = 12, maxLength = 12
        )
        @field:Pattern(regexp = "(^$|[0-9]{12})", message = "LoanNumber must be 12 digits")
        val loanNumber: String,


        @field:Schema(description = "대출 종류", example = "Home Loan", nullable = true)
        val loanType: String?,

        @field:NullOrCheck(type = ValidationType.POSITIVE, message = "Total loan can not be negative")
        @field:Schema(description = "총 대출 금액", example = "100000", nullable = true)
        val totalLoan: Int?,

        @field:NullOrCheck(type = ValidationType.POSITIVE_OR_ZERO, message = "Amount paid can not be negative")
        @field:Schema(description = "대출금 지급액", example = "1000", nullable = true)
        val amountPaid: Int?,

        @field:NullOrCheck(type = ValidationType.POSITIVE_OR_ZERO, message = "Outstanding amount can not be negative")
        @field:Schema(description = "총 미결제 금액", example = "99000", nullable = true)
        val outstandingAmount: Int?

)
