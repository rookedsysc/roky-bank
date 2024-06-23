package org.rookedsysc.loan.infrastructure.dto

import io.swagger.v3.oas.annotations.media.Schema


@Schema(
        name = "LoanResponse",
        description = "Schema to hold loan response information"
)
data class LoanResponse(
        @field:Schema(description = "휴대폰 번호", example = "4365327698")
        val mobileNumber: String,

        @field:Schema(
                description = "Loan 고객 번호 총 12자리", example = "548732457654",
        )
        val loanNumber: String,


        @field:Schema(description = "대출 종류", example = "Home Loan")
        val loanType: String,


        @field:Schema(description = "총 대출 금액", example = "100000")
        val totalLoan: Int,


        @field:Schema(description = "대출금 지급액", example = "1000")
        val amountPaid: Int,

        @field:Schema(description = "총 미결제 금액", example = "99000")
        val outstandingAmount: Int
)