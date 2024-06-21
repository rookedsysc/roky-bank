package org.rookedsysc.accounts.accounts.entity

import jakarta.persistence.*

@Entity
@Table(name = "customer")
class Customer(
        @Id
        @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "customer_id_generator")
        @SequenceGenerator(name = "customer_id_generator", sequenceName = "customer_id_seq", allocationSize = 1)
        @Column(name = "customer_id")
        var customerId: Long,

        var name: String,

        var email: String,

        @Column(name = "mobile_number")
        var mobileNumber: String,
) : BaseEntity()