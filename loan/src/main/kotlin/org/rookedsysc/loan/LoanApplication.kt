package org.rookedsysc.loan

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.jpa.repository.config.EnableJpaAuditing

@SpringBootApplication
@EnableJpaAuditing(auditorAwareRef = "auditAwareImpl")
class LoanApplication

fun main(args: Array<String>) {
    runApplication<LoanApplication>(*args)
}
