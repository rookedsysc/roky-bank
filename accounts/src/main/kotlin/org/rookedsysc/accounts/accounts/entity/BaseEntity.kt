package org.rookedsysc.accounts.accounts.entity

import jakarta.persistence.Column
import jakarta.persistence.EntityListeners
import jakarta.persistence.MappedSuperclass
import org.springframework.data.annotation.CreatedBy
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedBy
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.sql.Timestamp
import java.time.LocalDateTime

@MappedSuperclass
@EntityListeners(AuditingEntityListener::class)
abstract class BaseEntity {
        @CreatedDate
        @Column(updatable = false, name = "created_at")
        lateinit var createdAt: Timestamp

        @CreatedBy
        @Column(updatable = false, name = "created_by")
        lateinit var createdBy: String

        // 최초 생성시 데이터가 없음을 의미
        @LastModifiedDate
        @Column(insertable = false, name = "updated_at")
        var updatedAt: Timestamp? = null

        @LastModifiedBy
        @Column(insertable = false, name = "updated_by")
        var updateBy: String? = null
}