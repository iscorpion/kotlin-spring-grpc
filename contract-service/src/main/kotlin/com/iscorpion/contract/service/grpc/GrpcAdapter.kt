package com.iscorpion.contract.service.grpc

import ContractCreate
import ContractDetails
import com.iscorpion.contract.service.domain.ContractService
import customer.service.CustomerServiceGrpc.CustomerServiceBlockingStub
import customer.service.GetCustomerRequest
import net.devh.boot.grpc.client.inject.GrpcClient
import org.springframework.stereotype.Service


@Service
class GrpcAdapter(val contractService: ContractService) {
    @GrpcClient("customer-service")
    lateinit var customerService: CustomerServiceBlockingStub
    suspend fun getContractDetails(customerRequest: GetCustomerRequest): ContractDetails {

        val contract = contractService.findContractByCustomer(customerRequest.codCustomer)
        val customer = customerService.getCustomer(customerRequest)

        return contract?.let {
            ContractDetails
                .newBuilder()
                .setNumber(contract.numContract)
                .setValue(contract.totalValue.toDouble())
                .setCustomer(customer.codCustomer)
                .setPersonType(customer.personType)
                .build()
        } ?: ContractDetails.newBuilder().build()
    }

    suspend fun createContract(request: ContractCreate): ContractDetails {
        val customer = customerService.getCustomer(request.customer)
        customer ?: return ContractDetails.newBuilder().build()

        val contract = contractService.create(
            request.customer.codCustomer,
            request.value
        )

        return ContractDetails
            .newBuilder()
            .setNumber(contract.numContract)
            .setValue(contract.totalValue.toDouble())
            .setCustomer(contract.customer.toString())
            .setPersonType(customer.personType)
            .build()
    }
}
