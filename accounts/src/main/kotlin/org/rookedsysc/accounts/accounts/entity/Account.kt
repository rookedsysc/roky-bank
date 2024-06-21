package org.rookedsysc.accounts.accounts.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table

@Entity
@Table(name = "account")
class Account(
        @Column(name = "customer_id")
        var customerId: Long,

        @Id
        @Column(name = "account_number")
        var accountNumber: Long,

        @Column(name = "account_type")
        var accountType: String,

        @Column(name = "branch_address")
        var branchAddress: String,
) : BaseEntity()
