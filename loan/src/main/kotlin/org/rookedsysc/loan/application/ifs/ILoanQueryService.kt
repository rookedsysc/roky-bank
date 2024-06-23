package org.rookedsysc.loan.application.ifs

import org.rookedsysc.loan.infrastructure.dto.LoanResponse
import org.rookedsysc.loan.infrastructure.dto.LoanUpdateRequest

interface ILoanQueryService {
    fun create(mobileNumber: String)
    fun update(request: LoanUpdateRequest)
    fun delete(mobileNumber: String): Boolean
}