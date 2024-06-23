package org.rookedsysc.loan.domain.entity

import jakarta.persistence.*

@Entity
@Table(name = "loan")
class Loan(
        @Id
        @Column(name = "loan_id")
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        var loanId: Long? = null,

        @Column(name = "mobile_number")
        var mobileNumber: String,

        @Column(name = "loan_number")
        var loanNumber: String,

        @Column(name = "loan_type")
        var loanType: String,

        @Column(name = "total_loan")
        var totalLoan: Int,

        @Column(name = "amount_paid")
        var amountPaid: Int,

        @Column(name = "outstanding_amount")
        var outstandingAmount: Int
) : BaseEntity() {
    protected constructor() : this(null, "", "", "", 0, 0, 0)
}