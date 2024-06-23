package org.rookedsysc.loan.application.ifs

import org.rookedsysc.loan.infrastructure.dto.LoanResponse

interface ILoanReadService {
    fun getLoanDetails(mobileNumber: String): LoanResponse
}