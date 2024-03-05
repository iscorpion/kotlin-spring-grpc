package com.iscorpion.customer.service.adapter

import com.iscorpion.customer.service.domain.CreateCustomerService
import com.iscorpion.customer.service.domain.Customer
import com.iscorpion.customer.service.domain.SearchCustomerService
import customer.service.CustomerCreateRequest
import customer.service.CustomerCreateResponse
import customer.service.GetCustomerResponse
import kotlinx.coroutines.reactive.awaitFirstOrNull
import org.springframework.stereotype.Component

@Component
class GrpcServiceAdapter(
    private val createCustomerService: CreateCustomerService,
    private val searchCustomerService: SearchCustomerService
) {
    suspend fun findCustomer(codCustomer: String): GetCustomerResponse? {
        val customer = searchCustomerService.findCustomer(codCustomer).awaitFirstOrNull()

        return if(customer != null) GetCustomerResponse
            .newBuilder()
            .setCodCustomer(customer.id.toString())
            .setEmail(customer.email)
            .setName(customer.name)
            .setPersonType(customer.personType)
            .build()
        else GetCustomerResponse
            .newBuilder()
            .build()

    }

    suspend fun new(request: CustomerCreateRequest): CustomerCreateResponse {
        val newCustomer = createCustomerService.new(Customer(
            email = request.email.orEmpty(),
            name = request.name.orEmpty(),
            personType = request.personType.orEmpty()
        ))

        return CustomerCreateResponse
            .newBuilder()
            .setCodCustomer(newCustomer.id.toString())
            .build()
    }
}