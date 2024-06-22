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
        val cardNumber: String,

        @Column(name = "card_type")
        val cardType: String,

        @Column(name = "total_limit")
        val totalLimit: Int,

        @Column(name = "amount_used")
        val amountUsed: Int,

        @Column(name = "available_amount")
        val availableAmount: Int
) : BaseEntity()
