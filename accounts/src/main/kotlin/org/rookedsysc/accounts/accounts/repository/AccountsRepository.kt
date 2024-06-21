package org.rookedsysc.accounts.accounts.repository

import org.rookedsysc.accounts.accounts.entity.Accounts
import org.springframework.data.jpa.repository.JpaRepository

interface AccountsRepository : JpaRepository<Accounts, Long> {
}