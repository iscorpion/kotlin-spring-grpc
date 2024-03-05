package com.iscorpion.customer.service.domain

import java.time.LocalDateTime
import java.util.UUID
import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table

@Table
class Customer (
    @Id
    val id: UUID? = null,
    val name: String,
    val email: String,
    val personType: String,
    val datCreate: LocalDateTime = LocalDateTime.now()
)