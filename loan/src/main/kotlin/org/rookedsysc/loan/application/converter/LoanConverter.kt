package org.rookedsysc.loan.application.converter

import org.rookedsysc.loan.domain.entity.Loan
import org.rookedsysc.loan.infrastructure.dto.LoanResponse
import org.rookedsysc.loan.infrastructure.dto.LoanUpdateRequest

class LoanConverter {
    companion object {
        fun convertToResponse(loan: Loan): LoanResponse {
            return LoanResponse(
                    loanNumber = loan.loanNumber,
                    mobileNumber = loan.mobileNumber,
                    loanType = loan.loanType,
                    totalLoan = loan.totalLoan,
                    amountPaid = loan.amountPaid,
                    outstandingAmount = loan.outstandingAmount
            )
        }

        fun updateLoan(loanResponse: LoanUpdateRequest, loan: Loan): Loan {
            return Loan(
                    loanNumber = loanResponse.loanNumber,
                    mobileNumber = loanResponse.mobileNumber ?: loan.mobileNumber,
                    loanType = loanResponse.loanType ?: loan.loanType,
                    totalLoan = loanResponse.totalLoan ?: loan.totalLoan,
                    amountPaid = loanResponse.amountPaid ?: loan.amountPaid,
                    outstandingAmount = loanResponse.outstandingAmount ?: loan.outstandingAmount
            )
        }
    }
}