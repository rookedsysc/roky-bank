package org.rookedsysc.cards.domain.dto

import io.swagger.v3.oas.annotations.media.Schema

@Schema(
        name = "Response",
        description = "Schema to hold successful response information"
)
data class ResponseDto(
        @Schema(
                description = "Status code in the response"
        )
        val status: String,

        @Schema(
                description = "Status message in the response"
        )
        val statusMsg: String
)
