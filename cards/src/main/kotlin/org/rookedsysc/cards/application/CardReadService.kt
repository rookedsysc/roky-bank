package org.rookedsysc.cards.application

import org.rookedsysc.cards.application.converter.CardConverter
import org.rookedsysc.cards.application.ifs.ICardReadService
import org.rookedsysc.cards.common.exception.ResourceNotFoundException
import org.rookedsysc.cards.domain.entity.Card
import org.rookedsysc.cards.infrastructure.dto.response.CardResponse
import org.rookedsysc.cards.infrastructure.persistence.CardRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class CardReadService(
        private val cardRepository: CardRepository
) : ICardReadService {
    override fun cardDetailByMobileNumber(mobileNumber: String): CardResponse {
        val card: Card = cardRepository.findByMobileNumber(mobileNumber)
                .let { card ->
                    if (card == null) {
                        throw ResourceNotFoundException(resourceName = "card", fieldName = "mobileNumber", fieldValue = mobileNumber)
                    }
                    card
                }
        return CardConverter.toResponse(card)
    }
}