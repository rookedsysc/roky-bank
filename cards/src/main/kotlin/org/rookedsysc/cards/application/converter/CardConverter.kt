package org.rookedsysc.cards.application.converter

import org.rookedsysc.cards.domain.entity.Card
import org.rookedsysc.cards.infrastructure.dto.request.CardUpdateRequest

class CardConverter {
    companion object {
        fun toEntity(cardUpdateRequest: CardUpdateRequest, card: Card): Card {
            card.mobileNumber = cardUpdateRequest.mobileNumber ?: card.mobileNumber
            card.cardType = cardUpdateRequest.cardType ?: card.cardType
            card.totalLimit = cardUpdateRequest.totalLimit ?: card.totalLimit
            card.amountUsed = cardUpdateRequest.amountUsed ?: card.amountUsed
            card.availableAmount = cardUpdateRequest.availableAmount ?: card.availableAmount
            return card
        }
    }
}