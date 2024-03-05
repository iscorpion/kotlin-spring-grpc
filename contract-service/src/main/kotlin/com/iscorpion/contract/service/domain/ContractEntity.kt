package com.iscorpion.contract.service.domain

import java.time.LocalDateTime
import java.util.UUID
import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table

@Table(name = "contract")
class ContractEntity(
    @Id
    val id: Long? = null,
    val customer: UUID,
    val numContract: String,
    val totalValue: Number,
    val datCreate: LocalDateTime = LocalDateTime.now()
)