package org.rookedsysc.cards.infrastructure.dto.response

import io.swagger.v3.oas.annotations.media.Schema

@Schema(name = "Cards",
        description = "Schema to hold Card information"
)
class CardResponse(

        @field:Schema(description = "Mobile Number", example = "1234567890")
        val mobileNumber: String,

        @field:Schema(description = "Card Number", example = "100646930341")
        val cardNumber: String,

        @field:Schema(description = "Card Type", example = "Credit Card")
        val cardType: String,

        @field:Schema(description = "Total Limit", example = "100000")
        val totalLimit: Int,

        @field:Schema(description = "Amount Used", example = "10000")
        val amountUsed: Int,

        @field:Schema(description = "Available Amount", example = "90000")
        val availableAmount: Int
)

