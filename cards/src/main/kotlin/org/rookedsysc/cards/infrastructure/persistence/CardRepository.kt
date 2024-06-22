package org.rookedsysc.cards.infrastructure.persistence

import org.springframework.data.jpa.repository.JpaRepository
import org.rookedsysc.cards.domain.entity.Card

interface CardRepository : JpaRepository<Card, Long> {
    fun findByMobileNumber(mobileNumber: String): Card?
}