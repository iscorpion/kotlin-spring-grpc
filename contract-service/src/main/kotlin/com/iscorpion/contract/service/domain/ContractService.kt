package com.iscorpion.contract.service.domain

import java.util.UUID
import kotlin.random.Random
import kotlin.random.nextUInt
import kotlinx.coroutines.reactive.awaitFirstOrNull
import kotlinx.coroutines.reactive.awaitSingle
import org.springframework.stereotype.Service

@Service
class ContractService(private val repository: ContractRepository) {
    suspend fun findContractByCustomer(customer: String) = repository.findByCustomer(customer).awaitFirstOrNull()

    suspend fun create(codCustomer: String, value: Double): ContractEntity {
        return repository.save(
            ContractEntity(
                numContract = "A${Random.nextUInt()}-000",
                customer = UUID.fromString(codCustomer),
                totalValue = value
            )
        ).awaitSingle()
    }
}