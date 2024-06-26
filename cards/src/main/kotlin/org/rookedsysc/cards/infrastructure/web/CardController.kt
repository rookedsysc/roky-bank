package org.rookedsysc.cards.infrastructure.web

import io.swagger.v3.oas.annotations.Operation
import jakarta.validation.Valid
import jakarta.validation.constraints.Pattern
import org.rookedsysc.cards.application.ifs.ICardQueryService
import org.rookedsysc.cards.application.ifs.ICardReadService
import org.rookedsysc.cards.common.constants.CardConstants
import org.rookedsysc.cards.domain.dto.ResponseDto
import org.rookedsysc.cards.infrastructure.dto.request.CardUpdateRequest
import org.rookedsysc.cards.infrastructure.dto.response.CardResponse
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/cards")
class CardController(private val cardQueryService: ICardQueryService, private val cardReadService: ICardReadService) {
    @PostMapping
    @Operation(summary = "휴대전화 번호로 카드 생성")
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

    @GetMapping
    @Operation(summary = "카드 번호로 카드 조회")
    fun cardDetailByMobileNumber(
            @Valid
            @RequestParam
            @Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile number must be 10 digits")
            mobileNumber: String): ResponseEntity<CardResponse> {
        val cardResponse: CardResponse = cardReadService.cardDetailByMobileNumber(mobileNumber)
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(
                        cardResponse
                )
    }


    @PatchMapping
    @Operation(summary = "카드 업데이트")
    fun updateCardDetails(@Valid @RequestBody dto: CardUpdateRequest): ResponseEntity<ResponseDto> {
        val isUpdated: Boolean = cardQueryService.update(dto)
        return if (isUpdated) {
            ResponseEntity
                    .status(HttpStatus.OK)
                    .body<ResponseDto>(ResponseDto(CardConstants.STATUS_200, CardConstants.MESSAGE_200))
        } else {
            ResponseEntity
                    .status(HttpStatus.EXPECTATION_FAILED)
                    .body<ResponseDto>(ResponseDto(CardConstants.STATUS_417, CardConstants.MESSAGE_417_UPDATE))
        }
    }

    @DeleteMapping
    @Operation(summary = "휴대전화 번호로 카드 삭제")
    fun delete(@RequestParam mobileNumber: String): ResponseEntity<ResponseDto> {
        val isDeleted: Boolean = cardQueryService.delete(mobileNumber)
        return if (isDeleted) {
            ResponseEntity
                    .status(HttpStatus.OK)
                    .body<ResponseDto>(ResponseDto(CardConstants.STATUS_200, CardConstants.MESSAGE_200))
        } else {
            ResponseEntity
                    .status(HttpStatus.EXPECTATION_FAILED)
                    .body<ResponseDto>(ResponseDto(CardConstants.STATUS_417, CardConstants.MESSAGE_417_DELETE))
        }
    }
}