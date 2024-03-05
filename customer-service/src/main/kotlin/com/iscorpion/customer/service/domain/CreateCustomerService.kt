package com.iscorpion.customer.service.domain

import kotlinx.coroutines.reactive.awaitSingle
import org.springframework.stereotype.Service

@Service
class CreateCustomerService(val repository: CustomerRepository) {
    suspend fun new(request: Customer): Customer {
        val newCustomer = repository.save(request).awaitSingle()

        return newCustomer!!
    }
}