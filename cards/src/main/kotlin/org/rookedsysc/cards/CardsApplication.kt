package org.rookedsysc.cards

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.jpa.repository.config.EnableJpaAuditing

@SpringBootApplication
@EnableJpaAuditing(auditorAwareRef = "auditAwareImpl")
class CardsApplication

fun main(args: Array<String>) {
	runApplication<CardsApplication>(*args)
}
