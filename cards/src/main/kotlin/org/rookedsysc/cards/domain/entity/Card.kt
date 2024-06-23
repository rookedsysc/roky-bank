package org.rookedsysc.cards.domain.entity

import jakarta.persistence.*

@Entity
@Table(name = "card")
class Card(
        @Id
        @Column(name = "card_id")
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        var cardId: Long? = null,

        @Column(name = "mobile_number")
        var mobileNumber: String,

        @Column(name = "card_number")
        var cardNumber: String,

        @Column(name = "card_type")
        var cardType: String,

        @Column(name = "total_limit")
        var totalLimit: Int,

        @Column(name = "amount_used")
        var amountUsed: Int,

        @Column(name = "available_amount")
        var availableAmount: Int
) : BaseEntity()
