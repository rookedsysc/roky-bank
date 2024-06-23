package org.rookedsysc.cards.infrastructure.web

import jakarta.validation.Valid
import jakarta.validation.constraints.Pattern
import org.rookedsysc.cards.application.ifs.ICardQueryService
import org.rookedsysc.cards.common.constants.CardConstants
import org.rookedsysc.cards.domain.dto.ResponseDto
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/card")
class CardController(private val cardQueryService: ICardQueryService) {
    @PostMapping
    fun createCard(
            @Valid
            @RequestParam
            @Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile number must be 10 digits")
            mobileNumber: String): ResponseEntity<ResponseDto> {
        cardQueryService.create(mobileNumber)
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(
                        ResponseDto(
                                CardConstants.STATUS_201,
                                CardConstants.MESSAGE_201
                        )
                )
    }
}