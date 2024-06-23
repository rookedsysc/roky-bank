package org.rookedsysc.cards.infrastructure.dto.request

import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.Pattern
import org.rookedsysc.cards.domain.validation.NullOrCheck
import org.rookedsysc.cards.domain.validation.ValidationType

@Schema(name = "CardDto", description = "Card details")
data class CardUpdateRequest (
        @NullOrCheck(type = ValidationType.MOBILE, message = "Mobile number must be 10 digits")
        val mobileNumber: String?,

        @field:NotEmpty(message = "Card Number can not be a null or empty")
        @field:Pattern(regexp = "(^$|[0-9]{12})", message = "CardNumber must be 12 digits")
        val cardNumber: String,

        @field:Schema(
                description = "Type of the card", example = "Credit Card"
        )
        val cardType: String?,

        @field:NullOrCheck(type=ValidationType.POSITIVE, message = "Total limit can not be negative")
        @field:Schema(
                description = "Total limit of the card", example = "10000"
        )
        val totalLimit: Int?,

        @field:NullOrCheck(type=ValidationType.POSITIVE_OR_ZERO, message = "Amount used can not be negative")
        @field:Schema(
                description = "Amount used from the card", example = "0"
        )
        val amountUsed: Int?,

        @field:NullOrCheck(type=ValidationType.POSITIVE_OR_ZERO, message = "Available amount can not be negative")
        @field:Schema(
                description = "Available amount in the card", example = "10000"
        )
        val availableAmount: Int?,
)
