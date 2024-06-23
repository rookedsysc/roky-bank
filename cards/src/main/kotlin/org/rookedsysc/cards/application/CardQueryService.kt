package org.rookedsysc.cards.application

import jakarta.transaction.Transactional
import org.rookedsysc.cards.application.converter.CardConverter
import org.rookedsysc.cards.application.ifs.ICardQueryService
import org.rookedsysc.cards.common.constants.CardConstants
import org.rookedsysc.cards.common.exception.CardAlreadyExistsException
import org.rookedsysc.cards.common.exception.ResourceNotFoundException
import org.rookedsysc.cards.domain.entity.Card
import org.rookedsysc.cards.infrastructure.dto.request.CardUpdateRequest
import org.rookedsysc.cards.infrastructure.persistence.CardRepository
import org.springframework.stereotype.Service
import java.util.*

@Service
@Transactional
class CardQueryService(
        private val cardRepository: CardRepository
) : ICardQueryService {
    override fun create(mobileNumber: String) {
        cardRepository.findByMobileNumber(mobileNumber)
                .let {
                    if (it != null) throw CardAlreadyExistsException("Card already exists for mobile number: $mobileNumber")
                }
        cardRepository.save(createNewCard(mobileNumber))
    }

    private fun createNewCard(mobileNumber: String): Card {
        val randomCardNumber = 100000000000L + Random().nextInt(900000000)
        val newCard: Card = Card(
                cardNumber = randomCardNumber.toString(),
                mobileNumber = mobileNumber,
                cardType = CardConstants.CREDIT_CARD,
                totalLimit = CardConstants.NEW_CARD_LIMIT,
                amountUsed = 0,
                availableAmount = CardConstants.NEW_CARD_LIMIT
        )
        return newCard
    }

    override fun update(request: CardUpdateRequest): Boolean {
        val card: Card = cardRepository.findByCardNumber(request.cardNumber) ?: let {
            throw ResourceNotFoundException("Card", "cardNumber", request.cardNumber)
        }
        val newCard = CardConverter.toEntity(cardUpdateRequest = request, card = card)
        cardRepository.save(card)
        return true
    }
}