package org.rookedsysc.accounts.accounts.audit

import org.springframework.data.domain.AuditorAware
import org.springframework.stereotype.Component
import java.util.*

@Component("auditAwareImpl")
class AuditAwareImpl: AuditorAware<String> {
    override fun getCurrentAuditor(): Optional<String> {
        val username = "SYSTEM"
        return Optional.of(username)
    }
}