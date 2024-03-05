package com.iscorpion.customer.service.domain

import org.springframework.stereotype.Service
import reactor.core.publisher.Mono

@Service
class SearchCustomerService(val repository: CustomerRepository) {
    fun findCustomer(codCustomer: String): Mono<Customer> = repository.findById(codCustomer)
}
