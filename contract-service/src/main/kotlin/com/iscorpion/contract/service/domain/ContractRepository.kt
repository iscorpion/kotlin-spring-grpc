package com.iscorpion.contract.service.domain

import org.springframework.data.repository.reactive.ReactiveCrudRepository
import reactor.core.publisher.Mono

interface ContractRepository: ReactiveCrudRepository<ContractEntity, Long> {
    fun findByCustomer(customer: String): Mono<ContractEntity>
}
