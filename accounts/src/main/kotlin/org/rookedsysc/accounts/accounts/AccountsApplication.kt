package org.rookedsysc.accounts.accounts

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.jpa.repository.config.EnableJpaAuditing

@SpringBootApplication
@EnableJpaAuditing(auditorAwareRef = "auditAwareImpl")
class AccountsApplication

fun main(args: Array<String>) {
    runApplication<AccountsApplication>(*args)
}
